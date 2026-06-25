package com.mouli.studentmanagementsystem.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private static final String SECRET_KEY =
            "mySecretKeyForJwtAuthenticationStudentManagementSystem2026";

    private final Key key =
            Keys.hmacShaKeyFor(
                    SECRET_KEY.getBytes());

    // Generate Token with Role
    public String generateToken(
            String username,
            String role) {

        Map<String, Object> claims =
                new HashMap<>();

        claims.put(
                "role",
                role);

        return Jwts.builder()
                .claims(claims)
                .subject(username)
                .issuedAt(new Date())
                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + 1000 * 60 * 60))
                .signWith(
                        (SecretKey) key)
                .compact();
    }

    // Extract Username
    public String extractUsername(
            String token) {

        return extractClaims(token)
                .getSubject();
    }

    // Extract Role
    public String extractRole(
            String token) {

        return extractClaims(token)
                .get(
                        "role",
                        String.class);
    }

    // Validate Token
    public boolean isTokenValid(
            String token,
            String username) {

        return extractUsername(token)
                .equals(username);
    }

    // Extract Claims
    private Claims extractClaims(
            String token) {

        return Jwts.parser()
                .verifyWith((SecretKey) key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}