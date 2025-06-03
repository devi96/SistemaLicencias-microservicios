package com.gestionlicencias.cloud_gateway.security;


import com.gestionlicencias.cloud_gateway.exception.InvalidTokenException;
import com.gestionlicencias.cloud_gateway.exception.JwtExpiredException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements WebFilter {

    private final ReactiveAuthenticationManager authenticationManager;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        return getToken(exchange)
                .flatMap(this::authenticateToken)
                .flatMap(auth -> continueChainWithAuth(exchange, chain, auth))
                .onErrorMap(SignatureException.class, ex -> new InvalidTokenException("La firma del token no es válida", ex))
                .onErrorMap(ExpiredJwtException.class, ex -> new JwtExpiredException("Token expirado", ex)
                )
                //.onErrorMap(Exception.class, ex -> new RuntimeException("Error inesperado de autenticación", ex))
                .switchIfEmpty(chain.filter(exchange));

        /*return Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION))
                .filter(authHeader -> authHeader.startsWith("Bearer "))
                .switchIfEmpty(chain.filter(exchange).then(Mono.empty()))
                .map(token -> token.replace("Bearer ", ""))
                .flatMap(token -> authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(null, token)))
                .flatMap(auth -> chain.filter(exchange).contextWrite(ReactiveSecurityContextHolder.withAuthentication(auth)))
                .switchIfEmpty(chain.filter(exchange))
                .onErrorResume(SignatureException.class, e -> {
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete();
                })
                .onErrorResume(Exception.class, e -> {
                    log.error("Error during authentication: {}", e.getMessage());
                    exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
                    return exchange.getResponse().setComplete();
                });
        */
    }
    private Mono<String> getToken(ServerWebExchange exchange) {
        return Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION))
                .filter(authHeader -> authHeader.startsWith("Bearer "))
                .map(token -> token.replace("Bearer ", ""));
    }
    private Mono<Authentication> authenticateToken(String token) {
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(null, token));
    }
    private Mono<Void> continueChainWithAuth(ServerWebExchange exchange, WebFilterChain chain, Authentication auth) {
        return chain.filter(exchange)
                .contextWrite(ReactiveSecurityContextHolder.withAuthentication(auth));
    }
}
