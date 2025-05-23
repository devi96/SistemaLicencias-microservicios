package com.gestionlicencias.config_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

import com.gestionlicencias.config_server.config.EnvLoader;

@EnableConfigServer
@SpringBootApplication
public class ConfigServerApplication {

	public static void main(String[] args) {
		EnvLoader.loadEnv();
		SpringApplication.run(ConfigServerApplication.class, args);
	}

}
