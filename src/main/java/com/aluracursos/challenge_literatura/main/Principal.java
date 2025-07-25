package com.aluracursos.challenge_literatura.main;

import com.aluracursos.challenge_literatura.dto.DatosLibro;
import com.aluracursos.challenge_literatura.dto.DatosResultado;
import com.aluracursos.challenge_literatura.model.Autor;
import com.aluracursos.challenge_literatura.model.Libro;
import com.aluracursos.challenge_literatura.service.ApiLibros;
import com.aluracursos.challenge_literatura.service.AutorService;
import com.aluracursos.challenge_literatura.service.JsonToObjectConverter;
import com.aluracursos.challenge_literatura.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Service
public class Principal {
    @Autowired
    private LibroService libroService;
    @Autowired
    private AutorService autorService;
    Scanner teclado = new Scanner(System.in);

    public Principal() {}

    public void mostrarMenu() {
        int eleccion = -1;

        while (eleccion != 0) {
            mostrarOpciones();
            eleccion = teclado.nextInt();
            teclado.nextLine();
            switch (eleccion) {
                case 1 -> buscarLibroPorTitulo();
                case 2 -> listarLibros();
                case 3 -> listarAutores();
                case 4 -> listarAutoresVivos();
                case 5 -> obtenerLibrosPorIdioma();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opcion no reconocida");
            }
        }
        teclado.close();
    }

    private void mostrarOpciones () {
        System.out.println("""
                \nElija la opción a través de su número:
                    1 - buscar libro por título
                    2 - listar libros registrados
                    3 - listar autores registrados
                    4 - listar autores vivos en un determinado año
                    5 - listar libros por idioma
                    
                    0 - salir
                    """);
    }

    private void buscarLibroPorTitulo () {
        System.out.println("\nIngrese el título del libro");
        String nombre = teclado.nextLine();
        String resultadoJson = ApiLibros.buscarLibros(nombre);
        DatosResultado resultado = JsonToObjectConverter.convert(resultadoJson, DatosResultado.class);
        List<DatosLibro> libros = resultado.results();

        if (libros.isEmpty()) {
            System.out.println("\nNo se encontraron libros con el título ingresado");
        } else if (libroRegistrado(libros.get(0).title())) {
            System.out.println("\nEl libro ya se encuentra registrado");
        } else {
            Libro libro = new Libro(libros.get(0));
            Autor autor = libro.getAutor();
            autor.agregarLibro(libro);
            System.out.println("\nLibro encontrado: " + libro);
            autorService.guardar(autor);
            libroService.guardar(libro);
        }
    }

    private void listarLibros () {
        libroService.listarLibros().forEach(System.out::println);
    }

    private boolean libroRegistrado(String titulo) {
        List<Libro> libros = libroService.listarLibros();
        return libros.stream().anyMatch(libro -> libro.getTitulo().equals(titulo));
    }

    private void listarAutores () {
        autorService.listarAutores().forEach(System.out::println);
    }

    private void listarAutoresVivos () {
        System.out.println("\nIngrese el año: ");
        int anio = teclado.nextInt();
        teclado.nextLine();
        System.out.println("\nAutores vivos en el año " + anio);
        autorService.buscarAutoresPorAnio(anio).forEach(System.out::println);
    }

    private void obtenerLibrosPorIdioma() {
        mostrarMenuIdiomas();
        int eleccion = -1;
        String idioma = "";

        while (eleccion < 0 || eleccion > 5) {
            eleccion = teclado.nextInt();
            teclado.nextLine();
            System.out.println("dentro del while");
            switch (eleccion) {
                case 1 -> idioma = "es";
                case 2 -> idioma = "en";
                case 3 -> idioma = "fr";
                case 4 -> idioma = "pt";
                case 5 -> idioma = "it";
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no reconocida");
            }
        }
        filtrarLibrosPorIdioma(idioma);
    }

    private void filtrarLibrosPorIdioma(String idioma) {
        String finalIdioma = idioma;
        List<Libro> libros = libroService.listarLibros();
        List<Libro> librosMismoIdioma =libros.stream()
                .filter(libro -> libro.getIdioma().equals(finalIdioma))
                .collect(Collectors.toList());
        System.out.println("\nSe encontraron " + librosMismoIdioma.size()
                + " libros en el idioma " + finalIdioma + ":\n");
        librosMismoIdioma.forEach(System.out::println);
    }

    private void mostrarMenuIdiomas() {
        System.out.println("""
                \nElija la opción a través de su número:
                    1 - Español (ES)
                    2 - Inglés (EN)
                    3 - Francés (FR)
                    4 - Portugués (PT)
                    5 - Italiano (IT)
                    
                    0 - salir
                    """);
    }
}
