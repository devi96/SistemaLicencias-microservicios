package com.gestionlicencias.microservicio_usuario_noreactivo.repository;

import com.gestionlicencias.microservicio_usuario_noreactivo.model.entity.UsuarioEntity;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findByEmail(String email);
    // Aquí puedes agregar métodos personalizados si es necesario
}