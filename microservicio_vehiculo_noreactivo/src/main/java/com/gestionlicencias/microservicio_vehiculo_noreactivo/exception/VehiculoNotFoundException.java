package com.gestionlicencias.microservicio_vehiculo_noreactivo.exception;

public class VehiculoNotFoundException extends RuntimeException{
    public VehiculoNotFoundException(Long id) {
       super("Vehículo no encontrado con ID: " + id);
    }
}
