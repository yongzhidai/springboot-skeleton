package com.dyz.skeleton.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Component
public class JwtTokenUtil implements Serializable {
    private static final long serialVersionUID = -2550185165626007488L;
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    private static String secret = "xxxxx@email666";

    public static Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        Date expirationDate = claims.getExpiration();
        if(expirationDate.before(new Date())){
            return null;
        }
        return Long.parseLong(claims.getSubject());
    }


    //generate token
    public static String generateToken(Long userId) {
        return Jwts.builder().setSubject(String.valueOf(userId)).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

}