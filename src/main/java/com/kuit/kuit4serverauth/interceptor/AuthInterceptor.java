package com.kuit.kuit4serverauth.interceptor;

import com.kuit.kuit4serverauth.exception.CustomException;
import com.kuit.kuit4serverauth.exception.ErrorCode;
import com.kuit.kuit4serverauth.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Date;

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

            if (claims.getExpiration().before(new Date())) {
                throw new CustomException(ErrorCode.EXPIRED_TOKEN);
            }

            if(!claims.get("type", String.class).equals("access")){
                throw new CustomException(ErrorCode.ACCESS_TOKEN_REQUIRED);
            }

            request.setAttribute("username", claims.getSubject());
            request.setAttribute("role", claims.get("role"));
            return true;
        }
        throw new CustomException(ErrorCode.MISSING_AUTH_HEADER);
    }
}

