package com.gestionlicencias.microservicio_usuario_noreactivo.controller;
import java.util.List;
import java.util.Optional;

import com.gestionlicencias.microservicio_usuario_noreactivo.model.entity.dto.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gestionlicencias.microservicio_usuario_noreactivo.model.entity.UsuarioEntity;
import com.gestionlicencias.microservicio_usuario_noreactivo.service.UsuarioService;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService; // Asegúrate de tener un servicio para manejar la lógica de negocio

    /*
    @PostMapping("/validate")
    public ResponseEntity<String> validarUsuario(@RequestBody LoginRequest loginRequest) {
      boolean esValido = usuarioService.validateCredentials(loginRequest.getEmail(),loginRequest.getPassword());
      if(esValido) return ResponseEntity.ok("usuario valido");
      else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("usuario no valido");
    }*/

    @GetMapping
    public ResponseEntity<List<UsuarioEntity>> listarUsuarios() {
        List<UsuarioEntity> usuarioEntities = usuarioService.listarUsuarios(); // Método que devuelve una lista de usuarios
        return ResponseEntity.ok(usuarioEntities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioEntity> buscarPorId(@PathVariable Long id) {
        Optional<UsuarioEntity> usuario = usuarioService.buscarPorId(id); // Método que busca un usuario por ID
        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioEntity> actualizarUsuario(@PathVariable Long id, @RequestBody UsuarioEntity usuarioEntity) {
        UsuarioEntity usuarioEntityActualizado = usuarioService.actualizarUsuario(id, usuarioEntity); // Método que edita un usuario
        if (usuarioEntityActualizado != null) {
            return ResponseEntity.ok(usuarioEntityActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id); // Método que elimina un usuario por ID
        return ResponseEntity.noContent().build();
    }
    /*
    @PostMapping
    public ResponseEntity<UsuarioEntity> guardarUsuario(@RequestBody UsuarioEntity usuarioEntity) {
        UsuarioEntity nuevoUsuarioEntity = usuarioService.guardarUsuario(usuarioEntity); // Método que guarda un nuevo usuario
        return ResponseEntity.ok(nuevoUsuarioEntity);
    }*/
}