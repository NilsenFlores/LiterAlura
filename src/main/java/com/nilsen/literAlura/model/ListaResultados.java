package com.nilsen.literAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record ListaResultados(
        @JsonAlias("results") List<DatosLibro> lista
) {
}
