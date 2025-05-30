package com.gestionlicencias.authentication_server_jw.model.request;

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
