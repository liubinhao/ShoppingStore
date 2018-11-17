package com.shop.dev.controller;

import com.shop.dev.commons.ResultWrapper;
import com.shop.dev.entity.User;
import com.shop.dev.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @Resource
    private UserService userService;

    // 登录
    @PostMapping("/back/login.do")
    public ResultWrapper login(@RequestBody @Validated User user, HttpSession session) {
        try {
            boolean b = this.userService.isLogin(user.getUsername(), user.getPassword());
            if (b) {
                session.setAttribute("user", user);
            } else {
                session.invalidate();
            }
            return ResultWrapper.success(b);
        } catch (Exception e) {
            return ResultWrapper.success(false);
        }
    }
}
