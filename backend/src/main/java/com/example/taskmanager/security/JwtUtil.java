package com.example.taskmanager.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.List;
import java.util.Collections;

@Component
public class JwtUtil {
    private final Key key;
    private final long validity = 1000L * 60 * 60 * 24; // 24h

    public JwtUtil() {
        String secret = System.getenv().getOrDefault("JWT_SECRET", "dev-secret-very-weak-please-change");
        if (secret.length() < 32) {
            throw new IllegalArgumentException("JWT secret must be at least 32 characters long");
        }
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(String username, List<String> roles) {
        return Jwts.builder()
                .claim("roles", roles)  // Explicitly store roles as List<String>
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + validity))
                .signWith(key)
                .compact();
    }

    public Jws<Claims> validate(String token) throws JwtException {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            throw new JwtException("Token expired");
        } catch (UnsupportedJwtException e) {
            throw new JwtException("Unsupported token");
        } catch (MalformedJwtException e) {
            throw new JwtException("Invalid token format");
        } catch (SignatureException e) {
            throw new JwtException("Invalid token signature");
        } catch (IllegalArgumentException e) {
            throw new JwtException("Token cannot be empty");
        }
    }

    public List<String> extractRoles(Claims claims) {
        try {
            return claims.get("roles", List.class);
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}