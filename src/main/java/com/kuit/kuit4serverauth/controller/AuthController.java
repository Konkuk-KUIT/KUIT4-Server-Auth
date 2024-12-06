package com.kuit.kuit4serverauth.controller;

import com.kuit.kuit4serverauth.dto.Token;
import com.kuit.kuit4serverauth.exception.CustomException;
import com.kuit.kuit4serverauth.exception.ErrorCode;
import com.kuit.kuit4serverauth.model.User;
import com.kuit.kuit4serverauth.repository.UserRepository;
import com.kuit.kuit4serverauth.service.JwtService;
import com.kuit.kuit4serverauth.service.JwtUtil;
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
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<Token> login(@RequestBody Map<String, String> credentials) {
        return ResponseEntity.ok(jwtService.login(credentials));
    }

    @PostMapping("/refresh")
    public ResponseEntity<Token> refresh(@RequestBody Map<String, String> credentials) {
        return ResponseEntity.ok(jwtService.refresh(credentials));
    }
}

