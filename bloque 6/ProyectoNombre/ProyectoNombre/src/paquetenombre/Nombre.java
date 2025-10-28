package paquetenombre;

import java.util.Scanner;

public class Nombre {
    private String nombre;
    private String primerApellido;
    private String segundoApellido;

    public Nombre() {
        this.nombre = "";
        this.primerApellido = "";
        this.segundoApellido = "";
    }

    public void LeerNombre() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese nombre: ");
        nombre = sc.nextLine();
        System.out.print("Ingrese primer apellido: ");
        primerApellido = sc.nextLine();
        System.out.print("Ingrese segundo apellido: ");
        segundoApellido = sc.nextLine();
    }

    public void mostrar() {
        System.out.println("Nombre completo: " + nombre + " " + primerApellido + " " + segundoApellido);
    }
}

