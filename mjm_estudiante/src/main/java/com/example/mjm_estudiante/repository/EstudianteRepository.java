package com.example.mjm_estudiante.repository;

import com.example.mjm_estudiante.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    // Método para buscar por DNI
    Optional<Estudiante> findByDni(String dni);  // Aquí usamos dni en lugar de documento
}
