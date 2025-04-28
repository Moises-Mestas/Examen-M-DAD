package com.example.mjm_estudiante.service;

import com.example.mjm_estudiante.entity.Estudiante;

import java.util.List;
import java.util.Optional;

public interface EstudianteService {
    List<Estudiante> listar();

    Optional<Estudiante> buscar(Long id);

    Estudiante guardar(Estudiante estudiante);

    Estudiante actualizar(Long id, Estudiante estudiante);

    void eliminar(Long id);

    boolean documentoUnico(String documento);  // Método para validar si el documento es único
}
