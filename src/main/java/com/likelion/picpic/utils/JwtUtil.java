package com.likelion.picpic.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {  //토큰생성유틸
    public static String createJwt(String userName, String secretKey, Long expiredMs){
        Claims claims= Jwts.claims();
        claims.put("userName", userName);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
    
    public static boolean isExpired(String token, String secretKey){ //토큰 기간 점검
       return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().
               getExpiration().before(new Date()); 
    }
    
    public static String getUserName(String token, String secretKey){  //토큰에서 userName꺼내기
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
                .getBody().get("userName", String.class);
    }
}
