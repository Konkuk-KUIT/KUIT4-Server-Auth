package com.kuit.kuit4serverauth.service;

import com.kuit.kuit4serverauth.dto.Token;
import com.kuit.kuit4serverauth.exception.CustomException;
import com.kuit.kuit4serverauth.exception.ErrorCode;
import com.kuit.kuit4serverauth.model.User;
import com.kuit.kuit4serverauth.repository.TokenRepository;
import com.kuit.kuit4serverauth.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

import static com.kuit.kuit4serverauth.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    public Token login(@RequestBody Map<String, String> credentials){
        String username = credentials.get("username");
        String password = credentials.get("password");

        User user = userRepository.findByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            throw new CustomException(INVALID_USERNAME_OR_PASSWORD);
        }

        String token = jwtUtil.generateToken(user.getUsername(), user.getRole());
        String refreshToken = jwtUtil.generateRefreshToken(user.getUsername());

        tokenRepository.save(username, refreshToken);

        return new Token(token,refreshToken);
    }

    public Token refresh(Map<String, String> credentials) {
        String refreshToken = credentials.get("refreshToken");
        System.out.println("refreshToken = " + refreshToken);

        Claims claims = jwtUtil.validateToken(refreshToken);
        String username = claims.getSubject();

        String oldRefreshToken = tokenRepository.findByUsername(username);

        if(!oldRefreshToken.equals(refreshToken)){
            throw new CustomException(INVALID_REFRESH_TOKEN);
        }

        String newAccessToken = jwtUtil.generateToken(username, (String) claims.get("role"));
        String newRefreshToken = jwtUtil.generateRefreshToken(username);
        tokenRepository.updateRefreshToken(username, newRefreshToken);

        return new Token(newAccessToken,newRefreshToken);
    }
}
