package com.gestionlicencias.microservicio_solicitud_licencia_noreactivo.service.impl;

import com.gestionlicencias.microservicio_solicitud_licencia_noreactivo.mapper.SolicitudLicenciaMapper;
import com.gestionlicencias.microservicio_solicitud_licencia_noreactivo.model.dto.SolicitudLicenciaRequest;
import com.gestionlicencias.microservicio_solicitud_licencia_noreactivo.model.dto.SolicitudLicenciaResponse;
import com.gestionlicencias.microservicio_solicitud_licencia_noreactivo.repository.SolicitudLicenciaRepository;
import com.gestionlicencias.microservicio_solicitud_licencia_noreactivo.service.SolicitudLicenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SolicitudLlicenciaServiceImpl implements SolicitudLicenciaService {

    private final SolicitudLicenciaRepository repo;
    private final SolicitudLicenciaMapper mapper;

    @Override
    public SolicitudLicenciaResponse crearSolicitudLicencia(SolicitudLicenciaRequest request) {
        var entity = mapper.toEntity(request); // Convertir DTO a entidad
        var savedEntity = repo.save(entity); // Guardar la entidad en la base de datos
        return mapper.toDto(savedEntity); // Convertir la entidad guardada de vuelta a DTO
    }

    @Override
    public SolicitudLicenciaResponse actualizarSolicitudLicencia(Long solicitudId, SolicitudLicenciaRequest request) {
        var existingEntity = repo.findById(solicitudId)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada con ID: " + solicitudId));

        existingEntity.setTipoLicencia(request.tipoLicencia());
        existingEntity.setEstado(request.estado());
        existingEntity.setFechaEmision(request.fechaEmision());
        existingEntity.setFechaVencimiento(request.fechaVencimiento());
        existingEntity.setUsuarioId(request.usuarioId());

        var updatedEntity = repo.save(existingEntity);
        return mapper.toDto(updatedEntity);
    }

    @Override
    public List<SolicitudLicenciaResponse> obtenerTodasSolicitudes() {
        return repo.findAll()
                .stream()
                .map(mapper::toDto) // Convertir cada entidad a DTO
                .toList(); // Recoger los resultados en una lista
    }

    @Override
    public SolicitudLicenciaResponse obtenerSolicitudPorId(Long solicitudId) {
        return repo.findById(solicitudId)
                .map(mapper::toDto) // Convertir la entidad a DTO si se encuentra
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada con ID: " + solicitudId));
    }

    @Override
    public void eliminarSolicitudLicencia(Long solicitudId) {
        var existingEntity = repo.findById(solicitudId)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada con ID: " + solicitudId));
        repo.delete(existingEntity);
    }
}
