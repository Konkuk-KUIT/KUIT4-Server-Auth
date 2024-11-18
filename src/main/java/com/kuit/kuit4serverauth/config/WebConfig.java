package com.kuit.kuit4serverauth.config;

import com.kuit.kuit4serverauth.argumentResolver.RoleArgumentResolver;
import com.kuit.kuit4serverauth.argumentResolver.UsernameArgumentResolver;
import com.kuit.kuit4serverauth.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final AuthInterceptor authInterceptor;
    private final UsernameArgumentResolver usernameArgumentResolver;
    private final RoleArgumentResolver roleArgumentResolver;

    public WebConfig(AuthInterceptor authInterceptor,
                     UsernameArgumentResolver usernameArgumentResolver,
                     RoleArgumentResolver roleArgumentResolver) {
        this.authInterceptor = authInterceptor;
        this.usernameArgumentResolver = usernameArgumentResolver;
        this.roleArgumentResolver = roleArgumentResolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/profile")
                .addPathPatterns("/admin");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(usernameArgumentResolver);
        argumentResolvers.add(roleArgumentResolver);
    }
}
