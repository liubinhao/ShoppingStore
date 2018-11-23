package com.shop.dev.user.shiro;


import com.shop.dev.commons.JwtUtils;
import com.shop.dev.entity.UserInfo;
import com.shop.dev.user.service.UserInfoService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SimpleRealm extends AuthorizingRealm {
    @Resource
    private UserInfoService service;

    @Override
    public String getName() {
        return "simpleRealm";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof SimpleUsernameToken;
    }


    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        Subject subject = SecurityUtils.getSubject();
//        因为认证的时候存储的是userInfo对象,取出来的也是对象
//        UserInfo user = (UserInfo) subject.getPrincipal();

//        获取登录成功时存储的主要信息
//        Object principal = principals.getPrimaryPrincipal();
////        实际是一个token
//        String jwtToken = (String) principal;
////        从token中获取用户id
//        Long userId  = JwtUtils.getUserId(jwtToken);
////        查一下数据库
//        UserInfo user = service.findvById(userId);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        return info;

    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        String password = (String) token.getCredentials();
        UserInfo user = service.loginByUsername(username, password);
        if (user!=null){
//      说明用户从数据中能查到
//            为了方便调用,存的是该用户的详情


            String jwtToken = JwtUtils.newToken(user.getUserId());
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(jwtToken,password,getName());
            return info;

        }
        throw  new AuthenticationException("登录异常");
    }
}
