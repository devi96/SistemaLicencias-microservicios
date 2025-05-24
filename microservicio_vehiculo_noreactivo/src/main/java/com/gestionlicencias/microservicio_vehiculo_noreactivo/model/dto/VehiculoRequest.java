package com.gestionlicencias.microservicio_vehiculo_noreactivo.model.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.time.LocalDate;

public record VehiculoRequest(
        String marca,
        String modelo,
        String color,
        String placa,
        int anio,
        String tipoVehiculo,
        String estado,
        Long usuarioId) {
}
