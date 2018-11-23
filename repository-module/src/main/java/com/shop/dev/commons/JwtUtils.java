package com.shop.dev.commons;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

public class JwtUtils {
    /**
     * @param userId
     * @param expiredSeconds 过期时长
     * @return token
     */
    /**
     * 默认过期时间30分钟
     * @param
     * @return
     */
    public static String newToken(Long userId){
        return newToken(userId,Constants.DEFAULT_EXPIRED);
    }
    public static String newToken(Long userId, long expiredSeconds) {
        return JWT.create().withClaim("userId", userId)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + expiredSeconds * 1000))
                .sign(Algorithm.HMAC256(Constants.SECRET_KEY));

    }

    /**
     * 校验token是否合法
     * @param token 需要校验的token
     * @return 是否合法
     */
    public static boolean checkToken(String token){
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(Constants.SECRET_KEY))
                .build();
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
     * @param token 原token
     * @return 新token
     */
    public static String refreshToken(String token){
        // 解码
        DecodedJWT jwt = JWT.decode(token);
        //获取负载
        Long userId = jwt.getClaim("userId").asLong();
        return newToken(userId);


    }

    /**
     * 自动更新
     * @param token
     * @return
     */
    public static String autoRequie(String token){
        boolean check  =  checkToken(token);
        if (check) {
            DecodedJWT jwt = JWT.decode(token);
//            计算时间是否超过80
            long current = System.currentTimeMillis()/1000;
            Long start = jwt.getClaim("iat").asLong();
            Long end = jwt.getClaim("exp").asLong();
            if ((current-start)*1.0 /(end-start)>0.5){
                return refreshToken(token);
            }else {
                return token;
            }
        } else {
            throw new JWTVerificationException("token不合法");
        }

    }

    public static Long getUserId(String jwtToken) {
        return JWT.decode(jwtToken).getClaim("userId").asLong();
    }
}

