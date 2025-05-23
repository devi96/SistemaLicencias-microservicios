package com.gestionlicencias.authentication_server_jw.repository;

import com.gestionlicencias.authentication_server_jw.model.entity.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<RolEntity, Long> {
    // Aquí puedes agregar métodos personalizados si es necesario
}