package com.gestionlicencias.authentication_server_jw.service;

import com.gestionlicencias.authentication_server_jw.config.JwtService;
import com.gestionlicencias.authentication_server_jw.model.entity.RolEntity;
import com.gestionlicencias.authentication_server_jw.model.entity.UserEntity;
import com.gestionlicencias.authentication_server_jw.model.request.UserCreateEvent;
import com.gestionlicencias.authentication_server_jw.model.request.UserCredentials;
import com.gestionlicencias.authentication_server_jw.model.request.UserRegister;
import com.gestionlicencias.authentication_server_jw.repository.RolRepository;
import com.gestionlicencias.authentication_server_jw.repository.UserRepository;
import jakarta.websocket.SendResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
public class SecurityServiceTest {

    @InjectMocks
    private SecurityService securityService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private RolRepository rolRepository;
    @Mock
    private JwtService jwtService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private KafkaTemplate<String, UserCreateEvent> kafkaTemplate;

    String usuarioAuthTopic = "usuario-auth-creacion";

    @BeforeEach
    void setUp() {
        securityService = new SecurityService(
                userRepository,
                rolRepository,
                jwtService,
                passwordEncoder,
                authenticationManager,
                kafkaTemplate,
                usuarioAuthTopic
        );
    }

    @Test
    public void testRegister() {
        //Arrange
        String tokenEsperado = "jwt-token-generado";
        UserRegister user = new UserRegister("juan", "martinez",
                "pass" ,"juan@gmail.com", "15843556",
                "direccion", List.of( 1L, 2L));

        RolEntity rol1 = new RolEntity();
        rol1.setId(1L);
        RolEntity rol2 = new RolEntity();
        rol2.setId(2L);

        when(rolRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(rol1));
        when(rolRepository.findById(2L)).thenReturn(java.util.Optional.ofNullable(rol2));

        String encodedPassword = "encodedPass123";
        when(passwordEncoder.encode(anyString())).thenReturn(encodedPassword);

        // Simular que guarda el usuario y asigna ID
        UserEntity savedUser = UserEntity.builder()
                .id(100L)
                .email("juan@gmail.com")
                .password(encodedPassword)
                .roles(Set.of(rol1, rol2))
                .estado(true)
                .fechaRegistros(LocalDate.now())
                .build();

        when(userRepository.save(any(UserEntity.class)))
                .thenReturn(savedUser);

        when(kafkaTemplate.send(eq(usuarioAuthTopic), any(UserCreateEvent.class)))
                .thenReturn(null);

        when(jwtService.generateToken(any(UserEntity.class))).thenReturn(tokenEsperado);

        //Act
        String resultToken = securityService.register(user);

        //Assert
        assertEquals(tokenEsperado, resultToken);
        verify(userRepository).save(any(UserEntity.class));
        verify(kafkaTemplate).send(eq("usuario-auth-creacion"), any(UserCreateEvent.class));
    }
    @Test
    void testAuthenticate_whenValidCredentials_thenReturnToken() {
        // Arrange
        UserCredentials userCredentials = new UserCredentials("juan@gmail.com", "pass123");

        UserEntity userEntity = UserEntity.builder()
                .id(1L)
                .email("juan@gmail.com")
                .password("encodedPass123")
                .estado(true)
                .fechaRegistros(LocalDate.now())
                .build();

        // Simular autenticación exitosa
        when(userRepository.findByEmail(userCredentials.username()))
                .thenReturn(Optional.of(userEntity));

        when(jwtService.generateToken(userEntity))
                .thenReturn("token-jwt-valido");

        // Act
        String result = securityService.authenticate(userCredentials);

        // Assert
        assertEquals("token-jwt-valido", result);
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(userRepository).findByEmail("juan@gmail.com");
        verify(jwtService).generateToken(userEntity);
    }
}
