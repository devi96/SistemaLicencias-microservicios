package com.gestionlicencias.microservicio_licencia_noreactivo.exception.handler;

import com.gestionlicencias.microservicio_licencia_noreactivo.exception.LicenciaNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LicenciaNotFoundException.class)
    public ResponseEntity<String> handleLicenciaNotFound(LicenciaNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    // Puedes agregar validaciones más adelante (ej. MethodArgumentNotValidException)
}