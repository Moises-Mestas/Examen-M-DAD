package com.example.mjm_estudiante.service.impl;

import com.example.mjm_estudiante.entity.Estudiante;
import com.example.mjm_estudiante.repository.EstudianteRepository;
import com.example.mjm_estudiante.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteServiceImpl implements EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Override
    public List<Estudiante> listar() {
        return estudianteRepository.findAll();
    }

    @Override
    public Optional<Estudiante> buscar(Long id) {
        return estudianteRepository.findById(id);
    }

    @Override
    public Estudiante guardar(Estudiante estudiante) {
        // Verificar si el DNI ya está registrado
        if (documentoUnico(estudiante.getDni())) {
            throw new RuntimeException("EL DNI ya está registrado.");
        }
        return estudianteRepository.save(estudiante);
    }

    @Override
    public Estudiante actualizar(Long id, Estudiante estudiante) {
        // Verificar si el DNI ya está registrado, pero asegurándonos de que sea el mismo estudiante (actualización)
        if (documentoUnico(estudiante.getDni()) && !estudianteRepository.findById(id).get().getDni().equals(estudiante.getDni())) {
            throw new RuntimeException("EL DNI ya está registrado.");
        }
        estudiante.setId(id);  // Asegura que se actualice el estudiante con el ID correcto
        return estudianteRepository.save(estudiante);
    }

    @Override
    public void eliminar(Long id) {
        estudianteRepository.deleteById(id);
    }

    // Método para validar que el DNI sea único
    public boolean documentoUnico(String dni) {
        Optional<Estudiante> estudiante = estudianteRepository.findByDni(dni);  // Verifica el DNI en lugar de documento
        return estudiante.isPresent();
    }
}
