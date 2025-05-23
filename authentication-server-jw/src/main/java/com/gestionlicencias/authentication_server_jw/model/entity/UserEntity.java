package com.gestionlicencias.authentication_server_jw.model.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios")
@Data
@Builder
public class UserEntity implements UserDetails {

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
        private Set<RolEntity> roles = new HashSet<>();

        //private String[] roles;
        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
                return roles.stream()
                        .map( rol -> new SimpleGrantedAuthority( rol.getDescripcion()))
                        .collect(Collectors.toList());
        }

        @Override
        public String getUsername() {
                return email;
        }

        @Override
        public boolean isAccountNonExpired() {
                return true;
        }

        @Override
        public boolean isAccountNonLocked() {
                return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
                return true;
        }

        @Override
        public boolean isEnabled() {
                return estado != null && estado;
        }
}

