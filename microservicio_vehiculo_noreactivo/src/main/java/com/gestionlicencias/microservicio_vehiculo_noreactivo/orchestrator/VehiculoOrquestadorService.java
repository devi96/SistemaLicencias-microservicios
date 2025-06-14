package com.gestionlicencias.microservicio_vehiculo_noreactivo.orchestrator;

import com.gestionlicencias.microservicio_vehiculo_noreactivo.exception.UsuarioNotFoundException;
import com.gestionlicencias.microservicio_vehiculo_noreactivo.model.dto.UsuarioResponse;
import com.gestionlicencias.microservicio_vehiculo_noreactivo.model.dto.VehiculoRequest;
import com.gestionlicencias.microservicio_vehiculo_noreactivo.model.dto.VehiculoResponse;
import com.gestionlicencias.microservicio_vehiculo_noreactivo.services.VehiculoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class VehiculoOrquestadorService {

    private final VehiculoService service;
    private final RestTemplate restTemplate; // Usa RestTemplate para llamadas sincrónicas

    public VehiculoResponse registrarVehiculo(VehiculoRequest request) {
        validarUsuario(request.usuarioId());
        return service.createVehiculo(request);
    }

    private void validarUsuario(Long usuarioId) {
        try {
            UsuarioResponse usuario = restTemplate.getForObject(
                    "lb://service-usuario/usuarios/{id}", UsuarioResponse.class, usuarioId);
            if (usuario == null) {
                throw new UsuarioNotFoundException(usuarioId);
            }
        } catch (HttpClientErrorException.NotFound e) {
            throw new UsuarioNotFoundException(usuarioId);
        } catch (Exception e) {
            log.error("Error al validar usuario: {}", e.getMessage());
            throw new RuntimeException("Error validando usuario", e);
        }
    }
}
