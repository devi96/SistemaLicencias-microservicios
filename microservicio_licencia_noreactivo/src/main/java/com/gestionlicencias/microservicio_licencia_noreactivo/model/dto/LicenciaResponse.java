package com.gestionlicencias.microservicio_licencia_noreactivo.model.dto;

import java.time.LocalDate;

public record LicenciaResponse(
    Long id,
    String tipoLicencia,
    Boolean estado,
    LocalDate fechaEmision,
    LocalDate fechaVencimiento,
    Long usuarioId
) {
}
