package com.kuit.kuit4serverauth.controller;

import com.kuit.kuit4serverauth.dto.LoginReqDto;
import com.kuit.kuit4serverauth.dto.LoginResDto;
import com.kuit.kuit4serverauth.dto.RefreshTokenReqDto;
import com.kuit.kuit4serverauth.dto.RefreshTokenResponseDto;
import com.kuit.kuit4serverauth.exception.CustomException;
import com.kuit.kuit4serverauth.exception.ErrorCode;
import com.kuit.kuit4serverauth.model.AuthUser;
import com.kuit.kuit4serverauth.model.User;
import com.kuit.kuit4serverauth.repository.UserRepository;
import com.kuit.kuit4serverauth.service.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthController {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    /* TODO : dto로 변환 */
    @PostMapping("/login")
    public ResponseEntity<LoginResDto> login(@RequestBody LoginReqDto dto) {
        String username = dto.getUsername();
        String password = dto.getPassword();

        User user = userRepository.findByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            throw new CustomException(ErrorCode.INVALID_USERNAME_OR_PASSWORD);
        }

        String token = jwtUtil.generateAccessToken(user.getUsername(), user.getRole());
        LoginResDto response = LoginResDto.builder()
                .token(token)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshTokenResponseDto> refreshToken(@RequestBody RefreshTokenReqDto dto,
                                                                AuthUser authUser) {

        String accessToken = dto.getAccessToken();

        boolean isExpired = jwtUtil.isTokenExpired(accessToken);
        if (isExpired) {
            String username = authUser.getUsername();
            String refreshToken = jwtUtil.generateRefreshToken(username);
            RefreshTokenResponseDto response = RefreshTokenResponseDto.builder()
                    .RefreshToken(refreshToken)
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        throw new CustomException(ErrorCode.INVALID_TOKEN);
    }
}

