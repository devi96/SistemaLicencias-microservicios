package com.gestionlicencias.microservicio_usuario_noreactivo.service.impl;

import com.gestionlicencias.microservicio_usuario_noreactivo.model.entity.UsuarioEntity;
import com.gestionlicencias.microservicio_usuario_noreactivo.model.entity.dto.UserCreateEvent;
import com.gestionlicencias.microservicio_usuario_noreactivo.model.entity.dto.UsuarioRequest;
import com.gestionlicencias.microservicio_usuario_noreactivo.model.entity.dto.UsuarioResponse;
import com.gestionlicencias.microservicio_usuario_noreactivo.repository.UsuarioRepository;
import com.gestionlicencias.microservicio_usuario_noreactivo.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public List<UsuarioResponse> listarUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(this::convertirEntityUsuarioADTO)
                .collect(Collectors.toList());
    }
    @Override
    public Optional<UsuarioResponse> buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(this::convertirEntityUsuarioADTO);
    }
    @Override
    public UsuarioResponse actualizarUsuario(Long id, UsuarioRequest actualizar) {
        return usuarioRepository.findById(id).map(usuarioEntity -> {
                    usuarioEntity.setNombres(actualizar.nombres());
                    usuarioEntity.setTelefono(actualizar.telefono());
                    return usuarioRepository.save(usuarioEntity);
                }).map(this::convertirEntityUsuarioADTO)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
    }

    @KafkaListener(
            topics = "usuario-auth-creacion",
            groupId = "usuario-service-group",
            containerFactory = "kafkaListenerContainerFactory"
    )

    @Override
    public void crearPerfilDesdeEvento (UserCreateEvent event) {
        this.crearPerfil(event);
    }

    @Override
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    private UsuarioEntity crearPerfil(UserCreateEvent event){
        return usuarioRepository.save(UsuarioEntity.builder()
                .nombres(event.nombre())
                .apellidos(event.apellido())
                .email(event.email())
                .telefono(event.telefono())
                .direccion(event.direccion())
                .fechaRegistro(event.fechaRegistro())
                .usuarioAuthId(event.usuarioId())
                .build());
    }
    private UsuarioResponse convertirEntityUsuarioADTO(UsuarioEntity user ){
        return new UsuarioResponse(
                user.getId(),
                user.getNombres(),
                user.getApellidos(),
                user.getEmail(),
                user.getTelefono(),
                user.getDireccion(),
                user.getFechaRegistro()
        );
    }
}
