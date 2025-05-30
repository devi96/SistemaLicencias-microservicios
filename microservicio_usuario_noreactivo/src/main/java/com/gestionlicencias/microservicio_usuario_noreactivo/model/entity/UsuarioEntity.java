package com.gestionlicencias.microservicio_usuario_noreactivo.model.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "perfil_usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String nombres;

        @Column(nullable = false)
        private String apellidos;

        @Column(nullable = false, unique = true)
        private String email;

        @Column(nullable = false)
        private String telefono;

        @Column(nullable = false)
        private String direccion;

        @Column(nullable = false)
        private LocalDate fechaRegistro;

        @Column(nullable = false)
        private Long usuarioAuthId;
}

