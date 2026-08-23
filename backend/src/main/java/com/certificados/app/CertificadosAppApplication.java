package com.certificados.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Punto de entrada de la aplicacion.
 * Arquitectura monolitica: este mismo proceso sirve la API REST
 * y los archivos estaticos del frontend Angular (carpeta /static).
 */
@SpringBootApplication
public class CertificadosAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(CertificadosAppApplication.class, args);
    }
}
