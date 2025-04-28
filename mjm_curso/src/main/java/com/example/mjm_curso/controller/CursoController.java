package com.example.mjm_curso.controller;

import com.example.mjm_curso.entity.Curso;
import com.example.mjm_curso.repository.CursoRepository;
import com.example.mjm_curso.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @Autowired
    private CursoRepository cursoRepository;

    // Listar todos los cursos
    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(cursoService.listar());
    }

    // Buscar un curso por su ID
    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) {
        Optional<Curso> curso = cursoService.buscar(id);
        if (curso.isPresent()) {
            return ResponseEntity.ok(curso.get()); // Devuelve el curso si existe
        } else {
            // Devolvemos un Map con el mensaje de error
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Curso no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }


    // Crear un nuevo curso
    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Curso curso) {
        try {
            return ResponseEntity.ok(cursoService.guardar(curso));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Actualizar un curso
    @PutMapping("/{id}")
    public ResponseEntity<Curso> actualizar(@PathVariable("id") Long id, @RequestBody Curso curso) {
        Optional<Curso> cursoExistente = cursoRepository.findById(id);
        if (cursoExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Curso cursoActualizado = cursoExistente.get();
        cursoActualizado.setCapacidad(curso.getCapacidad());
        cursoRepository.save(cursoActualizado);

        return ResponseEntity.ok(cursoActualizado);
    }

    // Eliminar un curso por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        cursoService.eliminar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
