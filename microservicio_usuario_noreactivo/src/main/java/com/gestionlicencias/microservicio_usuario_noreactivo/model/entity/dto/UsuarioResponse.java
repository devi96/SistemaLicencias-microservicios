package com.gestionlicencias.microservicio_usuario_noreactivo.model.entity.dto;

import com.gestionlicencias.microservicio_usuario_noreactivo.model.entity.RolEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;


public record UsuarioResponse(
    Long id,
    String nombres,
    String apellidos,
    String email,
    String telefono,
    String direccion,
    LocalDate fecha_registros,
    Boolean estado,
    List<RolResponse> roles
){}
