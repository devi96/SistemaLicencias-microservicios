package com.gestionlicencias.microservicio_vehiculo_noreactivo.services;

import com.gestionlicencias.microservicio_vehiculo_noreactivo.model.dto.VehiculoRequest;
import com.gestionlicencias.microservicio_vehiculo_noreactivo.model.dto.VehiculoResponse;

import java.util.List;
import java.util.Optional;

public interface VehiculoService {
    List<VehiculoResponse> getAllVehiculos();
    Optional<VehiculoResponse> getVehiculoById(Long vehiculoId);
    VehiculoResponse createVehiculo(VehiculoRequest request);
    VehiculoResponse updateVehiculo(Long vehiculoId, VehiculoRequest request);
    void deleteVehiculo(Long vehiculoId);
}
