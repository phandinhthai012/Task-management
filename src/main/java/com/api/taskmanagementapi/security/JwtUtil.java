package com.api.taskmanagementapi.security;

import com.api.taskmanagementapi.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    private final String secretKey = "my-super-secret-key-123456789012"; // Use a strong secret key in production
    private final long expirationTime = 86400000; // 24 hours
    private Key getSignKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String generateToken(User info) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationTime);
        Map<String, Object> claims = new HashMap<String,Object>();
        claims.put("id", info.getId());
        claims.put("email", info.getEmail());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(info.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSignKey(),SignatureAlgorithm.HS256)
                .compact();
    }
    // parse tất cả
    public Claims parseToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSignKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e) {
            throw new IllegalArgumentException("Invalid token");
        }
    }
    //  get username
    public String getUsernameFromToken(String token) {
        return parseToken(token).getSubject();
    }
    // get email
    public String getEmailFromToken(String token) {
        return (String) parseToken(token).get("email");
    }
    // get id
    public Integer getIdFromToken(String token) {
        return (Integer) parseToken(token).get("id");
    }

    // check expired
    public boolean isTokenExpired(String token) {
        Date expiration = parseToken(token).getExpiration();
        return expiration.before(new Date());
    }

    // validate token
    public boolean validateToken(String token) {
        try {
            String extractedUsername = getUsernameFromToken(token);
            return  !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

}
