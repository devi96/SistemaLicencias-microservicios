package com.gestionlicencias.microservicio_usuario_noreactivo.controller;
import com.gestionlicencias.microservicio_usuario_noreactivo.model.entity.RolEntity;
import com.gestionlicencias.microservicio_usuario_noreactivo.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/roles")
public class RolController {

    @Autowired
    private RolService rolService;

    @GetMapping
    public ResponseEntity<List<RolEntity>> listarRoles() {
        List<RolEntity> roles = rolService.findAll();
        return ResponseEntity.ok(roles);
    }

    @PostMapping
    public ResponseEntity<RolEntity> agregarRol(@RequestBody RolEntity rolEntity) {
        RolEntity nuevoRolEntity = rolService.save(rolEntity);
        return ResponseEntity.ok(nuevoRolEntity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolEntity> editarRol(@PathVariable Long id, @RequestBody RolEntity rolEntity) {
        RolEntity rolEntityActualizado = rolService.update(id, rolEntity);
        return ResponseEntity.ok(rolEntityActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRol(@PathVariable Long id) {
        rolService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}