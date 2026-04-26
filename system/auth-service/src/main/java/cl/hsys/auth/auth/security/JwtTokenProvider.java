package cl.hsys.auth.auth.security;

import java.security.Key;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @Value("${app.jwt.expiration}")
    private long jwtExpirationInMs;

    @Value("${app.jwt.refresh-expiration}")
    private long refreshExpirationInMs;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    //Genera el token con roles
    public String generateAccessToken(
                    Authentication authentication, UUID userId, List<UUID> clientIds) {
        String username = authentication.getName();

        //Convierte los roles a String 
        String roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        //Convierte la lista de UUID's a una lista de Strings para el JSON del JWT
        List<String> clientIdsStr = clientIds.stream()
                .map(UUID::toString)
                .toList();

        return Jwts.builder()
                .setSubject(username)
                .claim("userId", userId.toString())
                .claim("clientIds", clientIdsStr.stream())
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }


    //Genera e, UUID userId, List<UUID> clientIdsl token 

    public boolean validateToken (String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e){
            return false;
        }
    }

    public String getUsernameFromToken (String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String generateRefreshToken (String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + refreshExpirationInMs))
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    //Metodo para extraer los clientIds del token (Util para el gateway)
    public List<String> getClientIdsFromToken (String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        Object clientIdsClaim = claims.get("clientIds");

        if(clientIdsClaim instanceof List<?> list){
            return list.stream()
                    .map(Object::toString)
                    .toList();
        }

        return Collections.emptyList();
    }

}
