package com.gestionlicencias.microservicio_vehiculo_noreactivo.services.impl;

import com.gestionlicencias.microservicio_vehiculo_noreactivo.model.dto.VehiculoRequest;
import com.gestionlicencias.microservicio_vehiculo_noreactivo.model.dto.VehiculoResponse;
import com.gestionlicencias.microservicio_vehiculo_noreactivo.model.entity.VehiculoEntity;
import com.gestionlicencias.microservicio_vehiculo_noreactivo.repository.VehiculoRepository;
import com.gestionlicencias.microservicio_vehiculo_noreactivo.services.VehiculoService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehiculoServiceImpl implements VehiculoService {

    private final VehiculoRepository repository;

    private VehiculoResponse entityToDTO(VehiculoEntity vehiculo){
        return new VehiculoResponse(
                vehiculo.getId(),
                vehiculo.getMarca(),
                vehiculo.getModelo(),
                vehiculo.getColor(),
                vehiculo.getPlaca(),
                vehiculo.getAnio(),
                vehiculo.getTipoVehiculo(),
                vehiculo.getEstado(),
                vehiculo.getFechaRegistro(),
                vehiculo.getUsuarioId()
        );
    }
    private VehiculoEntity dtoToEntity(VehiculoRequest request) {
        return VehiculoEntity.builder()
                .marca(request.marca())
                .modelo(request.modelo())
                .color(request.color())
                .placa(request.placa())
                .anio(request.anio())
                .tipoVehiculo(request.tipoVehiculo())
                .estado(request.estado())
                .fechaRegistro(LocalDate.now())
                .usuarioId(request.usuarioId())
                .build();
    }
    @Override
    public List<VehiculoResponse> getAllVehiculos() {
        return repository.findAll().stream()
                .map(this::entityToDTO)
                .toList();
    }

    @Override
    public Optional<VehiculoResponse> getVehiculoById(Long id) {
        return repository.findById(id)
                .map(this::entityToDTO);
    }

    @Override
    public VehiculoResponse createVehiculo(VehiculoRequest request) {
        VehiculoEntity saved = repository.save(this.dtoToEntity(request));
        return this.entityToDTO(saved);
    }

    @Override
    public VehiculoResponse updateVehiculo(Long vehiculoId, VehiculoRequest request) {
        VehiculoEntity entity = repository.findById(vehiculoId)
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));

        // Modificar los campos existentes, no crear uno nuevo
        entity.setMarca(request.marca());
        entity.setModelo(request.modelo());
        entity.setColor(request.color());
        entity.setPlaca(request.placa());
        entity.setAnio(request.anio());
        entity.setTipoVehiculo(request.tipoVehiculo());
        entity.setEstado(request.estado());
        entity.setUsuarioId(request.usuarioId());

        VehiculoEntity updated = repository.save(entity);
        return this.entityToDTO(updated);
    }

    @Override
    public void deleteVehiculo(Long vehiculoId) {
        repository.deleteById(vehiculoId);
    }
}
