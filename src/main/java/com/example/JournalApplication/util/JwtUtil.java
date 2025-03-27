package com.example.JournalApplication.util;

import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private static final String SECRET_KEY_STRING = "mysecretkeymysecretkeymysecretkeymysecretkey"; // ✅ 256-bit Base64 key

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY_STRING); // ✅ Decode Base64
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // ✅ Token Generate Karna
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 Hour Validity
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // ✅ Fixed Secret Key
                .compact();
    }

    // ✅ Token Validate Karna
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey()) // ✅ Same Key Use Karna
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // ✅ Token se Username Nikalna
    public String extractUsername(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey()) // ✅ Same Key Use Karna
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}
