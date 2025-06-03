package com.gestionlicencias.authentication_server_jw.service;

import com.gestionlicencias.authentication_server_jw.config.JwtService;
import com.gestionlicencias.authentication_server_jw.model.entity.RolEntity;
import com.gestionlicencias.authentication_server_jw.model.entity.UserEntity;
import com.gestionlicencias.authentication_server_jw.model.request.UserCreateEvent;
import com.gestionlicencias.authentication_server_jw.model.request.UserCredentials;
import com.gestionlicencias.authentication_server_jw.model.request.UserRegister;
import com.gestionlicencias.authentication_server_jw.repository.RolRepository;
import com.gestionlicencias.authentication_server_jw.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.swing.*;
import java.time.LocalDate;
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
    private final KafkaTemplate<String, UserCreateEvent> kafkaTemplate;
    @Value("${topic.usuario-auth-creacion}")
    private String usuarioAuthTopic;

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
                .fechaRegistros(LocalDate.now())
                .roles(roles)
                .estado(true)
                .build();
        userRepository.save(userEntity);

        //enviar a kafka el usuario registrado
        UserCreateEvent evento = new UserCreateEvent(
                userEntity.getId(),
                userRegister.email(),
                userRegister.nombres(),
                userRegister.apellidos(),
                userRegister.telefono(),
                userRegister.direccion(),
                LocalDate.now()
        );

        kafkaTemplate.send(usuarioAuthTopic, evento);

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
}
