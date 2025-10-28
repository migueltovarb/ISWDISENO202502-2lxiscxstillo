package facultad;

public class PersonalServicio extends Empleado {
    private String seccion;

    public PersonalServicio(String nombre, String apellidos, String identificacion, String estadoCivil, int anioIncorporacion, int numeroDespacho, String seccion) {
        super(nombre, apellidos, identificacion, estadoCivil, anioIncorporacion, numeroDespacho);
        this.seccion = seccion;
    }

    public void cambiarSeccion(String nuevaSeccion) {
        this.seccion = nuevaSeccion;
    }

    @Override
    public void imprimir() {
        super.imprimir();
        System.out.println("Sección: " + seccion);
    }
}
