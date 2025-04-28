package com.example.mjm_matricula.entity;

import com.example.mjm_matricula.dto.Estudiante;
import com.example.mjm_matricula.dto.Curso;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Data
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Clave primaria

    private Long estudianteId;  // ID del estudiante relacionado
    private Long cursoId;  // ID del curso al que el estudiante se matricula
    private String estado;  // Estado de la matrícula (activa, completada, etc.)
    private String ciclo;  // Ciclo (por ejemplo, 2023-1, 2023-2)

    private String fechaMatricula;  // Fecha en que se realizó la matrícula

    @Transient  // No persistir este campo en la base de datos
    private Estudiante estudiante;  // Información del Estudiante obtenida a través de Feign

    @Transient  // No persistir este campo en la base de datos
    private Curso curso;  // Información del Curso obtenida a través de Feign

    // Constructor con parámetros
    public Matricula(String estado) {
        this.id = id;
        this.estudianteId = estudianteId;
        this.cursoId = cursoId;
        this.estado = estado;
        this.ciclo = ciclo;
        this.fechaMatricula = fechaMatricula;
    }

    // Constructor vacío
    public Matricula() {
    }

    // Métodos getters y setters (opcionales si usas Lombok)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(Long estudianteId) {
        this.estudianteId = estudianteId;
    }

    public Long getCursoId() {
        return cursoId;
    }

    public void setCursoId(Long cursoId) {
        this.cursoId = cursoId;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public String getFechaMatricula() {
        return fechaMatricula;
    }

    public void setFechaMatricula(String fechaMatricula) {
        this.fechaMatricula = fechaMatricula;
    }

    // Métodos getters y setters para los objetos Estudiante y Curso (transitorios)
    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    // Método toString para representar la matrícula como string
    @Override
    public String toString() {
        return "Matricula{" +
                "id=" + id +
                ", estudianteId=" + estudianteId +
                ", cursoId=" + cursoId +
                ", estado='" + estado + '\'' +
                ", ciclo='" + ciclo + '\'' +
                ", fechaMatricula='" + fechaMatricula + '\'' +
                ", estudiante=" + estudiante +
                ", curso=" + curso +
                '}';
    }
}
