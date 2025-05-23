package com.gestionlicencias.authentication_server_jw.model.request;

public record UserCredentials(
        String username,
        String password
) {
}
