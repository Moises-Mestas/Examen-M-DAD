package com.example.mjm_matricula.service.impl;

import com.example.mjm_matricula.dto.Curso;
import com.example.mjm_matricula.dto.Estudiante;
import com.example.mjm_matricula.entity.Matricula;
import com.example.mjm_matricula.feign.CursoFeign;
import com.example.mjm_matricula.feign.EstudianteFeign;
import com.example.mjm_matricula.repository.MatriculaRepository;
import com.example.mjm_matricula.service.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class MatriculaServiceImpl implements MatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private EstudianteFeign estudianteFeign;

    @Autowired
    private CursoFeign cursoFeign;

    @Override
    public List<Matricula> listar() {
        try {
            // Obtener todas las matrículas de la base de datos
            List<Matricula> matriculas = matriculaRepository.findAll();
            System.out.println("Matrículas obtenidas: " + matriculas);

            // Iterar sobre cada matrícula para obtener los detalles del Estudiante y el Curso
            for (Matricula matricula : matriculas) {
                // Obtener los detalles del Estudiante a través de Feign
                Estudiante estudiante = estudianteFeign.listById(matricula.getEstudianteId()).getBody();
                System.out.println("Estudiante obtenido: " + estudiante);

                // Obtener los detalles del Curso a través de Feign
                Curso curso = cursoFeign.listById(matricula.getCursoId()).getBody();
                System.out.println("Curso obtenido: " + curso);

                // Si el curso tiene capacidad, reducirla
                if (curso.getCapacidad() > 0) {
                    curso.setCapacidad(curso.getCapacidad() - 1); // Reducir la capacidad
                    cursoFeign.actualizar(matricula.getCursoId(), curso); // Actualizar el curso
                }

                // Asignar los DTOs al objeto Matricula
                matricula.setEstudiante(estudiante);
                matricula.setCurso(curso);
            }

            return matriculas;
        } catch (Exception e) {
            // Manejo de errores
            System.err.println("Error al procesar la lista de matrículas: " + e.getMessage());
            e.printStackTrace();  // Imprimir la traza completa del error
            return List.of(); // Retornar una lista vacía en caso de error
        }
    }




    @Override
    public Optional<Matricula> listarPorId(Long id) {
        try {
            Matricula matricula = matriculaRepository.findById(id).get();
            System.out.println("Matrícula obtenida: " + matricula);

            Estudiante estudiante = estudianteFeign.listById(matricula.getEstudianteId()).getBody();
            System.out.println("Estudiante obtenido: " + estudiante);

            // Verificar si el estudiante está inactivo
            if ("Inactivo".equals(estudiante.getEstado())) {
                // Si el estudiante está inactivo, solo mostramos el mensaje
                return Optional.of(new Matricula("Estudiante no disponible"));
            }

            Curso curso = cursoFeign.listById(matricula.getCursoId()).getBody();
            matricula.setEstudiante(estudiante);
            matricula.setCurso(curso);

            return Optional.of(matricula);
        } catch (Exception e) {
            System.err.println("Error al procesar la matrícula: " + e.getMessage());
            e.printStackTrace();  // Imprimir la traza completa del error
            return Optional.empty();
        }
    }

    @Override
    public Matricula guardar(Matricula matricula) {
        // Verificar que el estudiante existe
        Estudiante estudiante = estudianteFeign.listById(matricula.getEstudianteId()).getBody();
        if (estudiante == null) {
            throw new RuntimeException("El estudiante no existe.");
        }

        // Verificar que el curso existe
        Curso curso = cursoFeign.listById(matricula.getCursoId()).getBody();
        if (curso == null) {
            throw new RuntimeException("El curso no existe.");
        }

        // Verificar si el estudiante está activo
        if ("Inactivo".equals(estudiante.getEstado())) {
            throw new RuntimeException("El estudiante está inactivo.");
        }

        //  Verificar si ya está matriculado en ese curso
        Optional<Matricula> existente = matriculaRepository.findByEstudianteIdAndCursoId(
                matricula.getEstudianteId(), matricula.getCursoId());
        if (existente.isPresent()) {
            throw new RuntimeException("El estudiante ya está matriculado en este curso.");
        }

        // Verificar la capacidad del curso
        if (curso.getCapacidad() <= 0) {
            throw new RuntimeException("El curso no tiene capacidad.");
        }

        // Reducir la capacidad del curso
        curso.setCapacidad(curso.getCapacidad() - 1);
        cursoFeign.actualizar(matricula.getCursoId(), curso); // Actualizamos la capacidad del curso

        // Asignar el estudiante y curso a la matrícula
        matricula.setEstudiante(estudiante);
        matricula.setCurso(curso);

        // Guardamos la matrícula
        return matriculaRepository.save(matricula);
    }



    @Override
    public Matricula actualizar(Matricula matricula) {
        return matriculaRepository.save(matricula);
    }

    @Override
    public void eliminar(Long id) {
        matriculaRepository.deleteById(id);
    }
}
