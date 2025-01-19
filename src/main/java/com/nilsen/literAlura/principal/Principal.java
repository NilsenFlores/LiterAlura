package com.nilsen.literAlura.principal;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.nilsen.literAlura.model.*;
import com.nilsen.literAlura.repository.LibroRepository;
import com.nilsen.literAlura.service.ConvierteDatos;
import com.nilsen.literAlura.service.HttpAPIRequest;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private final String URL_BASE = "https://gutendex.com/books/";
    private final HttpAPIRequest request = new HttpAPIRequest();
    private final Scanner scanner = new Scanner(System.in);
    private ConvierteDatos conversor = new ConvierteDatos();

    private LibroRepository libroRepository;

    public Principal(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }


    public void menu() {
        System.out.println("""
                               M E N U
                1) Buscar y registrar libro por titulo
                2) Libros registrados
                3) Autores registrados
                4) Autores vivos en un determinado año
                5) Filtrar libros por idioma
                
                6) Salir
                """);


    }

    @Transactional
    public void seleccionOpcion(int opcion) {
            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el titulo del libro que desea buscar:");
                    String titulo = scanner.nextLine();

                    String url = URL_BASE + "?search=" + titulo.replace(" ", "%20");
                    var json = request.solicitarDatos(url);
                    var datos = conversor.obtenerDatos(json, ListaResultados.class);

                    Optional<DatosLibro> libroEncontrado = datos.lista().stream()
                            .filter(l -> l.titulo().toLowerCase().contains(titulo.toLowerCase()))
                            .findFirst();

                    if (libroEncontrado.isPresent()) {
                        var libroE = libroEncontrado.get();
                        var autoresEncontrados = libroE.autores().stream()
                                .map(a -> new Autor(a.fechaDeNacimiento(), a.fechaFallecimiento(), a.nombre()))
                                .collect(Collectors.toList());

                        Libro libro = new Libro(libroE.idiomas(), libroE.numeroDescargas(), autoresEncontrados, libroE.titulo());
                        System.out.println(libro);
                        libroRepository.save(libro);
                    } else {
                        System.out.println("\nUps, libro no encontrado\n");
                    }
                    break;

                case 2:
                    List<Libro> librosRegistrados = libroRepository.findAllLibros();
                    if(librosRegistrados != null) {
                        System.out.print("\nL I B R O S   R E G I S T R A D O S");
                        librosRegistrados.forEach(System.out::print);
                        System.out.println();
                    }else{
                        System.out.println("\nSin libros registrados\n");
                    }
                    break;

                case 3:
                    List<Autor> autoresRegistrados = libroRepository.findAllAutores();
                    if(autoresRegistrados != null) {
                        System.out.print("\nA U T O R E S   R E G I S T R A D O S");
                        autoresRegistrados.forEach(System.out::print);
                        System.out.println();
                    }else{
                        System.out.println("\nSin autores registrados\n");
                    }
                    break;

                case 4:
                    var year = ingresarOpcionNumerica(1);
                    List<Autor> autoresFecha = libroRepository.findAutorByYear(year);
                    if(autoresFecha != null) {
                        System.out.print("\nA U T O R E S   E N C O N T R A D O S");
                        System.out.println("\t\tPor año: " + year);
                        autoresFecha.forEach(System.out::print);
                        System.out.println();
                    }else{
                        System.out.println("No se encontraron autores");
                    }
                    break;

                case 5:
                    System.out.println("""
                            Introduzca el numero de acuerdo al idioma por el que desea consultar los libros:
                            1) Español
                            2) Inglés
                            3) Francés
                            4) Portugués
                            5) Otro
                            """);
                    String idioma = null;
                    while(idioma == null) {

                        int option = ingresarOpcionNumerica();
                        switch (option) {
                            case 1:
                                idioma = "es";
                                break;
                            case 2:
                                idioma = "en";
                                break;
                            case 3:
                                idioma = "fr";
                                break;
                            case 4:
                                idioma = "pt";
                                break;
                            case 5:
                                System.out.println("Ingrese el código ISO 639-1 del idioma que desea buscar");
                                idioma = scanner.nextLine().toLowerCase();
                                break;
                            default:
                                System.out.println("Valor invalido");
                                break;
                        }
                    }


                    List<Libro> librosEncontrados = libroRepository.findLibroIdioma(idioma);
                    if (!librosEncontrados.isEmpty()) {
                        System.out.println("L I B R O S   R E G I S T R A D O S");
                        System.out.println("\t\tPor idioma: " + idioma);
                        librosEncontrados.forEach(System.out::println);
                    }else{
                        System.out.println("No se encontraron libros");
                    }
                    break;

                default:
                    System.out.println("Valor invalido");

            }


    }
    public int ingresarOpcionNumerica(){
        while(true) {
            try {
                System.out.println("Ingrese el numero de su opcion");
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Valor no reconocido | " + e.getMessage() + "\n");

            }
        }
    }

    public int ingresarOpcionNumerica(int varlid){
        while(true) {
            try {
                System.out.println("Introduzca el año: ");
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Valor no reconocido | " + e.getMessage() + "\n");

            }
        }
    }
}
