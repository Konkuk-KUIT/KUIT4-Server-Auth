package com.kuit.kuit4serverauth.config;

import com.kuit.kuit4serverauth.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final AuthInterceptor authInterceptor;

    public WebConfig(AuthInterceptor authInterceptor) {
        this.authInterceptor = authInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // TODO /profile, /admin 앞에 붙이기
        registry.addInterceptor(authInterceptor).addPathPatterns("/profile","/admin");
        //인터셉터 적용될 경로 지정 -> 두 경로에 대해 인증 절차 거치도록

    }
}
