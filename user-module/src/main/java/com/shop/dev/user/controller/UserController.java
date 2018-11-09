package com.shop.dev.user.controller;

import com.shop.dev.commons.ResultWrapper;
import com.shop.dev.entity.UserInfo;
import com.shop.dev.user.service.UserInfoService;
import com.shop.dev.user.service.UserInfoServiceImpl;
import com.shop.dev.user.shiro.SimpleUsernameToken;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;


@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserInfoService userInfoService;


    /**
     * 登录
     *
     * @param userInfo
     * @return lhw
     */

    @PostMapping("/login")
    public ResultWrapper login(UserInfo userInfo) {
        SimpleUsernameToken token = new SimpleUsernameToken(userInfo.getUsername(), userInfo.getPassword());
        SecurityUtils.getSubject().login(token);
        String jwtToken = (String) SecurityUtils.getSubject().getPrincipal();
        return ResultWrapper.success(jwtToken);
    }

    /**
     * 注册
     *
     * @return
     */
    @PostMapping("/register")
    public boolean registerOne(UserInfo userInfo) {
        userInfoService.register(userInfo);
        return true;
    }
}
