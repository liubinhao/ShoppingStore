package com.shop.dev.interceptor;

import com.shop.dev.entity.User;
import com.shop.dev.exception.MyException;
import com.shop.dev.utils.JWT.JWTUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @ClassName UserInterceptor
 * @Author 刘树青
 * @Date 2018/11/8 19:58
 * @Version 1.0
 */
@Component
public class UserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Jedis jedis = new Jedis();
        String authentication = jedis.get("Authentication");
        if (authentication != null) {
            String token = JWTUtils.autoRequire(authentication);
            jedis.set("authentication", token);
            return true;
        } else {
            throw new MyException("网页找不到");
        }
    }
}
