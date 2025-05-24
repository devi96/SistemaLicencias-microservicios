package com.gestionlicencias.authentication_server_jw.service;

import com.gestionlicencias.authentication_server_jw.config.JwtService;
import com.gestionlicencias.authentication_server_jw.model.entity.RolEntity;
import com.gestionlicencias.authentication_server_jw.model.entity.UserEntity;
import com.gestionlicencias.authentication_server_jw.model.request.UserCredentials;
import com.gestionlicencias.authentication_server_jw.model.request.UserRegister;
import com.gestionlicencias.authentication_server_jw.repository.RolRepository;
import com.gestionlicencias.authentication_server_jw.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.swing.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SecurityService {

    private final UserRepository userRepository;
    private final RolRepository rolRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public String register(UserRegister userRegister){
        // Traer los roles completos desde la base según los IDs enviados
        Set<RolEntity> roles = userRegister.rolId().stream()
                .map(id -> rolRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + id)))
                .collect(Collectors.toSet());

        UserEntity userEntity = UserEntity.builder()
                .email(userRegister.email())
                .password(passwordEncoder.encode(userRegister.password()))
                .nombres(userRegister.nombres())
                .apellidos(userRegister.apellidos())
                .telefono(userRegister.telefono())
                .direccion(userRegister.direccion())
                .fecha_registros(userRegister.fecha_registros())
                .roles(roles)
                .estado(true)
                .build();
        userRepository.save(userEntity);
        return jwtService.generateToken(userEntity);
    }
    public String authenticate(UserCredentials userCredentials) {
        log.info(userCredentials.toString());

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userCredentials.username(), userCredentials.password()));

        UserEntity userEntity = userRepository.findByEmail(userCredentials.username())
                .orElse(null);

        log.info("valor de userEntity: " + userEntity);

        if (userEntity == null) {
            throw new UsernameNotFoundException(String.format("Usuario %s no encontrado en la BD",
                    userCredentials.username()));
        }

        return jwtService.generateToken(userEntity);
    }

    /*
    public String authenticate(LoginRequest loginRequest) {
        ResponseEntity<String> response = restTemplate.postForEntity("lb://service-usuario/usuarios/validate", loginRequest, String.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            log.info("Usuario logueado correctamente");
            return "";
        } else {
            log.info("Error al loguear al usuario");
            return "Error al loguear el usuario";
        }
    }

    public String register(UserEntity usuarioEntity) {
        ResponseEntity<String> response = restTemplate.postForEntity("lb://service-usuario/usuarios/guardarUsuario", usuarioEntity, String.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return "Usuario registrado correctamente";
        } else
            return "Error al registrar el usuario";
    }
     */
}
