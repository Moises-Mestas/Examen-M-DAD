package com.example.mjm_matricula.dto;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Clave primaria
    private String codigo;  // Código del curso
    private String nombre;  // Nombre del curso
    private Integer capacidad;  // Capacidad del curso
    private String ciclo;  // Ciclo académico en el que se imparte el curso (ej. 2023-1, 2023-2)
    private String horario;  // Horario del curso (puede ser un String con la información del horario)

    // Constructor vacío
    public Curso(String cursoNoDisponible) {
    }

    // Constructor con parámetros
    public Curso(Long id, String codigo, String nombre, Integer capacidad, String ciclo, String horario) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.ciclo = ciclo;
        this.horario = horario;
    }

    // Métodos getter y setter (opcional si usas Lombok)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    // Método toString para representar el curso como string
    @Override
    public String toString() {
        return "Curso{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", capacidad=" + capacidad +
                ", ciclo='" + ciclo + '\'' +
                ", horario='" + horario + '\'' +
                '}';
    }
}

