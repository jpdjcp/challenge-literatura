package com.aluracursos.challenge_literatura.repository;

import com.aluracursos.challenge_literatura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ILibroRepository extends JpaRepository<Libro, Long> {

    List<Libro> findByIdioma(String idioma);
}
