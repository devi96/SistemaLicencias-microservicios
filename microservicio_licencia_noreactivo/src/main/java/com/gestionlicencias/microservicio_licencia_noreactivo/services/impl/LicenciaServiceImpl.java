package com.gestionlicencias.microservicio_licencia_noreactivo.services.impl;

import com.gestionlicencias.microservicio_licencia_noreactivo.exception.LicenciaNotFoundException;
import com.gestionlicencias.microservicio_licencia_noreactivo.model.dto.LicenciaRequest;
import com.gestionlicencias.microservicio_licencia_noreactivo.model.dto.LicenciaResponse;
import com.gestionlicencias.microservicio_licencia_noreactivo.model.entity.LicenciaEntity;
import com.gestionlicencias.microservicio_licencia_noreactivo.repository.LicenciaRepository;
import com.gestionlicencias.microservicio_licencia_noreactivo.services.LicenciaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class LicenciaServiceImpl implements LicenciaService {
    private final LicenciaRepository licenciaRepository;
    // Implementación de los métodos de la interfaz LicenciaService
    @Override
    public List<LicenciaResponse> getAllLicencias() {
        log.info("Obteniendo todas las licencias");
        return licenciaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<LicenciaResponse> getLicenciaById(Long licenciaId) {
        log.info("Obteniendo licencia con ID: {}", licenciaId);
        return licenciaRepository.findById(licenciaId)
                .map(this::convertToDTO);
    }

    @Override
    public LicenciaResponse createLicencia(LicenciaRequest request) {
        LicenciaEntity entity = LicenciaEntity.builder()
                .tipoLicencia(request.tipoLicencia())
                .estado(true)
                .fechaEmision(LocalDate.now())
                .fechaVencimiento(request.fechaVencimiento()) // Asumiendo que la licencia es válida por un año
                .usuarioId(request.usuarioId()).build();

        return convertToDTO(licenciaRepository.save(entity));
    }

    @Override
    public LicenciaResponse updateLicencia(Long licenciaId, LicenciaRequest request) {
        LicenciaEntity entity = licenciaRepository.findById(licenciaId)
                .orElseThrow(() -> new LicenciaNotFoundException(licenciaId));
        entity.setTipoLicencia(request.tipoLicencia());
        entity.setEstado(request.estado());
        entity.setFechaVencimiento(request.fechaVencimiento());
        return convertToDTO(licenciaRepository.save(entity));
    }

    @Override
    public void deleteLicencia(Long licenciaId) {
        log.info("Eliminando licencia con ID: {}", licenciaId);
        if (!licenciaRepository.existsById(licenciaId)) {
            throw new LicenciaNotFoundException(licenciaId);
        }
        licenciaRepository.deleteById(licenciaId);
    }
    private LicenciaResponse convertToDTO(LicenciaEntity entity){
        return new LicenciaResponse(
                entity.getId(),
                entity.getTipoLicencia(),
                entity.getEstado(),
                entity.getFechaEmision(),
                entity.getFechaVencimiento(),
                entity.getUsuarioId()
        );
    }
}
