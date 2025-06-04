package com.gestionlicencias.microservicio_vehiculo_noreactivo.repository;

import com.gestionlicencias.microservicio_vehiculo_noreactivo.model.entity.VehiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehiculoRepository extends JpaRepository<VehiculoEntity, Long> {

    List<VehiculoEntity> findByUsuarioId(Long usuarioId);
}
