package com.gestionlicencias.microservicio_solicitud_licencia_noreactivo.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "solicitudes_licencia")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudLicenciaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipoLicencia;
    @Enumerated(EnumType.STRING)
    private Estado estado;
    @Column(nullable = false)
    private LocalDate fechaEmision;
    @Column(nullable = false)
    private LocalDate fechaVencimiento;
    @Column(nullable = false)
    private Long usuarioId;
}
