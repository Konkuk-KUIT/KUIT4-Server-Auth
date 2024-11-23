package com.kuit.kuit4serverauth.controller;

import com.kuit.kuit4serverauth.exception.CustomException;
import com.kuit.kuit4serverauth.exception.ErrorCode;
import com.kuit.kuit4serverauth.model.User;
import com.kuit.kuit4serverauth.repository.UserRepository;
import com.kuit.kuit4serverauth.service.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class AuthController {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        User user = userRepository.findByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            throw new CustomException(ErrorCode.INVALID_USERNAME_OR_PASSWORD);
        }

        String token = jwtUtil.generateToken(user.getUsername(), user.getRole());
        String refreshToken = jwtUtil.generateRefreshToken(user.getUsername(), user.getRole());
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        response.put("refreshToken", refreshToken);

        return ResponseEntity.ok(response);
    }

    // RefreshToken을 사용해 AccessToken 재발급
    @PostMapping("/refresh-token")
    public ResponseEntity<Map<String, String>> refreshAccessToken(@RequestBody Map<String, String> credentials) {
        String refreshToken = credentials.get("refreshToken");

        try {
            // RefreshToken 유효성 검사 및 AccessToken 재발급
            Claims claims = jwtUtil.validateToken(refreshToken);
            String username = claims.getSubject();
            String role = claims.get("role", String.class);

            if (role != null && role.equals("ROLE_ADMIN")) {
                role = "admin";
            } else {
                role = "user";
            }

            String newAccessToken = jwtUtil.generateToken(username, role);  // Role은 실제 사용자 role에 맞게 설정

            Map<String, String> response = new HashMap<>();
            response.put("accessToken", newAccessToken);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }
    }
}

