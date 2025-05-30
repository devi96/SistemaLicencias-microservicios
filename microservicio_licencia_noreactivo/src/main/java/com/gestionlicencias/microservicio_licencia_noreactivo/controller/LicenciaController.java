package com.gestionlicencias.microservicio_licencia_noreactivo.controller;

import com.gestionlicencias.microservicio_licencia_noreactivo.model.dto.LicenciaRequest;
import com.gestionlicencias.microservicio_licencia_noreactivo.model.dto.LicenciaResponse;
import com.gestionlicencias.microservicio_licencia_noreactivo.services.LicenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@RequestMapping("/licencias")
public class LicenciaController {
    private final LicenciaService service;

    @GetMapping
    public ResponseEntity<List<LicenciaResponse>> getLicencias() {
        return ResponseEntity.ok(service.getAllLicencias());
    }
    @GetMapping("/{id}")
    public ResponseEntity<LicenciaResponse> getLicenciaById(@PathVariable Long id) {
        return service.getLicenciaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}")
    public ResponseEntity<LicenciaResponse> updateLicencia(@PathVariable Long id, @RequestBody LicenciaRequest licencia) {
        LicenciaResponse response = service.updateLicencia(id, licencia);
        return ResponseEntity.ok(response);
    }
    @PostMapping
    public ResponseEntity<EntityModel<LicenciaResponse>> createLicencia(@RequestBody LicenciaRequest licencia) {
        LicenciaResponse response = service.createLicencia(licencia);
        EntityModel<LicenciaResponse> resource = EntityModel.of(response);
        resource.add(linkTo(methodOn(LicenciaController.class).getLicenciaById(response.id())).withSelfRel());
        URI location = linkTo(methodOn(LicenciaController.class).getLicenciaById(response.id())).toUri();
        return ResponseEntity.created(location).body(resource);
    }
    @DeleteMapping
    public ResponseEntity<Void> deleteLicencia(@PathVariable Long id) {
        service.deleteLicencia(id);
        return ResponseEntity.noContent().build();
    }
}
