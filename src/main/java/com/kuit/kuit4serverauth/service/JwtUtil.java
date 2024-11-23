package com.kuit.kuit4serverauth.service;

import com.kuit.kuit4serverauth.exception.CustomException;
import com.kuit.kuit4serverauth.exception.ErrorCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

// JWT를 생성하고 검증
@Component
public class JwtUtil {
    // todo
    // private final String secret = "mysecretkey";
    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256); // 안전한 비밀 키 생성
    private final long expirationMs = 3600000; // 1 hour, 만료시간
    // yml 파일에서 환경변수 설정으로 가져오기 -> 이걸 이용해서 수정

    public String generateToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(secretKey)
                .compact();
    }

    // 가져온 토큰을 파싱
    public Claims validateToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secretKey) // 발급 한 토큰이 맞는지 검증
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }
    }

    // Claims에서 username 추출
    public String getUsernameFromToken(String token) {
        return validateToken(token).getSubject();
    }

    // Claims에서 role추출
    public String getRoleFromToken(String token) {
        return validateToken(token).get("role").toString();
    }

}