package com.shop.dev.controller;

import com.shop.dev.commons.ResultWrapper;
import com.shop.dev.entity.User;
import com.shop.dev.service.UserService;
import com.shop.dev.utils.JWT.JWTUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @ClassName UserController
 * @Author 刘树青
 * @Date 2018/11/8 9:34
 * @Version 1.0
 */
@RestController
public class UserController {
    //    private ThreadLocal<Jedis> threadLocal = new ThreadLocal<Jedis>();
    @Resource
    private UserService userService;

    // 登录
    @PostMapping("/back/login.do")
    public ResultWrapper login(@RequestBody @Validated User user) {
        Jedis jedis = new Jedis();
        try {
            User u = this.userService.findUser(user.getUsername(), user.getPassword());
            if (u != null) {
                String token = JWTUtils.newToken(u.getId());
                jedis.set("Authentication", token);
            }
            return ResultWrapper.success(u != null);
        } catch (Exception e) {
            return ResultWrapper.success(false);
        }
    }
}
