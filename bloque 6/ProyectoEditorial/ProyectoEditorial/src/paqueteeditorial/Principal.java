package paqueteeditorial;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // ======== LIBRO ========
        System.out.println("=== Datos del Libro ===");
        System.out.print("Título: ");
        String tituloLibro = sc.nextLine();
        System.out.print("Precio: ");
        float precioLibro = sc.nextFloat();
        System.out.print("Número de páginas: ");
        int paginas = sc.nextInt();
        System.out.print("Año de publicación: ");
        int anio = sc.nextInt();
        sc.nextLine(); // limpiar buffer

        Libro libro = new Libro(tituloLibro, precioLibro, paginas, anio);

        // ======== DISCO ========
        System.out.println("\n=== Datos del Disco ===");
        System.out.print("Título: ");
        String tituloDisco = sc.nextLine();
        System.out.print("Precio (entero): ");
        int precioDisco = sc.nextInt();
        System.out.print("Duración (minutos): ");
        float duracion = sc.nextFloat();

        Disco disco = new Disco(tituloDisco, precioDisco, duracion);

        // ======== MOSTRAR ========
        System.out.println("\n=== Fichas de Publicaciones ===");
        System.out.println(libro.toString());
        System.out.println(disco.toString());

        sc.close();
    }
}

