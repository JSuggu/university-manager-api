package com.example.api_university_manager.components.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.IOException;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JwtService {
    private static final String SECRETKEY = "t12321321d1jd9012jdfh890rbvdj9kd9012dk8jhd912378ghj1d8039";
    private static final long TOKENTIME = 1000 * 60 * 60;

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        var roles = userDetails.getAuthorities().stream().collect(Collectors.toList());
        claims.put("roles", roles);

        return createToken(claims,userDetails);
    }

    public <T> T extractClaim(String token,Function<Claims, T> claimResolver){
        Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        String username = extractUsername(token);
        return (userDetails.getUsername().equals(username) && !isTokenExpired(token));
    }

    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    private String createToken(Map<String, Object> claims, UserDetails user){
        return Jwts
                .builder()
                .claims(claims)
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + TOKENTIME))
                .signWith(getKey())
                .compact();
    }

    private Claims extractAllClaims(String token){
        return  Jwts
                .parser()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();


    }

    public SecretKey getKey(){
        byte[] keyBytes = SECRETKEY.getBytes();
        SignatureAlgorithm sa = SignatureAlgorithm.HS256;
        return new SecretKeySpec(keyBytes, sa.getJcaName());
    }
}
