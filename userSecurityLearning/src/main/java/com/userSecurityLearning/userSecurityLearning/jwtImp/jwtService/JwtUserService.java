package com.userSecurityLearning.userSecurityLearning.jwtImp.jwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;


import java.security.Key;
import java.util.Date;
import java.util.HashMap;



@Service
public class JwtUserService {
    public static final String SECRET="ThisIsMySecretKey@#$StringTo&$generateKey";
    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
                .addClaims(new HashMap<>())
                .signWith(getSecretKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    private  Key getSecretKey(){
         return Keys.hmacShaKeyFor(SECRET.getBytes());
    }
    public Claims verifySignatureAnExtractClaims(String token){
        return Jwts.parser()
                .setSigningKey(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getBody();
    }
    public String extractUsername(String token){
        return verifySignatureAnExtractClaims(token).getSubject();
    }
    public Date getExpiryTime(String token){
        return  verifySignatureAnExtractClaims(token).getExpiration();
    }
    public boolean isExpired(String token){
        return getExpiryTime(token).before(new Date());
    }
}
