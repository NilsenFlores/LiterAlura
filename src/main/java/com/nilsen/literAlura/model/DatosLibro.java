package com.nilsen.literAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<DatosAutor> autores,
        @JsonAlias("languages") List<String> idiomas,
        @JsonAlias("download_count") int numeroDescargas)
        {

                @Override
                public String toString() {
                        String autoresNombres = autores.stream()
                                .map(DatosAutor::nombre)
                                .collect(Collectors.joining(", "));

                        return  "\n************************" +
                                "\nTitulo: " + titulo +
                                "\nAutores: " + autoresNombres +
                                "\nIdiomas: " + idiomas +
                                "\nNumero de descargas: " + numeroDescargas
                                +"\n************************";

                }
        }
