package com.gestionlicencias.config_server.config;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvLoader {
    public static void loadEnv() {
        Dotenv dotenv = Dotenv.configure().load();
        System.setProperty("GIT_USERNAME", dotenv.get("GIT_USERNAME"));
        System.setProperty("GIT_PASSWORD", dotenv.get("GIT_PASSWORD"));
    }
}