package com.kuit.kuit4serverauth.controller;

import com.kuit.kuit4serverauth.service.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final JwtUtil jwtUtil;

    @GetMapping("/profile")
    public ResponseEntity<String> getProfile(HttpServletRequest request) {
        // TODO : 로그인 한 사용자면 username 이용해 "Hello, {username}" 반환하기

        String token = extractToken(request);
        if (token == null || (jwtUtil.validateToken(token) == null)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }

        Claims claims = jwtUtil.validateToken(token);
        String username = claims.getSubject();

        return ResponseEntity.ok("Hello, " + username);
    }

    @GetMapping("/admin")
    public ResponseEntity<String> getAdmin(HttpServletRequest request) {
        String token = extractToken(request);
        if (token == null || (jwtUtil.validateToken(token) == null)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }

        Claims claims = jwtUtil.validateToken(token);
        String role = claims.get("role", String.class);

        if (role != null && role.equals("ROLE_ADMIN")) {
            return ResponseEntity.ok("Hello, admin");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden");
        }
    }

    private String extractToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);  // Remove "Bearer " prefix
        }
        return null;
    }
}
