package com.example.mjm_matricula.feign;

import com.example.mjm_matricula.dto.Estudiante;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "mjm-estudiante-service", path = "/estudiantes")
public interface EstudianteFeign {

    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> listById(@PathVariable(required = true) Long id);
}