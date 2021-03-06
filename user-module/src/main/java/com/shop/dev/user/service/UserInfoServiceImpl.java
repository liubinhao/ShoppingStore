package com.shop.dev.user.service;

import com.shop.dev.entity.UserInfo;
import com.shop.dev.repository.UserInfoRepository;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;

@Service
public class UserInfoServiceImpl implements UserInfoService {


    @Resource
    private UserInfoRepository userInfoRepository;


    /**
     * 登录
     *
     * @param username
     * @param password
     * @return lhw
     */
    @Override
    public UserInfo loginByUsername(String username, String password) {
        Md5Hash md5Hash = new Md5Hash(password,"salt");
        UserInfo userInfo = userInfoRepository.findByUsername(username);
        if (userInfo != null && userInfo.getPassword().equals(String.valueOf(md5Hash))) {
            return userInfo;
        }
        return null;

    }


    /**
     * 注册
     *
     * @param userInfo
     * @return
     */
    @Override
    public boolean register(UserInfo userInfo) {
        userInfo.setCreated(new Timestamp(System.currentTimeMillis()));
        userInfo.setUpdated(new Timestamp(System.currentTimeMillis()));
        Md5Hash md5Hash = new Md5Hash(userInfo.getPassword(),"salt");
        userInfo.setPassword(String.valueOf(md5Hash));
        userInfoRepository.save(userInfo);
        return true;
    }

    /**
     * 按Id查
     *
     * @param
     * @return lhw
     */

    @Override
    public UserInfo findById(Long userId) {
        return userInfoRepository.getOne(userId);
    }

    /**
     * 检查用户名是否存在
     * @param username
     * @return
     */
    @Override
    public UserInfo findByUsername(String username) {
        UserInfo userInfo = userInfoRepository.findByUsername(username);
        return userInfo;
    }

    /**
     * 检查手机号是否存在
     * @param phone
     * @return
     */
    @Override
    public UserInfo findByPhone(String phone) {
        UserInfo userInfo = userInfoRepository.findByPhone(phone);
        return userInfo;
    }


}
