package com.kuit.kuit4serverauth.global.config;

import com.kuit.kuit4serverauth.global.interceptor.AuthInterceptor;
import com.kuit.kuit4serverauth.global.resolver.UserDtoArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final AuthInterceptor authInterceptor;
    private final UserDtoArgumentResolver userDtoArgumentResolver;

    public WebConfig(AuthInterceptor authInterceptor) {
        this.authInterceptor = authInterceptor;
        this.userDtoArgumentResolver = new UserDtoArgumentResolver();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/orders/**")
                .addPathPatterns("/users/**")
                .addPathPatterns("/stores/**");
//                .addPathPatterns("/**")
//                .excludePathPatterns("/auth/login")
//                .excludePathPatterns("/auth/token")
//                .excludePathPatterns("/swagger-ui/index.html");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(userDtoArgumentResolver);
    }
}
