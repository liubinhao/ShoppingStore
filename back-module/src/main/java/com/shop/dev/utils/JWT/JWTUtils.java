package com.shop.dev.utils.JWT;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

/**
 * @ClassName JWTUtils
 * @Author 刘树青
 * @Date 2018/11/1 11:40
 * @Version 1.0
 */
public class JWTUtils {

    /**
     * 默认过期时间 30 分钟
     *
     * @Author 刘树青
     * @Date 2018/11/1 11:52
     * @param: [userId 用户id]
     * return: java.lang.String 返回token
     */
    public static String newToken(Long userId) {
        return newToken(userId, Contants.DEFAULT_EXPIRED_SECONDS);
    }
    // 一般存用户的 id 和 过期的时间 即可

    /**
     * @Author 刘树青
     * @Date 2018/11/1 11:44
     * @param: [userId 用户id, expiredSeconds 过期时长]
     * return: java.lang.String 返回的是token
     */
    public static String newToken(Long userId, Long expiredSeconds) {
        return JWT.create()
                .withClaim("userId", userId)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + expiredSeconds * 1000))
                .sign(Algorithm.HMAC256(Contants.SECRET_KEY));
    }

    /**
     * 校验 token 是否合法
     *
     * @Author 刘树青
     * @Date 2018/11/1 11:53
     * @param: [token 需要校验的token]
     * return: boolean
     */
    public static boolean checkToken(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(Contants.SECRET_KEY)).build();
        try {
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 刷新token
     *
     * @Author 刘树青
     * @Date 2018/11/1 11:58
     * @param: [token 原token]
     * return: java.lang.String 返回新 token
     */
    public static String refreshToken(String token) {
        DecodedJWT jwt = JWT.decode(token);
        Long userId = jwt.getClaim("userId").asLong();
        return newToken(userId);
    }


    public static String autoRequire(String token) {
        boolean check = checkToken(token);
        if (check) {
            DecodedJWT jwt = JWT.decode(token);
            // 计算时间是否超过50%
            long current = System.currentTimeMillis() / 1000;
            Long start = jwt.getClaim("iat").asLong();
            Long end = jwt.getClaim("exp").asLong();
            if ((current - start) * 1.0 / (end - start) > 0.5) {
                return refreshToken(token);
            } else {
                return token;
            }
        } else {
            throw new JWTVerificationException("token 不合法");
        }
    }

    // 根据token获取用户id
    public static Long getUserId(String jwtToken) {
        return JWT.decode(jwtToken).getClaim("userId").asLong();
    }


}
