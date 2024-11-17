package com.kuit.kuit4serverauth.controller;

import com.kuit.kuit4serverauth.interceptor.AuthInterceptor;
import com.kuit.kuit4serverauth.service.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.*;

@RestController
public class UserController {
    @GetMapping("/profile")
    public ResponseEntity<String> getProfile(HttpServletRequest request) {
        // TODO : 로그인 한 사용자면 username 이용해 "Hello, {username}" 반환하기
        Object username = request.getAttribute("username");
        if (username != null) {
            return ResponseEntity.ok().body("Hello, " + username);
        }
        return ResponseEntity.status(UNAUTHORIZED).body("Unauthorized");
    }

    @GetMapping("/admin")
    public ResponseEntity<String> getAdmin(HttpServletRequest request) {
        // TODO: role이 admin이면 "Hello, admin" 반환하기
        Object role = request.getAttribute("role");
        System.out.println(role);
        if(role.equals("ROLE_ADMIN")) {
            return ResponseEntity.ok().body("Hello, admin");
        }
        return ResponseEntity.status(FORBIDDEN).body("Forbidden");
    }
}
