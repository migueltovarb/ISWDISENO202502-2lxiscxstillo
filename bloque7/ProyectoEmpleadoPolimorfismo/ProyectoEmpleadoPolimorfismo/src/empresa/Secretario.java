package empresa;

public class Secretario extends Empleado {
    private String despacho;
    private String numeroFax;

    public Secretario(String nombre, String apellidos, String dni, String direccion, String telefono, double salario,
                      String despacho, String numeroFax) {
        super(nombre, apellidos, dni, direccion, telefono, salario);
        this.despacho = despacho;
        this.numeroFax = numeroFax;
    }

    @Override
    public void imprimir() {
        System.out.println("----- Secretario -----");
        super.imprimir();
        System.out.println("Despacho: " + despacho);
        System.out.println("Fax: " + numeroFax);
        System.out.println();
    }

    @Override
    public void incrementarSalario() {
        super.incrementarSalario(5.0);
    }
}

