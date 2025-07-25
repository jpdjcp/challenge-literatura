package com.aluracursos.challenge_literatura.repository;

import com.aluracursos.challenge_literatura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IAutorRepository extends JpaRepository<Autor, Long> {

    // native query
    @Query(value = "SELECT * FROM autores WHERE anio_nacimiento < :anio AND anio_muerte > :anio", nativeQuery = true)
    List<Autor> buscarPorAnio(Integer anio);

    // SPQL
    @Query("SELECT a FROM Autor a WHERE a.anioNacimiento < :anio AND a.anioMuerte > :anio")
    List<Autor> findByAnio(Integer anio);

    // derived query
    List<Autor> findByAnioNacimientoLessThanAndAnioMuerteGreaterThan(Integer anio1, Integer anio2);
}
