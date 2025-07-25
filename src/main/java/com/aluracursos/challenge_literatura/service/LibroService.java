package com.aluracursos.challenge_literatura.service;

import com.aluracursos.challenge_literatura.model.Libro;
import com.aluracursos.challenge_literatura.repository.ILibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LibroService {
    @Autowired
    private ILibroRepository repository;

    public void guardar(Libro libro) {
        repository.save(libro);
    }

    public List<Libro> listarLibros() {
        return repository.findAll();
    }

    public List<Libro> buscarLibrosPorIdioma(String idioma) {
        return repository.findByIdioma(idioma);
    }
}
