package com.kuit.kuit4serverauth.controller;

import com.kuit.kuit4serverauth.dto.LoginRequest;
import com.kuit.kuit4serverauth.dto.TokenDTO;
import com.kuit.kuit4serverauth.repository.UserRepository;
import com.kuit.kuit4serverauth.service.AuthService;
import com.kuit.kuit4serverauth.service.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    // 해당 url로 요청을 보내면 RefreshToken을 통해 AccessToken을 새로 발급해줌.
    @PostMapping("/refresh-token")
    public ResponseEntity<TokenDTO> refreshToken(@RequestBody TokenDTO tokenRequest) {
        return ResponseEntity.ok(authService.refresh(tokenRequest));
    }
}

