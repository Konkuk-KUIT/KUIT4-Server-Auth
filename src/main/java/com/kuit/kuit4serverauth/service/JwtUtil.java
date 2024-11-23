package com.kuit.kuit4serverauth.service;

import com.kuit.kuit4serverauth.exception.CustomException;
import com.kuit.kuit4serverauth.exception.ErrorCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.accessExpiration}")
    private Long accessExpirationMs;    // 1 hour
    @Value("${jwt.refreshExporation}")
    private Long refreshExpirationMs;    // 24 hour

    private final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);


    public String generateToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + accessExpirationMs))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    // RefreshToken 발급
    public String generateRefreshToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + refreshExpirationMs))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    // RefreshToken으로 AccessToken 재발급
    public String refreshAccessToken(String refreshToken) {
        Claims claims = validateToken(refreshToken);
        String username = claims.getSubject();
        String role = claims.get("role", String.class);
        return generateToken(username, role);  // 새로운 AccessToken 발급
    }

    public Claims validateToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }
    }
}