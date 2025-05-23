package com.gestionlicencias.microservicio_usuario_noreactivo.repository;
import com.gestionlicencias.microservicio_usuario_noreactivo.model.entity.RolEntity;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<RolEntity, Long> {
    // Aquí puedes agregar métodos personalizados si es necesario
}