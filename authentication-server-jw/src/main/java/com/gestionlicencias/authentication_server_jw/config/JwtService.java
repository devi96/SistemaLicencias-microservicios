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

@Service
public class JwtService {

    @Value("${mitocode.security.key}")
    public String mitocodeKey;

    public String generateToken(UserDetails userDetails) {
        UserEntity user = (UserEntity) userDetails;
        // Implement JWT token generation logic here
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Token valid for 10 hours
                .signWith(getKey())
                .compact();
    }
    private Key getKey() {
        return Keys.hmacShaKeyFor(Base64.getEncoder().encode(mitocodeKey.getBytes()));
    }
}
