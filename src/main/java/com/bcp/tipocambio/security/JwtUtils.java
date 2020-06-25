package com.bcp.tipocambio.security;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";
    private static final Logger log= LoggerFactory.getLogger(JwtUtils.class);

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.token-expiration-in-ms}")
    private int jwtExpirationMs;

    public String generatedJwtToken(Authentication authentication){
        UserDetails userPrincipal=(UserDetails) authentication.getPrincipal();
        Date tokenExpirationDate = new Date(System.currentTimeMillis() + (jwtExpirationMs));
        return Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()) )
                .setHeaderParam("typ", TOKEN_TYPE)
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(tokenExpirationDate)
                .claim("roles",userPrincipal.getAuthorities())
                .compact();
    }

    public String getUsernameFromJwtToken(String token){
        return Jwts.parser().setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes()) )
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateJwtToken(String authToken) {

        // Todas estas excepciones son de JJwt
        try {
            Jwts.parser().setSigningKey(jwtSecret.getBytes()).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            log.info("Error en la firma del token JWT: " + ex.getMessage());
        } catch (MalformedJwtException ex) {
            log.info("Token malformado: " + ex.getMessage());
        } catch (ExpiredJwtException ex) {
            log.info("El token ha expirado: " + ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            log.info("Token JWT no soportado: " + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            log.info("JWT claims vacío");
        }
        return false;

    }

}
