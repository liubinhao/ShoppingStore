package com.shop.dev.user.shiro;

import org.apache.shiro.authc.AuthenticationToken;

public class SimpleUsernameToken implements AuthenticationToken {
    private String username;
    private String password;

    public SimpleUsernameToken(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * 获取主要信息
     * @return
     * lhw
     */
    @Override
    public Object getPrincipal() {
        return username;
    }

    /**
     * 获取密码
     * @return
     * lhw
     */

    @Override
    public Object getCredentials() {
        return password;
    }
}
