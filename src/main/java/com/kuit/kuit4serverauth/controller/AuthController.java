package com.kuit.kuit4serverauth.controller;

import com.kuit.kuit4serverauth.dto.LoginRequest;
import com.kuit.kuit4serverauth.dto.LoginResponse;
import com.kuit.kuit4serverauth.dto.TokenResponse;
import com.kuit.kuit4serverauth.exception.CustomException;
import com.kuit.kuit4serverauth.exception.ErrorCode;
import com.kuit.kuit4serverauth.model.User;
import com.kuit.kuit4serverauth.repository.UserRepository;
import com.kuit.kuit4serverauth.service.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        User user = userRepository.findByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            throw new CustomException(ErrorCode.INVALID_USERNAME_OR_PASSWORD);
        }

        String accessToken = jwtUtil.generateToken(user.getUsername(), user.getRole());
        String refreshToken = jwtUtil.generateRefreshToken(user.getUsername());

        LoginResponse response = new LoginResponse(accessToken, refreshToken);
        return ResponseEntity.ok(response);
    }

    // 해당 url로 요청을 보내면 RefreshToken을 통해 AccessToken을 새로 발급해줌.
    @PostMapping("/refresh-token")
    public ResponseEntity<TokenResponse> refreshToken(@RequestBody Map<String, String> tokens) {
        String refreshToken = tokens.get("refreshToken");

        Claims claims = jwtUtil.validateToken(refreshToken);
        String username = claims.getSubject();

        String newAccessToken = jwtUtil.generateToken(username, (String)claims.get("role"));

        TokenResponse response = new TokenResponse(newAccessToken);

        return ResponseEntity.ok(response);
    }
}

