package com.shop.dev.user.controller;

import com.shop.dev.commons.ResultWrapper;
import com.shop.dev.entity.UserInfo;
import com.shop.dev.user.service.UserInfoService;
import com.shop.dev.user.shiro.SimpleUsernameToken;
import org.apache.shiro.SecurityUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserInfoService userInfoService;

    @Resource
    private RedisTemplate redisTemplate;
    /**
     * 登录
     *
     * @param userInfo
     * @return lhw
     */

    @RequestMapping("/login")
    public ResultWrapper login(UserInfo userInfo) {

        SimpleUsernameToken token = new SimpleUsernameToken(userInfo.getUsername(), userInfo.getPassword());
        SecurityUtils.getSubject().login(token);
        String jwtToken = (String) SecurityUtils.getSubject().getPrincipal();
        ValueOperations operations = redisTemplate.opsForValue();
        operations.set("userInfo："+userInfo.getUsername(),userInfo);

        return ResultWrapper.success(jwtToken);
    }

    /**
     * 注册
     *
     *
     * @return
     */
    @PostMapping("/register")
    public ResultWrapper registerOne(UserInfo userInfo) {


        userInfoService.register(userInfo);
        return ResultWrapper.success(userInfo);
    }

    @RequestMapping("/check")
    public ResultWrapper check(String username, String phone) {
        if (username==null){
            return ResultWrapper.error(423, "用户名为空");
        }
        if (phone==null){
            return ResultWrapper.error(423, "电话号码为空");
        }
        UserInfo userInfo = userInfoService.findByUsername(username);
        System.out.println(username);
        if (userInfo == null ) {
            UserInfo userInfo1 = userInfoService.findByPhone(phone);
            System.out.println(userInfo1);
            if (userInfo1 == null) {
                return ResultWrapper.success(username);
            }else {
                return ResultWrapper.error(421, "手机号码已存在");

            }
        } else {
            return ResultWrapper.error(422, "用户名已存在");
        }
    }





}
