package com.example.mjm_matricula.repository;

import com.example.mjm_matricula.entity.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {

    Optional<Matricula> findByEstudianteIdAndCursoId(Long estudianteId, Long cursoId);
}
