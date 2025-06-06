package com.gestionlicencias.microservicio_usuario_noreactivo.usuarios;

import com.gestionlicencias.microservicio_usuario_noreactivo.Mapper.UsuarioMapper;
import com.gestionlicencias.microservicio_usuario_noreactivo.model.entity.UsuarioEntity;
import com.gestionlicencias.microservicio_usuario_noreactivo.model.entity.dto.UsuarioResponse;
import com.gestionlicencias.microservicio_usuario_noreactivo.repository.UsuarioRepository;
import com.gestionlicencias.microservicio_usuario_noreactivo.service.impl.UsuarioServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {
    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @Mock
    private UsuarioMapper mapper;

    private UsuarioEntity usuario1;
    private UsuarioEntity usuario2;
    private UsuarioResponse dto1;
    private UsuarioResponse dto2;

    @BeforeEach
    void setUp(){
        // Arrange
        usuario1 = new UsuarioEntity();
        usuario1.setId(1L);
        usuario1.setNombres("Juan");

        usuario2 = new UsuarioEntity();
        usuario2.setId(2L);
        usuario2.setNombres("Ana");

        dto1 = new UsuarioResponse(1L, "Juan", null, null, null, null, null);
        dto2 = new UsuarioResponse(2L, "Ana", null, null, null, null, null);
    }

    @Test
    void testListarUsuarios() {

        List<UsuarioEntity> usuariosMock = List.of(usuario1, usuario2);

        Mockito.when(usuarioRepository.findAll()).thenReturn(usuariosMock);
        Mockito.when(mapper.toDtoUsuarioResponse(usuario1)).thenReturn(dto1);
        Mockito.when(mapper.toDtoUsuarioResponse(usuario2)).thenReturn(dto2);

        // Act
        List<UsuarioResponse> resultado = usuarioService.listarUsuarios();

        // Assert
        Assertions.assertEquals(2, resultado.size());
        Assertions.assertEquals("Juan", resultado.get(0).nombres());
        Assertions.assertEquals("Ana", resultado.get(1).nombres());
    }

}
