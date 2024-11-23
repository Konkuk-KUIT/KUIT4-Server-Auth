package com.kuit.kuit4serverauth.interceptor;

import com.kuit.kuit4serverauth.exception.CustomException;
import com.kuit.kuit4serverauth.exception.ErrorCode;
import com.kuit.kuit4serverauth.service.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

// HTTP 요청 처리 전 특정 검증 로직 수행
// JWT 검증 + 사용자 정보를 요청 속성에 추가
@Component
public class AuthInterceptor implements HandlerInterceptor {
    private final JwtUtil jwtUtil;

    public AuthInterceptor(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            Claims claims = jwtUtil.validateToken(token);

            request.setAttribute("username", claims.getSubject());
            request.setAttribute("role", claims.get("role"));

            // userName / role을 request에 담아서 controller에 보냄
            return true;
        }

        throw new CustomException(ErrorCode.MISSING_AUTH_HEADER);
    }
}

