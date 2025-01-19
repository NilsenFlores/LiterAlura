package com.nilsen.literAlura.repository;

import com.nilsen.literAlura.model.Autor;
import com.nilsen.literAlura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro,Long> {

    @Query("SELECT l FROM Libro l")
    List<Libro> findAllLibros();

    @Query("SELECT a FROM Autor a")
    List<Autor> findAllAutores();

    @Query("SELECT a FROM Autor a WHERE a.fechaNacimiento <= :year AND a.fechaFallecimiento >= :year")
    List<Autor> findAutorByYear(int year);

   // List<Serie> findByGenero(Categoria categoria);

    //@Query("SELECT l FROM Libro l WHERE :idioma IN (l.idiomas)")
    @Query("SELECT l FROM Libro l WHERE :idioma MEMBER OF l.idiomas")
    List<Libro> findLibroIdioma(String idioma);
}