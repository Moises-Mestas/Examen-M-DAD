package com.example.mjm_curso.repository;

import com.example.mjm_curso.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    Optional<Object> findByHorario(String horario);
}
