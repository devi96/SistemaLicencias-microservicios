package com.gestionlicencias.microservicio_solicitud_licencia_noreactivo.controller;

import com.gestionlicencias.microservicio_solicitud_licencia_noreactivo.model.dto.SolicitudLicenciaRequest;
import com.gestionlicencias.microservicio_solicitud_licencia_noreactivo.model.dto.SolicitudLicenciaResponse;
import com.gestionlicencias.microservicio_solicitud_licencia_noreactivo.service.SolicitudLicenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/solicitudes-licencia")
@RequiredArgsConstructor
public class SolicitudLicenciaController {

    private final SolicitudLicenciaService service;

    @GetMapping
    public ResponseEntity<List<SolicitudLicenciaResponse>> obtenerTodasSolicitudes() {
        List<SolicitudLicenciaResponse> lista = service.obtenerTodasSolicitudes();

        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(lista);
    }
    @GetMapping("/{id}")
    public ResponseEntity<SolicitudLicenciaResponse> obtenerSolicitudPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerSolicitudPorId(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<SolicitudLicenciaResponse> actualizarSolicitud(@PathVariable Long id, @RequestBody SolicitudLicenciaRequest solicitud) {
        SolicitudLicenciaResponse updatedSolicitud = service.actualizarSolicitudLicencia(id, solicitud);
        return ResponseEntity.ok(updatedSolicitud);
    }
    @PostMapping
    public ResponseEntity<SolicitudLicenciaResponse> crearSolicitud(@RequestBody SolicitudLicenciaRequest solicitud) {
        SolicitudLicenciaResponse createdSolicitud = service.crearSolicitudLicencia(solicitud);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSolicitud);
    }
    @DeleteMapping
    public ResponseEntity<Void> eliminarSolicitud(@RequestParam Long id) {
        service.eliminarSolicitudLicencia(id);
        return ResponseEntity.noContent().build();
    }
}
