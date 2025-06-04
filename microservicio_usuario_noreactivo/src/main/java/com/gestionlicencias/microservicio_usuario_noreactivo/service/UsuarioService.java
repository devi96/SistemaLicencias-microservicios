package com.gestionlicencias.microservicio_usuario_noreactivo.service;
import com.gestionlicencias.microservicio_usuario_noreactivo.model.entity.UsuarioEntity;
import com.gestionlicencias.microservicio_usuario_noreactivo.model.entity.dto.UserCreateEvent;
import com.gestionlicencias.microservicio_usuario_noreactivo.model.entity.dto.UsuarioRequest;
import com.gestionlicencias.microservicio_usuario_noreactivo.model.entity.dto.UsuarioResponse;
import com.gestionlicencias.microservicio_usuario_noreactivo.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


public interface UsuarioService {
    public List<UsuarioResponse> listarUsuarios();
    public Optional<UsuarioResponse> buscarPorId(Long id);
    public Optional<UsuarioResponse> buscarPorIdAuth(Long id);
    public UsuarioResponse actualizarUsuario(Long id, UsuarioRequest actualizar);
    public void crearPerfilDesdeEvento (UserCreateEvent event);
    public void eliminarUsuario(Long id);

}