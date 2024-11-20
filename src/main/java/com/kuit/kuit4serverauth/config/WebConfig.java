package com.kuit.kuit4serverauth.config;

import com.kuit.kuit4serverauth.argumentResolver.UserInfoArgumentResolver;
import com.kuit.kuit4serverauth.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final AuthInterceptor authInterceptor;
    private final UserInfoArgumentResolver userInfoArgumentResolver;

    public WebConfig(AuthInterceptor authInterceptor,
                     UserInfoArgumentResolver userInfoArgumentResolver) {
        this.authInterceptor = authInterceptor;
        this.userInfoArgumentResolver = userInfoArgumentResolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/profile")
                .addPathPatterns("/admin");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(userInfoArgumentResolver);
    }
}
