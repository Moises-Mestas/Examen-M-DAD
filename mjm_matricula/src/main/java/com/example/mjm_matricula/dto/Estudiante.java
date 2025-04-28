package com.example.mjm_matricula.dto;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Clave primaria
    private String nombre;  // Nombre del estudiante
    private String dni;  // Documento único (DNI)
    private String carrera;  // Carrera del estudiante
    private String estado;  // Estado del estudiante (activo, inactivo)
    private String ciclo;  // Ciclo actual (2023-1, 2023-2, etc.)

    // Constructor con parámetros
    public Estudiante(Long id, String nombre, String dni, String carrera, String estado, String ciclo) {
        this.id = id;
        this.nombre = nombre;
        this.dni = dni;
        this.carrera = carrera;
        this.estado = estado;
        this.ciclo = ciclo;
    }

    // Constructor vacío
    public Estudiante() {
    }

    // Métodos getters y setters (opcionales si usas Lombok)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
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

    // Método toString para representar el estudiante como string
    @Override
    public String toString() {
        return "Estudiante{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", dni='" + dni + '\'' +
                ", carrera='" + carrera + '\'' +
                ", estado='" + estado + '\'' +
                ", ciclo='" + ciclo + '\'' +
                '}';
    }
}
