package com.gestionlicencias.authentication_server_jw.model.request;

import com.gestionlicencias.authentication_server_jw.model.entity.RolEntity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public record UserRegister(

     Long id,
     String nombres,
     String apellidos,
     String password,
     String email,
     String telefono,
     String direccion,
     LocalDate fecha_registros,
     List<Long> rolId
){}
