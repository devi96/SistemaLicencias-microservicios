package com.gestionlicencias.microservicio_usuario_noreactivo.service;
import com.gestionlicencias.microservicio_usuario_noreactivo.model.entity.RolEntity;
import com.gestionlicencias.microservicio_usuario_noreactivo.model.entity.UsuarioEntity;
import com.gestionlicencias.microservicio_usuario_noreactivo.model.entity.dto.RolResponse;
import com.gestionlicencias.microservicio_usuario_noreactivo.model.entity.dto.UsuarioRequest;
import com.gestionlicencias.microservicio_usuario_noreactivo.model.entity.dto.UsuarioResponse;
import com.gestionlicencias.microservicio_usuario_noreactivo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UsuarioResponse> listarUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(this::convertirEntityUsuarioADTO)
                .collect(Collectors.toList());
    }

    private UsuarioResponse convertirEntityUsuarioADTO(UsuarioEntity user ){

        List<RolResponse> rolesDTO = user.getRoles().stream()
                .map(r -> new RolResponse(r.getId(), r.getDescripcion()))
                .collect(Collectors.toList());

        return new UsuarioResponse(
                user.getId(),
                user.getNombres(),
                user.getApellidos(),
                user.getEmail(),
                user.getTelefono(),
                user.getDireccion(),
                user.getFecha_registros(),
                user.getEstado(),
                rolesDTO
        );
    }

    public Optional<UsuarioResponse> buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(this::convertirEntityUsuarioADTO);
    }

    public UsuarioResponse actualizarUsuario(Long id, UsuarioRequest actualizar) {
        return usuarioRepository.findById(id).map(usuarioEntity -> {
            usuarioEntity.setNombres(actualizar.nombres());
            usuarioEntity.setEmail(actualizar.email());
            usuarioEntity.setTelefono(actualizar.telefono());
            return usuarioRepository.save(usuarioEntity);
        }).map(this::convertirEntityUsuarioADTO)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
    }

    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    /*
    public boolean validateCredentials(String email, String password){
        Optional<UsuarioEntity> userOpt = usuarioRepository.findByEmail(email);
        UsuarioEntity user = userOpt.get();
        return passwordEncoder.matches(user.getPassword(), password);
    }
    public UsuarioEntity guardarUsuario(UsuarioEntity usuarioEntity) {
        String passwordEncriptado = passwordEncoder.encode(usuarioEntity.getPassword());
        usuarioEntity.setPassword(passwordEncriptado);
        return usuarioRepository.save(usuarioEntity);
    }*/
}