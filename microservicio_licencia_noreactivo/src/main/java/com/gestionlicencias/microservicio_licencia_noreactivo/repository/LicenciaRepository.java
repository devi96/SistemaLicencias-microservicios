package com.gestionlicencias.microservicio_licencia_noreactivo.repository;

import com.gestionlicencias.microservicio_licencia_noreactivo.model.entity.LicenciaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicenciaRepository extends JpaRepository<LicenciaEntity, Long> {
}
