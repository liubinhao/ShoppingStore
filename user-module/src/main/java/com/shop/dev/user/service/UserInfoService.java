package com.shop.dev.user.service;


import com.shop.dev.entity.UserInfo;

public interface UserInfoService {

    /**
     * 登录
     * @param username
     * @param password
     * @return
     * lhw
     */
    UserInfo loginByUsername(String username, String password);



    /**
     * 注册
     * @param userInfo
     * @return
     */
    boolean register(UserInfo userInfo);


    /**
     * 按id查
     * @param
     * @return
     * lhw
     */
    UserInfo findById(Long userId);

    /**
     * 按用户名查
     * @param username
     * @return
     */
    UserInfo findByUsername(String username);

    /**
     * 按电话号码查
     * @param phone
     * @return
     */
    UserInfo findByPhone(String phone);






}
