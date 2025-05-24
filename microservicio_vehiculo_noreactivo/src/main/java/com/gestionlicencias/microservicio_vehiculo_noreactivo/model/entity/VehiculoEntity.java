package com.gestionlicencias.microservicio_vehiculo_noreactivo.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Max;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "vehiculos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehiculoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String marca;
    @Column(nullable = false)
    private String modelo;
    @Column(nullable = false)
    private String color;
    @Column(nullable = false)
    private String placa;
    @Column(nullable = false)
    @Min(1900)
    @Max(2100)
    private int anio;
    @Column(nullable = false)
    private String tipoVehiculo;
    @Column(nullable = false)
    private String estado;
    @Column(nullable = false)
    private LocalDate fechaRegistro;
    @Column(nullable = false)
    private Long usuarioId;
}
