package com.kuit.kuit4serverauth.service;

import com.kuit.kuit4serverauth.exception.CustomException;
import com.kuit.kuit4serverauth.exception.ErrorCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    // SecretKey 객체 타입으로서의 키 필요
    private final SecretKey secretKey;
    private final long accExpirationMs = 3600000; // 1 hour
    private final long refExpirationMs = 604800000;

    // 생성자
    public JwtUtil(@Value("${mysecretkey}")String secret) {
        // String 타입의 키를 암호화해서 SecretKey 타입으로 변환
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + accExpirationMs))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String generateRefreshToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + refExpirationMs)) // 7일
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
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

    public boolean isTokenExpired(String accessToken) {
        try {
            // 토큰의 Claims를 파싱
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey) // secretKey는 JwtUtil에 저장된 키
                    .parseClaimsJws(accessToken)
                    .getBody();

            // 토큰의 만료 시간 가져오기
            Date expiration = claims.getExpiration();

            // 현재 시간과 비교하여 만료 여부 반환
            return expiration.before(new Date());
        } catch (ExpiredJwtException e) {
            // 토큰이 이미 만료된 경우
            return true;
        } catch (Exception e) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }
    }
}