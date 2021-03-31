package com.fashion.spring.jwt.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author: yangyuguang
 * @Date: 2021/3/31 10:28
 */
@Component
@ConfigurationProperties(prefix = "config.jwt")
public class JwtConfig {

    /**
     * secret: iwqjhda8232bjgh432[cicada-smile]
     * expire: 3600
     * header: token
     */
    private String secret;

    private Long expire;

    private String header;

    public String getToken(String identityId){
        Date date = new Date();
        Date expireDate = new Date(date.getTime() + expire * 1000);

        return Jwts.builder().setHeaderParam("type", "JWT")
                .setSubject(identityId)
                .setIssuedAt(date)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }


    public Claims getTokenClaim(String token){
        try{
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    public boolean isTokenExpired(Date expirationTime){
        return expirationTime.before(new Date());
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Long getExpire() {
        return expire;
    }

    public void setExpire(Long expire) {
        this.expire = expire;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
