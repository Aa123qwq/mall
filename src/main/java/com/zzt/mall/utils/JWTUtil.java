package com.zzt.mall.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

public class JWTUtil {

    public static final long EXPIRE_TIME=3*60*60*1000;
    /**
     * 检验token是否正确
     * @param token 密钥
     * @param secret 用户密码
     * @return 是否正确
     */
    public static boolean verify(String token,String username,String secret){
        try{
            Algorithm algorithm=Algorithm.HMAC256(secret);
            JWTVerifier verifier= JWT.require(algorithm)
                    .withClaim("username",username)
                    .build();
            verifier.verify(token);
            return true;
        }catch (Exception e){
            System.out.println("token无效");
            return false;
        }
    }

    public static String getUserName(String token) {
        if(token==null||token.equals(""))
            return null;
        try{
            DecodedJWT jwt=JWT.decode(token);
            return jwt.getClaim("username").asString();
        }catch (JWTDecodeException e){
            return null;
        }
    }

    public static String sign(String userName,String password){
        Date expireTime=new Date(System.currentTimeMillis()+EXPIRE_TIME);
        Algorithm algorithm=Algorithm.HMAC256(password);
        return JWT.create()
                .withClaim("userName",userName)
                .withExpiresAt(expireTime)
                .sign(algorithm);
    }
}
