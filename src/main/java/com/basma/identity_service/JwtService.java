package com.basma.identity_service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class JwtService {

    // ????? ??? ??? ?????? ??? Token (???? ?? 256 ?? ??? ?????)
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // ???? ????? ?????? ???????? ??? ????????
    public String generateToken(String username) {
Map<String, Object> claims = new HashMap<String, Object>();        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String username) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // ?????? ?????? 10 ?????
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }
}