package com.example.EventManager.configuration;



import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtils {

    @Value("${app.secret-key}")
    private String secretKey;

    @Value("${app.expiration-time}")
    private Long expirationTime;

    public String generateToken(String username, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        return createToken(claims,username);
    }

    private Key getSignKey() {
        byte[] keyBytes = Base64.getDecoder().decode(secretKey); // Décodage base64
        return new SecretKeySpec(keyBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    @PostConstruct
    public void testSecretKey() {
        System.out.println("=== secretKey loaded ===");
        System.out.println("secretKey: " + secretKey);
    }


    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS256, getSignKey())
                .compact();
    }

//    private Key getSignKey() {
//        byte[] keyBytes = secretKey.getBytes();
//        return new SecretKeySpec(keyBytes, SignatureAlgorithm.HS256.getJcaName());
//    }


    //validate token
    public   boolean validateToken(String token, UserDetails userDetails)
    {
        String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));

    }

    private boolean isTokenExpired(String token) {
        return extractExpirationDate(token).before(new Date()) ;
    }

    private Date extractExpirationDate(String token) {
        return  extratClaim(token, Claims::getExpiration);
    }

    public String extractUsername(String token) {
        return extratClaim(token, Claims::getSubject);
    }

    private <T> T extratClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extratAllClaims(token);
        return claimsResolver.apply(claims);

    }

    public Claims extratAllClaims(String token) {
        if (token == null || token.trim().isEmpty()) {
            throw new IllegalArgumentException("Token is null or empty");
        }

        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    public String extractRole(String token) {
        return extratAllClaims(token).get("role", String.class);
    }


}
