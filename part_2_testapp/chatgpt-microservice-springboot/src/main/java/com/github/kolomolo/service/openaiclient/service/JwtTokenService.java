package com.github.kolomolo.service.openaiclient.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.Date;

@Service
public class JwtTokenService {

    @Value("${application.security.jwt.secret-key}")
    private String secret;

    @Value("${application.security.jwt.expr}")
    private long expiration;

    public String generateToken(String username) {
        if (StringUtils.isEmpty(username)) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }

    public long getExpirationTime() {
        return expiration;
    }

    public String getUsernameFromToken(String token) {
        Claims claims = getClaimFromToken(token);
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Claims claims = getClaimFromToken(token);
            Date expiration = claims.getExpiration();
            if (expiration != null && expiration.before(Date.from(Instant.now()))) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    private Claims getClaimFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
