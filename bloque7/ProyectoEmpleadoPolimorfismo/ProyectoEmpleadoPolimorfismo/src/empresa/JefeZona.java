package empresa;

import java.util.ArrayList;
import java.util.List;

public class JefeZona extends Empleado {
    private String despacho;
    private Secretario secretario;
    private List<Vendedor> listaVendedores;
    private Coche coche;

    public JefeZona(String nombre, String apellidos, String dni, String direccion, String telefono, double salario,
                    String despacho, Coche coche, Secretario secretario) {
        super(nombre, apellidos, dni, direccion, telefono, salario);
        this.despacho = despacho;
        this.coche = coche;
        this.secretario = secretario;
        this.listaVendedores = new ArrayList<>();
    }

    @Override
    public void imprimir() {
        System.out.println("----- Jefe de Zona -----");
        super.imprimir();
        System.out.println("Despacho: " + despacho);
        System.out.println("Secretario: " + (secretario != null ? (secretario.nombre + " " + secretario.apellidos) : "(ninguno)"));
        System.out.println("Coche: " + (coche != null ? coche.toString() : "(sin coche)"));
        System.out.println("Vendedores a cargo: " + listaVendedores.size());
        System.out.println();
    }

    public void cambiarSecretario(Secretario secretario) {
        this.secretario = secretario;
    }

    public void cambiarCoche(Coche coche) {
        this.coche = coche;
    }

    public void altaVendedor(Vendedor vendedor) {
        listaVendedores.add(vendedor);
    }

    public void bajaVendedor(Vendedor vendedor) {
        listaVendedores.remove(vendedor);
    }

    @Override
    public void incrementarSalario() {
        super.incrementarSalario(20.0);
    }
}
