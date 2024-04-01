package com.care_health.care_health.configurations;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.security.SecureRandom;
import java.util.Base64;


import java.util.Date;

@Slf4j
@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String JWT_SECRET;

    @Value("${jwt.expire}")
    private int JWT_EXPIRE;


    /* create token from info user*/
    public String generateToken(CustomUserDetail customUserDetail) {
        System.out.println("start generate");
        Date now = new Date();
        Date dateExpired = new Date(now.getTime() + JWT_EXPIRE);
        System.out.println("generateToken");
        return Jwts.builder()
                .setSubject(customUserDetail.getUsername())
                .setIssuedAt(now)
                .setExpiration(dateExpired)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();

    }

    // get username from jwt
    public String getUserNameFromJWT(String token) {
        if (token != null && token.startsWith("Bearer ") || token != null && token.startsWith("bearer ")) {
            token = token.substring(7); // Loại bỏ phần 'Bearer ' khỏi token
        }

        System.out.println("token in jwt provider " + token);

        // Tiếp tục xử lý token đã được loại bỏ 'Bearer '
        Claims claims = Jwts.parser().setSigningKey(JWT_SECRET)
                .parseClaimsJws(token).getBody();
        System.out.println("claim successfully");
        return claims.getSubject();

    }

    // Validate jwt
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET)
                    .parseClaimsJws(token);
            return true;
        }
        catch (MalformedJwtException e) {
            log.error("invalid jwt token !!!");
        }
        catch (ExpiredJwtException e) {
            log.error("jwt expired !!!");
        }
        catch (UnsupportedJwtException e) {
            log.error("unsupported jwt token !!!");
        }
        catch (IllegalArgumentException e) {
            log.error("jwt claims string is empty");
        }
        return false;
    }

}
