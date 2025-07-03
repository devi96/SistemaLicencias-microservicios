package com.gestionlicencias.microservicio_solicitud_licencia_noreactivo.repository;

import com.gestionlicencias.microservicio_solicitud_licencia_noreactivo.model.entity.SolicitudLicenciaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitudLicenciaRepository extends JpaRepository<SolicitudLicenciaEntity, Long> {
    // Aquí puedes definir métodos adicionales de consulta si es necesario
}
