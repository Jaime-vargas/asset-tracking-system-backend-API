package com.control_activos.sks.control_activos.Jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {


    private final SecretKey secretKey;
    public JwtUtil() {
        this.secretKey = Keys.hmacShaKeyFor(
                "0cRJNYRU8UxYn5eZ0D6SPwHmRHsnpiIB".getBytes(StandardCharsets.UTF_8)
        );
    }

    public String generateToken (Long id){

        // Test Calims
        Map<String, Object> claims = new HashMap<>();
        claims.put("Device ID", id);
        claims.put("Type", "camera");

        return Jwts.builder()
                .claims(claims)
                .signWith(secretKey)
                .compact();
    }

    public Long validateToken(String token){
         Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();

         return claims.get("Device ID", Long.class);
    }

}
