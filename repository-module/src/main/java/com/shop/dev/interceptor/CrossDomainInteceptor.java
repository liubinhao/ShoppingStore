package com.shop.dev.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CrossDomainInteceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        response.setHeader("Access-Control-Allow-Origin", "http://172.16.16.87:8999");
        // 设置跨越请求过来的请求方法
        response.setHeader("Access-Control-Allow-Methods", "POST, GET");
        // 预检请求的有效时间
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With, Authentication");
        return true;
    }
}
