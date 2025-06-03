package com.gestionlicencias.microservicio_vehiculo_noreactivo.exception.handler;

import com.gestionlicencias.microservicio_vehiculo_noreactivo.exception.UsuarioNotFoundException;
import com.gestionlicencias.microservicio_vehiculo_noreactivo.exception.VehiculoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UsuarioNotFoundException.class)
    public ResponseEntity<String> handleUsuarioNotFoundException(UsuarioNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(VehiculoNotFoundException.class)
    public ResponseEntity<String> handleUsuarioNotFoundException(VehiculoNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
