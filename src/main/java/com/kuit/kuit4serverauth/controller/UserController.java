package com.kuit.kuit4serverauth.controller;

import com.kuit.kuit4serverauth.model.User;
import com.kuit.kuit4serverauth.repository.UserRepository;
import com.kuit.kuit4serverauth.service.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public UserController(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/profile")
    public ResponseEntity<String> getProfile(HttpServletRequest request) {
        // TODO : 로그인 한 사용자면 username 이용해 "Hello, {username}" 반환하기
        String token = extractToken(request);

        if (token != null) {
            try {
                Claims claims = jwtUtil.validateToken(token);
                String userName = claims.getSubject();

                User user = userRepository.findByUsername(userName);
                if (user != null) {
                    return ResponseEntity.ok("Hello, " + user.getUsername());
                }
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }

    @GetMapping("/admin")
    public ResponseEntity<String> getAdmin(HttpServletRequest request) {
        // TODO: role이 admin이면 "Hello, admin" 반환하기
        String token = extractToken(request);

        if (token != null) {
            try {
                Claims claims = jwtUtil.validateToken(token);
                String userName = claims.getSubject();

                User user = userRepository.findByUsername(userName);
                if (user == null || !"ROLE_ADMIN".equals(user.getRole())) {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden");
                }

                return ResponseEntity.ok("Hello, admin");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
            }
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden");
    }


    private String extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // "Bearer " 제거
        }

        return null;
    }
}
