package com.shop.dev.service;

import com.shop.dev.back_respository.UserRepository;
import com.shop.dev.entity.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName UserServiceImpl
 * @Author 刘树青
 * @Date 2018/11/8 9:21
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    /**
     * @Author 刘树青
     * @Date 2018/11/8 9:29
     * @param: [username, password]
     * return: boolean
     */
    @Cacheable(value = "userService")
    @Override
    public boolean isLogin(String username, String password) {
        User user = this.userRepository.findByUsername(username);
        if (user.getPassword() != null && user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }
}
