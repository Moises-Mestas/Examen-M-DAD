package com.example.mjmconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class MjmConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MjmConfigServerApplication.class, args);
    }

}
