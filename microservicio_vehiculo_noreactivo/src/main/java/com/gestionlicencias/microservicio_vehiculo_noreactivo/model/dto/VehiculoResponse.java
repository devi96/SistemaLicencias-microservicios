package com.gestionlicencias.microservicio_vehiculo_noreactivo.model.dto;

import java.time.LocalDate;

public record VehiculoResponse(
        Long id,
        String marca,
        String modelo,
        String color,
        String placa,
        int anio,
        String tipoVehiculo,
        String estado,
        LocalDate fechaRegistro,
        Long usuarioId
) {
}
