/*//Josselin Nayeli Lanza Ramos 202110020022
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tarea3;

import java.util.ArrayList;
import java.util.Scanner;

class Libro {
    private final String titulo;
    private final String autor;
    private final int anioPublicacion;
    private final String genero;
    private boolean disponibilidad;

    // Constructor
    public Libro(String titulo, String autor, int anioPublicacion, String genero) {
        this.titulo = titulo;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
        this.genero = genero;
        this.disponibilidad = true; // Todos los libros nuevos estan disponibles
    }

    // Metodo para prestar el libro
    public void prestar() {
        if (disponibilidad) {
            disponibilidad = false;
            System.out.println(titulo + " ha sido prestado.");
        } else {
            System.out.println(titulo + " no esta disponible para prestamo.");
        }
    }

    // Metodo para devolver el libro
    public void devolver() {
        disponibilidad = true;
        System.out.println(titulo + " ha sido devuelto.");
    }

    // Metodo para mostrar la información del libro
    public void mostrarInformacion() {
        System.out.println("Titulo: " + titulo);
        System.out.println("Autor: " + autor);
        System.out.println("Año de Publicacion: " + anioPublicacion);
        System.out.println("Genero: " + genero);
        System.out.println("Disponibilidad: " + (disponibilidad? "Disponible" : "Prestado"));
    }

    // Getters y Setters aqui...
    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public boolean isDisponible() {
        return disponibilidad;
    }
}

class Biblioteca {
    private final ArrayList<Libro> libros;

    // Constructor
    public Biblioteca() {
        libros = new ArrayList<>();
    }

    // Metodo para agregar un libro
    public void agregarLibro(Libro libro) {
        libros.add(libro);
        System.out.println(libro.getTitulo() + " ha sido agregado a la biblioteca.");
    }

    // Metodo para buscar libros por titulo o autor
    public void buscarLibro(String busqueda) {
        boolean encontrado = false;
        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(busqueda) || libro.getAutor().equalsIgnoreCase(busqueda)) {
                libro.mostrarInformacion();
                encontrado = true;
                break; // Sale del bucle después de encontrar el primer libro que coincide
            }
        }
        if (!encontrado) {
            System.out.println("Libro no encontrado.");
        }
    }

    // Metodo para prestar un libro
    public void prestarLibro(String titulo) {
        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(titulo) && libro.isDisponible()) {
                libro.prestar();
                return;
            }
        }
        System.out.println("Libro no disponible o no encontrado.");
    }

    // Metodo para devolver un libro
    public void devolverLibro(String titulo) {
        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(titulo) &&!libro.isDisponible()) {
                libro.devolver();
                return;
            }
        }
        System.out.println("Libro no encontrado o ya está disponible.");
    }

    // Metodo para mostrar todos los libros disponibles
    public void mostrarDisponibles() {
        for (Libro libro : libros) {
            if (libro.isDisponible()) {
                libro.mostrarInformacion();
            } else {
            }
        }
    }
}

// Continuación de la clase Main
public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("--¿Que deseas hacer?--");
            System.out.println("1. Agregar libro");
            System.out.println("2. Buscar libro");
            System.out.println("3. Prestar libro");
            System.out.println("4. Devolver libro");
            System.out.println("5. Mostrar libros disponibles");
            System.out.println("6. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consume la nueva linea

            switch (opcion) {
                case 1 -> {
                    System.out.println("Ingrese titulo del libro:");
                    String titulo = scanner.nextLine();
                    System.out.println("Ingrese autor del libro:");
                    String autor = scanner.nextLine();
                    System.out.println("Ingrese año de publicacion del libro:");
                    int anioPublicacion = scanner.nextInt();
                    System.out.println("Ingrese genero del libro:");
                    scanner.nextLine(); // Consume la nueva linea
                    String genero = scanner.nextLine();
                    Libro libro = new Libro(titulo, autor, anioPublicacion, genero);
                    biblioteca.agregarLibro(libro);
                }
                case 2 -> {
                    System.out.println("Ingrese el titulo o autor del libro a buscar:");
                    String busqueda = scanner.nextLine();
                    biblioteca.buscarLibro(busqueda);
                }
                case 3 -> {
                    System.out.println("Ingrese el titulo del libro a prestar:");
                    String tituloPrestar = scanner.nextLine();
                    biblioteca.prestarLibro(tituloPrestar);
                }
                case 4 -> {
                    System.out.println("Ingrese el titulo del libro a devolver:");
                    String tituloDevolver = scanner.nextLine();
                    biblioteca.devolverLibro(tituloDevolver);
                }
                case 5 -> biblioteca.mostrarDisponibles();
                case 6 -> {
                    System.out.println("Saliendo del programa,--Feliz Dia--...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Opcion no valida. Por favor, intente de nuevo.");
            }
        }
    }
}
