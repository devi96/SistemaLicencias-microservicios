package com.gestionlicencias.config_server.config;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvLoader {
    public static void loadEnv() {
        //Dotenv dotenv = Dotenv.configure().load();
        System.setProperty("GIT_USERNAME", System.getenv("GIT_USERNAME"));
        System.setProperty("GIT_PASSWORD", System.getenv("GIT_PASSWORD"));
    }
}