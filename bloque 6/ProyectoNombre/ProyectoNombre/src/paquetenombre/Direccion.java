package paquetenombre;

import java.util.Scanner;

public class Direccion extends Nombre {
    private String calle;
    private String ciudad;
    private String provincia;
    private String codigoPostal;

    public Direccion() {
        super();
        this.calle = "";
        this.ciudad = "";
        this.provincia = "";
        this.codigoPostal = "";
    }

    public void nuevaDireccion() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese calle: ");
        calle = sc.nextLine();
        System.out.print("Ingrese ciudad: ");
        ciudad = sc.nextLine();
        System.out.print("Ingrese provincia: ");
        provincia = sc.nextLine();
        System.out.print("Ingrese código postal: ");
        codigoPostal = sc.nextLine();
    }

    public void nuevoNombre() {
        LeerNombre(); // método heredado de Nombre
    }

    @Override
    public void mostrar() {
        super.mostrar();
        System.out.println("Dirección: " + calle + ", " + ciudad + ", " + provincia + ", " + codigoPostal);
    }
}

