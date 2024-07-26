package com.example.FirstProject.service.security;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Slf4j
@Component
@Builder
public class JwtUtils {


    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    private final String  jwtSecret = "b88631ed7ddf4fd879402d562f44838ad0f83605c74a0361e5847a8781a49ce68";


    private final int  jwtExpiration  = 86400000 ;

    public String generateJwtToken(Authentication authentication){

        // authentication.getPrincipal -> it gets the current authenticated user (Payload for ex:)
        CustomerDetailsImpl userPrincipal = (CustomerDetailsImpl) authentication.getPrincipal();

        // generates and returns the token
        // sihnWith -> it hashes the token after generation
        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpiration))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }


    public String getUserNameFromJwtToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key()).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }


}
