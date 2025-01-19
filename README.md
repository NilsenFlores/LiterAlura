# LiterAlura
Challenge | Oracle Next Education

## Descripción
**LiterAlura** es una aplicación que permite buscar libros mediante una API pública llamada **Gutendex**, registrar los libros encontrados en una base de datos, y consultar información sobre libros y autores a través de un sistema de gestión de base de datos. El sistema también permite realizar búsquedas filtradas por idioma y año de vida de los autores.

## Funcionalidades
El proyecto ofrece las siguientes opciones al usuario:
1. **Buscar y registrar libro por título**: Permite buscar un libro utilizando su título y lo registra en la base de datos si se encuentra.
2. **Ver libros registrados**: Muestra todos los libros que han sido registrados en la base de datos.
3. **Ver autores registrados**: Muestra todos los autores registrados en la base de datos.
4. **Filtrar autores vivos en un año determinado**: Permite consultar los autores que estaban vivos en un año específico.
5. **Filtrar libros por idioma**: Permite buscar libros filtrados por idioma.

## Tecnologías
- **Spring Framework**: Para la gestión de la aplicación y la base de datos.
- **PostgreSQL**: Base de datos utilizada para almacenar los libros y autores registrados.
- **Jackson Databind**: Para manejar la conversión entre los datos JSON recibidos de la API y los objetos Java.
- **JPQL (Java Persistence Query Language)**: Para realizar consultas a la base de datos.

## A considerar
- No olvides crear tu base de datos y colocar los datos correpondietes en **application.properties**
