package com.gestionlicencias.microservicio_usuario_noreactivo.model.entity.dto;

import java.time.LocalDate;
import java.util.Set;

public record UsuarioRequest(
        Long id,
        String nombres,
        String apellidos,
        String email,
        String telefono,
        String direccion
) {
}
