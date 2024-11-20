package com.kuit.kuit4serverauth.controller;

import com.kuit.kuit4serverauth.exception.CustomException;
import com.kuit.kuit4serverauth.exception.ErrorCode;
import com.kuit.kuit4serverauth.model.User;
import com.kuit.kuit4serverauth.model.dao.RefreshToken;
import com.kuit.kuit4serverauth.repository.RefreshTokenRepository;
import com.kuit.kuit4serverauth.repository.UserRepository;
import com.kuit.kuit4serverauth.service.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final RefreshTokenRepository refreshTokenRepository;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        User user = userRepository.findByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            throw new CustomException(ErrorCode.INVALID_USERNAME_OR_PASSWORD);
        }

        String accessToken = jwtUtil.generateAccessToken(user.getUsername(), user.getRole());
        String refreshToken = jwtUtil.generateRefreshToken(user.getUsername());

        RefreshToken token = new RefreshToken(user.getUsername(), refreshToken, new Date(System.currentTimeMillis() + jwtUtil.getExpirationMs() * 24));
        refreshTokenRepository.save(token);
        log.info(refreshToken);
        Map<String, String> response = new HashMap<>();
        response.put("token", accessToken);
        response.put("refreshToken", refreshToken);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<Map<String, String>> refresh(@RequestBody Map<String, String> request) {

        String refreshToken = request.get("refreshToken");

        Claims claims = jwtUtil.validateRefreshToken(refreshToken);
        String username = claims.getSubject();

        RefreshToken storedToken = refreshTokenRepository.findByUsername(username);
        String token = storedToken.getRefreshToken();

        if (token == null || !token.equals(refreshToken)) {
            throw new CustomException(ErrorCode.INVALID_REFRESH_TOKEN);
        }

        User findUser = userRepository.findByUsername(username);
        String accessToken = jwtUtil.generateAccessToken(username, findUser.getRole());

        Map<String, String> response = new HashMap<>();
        response.put("token", accessToken);
        return ResponseEntity.ok(response);
    }
}

