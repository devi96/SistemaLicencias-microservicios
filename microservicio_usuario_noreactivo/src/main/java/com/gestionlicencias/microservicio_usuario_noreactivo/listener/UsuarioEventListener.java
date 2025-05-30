package com.gestionlicencias.microservicio_usuario_noreactivo.listener;

import com.gestionlicencias.microservicio_usuario_noreactivo.model.entity.dto.UserCreateEvent;
import com.gestionlicencias.microservicio_usuario_noreactivo.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsuarioEventListener {
    private final UsuarioService usuarioService;

    @KafkaListener(
            topics = "usuario-auth-creacion",
            groupId = "usuario-service-group",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consumir(UserCreateEvent event) {
        usuarioService.crearPerfilDesdeEvento(event);
    }
}
