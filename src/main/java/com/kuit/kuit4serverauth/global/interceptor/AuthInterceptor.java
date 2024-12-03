package com.kuit.kuit4serverauth.global.interceptor;

import com.kuit.kuit4serverauth.global.response.exception.CustomException;
import com.kuit.kuit4serverauth.global.response.exception.ErrorCode;
import com.kuit.kuit4serverauth.domain.user.service.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
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
            Claims claims = jwtUtil.validateAccessToken(token);
            request.setAttribute("username", claims.getSubject());
            request.setAttribute("role", claims.get("role"));
            log.info("사용자 {} 인증", request.getAttribute("username"));
            return true;
        }
        throw new CustomException(ErrorCode.MISSING_AUTH_HEADER);
    }
}

