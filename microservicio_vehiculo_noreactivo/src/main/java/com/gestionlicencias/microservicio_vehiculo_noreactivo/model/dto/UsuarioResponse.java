package com.gestionlicencias.microservicio_vehiculo_noreactivo.model.dto;

import java.time.LocalDate;


public record UsuarioResponse(
    Long id,
    String nombres,
    String apellidos,
    String email,
    String telefono,
    String direccion,
    LocalDate fechaRegistro
){}
