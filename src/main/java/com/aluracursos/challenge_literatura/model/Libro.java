package com.aluracursos.challenge_literatura.model;

import com.aluracursos.challenge_literatura.dto.DatosLibro;
import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;
    private String idioma;
    private Integer cantDescargas;

    public Libro() {}

    public Libro(DatosLibro datos) {
        this.titulo = datos.title();
        this.autor = new Autor(datos.authors().get(0));
        this.idioma = datos.languages().get(0);
        this.cantDescargas = datos.download_count();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getCantDescargas() {
        return cantDescargas;
    }

    public void setCantDescargas(Integer cantDescargas) {
        this.cantDescargas = cantDescargas;
    }

    @Override
    public String toString() {
        return "\nTítulo: '" + titulo + '\'' +
                ", autor: " + autor.getNombre() +
                ", idioma: '" + idioma + '\'' +
                ", cantidad de descargas: " + cantDescargas;
    }
}
