package com.gestionlicencias.microservicio_solicitud_licencia_noreactivo.service;

import com.gestionlicencias.microservicio_solicitud_licencia_noreactivo.model.dto.SolicitudLicenciaRequest;
import com.gestionlicencias.microservicio_solicitud_licencia_noreactivo.model.dto.SolicitudLicenciaResponse;

import java.util.List;

public interface SolicitudLicenciaService {
    public SolicitudLicenciaResponse crearSolicitudLicencia(SolicitudLicenciaRequest request);
    public SolicitudLicenciaResponse actualizarSolicitudLicencia(Long solicitudId, SolicitudLicenciaRequest request);
    public List<SolicitudLicenciaResponse> obtenerTodasSolicitudes();
    public SolicitudLicenciaResponse obtenerSolicitudPorId(Long solicitudId);
    public void eliminarSolicitudLicencia(Long solicitudId);
}
