package com.lemon.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {
    // 密钥
    private static final String signKey = "SVRIRUlNQQ==";
    // 过期时间
    private static final Long expire = 43200000L;

    /**
     * 生成令牌
     * @param claims 载荷(包含用户id和用户名字username)
     * @return
     */
    public static String generateJwt(Map<String,Object> claims){
        return Jwts.builder()
                       .signWith(SignatureAlgorithm.HS256,signKey)
                       .addClaims(claims)
                       .setExpiration(new Date(System.currentTimeMillis() + expire))
                       .compact();
    }

    /**
     * 解析令牌
     * @param jwt 令牌
     * @return
     */
    public static Map<String,Object> parseJWT(String jwt){
        try {
            Claims body = Jwts.parser()
                                  .setSigningKey(signKey)
                                  .parseClaimsJws(jwt)
                                  .getBody();
            return body;
        } catch (RuntimeException e) {
            return null;
        }
    }
}
