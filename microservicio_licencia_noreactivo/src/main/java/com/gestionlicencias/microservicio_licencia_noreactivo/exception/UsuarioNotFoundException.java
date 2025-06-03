package com.gestionlicencias.microservicio_licencia_noreactivo.exception;

public class UsuarioNotFoundException extends RuntimeException {
  public UsuarioNotFoundException(String message) {
    super(message);
  }
}
