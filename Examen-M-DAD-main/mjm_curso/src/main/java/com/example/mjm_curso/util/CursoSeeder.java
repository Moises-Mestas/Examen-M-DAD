package com.example.mjm_curso.util;

import com.example.mjm_curso.entity.Curso;
import com.example.mjm_curso.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CursoSeeder implements CommandLineRunner {

    @Autowired
    private CursoRepository cursoRepository;

    @Override
    public void run(String... args) throws Exception {
        // Datos de ejemplo existentes
        Curso curso1 = new Curso(null, "CS101", "Curso de Programación", 30, "2023-1", "Lunes 9:00-12:00, Miércoles 9:00-12:00");
        Curso curso2 = new Curso(null, "CS102", "Curso de Bases de Datos", 25, "2023-2", "Martes 10:00-13:00, Jueves 10:00-13:00");
        Curso curso3 = new Curso(null, "CS103", "Curso de Redes", 40, "2023-1", "Lunes 14:00-17:00, Viernes 14:00-17:00");
        Curso curso4 = new Curso(null, "CS104", "Curso de Sistemas Operativos", 35, "2023-2", "Martes 8:00-11:00, Jueves 8:00-11:00");
        Curso curso5 = new Curso(null, "CS105", "Curso de Seguridad Informática", 20, "2023-1", "Miércoles 16:00-19:00, Viernes 16:00-19:00");

        // Nuevos cursos que me pediste agregar
        Curso curso6 = new Curso(null, "CS106", "Curso de Psicología", 19, "2025-1", "Lunes 20:00-21:00, Miércoles 1:00-3:00");
        Curso curso7 = new Curso(null, "CS107", "Curso de Taller", 52, "2025-2", "Lunes 21:00-21:00, Miércoles 1:00-3:00");
        Curso curso8 = new Curso(null, "CS108", "Curso de Cocina", 50, "2025-1", "Lunes 5:00-6:00, Miércoles 19:00-20:00");

        // Guardar todos los cursos en la base de datos
        cursoRepository.save(curso1);
        cursoRepository.save(curso2);
        cursoRepository.save(curso3);
        cursoRepository.save(curso4);
        cursoRepository.save(curso5);
        cursoRepository.save(curso6); // Guardando el nuevo curso
        cursoRepository.save(curso7); // Guardando el nuevo curso
        cursoRepository.save(curso8); // Guardando el nuevo curso

        System.out.println("Cursos de ejemplo insertados correctamente");
    }
}
