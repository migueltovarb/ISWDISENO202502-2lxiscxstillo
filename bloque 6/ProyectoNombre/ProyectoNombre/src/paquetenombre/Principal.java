package paquetenombre;

public class Principal {
    public static void main(String[] args) {
        Direccion persona = new Direccion();

        System.out.println("=== INGRESAR DATOS DE PERSONA ===");
        persona.nuevoNombre();
        persona.nuevaDireccion();

        System.out.println("\n=== INFORMACIÓN COMPLETA ===");
        persona.mostrar();
    }
}
