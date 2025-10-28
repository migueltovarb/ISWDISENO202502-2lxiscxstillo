package facultad;

public class Empleado extends Persona {
    protected int anioIncorporacion;
    protected int numeroDespacho;

    public Empleado(String nombre, String apellidos, String identificacion, String estadoCivil, int anioIncorporacion, int numeroDespacho) {
        super(nombre, apellidos, identificacion, estadoCivil);
        this.anioIncorporacion = anioIncorporacion;
        this.numeroDespacho = numeroDespacho;
    }

    public void reasignarDespacho(int nuevoDespacho) {
        this.numeroDespacho = nuevoDespacho;
    }

    @Override
    public void imprimir() {
        super.imprimir();
        System.out.println("Año de incorporación: " + anioIncorporacion);
        System.out.println("Despacho: " + numeroDespacho);
    }
}

