package com.gestionlicencias.authentication_server_jw.repository;

import com.gestionlicencias.authentication_server_jw.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    // Aquí puedes agregar métodos personalizados si es necesario

}