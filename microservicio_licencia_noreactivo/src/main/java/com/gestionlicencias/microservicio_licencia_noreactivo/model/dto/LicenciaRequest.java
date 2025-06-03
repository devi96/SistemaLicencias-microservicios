package com.gestionlicencias.microservicio_licencia_noreactivo.model.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record LicenciaRequest(
    @NotNull(message = "El tipo de licencia no puede ser nulo")
    String tipoLicencia,
    @NotNull
    @Future(message = "La fecha de vencimiento debe ser una fecha futura")
    LocalDate fechaVencimiento,
    Boolean estado,
    Long usuarioId
){
}
