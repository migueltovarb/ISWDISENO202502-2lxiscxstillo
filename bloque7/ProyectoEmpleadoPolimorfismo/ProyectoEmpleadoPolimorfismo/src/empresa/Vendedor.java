package empresa;

import java.util.ArrayList;
import java.util.List;

public class Vendedor extends Empleado {
    private Coche coche;
    private String telefonoMovil;
    private String areaVenta;
    private List<Cliente> listaClientes;
    private double porcentajeComision;

    public Vendedor(String nombre, String apellidos, String dni, String direccion, String telefono, double salario,
                    Coche coche, String telefonoMovil, String areaVenta, double porcentajeComision) {
        super(nombre, apellidos, dni, direccion, telefono, salario);
        this.coche = coche;
        this.telefonoMovil = telefonoMovil;
        this.areaVenta = areaVenta;
        this.porcentajeComision = porcentajeComision;
        this.listaClientes = new ArrayList<>();
    }

    @Override
    public void imprimir() {
        System.out.println("----- Vendedor -----");
        super.imprimir();
        System.out.println("Coche: " + (coche != null ? coche.toString() : "(sin coche)"));
        System.out.println("Móvil: " + telefonoMovil);
        System.out.println("Área de venta: " + areaVenta);
        System.out.println("Comisión: " + (porcentajeComision * 100) + "%");
        System.out.println("Clientes: " + listaClientes);
        System.out.println();
    }

    public void altaCliente(Cliente cliente) {
        listaClientes.add(cliente);
    }

    public void bajaCliente(Cliente cliente) {
        listaClientes.remove(cliente);
    }

    public void cambiarCoche(Coche coche) {
        this.coche = coche;
    }


    @Override
    public void incrementarSalario() {
        super.incrementarSalario(10.0);
    }
}

