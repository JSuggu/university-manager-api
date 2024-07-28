package com.example.api_university_manager.components.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

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
        Object roles = userDetails.getAuthorities().stream().collect(Collectors.toList());
        claims.put("roles", roles);

        return createToken(claims,userDetails.getUsername());
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

    private String createToken(Map<String, Object> claims, String username){
        return Jwts
                .builder()
                .subject(username)
                .claims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKENTIME))
                .signWith(SignatureAlgorithm.HS256, SECRETKEY)
                .compact();
    }

    private Claims extractAllClaims(String token){
        return Jwts
                .parser()
                .setSigningKey(SECRETKEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
