package com.nilsen.literAlura.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "libro_autores",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Autor> autores;
    private int numeroDescargas;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "idiomas_libro", joinColumns = @JoinColumn(name = "libro_id"))
    @Column(name = "idioma")
    private List<String> idiomas;

    public Libro() {
    }

    public Libro(List<String> idiomas, int numeroDescargas, List<Autor> autores, String titulo) {
        this.idiomas = idiomas;
        this.numeroDescargas = numeroDescargas;
        this.autores = autores;
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        String autoresNombres = autores.stream()
                .map(Autor::getNombre)
                .collect(Collectors.joining(", "));
        String idiomasSN = String.join(", ", idiomas);
        return  "\n************************" +
                "\nTitulo: " + titulo +
                "\nAutores: " + autoresNombres +
                "\nIdiomas: " + idiomasSN +
                "\nNumero de descargas: " + numeroDescargas
                +"\n************************";
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public int getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(int numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
