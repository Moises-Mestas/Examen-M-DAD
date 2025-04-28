package com.example.mjm_matricula.feign;

import com.example.mjm_matricula.dto.Curso;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.CacheRequest;

@FeignClient(name = "mjm-curso-service", url = "http://localhost:8082/cursos")
public interface CursoFeign {
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    ResponseEntity<Curso> listById(@PathVariable("id") Long id);

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    ResponseEntity<Curso> actualizar(@PathVariable("id") Long id, @RequestBody Curso curso);
}
