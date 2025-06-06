package com.gestionlicencias.microservicio_usuario_noreactivo.service.impl;

import com.gestionlicencias.microservicio_usuario_noreactivo.Mapper.UsuarioMapper;
import com.gestionlicencias.microservicio_usuario_noreactivo.exception.UsuarioNotFoundException;
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
    private final UsuarioMapper mapper;
    @Override
    public List<UsuarioResponse> listarUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(this.mapper::toDtoUsuarioResponse)
                .collect(Collectors.toList());
    }
    @Override
    public Optional<UsuarioResponse> buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(this.mapper::toDtoUsuarioResponse);
    }
    @Override
    public Optional<UsuarioResponse> buscarPorIdAuth(Long id) {
        return usuarioRepository.findByUsuarioAuthId(id)
                .map(this.mapper::toDtoUsuarioResponse);
    }
    @Override
    public UsuarioResponse actualizarUsuario(Long id, UsuarioRequest actualizar) {
        return usuarioRepository.findById(id).map(usuarioEntity -> {
                    usuarioEntity.setNombres(actualizar.nombres());
                    usuarioEntity.setTelefono(actualizar.telefono());
                    return usuarioRepository.save(usuarioEntity);
                }).map(this.mapper::toDtoUsuarioResponse)
                .orElseThrow(() -> new UsuarioNotFoundException(id));
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

}
