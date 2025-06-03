package com.example.isshop.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

@Component
public class Jwtutil {
    //JWT簽發者
    private String issuer = "isshop";
    //生成一段密鑰
    private static final String TOKEN_SECRET = "cuAihCz53DZRjZwbsGcZJ2Ai6At+T142uphtJMsk7iQ=";

    public String createToken(String subject) {
        Date now=new Date(System.currentTimeMillis());//現在時間
        Date expiryDate = new Date(now.getTime() + 1000 * 60 * 30);//過期時間，以毫秒計算，1000毫秒=1秒
        Key secretKey = generalKey();

        JwtBuilder builder = Jwts.builder()
                .setSubject(subject) //主題可以是json數據
                .setIssuer(issuer) //簽名
                .setIssuedAt(now) //簽發時間
                .setExpiration(expiryDate)//設置過期時間
                .signWith(secretKey);//密鑰
        return builder.compact(); //最後使用compact()
    }
    //使用密鑰轉換成byte使用
    public Key generalKey(){
        byte[] encodeKey = Decoders.BASE64.decode(TOKEN_SECRET);
        Key key= Keys.hmacShaKeyFor(encodeKey);
        return key;
    }

    public Claims parseToken(String jwt) throws JwtException {
        return Jwts.parserBuilder()
                .setSigningKey(generalKey())
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }


}
