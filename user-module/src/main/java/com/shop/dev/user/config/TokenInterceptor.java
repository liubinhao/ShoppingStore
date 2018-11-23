package com.shop.dev.user.config;


import com.shop.dev.user.shiro.JwtToken;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 */
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("Authentication");
        try {
            JwtToken jwtToken = new JwtToken(token);
            SecurityUtils.getSubject().login(jwtToken);
//            String newToken = JwtUtils.autoRequie(token);
            String newToken = (String) SecurityUtils.getSubject().getPrincipal();
            response.addHeader("Authentication",newToken);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
