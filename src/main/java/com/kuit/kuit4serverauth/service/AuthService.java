package com.kuit.kuit4serverauth.service;

import com.kuit.kuit4serverauth.dto.LoginRequest;
import com.kuit.kuit4serverauth.dto.TokenDTO;
import com.kuit.kuit4serverauth.exception.CustomException;
import com.kuit.kuit4serverauth.exception.ErrorCode;
import com.kuit.kuit4serverauth.model.User;
import com.kuit.kuit4serverauth.repository.RefreshTokenRepository;
import com.kuit.kuit4serverauth.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;


    public TokenDTO login(LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        User user = userRepository.findByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            throw new CustomException(ErrorCode.INVALID_USERNAME_OR_PASSWORD);
        }

        String accessToken = jwtUtil.generateToken(user.getUsername(), user.getRole());
        String refreshToken = jwtUtil.generateRefreshToken(user.getUsername());

        refreshTokenRepository.save(username, refreshToken);

        return new TokenDTO(accessToken, refreshToken);
    }

    public TokenDTO refresh(@RequestBody TokenDTO tokenRequest) {
        String refreshToken = tokenRequest.getRefreshToken();

        Claims claims = jwtUtil.validateToken(refreshToken);
        String username = claims.getSubject();

        String oldRefreshToken = refreshTokenRepository.findByUsername(username);

        if(!oldRefreshToken.equals(refreshToken)) {
            throw new CustomException(ErrorCode.INVALID_REFRESH_TOKEN);
        }

        String newAccessToken = jwtUtil.generateToken(username, (String)claims.get("role"));

        //Refresh-Token-Rotation
        String newRefreshToken = jwtUtil.generateRefreshToken(username);
        refreshTokenRepository.updateRefreshToken(username, newRefreshToken);


        return new TokenDTO(newAccessToken, newRefreshToken);
    }


}
