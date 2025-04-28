package com.example.mjm_estudiante.controller;

import com.example.mjm_estudiante.entity.Estudiante;
import com.example.mjm_estudiante.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    // Listar todos los estudiantes
    @RequestMapping
    public List<Estudiante> listar() {
        return estudianteService.listar();
    }

    // Buscar un estudiante por su ID
    @RequestMapping("/{id}")
    public Optional<Estudiante> buscar(@PathVariable Long id) {
        return estudianteService.buscar(id);
    }

    // Guardar un nuevo estudiante
    @PostMapping
    public Estudiante guardar(@RequestBody Estudiante estudiante) {
        return estudianteService.guardar(estudiante);
    }

    // Actualizar los datos de un estudiante
    @PutMapping("/{id}")
    public Estudiante actualizar(@PathVariable Long id, @RequestBody Estudiante estudiante) {
        return estudianteService.actualizar(id, estudiante);
    }

    // Eliminar un estudiante por su ID
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        estudianteService.eliminar(id);
    }
}
