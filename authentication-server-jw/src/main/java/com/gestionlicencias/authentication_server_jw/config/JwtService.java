package com.gestionlicencias.authentication_server_jw.config;

import com.gestionlicencias.authentication_server_jw.model.entity.UserEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class JwtService {

    @Value("${mitocode.security.key}")
    public String mitocodeKey;

    public String generateToken(UserDetails userDetails) {
        UserEntity user = (UserEntity) userDetails;
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("email", user.getEmail());
        claims.put("roles", user.getRoles().stream()
                .map(e -> e.getDescripcion())
                .collect(Collectors.toList()));
        // Implement JWT token generation logic here
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Token valid for 10 hours
                .signWith(getKey())
                .compact();
    }
    private Key getKey() {
        return Keys.hmacShaKeyFor(Base64.getEncoder().encode(mitocodeKey.getBytes()));
    }
}
