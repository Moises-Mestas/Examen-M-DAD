package com.example.mjm_matricula.controller;

import com.example.mjm_matricula.dto.Curso;
import com.example.mjm_matricula.dto.Estudiante;
import com.example.mjm_matricula.entity.Matricula;
import com.example.mjm_matricula.feign.CursoFeign;
import com.example.mjm_matricula.feign.EstudianteFeign;
import com.example.mjm_matricula.service.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/matricula")
public class MatriculaController {

    @Autowired
    private MatriculaService matriculaService;
    @Autowired
    private EstudianteFeign estudianteFeign; // Inyectamos EstudianteFeign
    @Autowired
    private CursoFeign cursoFeign; // Inyectamos CursoFeign

    // Listar todas las matrículas
    @GetMapping
    public ResponseEntity<List<Matricula>> list() {
        return ResponseEntity.ok().body(matriculaService.listar());
    }

    // Crear una nueva matrícula
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Matricula matricula) {
        try {
            // Verificar si el estudiante está activo
            Estudiante estudiante = estudianteFeign.listById(matricula.getEstudianteId()).getBody();
            if ("Inactivo".equals(estudiante.getEstado())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El estudiante está inactivo.");
            }

            // Verificar si el curso tiene capacidad
            Curso curso = cursoFeign.listById(matricula.getCursoId()).getBody();
            if (curso.getCapacidad() <= 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El curso no tiene capacidad.");
            }

            // Reducir la capacidad del curso en la base de datos
            curso.setCapacidad(curso.getCapacidad() - 0); // Reducir la capacidad
            cursoFeign.actualizar(matricula.getCursoId(), curso); // Llamada para actualizar la capacidad del curso

            // Guardar la matrícula
            Matricula nuevaMatricula = matriculaService.guardar(matricula);

            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaMatricula); // Respuesta 201 si se crea correctamente
        } catch (RuntimeException e) {
            // En caso de error (por ejemplo, si el estudiante o curso no existen)
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", e.getMessage()); // Mensaje de error
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse); // Respuesta 400 si hubo un error
        }
    }



    // Actualizar una matrícula
    @PutMapping
    public ResponseEntity<Matricula> update(@RequestBody Matricula matricula) {
        return ResponseEntity.ok(matriculaService.actualizar(matricula));
    }

    // Buscar matrícula por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> listById(@PathVariable Long id) {
        Optional<Matricula> matricula = matriculaService.listarPorId(id);

        if (matricula.isPresent()) {
            // Verificamos si el estudiante está inactivo
            if ("Estudiante no disponible".equals(matricula.get().getEstado())) {
                // Si el estudiante está inactivo, solo mostramos el mensaje
                return ResponseEntity.status(HttpStatus.OK).body("Estudiante no disponible");
            }
            // Si la matrícula está disponible, mostramos la información
            return ResponseEntity.ok(matricula.get());
        } else {
            // Si no se encuentra la matrícula, mostramos el mensaje de error
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Matrícula no encontrada");
        }
    }

    // Eliminar una matrícula por ID
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        matriculaService.eliminar(id);
    }
}
