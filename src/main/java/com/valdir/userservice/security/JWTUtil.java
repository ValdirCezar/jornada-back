package com.valdir.userservice.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Log4j2
@Component
public class JWTUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String generateToken(String cpf) {
        log.info("JWTUtil - GERANDO TOKEN");
        return Jwts.builder()
                .setSubject(cpf)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret.getBytes()).compact();
    }

    public boolean tokenValido(String token) {
        log.info("JWTUtil - VERIFICANDO SE TOKEN É VÁLIDO");
        Claims claims = getClaimsToken(token);

        if (claims != null) {
            String username = claims.getSubject();
            Date expirationDate = claims.getExpiration();
            Date now = new Date(System.currentTimeMillis());
            return username != null && expirationDate != null && now.before(expirationDate);
        }

        return false;
    }

    private Claims getClaimsToken(String token) {
        log.info("JWTUtil - OBTENDO CLAIMS DO TOKEN");
        try {
            return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public String getUsername(String token) {
        log.info("JWTUtil - OBTENDO USERNAME DO TOKEN");
        Claims claims = getClaimsToken(token);
        if (claims != null) {
            return claims.getSubject();
        }
        return null;
    }
}