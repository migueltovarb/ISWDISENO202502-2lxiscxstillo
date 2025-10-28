package facultad;

public class Profesor extends Empleado {
    private String departamento;

    public Profesor(String nombre, String apellidos, String identificacion, String estadoCivil, int anioIncorporacion, int numeroDespacho, String departamento) {
        super(nombre, apellidos, identificacion, estadoCivil, anioIncorporacion, numeroDespacho);
        this.departamento = departamento;
    }

    public void cambiarDepartamento(String nuevoDepartamento) {
        this.departamento = nuevoDepartamento;
    }

    @Override
    public void imprimir() {
        super.imprimir();
        System.out.println("Departamento: " + departamento);
    }
}
