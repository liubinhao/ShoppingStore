package com.shop.dev.service;

import com.shop.dev.entity.UserInfo;
import com.shop.dev.repository.UserInfoRepository;
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
    private UserInfoRepository userInfoRepository;

    /**
     * @Author 刘树青
     * @Date 2018/11/8 9:29
     * @param: [username, password]
     * return: boolean
     */
    @Cacheable(value = "userService")
    @Override
    public UserInfo findUser(String username, String password) {
        UserInfo user = this.userInfoRepository.findByUsername(username);
        if (user.getPassword() != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    @Override
    public UserInfo findUser(String username) {
        return this.userInfoRepository.findByUsername(username);
    }

}
