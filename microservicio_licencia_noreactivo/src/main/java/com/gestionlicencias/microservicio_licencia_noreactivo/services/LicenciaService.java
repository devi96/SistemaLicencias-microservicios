package com.gestionlicencias.microservicio_licencia_noreactivo.services;

import com.gestionlicencias.microservicio_licencia_noreactivo.model.dto.LicenciaRequest;
import com.gestionlicencias.microservicio_licencia_noreactivo.model.dto.LicenciaResponse;

import java.util.List;
import java.util.Optional;


public interface LicenciaService {
    List<LicenciaResponse> getAllLicencias();
    Optional<LicenciaResponse> getLicenciaById(Long licenciaId);
    LicenciaResponse createLicencia(LicenciaRequest request);
    LicenciaResponse updateLicencia(Long licenciaId, LicenciaRequest request);
    void deleteLicencia(Long licenciaId);
}
