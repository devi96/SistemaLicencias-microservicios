package com.gestionlicencias.microservicio_usuario_noreactivo.model.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usuarios")
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

        @Column(nullable = false)
        private String password;

        @Column(nullable = false, unique = true)
        private String email;

        @Column(nullable = false)
        private String telefono;

        @Column(nullable = false)
        private String direccion;

        @Column(nullable = false)
        private LocalDate fecha_registros;

        @Column(nullable = false)
        private Boolean estado;

        @ManyToMany
        @JoinTable(
            name = "usuario_rol",  // nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "usuario_id"),  // FK a usuario
            inverseJoinColumns = @JoinColumn(name = "rol_id") // FK a rol
        )
        private Set<RolEntity> roles;
 }

