package com.example.mjm_matricula;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients  // Habilitar Feign Client
public class MjmMatriculaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MjmMatriculaApplication.class, args);
    }
}
