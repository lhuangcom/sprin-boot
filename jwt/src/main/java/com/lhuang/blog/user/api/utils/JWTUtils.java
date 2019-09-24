package com.lhuang.blog.user.api.utils;

import com.lhuang.blog.user.api.config.JWTConfig;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtils{

    @Autowired
    private JWTConfig jwtConfig;

    public  String generateToken(String userId){
        Date date = new Date();
        Date expireDate = new Date(date.getTime()+jwtConfig.getExpire()*1000);
        return Jwts.builder()
                .setHeaderParam("typ","jwt")
                .setSubject(userId)
                .setExpiration(expireDate)
                .setIssuedAt(date)
                .signWith(SignatureAlgorithm.HS512,jwtConfig.getSecret())
                .compact();
    }

    public Claims getClaimByToken(String token){

        try {
            return Jwts.parser()
                    .setSigningKey(jwtConfig.getSecret())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Boolean isExpire(Date expiration){
        return new Date().after(expiration);
    }



}
