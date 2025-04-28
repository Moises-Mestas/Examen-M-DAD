package com.example.mjm_matricula.service;



import com.example.mjm_matricula.entity.Matricula;

import java.util.List;
import java.util.Optional;

public interface MatriculaService {
    List<Matricula> listar();

    Optional<Matricula> listarPorId(Long id);

    Matricula guardar(Matricula matricula);

    Matricula actualizar(Matricula matricula);

    void eliminar(Long id);
}
