package com.gestionlicencias.microservicio_usuario_noreactivo.model.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequest {
    String email;
    String password;
}
