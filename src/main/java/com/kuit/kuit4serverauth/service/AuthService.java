package com.kuit.kuit4serverauth.service;

import com.kuit.kuit4serverauth.exception.CustomException;
import com.kuit.kuit4serverauth.exception.ErrorCode;
import com.kuit.kuit4serverauth.model.User;
import com.kuit.kuit4serverauth.model.dao.RefreshToken;
import com.kuit.kuit4serverauth.model.dto.request.LoginRequest;
import com.kuit.kuit4serverauth.model.dto.request.RefreshRequest;
import com.kuit.kuit4serverauth.model.dto.response.LoginResponse;
import com.kuit.kuit4serverauth.model.dto.response.RefreshResponse;
import com.kuit.kuit4serverauth.repository.RefreshTokenRepository;
import com.kuit.kuit4serverauth.repository.UserRepository;
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

    public LoginResponse login(LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        User user = userRepository.findByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            throw new CustomException(ErrorCode.INVALID_USERNAME_OR_PASSWORD);
        }

        String accessToken = jwtUtil.generateAccessToken(user.getUsername(), user.getRole());
        String refreshToken = jwtUtil.generateRefreshToken(user.getUsername());

        RefreshToken token = new RefreshToken(user.getUsername(), refreshToken, new Date(System.currentTimeMillis() + jwtUtil.getExpirationMs() * 24));
        refreshTokenRepository.save(token);
        log.info(refreshToken);

        return LoginResponse.of(accessToken, refreshToken);
    }

    public RefreshResponse refresh(RefreshRequest refreshRequest) {
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

        return RefreshResponse.of(accessToken);
    }
}
