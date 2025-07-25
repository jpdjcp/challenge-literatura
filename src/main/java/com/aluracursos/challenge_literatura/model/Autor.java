package com.aluracursos.challenge_literatura.model;

import com.aluracursos.challenge_literatura.dto.DatosAutor;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Integer anioNacimiento;
    private Integer anioMuerte;
    @OneToMany(mappedBy = "autor")
    private List<Libro> libros;

    public Autor() {}

    public Autor(DatosAutor datos) {
        this.nombre = datos.name();
        this.anioNacimiento = datos.birth_year();
        this.anioMuerte = datos.death_year();
        this.libros = new ArrayList<>();
    }

    public void agregarLibro(Libro libro) {
        this.libros.add(libro);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAnioNacimiento() {
        return anioNacimiento;
    }

    public void setAnioNacimiento(Integer anioNacimiento) {
        this.anioNacimiento = anioNacimiento;
    }

    public Integer getAnioMuerte() {
        return anioMuerte;
    }

    public void setAnioMuerte(Integer anioMuerte) {
        this.anioMuerte = anioMuerte;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    @Override
    public String toString() {
        return nombre + ", año de nacimiento: " + anioNacimiento + ", año de muerte: " + anioMuerte;
    }
}
