package com.gestionlicencias.microservicio_licencia_noreactivo.orchestrator;

import com.gestionlicencias.microservicio_licencia_noreactivo.exception.UsuarioNotFoundException;
import com.gestionlicencias.microservicio_licencia_noreactivo.model.dto.LicenciaRequest;
import com.gestionlicencias.microservicio_licencia_noreactivo.model.dto.LicenciaResponse;
import com.gestionlicencias.microservicio_licencia_noreactivo.model.dto.UsuarioResponse;
import com.gestionlicencias.microservicio_licencia_noreactivo.services.LicenciaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class LicenciaOrquestadorService {

    private final LicenciaService service;
    private final RestTemplate restTemplate; // Usa RestTemplate para llamadas sincrónicas

    public LicenciaResponse createLicencia(LicenciaRequest request) {
        validarUsuario(request.usuarioId());
        return service.createLicencia(request);
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

