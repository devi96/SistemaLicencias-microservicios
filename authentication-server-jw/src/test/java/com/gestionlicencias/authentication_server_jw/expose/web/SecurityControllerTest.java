package com.gestionlicencias.authentication_server_jw.expose.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestionlicencias.authentication_server_jw.config.SecurityConfig;
import com.gestionlicencias.authentication_server_jw.model.request.UserRegister;
import com.gestionlicencias.authentication_server_jw.service.SecurityService;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

//@SpringBootTest
//@AutoConfigureMockMvc
public class SecurityControllerTest{

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SecurityService securityService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Disabled
    void testRegister() throws Exception {
        // Arrange
        UserRegister user = new UserRegister("juan", "martinez",
                "pass" ,"juan@gmail.com", "15843556",
                "direccion", List.of( 1L, 2L));
        String expectedToken = "fake-jwt-tokennnnnnnnnnnnnnnnnn";
        Mockito.when(securityService.register(any(UserRegister.class))).thenReturn(expectedToken);

        // Act & Assert
        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user))
                        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value(expectedToken));
    }
}
