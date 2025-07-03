package com.gestionlicencias.microservicio_solicitud_licencia_noreactivo.model.dto;

import com.gestionlicencias.microservicio_solicitud_licencia_noreactivo.model.entity.Estado;

import java.time.LocalDate;

public record SolicitudLicenciaResponse(
        Long id,
        String tipoLicencia,
        Estado estado,
        LocalDate fechaEmision,
        LocalDate fechaVencimiento,
        Long usuarioId
) {
}
