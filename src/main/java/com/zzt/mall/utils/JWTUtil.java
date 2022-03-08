package com.zzt.mall.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

public class JWTUtil {
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

}
