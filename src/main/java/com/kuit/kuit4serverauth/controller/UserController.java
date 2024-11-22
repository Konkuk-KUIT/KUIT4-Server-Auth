package com.kuit.kuit4serverauth.controller;

import com.kuit.kuit4serverauth.repository.UserRepository;
import com.kuit.kuit4serverauth.service.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

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
        String username = (String) request.getAttribute("username"); // 인터셉터에서 저장한 username
        if (username != null) {
            return ResponseEntity.ok("Hello, " + username);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }

    @GetMapping("/admin")
    public ResponseEntity<String> getAdmin(HttpServletRequest request) {
        String role = (String) request.getAttribute("role");
        if (Objects.equals(role, "ROLE_ADMIN")) {
            return ResponseEntity.ok("Hello, admin");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden");
    }
}
