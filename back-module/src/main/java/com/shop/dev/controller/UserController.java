package com.shop.dev.controller;

import com.shop.dev.entity.User;
import com.shop.dev.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName UserController
 * @Author 刘树青
 * @Date 2018/11/8 9:34
 * @Version 1.0
 */
@RestController
@RequestMapping("/back")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/login.do")
    public boolean login(@RequestBody @Validated User user) {
        return this.userService.isLogin(user.getUsername(), user.getPassword());
    }
}
