package com.gestionlicencias.microservicio_usuario_noreactivo.Mapper;

import com.gestionlicencias.microservicio_usuario_noreactivo.model.entity.UsuarioEntity;
import com.gestionlicencias.microservicio_usuario_noreactivo.model.entity.dto.UsuarioResponse;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {
    public UsuarioResponse toDtoUsuarioResponse(UsuarioEntity user ){
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
