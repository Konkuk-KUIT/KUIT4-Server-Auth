package com.kuit.kuit4serverauth.controller;

import com.kuit.kuit4serverauth.exception.CustomException;
import com.kuit.kuit4serverauth.exception.ErrorCode;
import com.kuit.kuit4serverauth.service.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final JwtUtil jwtUtil;

    public UserController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    // API를 인증된 사용자만 접근 가능하도록
    @GetMapping("/profile")
    public ResponseEntity<String> getProfile(HttpServletRequest request) {
        // TODO : 로그인 한 사용자면 username 이용해 "Hello, {username}" 반환하기
        String token = request.getHeader("Authorization");

        if (token == null) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }

        String username = jwtUtil.getUsernameFromToken(token);
        return ResponseEntity.ok("Hello, " + username);
    }

    // API에 관리자 권한 (ROLE_ADMIN) 있는 사용자만 접근 가능하도록
    @GetMapping("/admin")
    public ResponseEntity<String> getAdmin(HttpServletRequest request) {
        // TODO: role이 admin이면 "Hello, admin" 반환하기
        String token = request.getHeader("Authorization");

        if (token == null) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }

        String role = jwtUtil.getRoleFromToken(token);
        if (! "ROLE_ADMIN".equals(role)) {
            throw new CustomException(ErrorCode.FORBIDDEN_ACCESS);
        }

        return ResponseEntity.ok("Hello, admin");

    }
}
