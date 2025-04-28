package com.example.mjm_curso.service.impl;

import com.example.mjm_curso.entity.Curso;
import com.example.mjm_curso.repository.CursoRepository;
import com.example.mjm_curso.service.CursoService;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoServiceImpl implements CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Override
    public List<Curso> listar() {
        return cursoRepository.findAll();
    }

    @Override
    public Optional<Curso> buscar(Long id) {
        return cursoRepository.findById(id);
    }

    @Override
    public Curso guardar(Curso curso) {
        // Verificar si el horario ya está ocupado
        if (horarioExistente(curso.getHorario())) {
            throw new RuntimeException("El horario ya está ocupado por otro curso.");
        }
        return cursoRepository.save(curso);
    }

    public Curso actualizar(Long id, Curso curso) {
        // Verificar si el horario ya está ocupado antes de realizar la actualización
        if (horarioExistente(curso.getHorario())) {
            throw new BadRequestException("El horario ya está ocupado por otro curso.");
        }

        // Recuperar el curso existente para actualizar la capacidad
        Curso cursoExistente = cursoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Curso no encontrado."));

        // Reducir la capacidad del curso (si la capacidad es mayor a 0)
        if (cursoExistente.getCapacidad() > 0) {
            cursoExistente.setCapacidad(cursoExistente.getCapacidad() - 1); // Disminuir capacidad
        } else {
            throw new BadRequestException("No hay capacidad disponible para el curso.");
        }

        // Establecer el ID del curso para la actualización
        curso.setId(id);

        // Guardar el curso actualizado
        return cursoRepository.save(curso);
    }



    @Override
    public void eliminar(Long id) {
        cursoRepository.deleteById(id);
    }

    public boolean horarioExistente(String horario) {
        // Verificar si el horario del curso ya está ocupado
        return cursoRepository.findByHorario(horario).isPresent();
    }
}
