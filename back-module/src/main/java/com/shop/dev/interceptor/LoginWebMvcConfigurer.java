package com.shop.dev.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @ClassName LoginWebMvcConfigurer
 * @Author 刘树青
 * @Date 2018/11/16 14:20
 * @Version 1.0
 */
@Configuration
public class LoginWebMvcConfigurer implements WebMvcConfigurer {
    @Resource
    private UserInterceptor userInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInterceptor).addPathPatterns("/content/**", "/item/**", "/pic/upload");
    }
}
