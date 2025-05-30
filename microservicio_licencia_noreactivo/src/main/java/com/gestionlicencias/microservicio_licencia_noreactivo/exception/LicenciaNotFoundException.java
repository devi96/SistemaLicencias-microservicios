package com.gestionlicencias.microservicio_licencia_noreactivo.exception;

public class LicenciaNotFoundException extends RuntimeException {
    public LicenciaNotFoundException(Long id) {
        super("Licencia no encontrada con ID: " + id);
    }
}