package com.kuit.kuit4serverauth.domain.user.controller;

import com.kuit.kuit4serverauth.domain.user.model.dto.request.LoginRequest;
import com.kuit.kuit4serverauth.domain.user.model.dto.request.RefreshRequest;
import com.kuit.kuit4serverauth.domain.user.model.dto.response.TokenResponse;
import com.kuit.kuit4serverauth.global.response.BaseResponse;
import com.kuit.kuit4serverauth.domain.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public BaseResponse<TokenResponse> login(@RequestBody LoginRequest loginRequest) {
        return BaseResponse.ok(authService.login(loginRequest));
    }

    @PostMapping("/token")
    public BaseResponse<TokenResponse> refresh(@RequestBody RefreshRequest refreshRequest) {
        return BaseResponse.ok(authService.refresh(refreshRequest));
    }
}

