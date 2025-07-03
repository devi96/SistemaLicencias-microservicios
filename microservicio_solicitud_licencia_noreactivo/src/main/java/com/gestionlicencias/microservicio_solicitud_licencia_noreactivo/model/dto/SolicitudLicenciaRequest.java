package com.gestionlicencias.microservicio_solicitud_licencia_noreactivo.model.dto;

import com.gestionlicencias.microservicio_solicitud_licencia_noreactivo.model.entity.Estado;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record SolicitudLicenciaRequest(
        @NotBlank String tipoLicencia,
        @NotNull Estado estado,
        @NotNull LocalDate fechaEmision,
        @NotNull @Future LocalDate fechaVencimiento,
        @NotNull Long usuarioId
) {
}
