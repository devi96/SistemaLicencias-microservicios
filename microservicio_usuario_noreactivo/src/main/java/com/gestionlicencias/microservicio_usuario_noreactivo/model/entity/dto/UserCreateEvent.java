package com.gestionlicencias.microservicio_usuario_noreactivo.model.entity.dto;

import java.time.LocalDate;

public record UserCreateEvent(
    Long usuarioId,
    String email,
    String nombre,
    String apellido,
    String telefono,
    String direccion,
    LocalDate fechaRegistro
)
{}
