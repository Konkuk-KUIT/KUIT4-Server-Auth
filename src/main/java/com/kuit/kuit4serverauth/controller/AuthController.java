package com.kuit.kuit4serverauth.controller;

import com.kuit.kuit4serverauth.model.dto.request.LoginRequest;
import com.kuit.kuit4serverauth.model.dto.request.RefreshRequest;
import com.kuit.kuit4serverauth.model.dto.response.TokenResponse;
import com.kuit.kuit4serverauth.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> refresh(@RequestBody RefreshRequest refreshRequest) {
        return ResponseEntity.ok(authService.refresh(refreshRequest));
    }
}

