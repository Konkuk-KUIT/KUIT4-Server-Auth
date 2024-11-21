package com.kuit.kuit4serverauth.service;

import com.kuit.kuit4serverauth.exception.CustomException;
import com.kuit.kuit4serverauth.exception.ErrorCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expirationMs; // 1 hour

    public String generateToken(String username, String role) {
        // username과 role 정보를 받아서 Token 생성
        System.out.println(secret);
        String token =  Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs)) // 유효기간 설정
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
        return token;
    }

    public Claims validateToken(String token) {
        // Token을 검증
        try {
            // Token을 Parsing
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }
    }
}