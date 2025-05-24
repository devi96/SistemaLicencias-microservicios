package com.gestionlicencias.microservicio_vehiculo_noreactivo.controller;

import com.gestionlicencias.microservicio_vehiculo_noreactivo.model.dto.VehiculoRequest;
import com.gestionlicencias.microservicio_vehiculo_noreactivo.model.dto.VehiculoResponse;
import com.gestionlicencias.microservicio_vehiculo_noreactivo.services.VehiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vehiculos")
public class VehiculoController {
    private final VehiculoService service;

    @GetMapping
    public ResponseEntity<List<VehiculoResponse>> findAll() {
        return ResponseEntity.ok(service.getAllVehiculos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<VehiculoResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getVehiculoById(id).orElse(null));
    }

    @PostMapping
    public ResponseEntity<VehiculoResponse> save(@RequestBody VehiculoRequest vehiculo) {
        return ResponseEntity.ok(service.createVehiculo(vehiculo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehiculoResponse> update(@RequestBody VehiculoRequest vehiculo, @PathVariable Long id) {
        VehiculoResponse response = service.updateVehiculo(id,vehiculo);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping
    public void delete(@RequestParam Long id) {
        service.deleteVehiculo(id);
    }

}
