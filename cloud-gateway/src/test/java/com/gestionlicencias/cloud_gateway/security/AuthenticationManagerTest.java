package com.gestionlicencias.cloud_gateway.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import reactor.test.StepVerifier;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.List;

public class AuthenticationManagerTest {

    private AuthenticationManager authenticationManager;

    @BeforeEach
    void setUp() {
        authenticationManager = new AuthenticationManager();
        authenticationManager.mitocodeKey = "mitocode-segura-para-jwt-test-123456"; // clave usada para firmar
    }


    @Test
    void shouldAuthenticateValidToken() {
        // Crear la clave secreta con el mismo algoritmo usado en el manager
        SecretKey key = Keys.hmacShaKeyFor(Base64.getEncoder().encode("mitocode-segura-para-jwt-test-123456".getBytes()));

        // Crear un JWT válido
        String token = Jwts.builder()
                .setSubject("usuario_test")
                .claim("roles", List.of("ROLE_USER", "ROLE_ADMIN"))
                .signWith(key)
                .compact();

        Authentication request = new UsernamePasswordAuthenticationToken(null, token);

        StepVerifier.create(authenticationManager.authenticate(request))
                .expectNextMatches(auth -> {
                    return auth.getName().equals("usuario_test")
                            && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER"))
                            && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
                })
                .verifyComplete();
    }
    @Test
    void shouldFailOnInvalidToken() {
        String invalidToken = "esteNoEsUnJWT";

        Authentication request = new UsernamePasswordAuthenticationToken(null, invalidToken);

        StepVerifier.create(authenticationManager.authenticate(request))
                .expectError() // puedes especificar el tipo si quieres: JwtException.class
                .verify();
    }
}
