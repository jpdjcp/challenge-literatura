package com.aluracursos.challenge_literatura.service;

import com.aluracursos.challenge_literatura.model.Autor;
import com.aluracursos.challenge_literatura.repository.IAutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {
    @Autowired
    private IAutorRepository repository;

    public void guardar(Autor autor) {
        repository.save(autor);
    }

    public List<Autor> listarAutores() {
        return repository.findAll();
    }

    public List<Autor> buscarAutoresPorAnio(Integer anio) {
        return repository.findByAnioNacimientoLessThanAndAnioMuerteGreaterThan(anio, anio);
    }
}
