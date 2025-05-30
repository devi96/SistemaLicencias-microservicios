package com.gestionlicencias.microservicio_licencia_noreactivo.model.dto;

import java.time.LocalDate;

public record LicenciaRequest(
    String tipoLicencia,
    LocalDate fechaVencimiento,
    Boolean estado,
    Long usuarioId
){
}
