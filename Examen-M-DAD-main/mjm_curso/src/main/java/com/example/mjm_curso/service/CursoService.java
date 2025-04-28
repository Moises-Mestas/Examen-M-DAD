package com.example.mjm_curso.service;

import com.example.mjm_curso.entity.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoService {
    List<Curso> listar();

    Optional<Curso> buscar(Long id);

    Curso guardar(Curso curso);

    Curso actualizar(Long id, Curso curso);

    void eliminar(Long id);

    boolean horarioExistente(String horario);  // Validación de horario
}
