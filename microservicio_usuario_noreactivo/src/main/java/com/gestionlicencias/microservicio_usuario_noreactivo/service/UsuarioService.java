package com.gestionlicencias.microservicio_usuario_noreactivo.service;
import com.gestionlicencias.microservicio_usuario_noreactivo.model.entity.UsuarioEntity;
import com.gestionlicencias.microservicio_usuario_noreactivo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UsuarioEntity> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<UsuarioEntity> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public UsuarioEntity actualizarUsuario(Long id, UsuarioEntity usuarioEntityActualizado) {
        return usuarioRepository.findById(id).map(usuarioEntity -> {
            usuarioEntity.setNombres(usuarioEntityActualizado.getNombres());
            usuarioEntity.setEmail(usuarioEntityActualizado.getEmail());
            usuarioEntity.setTelefono(usuarioEntityActualizado.getTelefono());
            return usuarioRepository.save(usuarioEntity);
        }).orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
    }

    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    /*
    public boolean validateCredentials(String email, String password){
        Optional<UsuarioEntity> userOpt = usuarioRepository.findByEmail(email);
        UsuarioEntity user = userOpt.get();
        return passwordEncoder.matches(user.getPassword(), password);
    }
    public UsuarioEntity guardarUsuario(UsuarioEntity usuarioEntity) {
        String passwordEncriptado = passwordEncoder.encode(usuarioEntity.getPassword());
        usuarioEntity.setPassword(passwordEncriptado);
        return usuarioRepository.save(usuarioEntity);
    }*/
}