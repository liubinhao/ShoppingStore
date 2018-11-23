package com.shop.dev.service;

import com.shop.dev.entity.User;

/**
 * @ClassName UserService
 * @Author 刘树青
 * @Date 2018/11/8 9:21
 * @Version 1.0
 */
public interface UserService {

    // 后台:校验是否登录成功
    User findUser(String username, String password);

    User findUser(String username);
}
