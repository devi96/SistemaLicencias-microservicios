package com.gestionlicencias.microservicio_licencia_noreactivo.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "licencias")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LicenciaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String tipoLicencia;
    @Column(nullable = false)
    private Boolean estado;
    @Column(nullable = false)
    private LocalDate fechaEmision;
    @Column(nullable = false)
    private LocalDate fechaVencimiento;
    @Column(nullable = false)
    private Long usuarioId;
}
