package com.gestionlicencias.microservicio_usuario_noreactivo.controller;
import java.util.List;
import java.util.Optional;

import com.gestionlicencias.microservicio_usuario_noreactivo.model.entity.dto.UsuarioRequest;
import com.gestionlicencias.microservicio_usuario_noreactivo.model.entity.dto.UsuarioResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gestionlicencias.microservicio_usuario_noreactivo.model.entity.UsuarioEntity;
import com.gestionlicencias.microservicio_usuario_noreactivo.service.UsuarioService;


@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService; // Asegúrate de tener un servicio para manejar la lógica de negocio

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listarUsuarios() {
        List<UsuarioResponse> usuarios = usuarioService.listarUsuarios(); // Método que devuelve una lista de usuarios
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> buscarPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/auth/{usuarioAuthId}")
    public ResponseEntity<UsuarioResponse> buscarPorUsuarioAuthId(@PathVariable Long usuarioAuthId) {
        return usuarioService.buscarPorIdAuth(usuarioAuthId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> actualizarUsuario(@PathVariable Long id, @RequestBody UsuarioRequest request) {
        UsuarioResponse usuario = usuarioService.actualizarUsuario(id, request); // Método que edita un usuario
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id); // Método que elimina un usuario por ID
        return ResponseEntity.noContent().build();
    }
}