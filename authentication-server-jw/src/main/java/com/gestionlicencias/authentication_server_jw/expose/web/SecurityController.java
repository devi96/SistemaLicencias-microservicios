package com.gestionlicencias.authentication_server_jw.expose.web;

import com.gestionlicencias.authentication_server_jw.model.request.UserCredentials;
import com.gestionlicencias.authentication_server_jw.model.request.UserRegister;
import com.gestionlicencias.authentication_server_jw.model.response.AuthResponse;
import com.gestionlicencias.authentication_server_jw.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class SecurityController {

    private final SecurityService securityService;
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody UserRegister userRegister) {
        String token = securityService.register(userRegister);
        return ResponseEntity.ok(new AuthResponse(token));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody UserCredentials userCredentials) {
        String token = securityService.authenticate(userCredentials);
        return ResponseEntity.ok(new AuthResponse(token));
    }

}
