package com.gestionlicencias.microservicio_solicitud_licencia_noreactivo.mapper;

import com.gestionlicencias.microservicio_solicitud_licencia_noreactivo.model.dto.SolicitudLicenciaRequest;
import com.gestionlicencias.microservicio_solicitud_licencia_noreactivo.model.dto.SolicitudLicenciaResponse;
import com.gestionlicencias.microservicio_solicitud_licencia_noreactivo.model.entity.SolicitudLicenciaEntity;
import org.springframework.stereotype.Component;

@Component
public class SolicitudLicenciaMapper {
    public SolicitudLicenciaEntity toEntity(SolicitudLicenciaRequest dto) {
        return SolicitudLicenciaEntity.builder()
                .tipoLicencia(dto.tipoLicencia())
                .estado(dto.estado())
                .fechaEmision(dto.fechaEmision())
                .fechaVencimiento(dto.fechaVencimiento())
                .usuarioId(dto.usuarioId())
                .build();
    }
    public SolicitudLicenciaResponse toDto(SolicitudLicenciaEntity entity) {
        return new SolicitudLicenciaResponse(
                entity.getId(),
                entity.getTipoLicencia(),
                entity.getEstado(),
                entity.getFechaEmision(),
                entity.getFechaVencimiento(),
                entity.getUsuarioId()
        );
    }

}
