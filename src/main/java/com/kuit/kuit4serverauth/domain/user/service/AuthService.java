package com.kuit.kuit4serverauth.domain.user.service;

import com.kuit.kuit4serverauth.global.response.exception.CustomException;
import com.kuit.kuit4serverauth.global.response.exception.ErrorCode;
import com.kuit.kuit4serverauth.domain.user.model.dao.User;
import com.kuit.kuit4serverauth.domain.user.model.dao.RefreshToken;
import com.kuit.kuit4serverauth.domain.user.model.dto.request.LoginRequest;
import com.kuit.kuit4serverauth.domain.user.model.dto.request.RefreshRequest;
import com.kuit.kuit4serverauth.domain.user.model.dto.response.TokenResponse;
import com.kuit.kuit4serverauth.domain.user.repository.RefreshTokenRepository;
import com.kuit.kuit4serverauth.domain.user.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public TokenResponse login(LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        User user = userRepository.findByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            throw new CustomException(ErrorCode.INVALID_USERNAME_OR_PASSWORD);
        }

        String accessToken = jwtUtil.generateAccessToken(user.getUsername(), user.getRole());
        String refreshToken = jwtUtil.generateRefreshToken(user.getUsername());

        RefreshToken token = new RefreshToken(user.getUsername(), refreshToken, new Date(System.currentTimeMillis() + jwtUtil.getExpirationMs() * 24));
        refreshTokenRepository.update(token);
        log.info(refreshToken);

        return TokenResponse.of(accessToken, refreshToken);
    }

    public TokenResponse refresh(RefreshRequest refreshRequest) {
        String refreshToken = refreshRequest.getRefreshToken();

        Claims claims = jwtUtil.validateRefreshToken(refreshToken);
        String username = claims.getSubject();

        RefreshToken storedToken = refreshTokenRepository.findByUsername(username);
        String token = storedToken.getRefreshToken();

        if (token == null || !token.equals(refreshToken)) {
            throw new CustomException(ErrorCode.INVALID_REFRESH_TOKEN);
        }

        User findUser = userRepository.findByUsername(username);
        String accessToken = jwtUtil.generateAccessToken(username, findUser.getRole());

        //Refresh Token Rotation 구현
        String newRefreshToken = jwtUtil.generateRefreshToken(findUser.getUsername());
        RefreshToken rt= new RefreshToken(findUser.getUsername(), newRefreshToken, new Date(System.currentTimeMillis() + jwtUtil.getExpirationMs()*24));
        refreshTokenRepository.update(rt);

        return TokenResponse.of(accessToken, newRefreshToken);
    }
}
