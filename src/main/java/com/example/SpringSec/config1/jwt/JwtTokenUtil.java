package com.example.SpringSec.config1.jwt;

import com.example.SpringSec.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    /**
     * This method is used to generate JWT token
     *
     * @param userDetails user details for which token is to be generated
     * @return JWT token
     */
    public String generateToken(UserEntity userDetails) {
        return Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .setId(String.valueOf(userDetails.getId()))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()), SignatureAlgorithm.HS256).compact();
    }

    /**
     * This method is used to validate JWT token
     *
     * @param token       JWT token to be validated
     * @param userDetails user details for which token is to be validated
     * @return true if token is valid else false
     */
    public boolean validateToken(String token, UserDetails userDetails) throws SignatureException {
        // Validate JWT token logic
        return userDetails.getUsername().equals(extractUsername(token)) && !isTokenExpired(token);
    }

    /**
     * This method is used to check if the token is expired
     *
     * @param token JWT token to be validated
     * @return true if token is expired else false
     */
    private boolean isTokenExpired(String token) throws SignatureException, MalformedJwtException {
        // Check if token is expired logic
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    public String extractUsername(String token) throws SignatureException, MalformedJwtException {
        return extractAllClaims(token).getSubject();
    }

    private Claims extractAllClaims(String token) throws SignatureException, MalformedJwtException {
        return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes())).build().parseClaimsJws(token).getBody();
    }

    public String generateRefreshToken(UserEntity user) {
        return Jwts.builder().setSubject(user.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .setId(String.valueOf(user.getId()))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()), SignatureAlgorithm.HS256).compact();
    }

    public Long getExpiration() {
        return expiration;
    }
}
