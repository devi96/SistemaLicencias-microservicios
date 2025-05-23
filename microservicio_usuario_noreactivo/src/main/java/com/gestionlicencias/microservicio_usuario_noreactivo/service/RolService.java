package com.gestionlicencias.microservicio_usuario_noreactivo.service;
import com.gestionlicencias.microservicio_usuario_noreactivo.model.entity.RolEntity;
import com.gestionlicencias.microservicio_usuario_noreactivo.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    public List<RolEntity> findAll() {
        return rolRepository.findAll();
    }

    public Optional<RolEntity> findById(Long id) {
        return rolRepository.findById(id);
    }

    public RolEntity save(RolEntity rolEntity) {
        return rolRepository.save(rolEntity);
    }

    public RolEntity update(Long id, RolEntity rolEntity) {
        return rolRepository.findById(id)
                .map(existingRolEntity -> {
                    if (rolEntity.getDescripcion() != null) existingRolEntity.setDescripcion(rolEntity.getDescripcion());
                    return rolRepository.save(existingRolEntity);
                })
                .orElseThrow(() -> new RuntimeException("Rol not found with id " + id));
    }

    public void deleteById(Long id) {
        if (rolRepository.existsById(id)) {
            rolRepository.deleteById(id);
        } else {
            throw new RuntimeException("Rol not found with id " + id);
        }
    }
}