package com.kuit.kuit4serverauth.controller;

import com.kuit.kuit4serverauth.dto.LoginRequestDto;
import com.kuit.kuit4serverauth.exception.CustomException;
import com.kuit.kuit4serverauth.exception.ErrorCode;
import com.kuit.kuit4serverauth.model.User;
import com.kuit.kuit4serverauth.repository.UserRepository;
import com.kuit.kuit4serverauth.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Tag(name = "Auth APIs", description = "인증 및 인가 관련 API")
public class AuthController {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    @Operation(
            summary = "로그인 요청 - 1",
            description = "username과 password를 받아 검증 후 access, refresh 토큰을 발급합니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "성공적으로 액세스 및 리프레시 토큰을 발급합니다.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Map.class),
                                    examples = @ExampleObject(value = "{\n" +
                                            "  \"access-token\": \"newAccessToken123\",\n" +
                                            "  \"refresh-token\": \"newRefreshToken456\"\n}")
                            )
                    )
            }
    )
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        User user = userRepository.findByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            throw new CustomException(ErrorCode.INVALID_USERNAME_OR_PASSWORD);
        }

        Map<String, String> response = generateTokenResponse(user);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login2")
    @Operation(
            summary = "로그인 요청 - 2",
            description = "username과 password를 받아 검증 후 access, refresh 토큰을 발급합니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "성공적으로 액세스 및 리프레시 토큰을 발급합니다.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Map.class),
                                    examples = @ExampleObject(value = "{\n" +
                                            "  \"access-token\": \"newAccessToken123\",\n" +
                                            "  \"refresh-token\": \"newRefreshToken456\"\n}")
                            )
                    )
            }
    )
    public ResponseEntity<Map<String, String>> login2(@RequestBody LoginRequestDto loginRequestDto) {
        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        User user = userRepository.findByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            throw new CustomException(ErrorCode.INVALID_USERNAME_OR_PASSWORD);
        }

        Map<String, String> response = generateTokenResponse(user);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh-token")
    @Operation(
            summary = "엑세스 토큰 재요청",
            description = "refresh-token을 받아 access, refresh 토큰을 발급합니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "성공적으로 액세스 및 리프레시 토큰을 발급합니다.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Map.class),
                                    examples = @ExampleObject(value = "{\n" +
                                            "  \"access-token\": \"newAccessToken123\",\n" +
                                            "  \"refresh-token\": \"newRefreshToken456\"\n}")
                            )
                    )
            }
    )
    public ResponseEntity<Map<String, String>> refreshToken(@RequestBody Map<String, String> tokenRequest) {
        String refreshToken = tokenRequest.get("refresh-token");
        Claims claims = jwtUtil.validateToken(refreshToken);
        String username = claims.getSubject();

        User user = userRepository.findByUsername(username);

        Map<String, String> response = generateTokenResponse(user);
        return ResponseEntity.ok(response);
    }

    private Map<String, String> generateTokenResponse(User user) {
        String newAccessToken = jwtUtil.generateAccessToken(user.getUsername(), user.getRole());
        String newRefreshToken = jwtUtil.generateRefreshToken(user.getUsername());
        Map<String, String> response = new HashMap<>();
        response.put("access-token", newAccessToken);
        response.put("refresh-token", newRefreshToken);
        return response;
    }
}

