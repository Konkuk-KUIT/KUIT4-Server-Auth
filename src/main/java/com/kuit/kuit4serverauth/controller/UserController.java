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

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/profile")
    public ResponseEntity<String> getProfile(HttpServletRequest request) {
        // TODO : 로그인 한 사용자면 username 이용해 "Hello, {username}" 반환하기
        String username = (String) request.getAttribute("username");

        if (username != null) {
            User user = userRepository.findByUsername(username);
            if (user != null) {
                return ResponseEntity.ok("Hello, " + user.getUsername());
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }

    @GetMapping("/admin")
    public ResponseEntity<String> getAdmin(HttpServletRequest request) {
        // TODO: role이 admin이면 "Hello, admin" 반환하기
        String role = (String) request.getAttribute("role");

        if ("ROLE_ADMIN".equals(role)) {
            return ResponseEntity.ok("Hello, admin");
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden");
    }
}
