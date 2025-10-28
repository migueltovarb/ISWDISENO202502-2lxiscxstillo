package empresa;

public class Empleado {
    protected String nombre;
    protected String apellidos;
    protected String dni;
    protected String direccion;
    protected int antiguedad;    // según el UML textual
    protected String telefono;
    protected double salario;
    protected Empleado supervisor;

    public Empleado(String nombre, String apellidos, String dni, String direccion, String telefono, double salario) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.direccion = direccion;
        this.telefono = telefono;
        this.salario = salario;
        this.antiguedad = 0;
        this.supervisor = null;
    }

    public void imprimir() {
        System.out.println("Empleado: " + nombre + " " + apellidos);
        System.out.println("DNI: " + dni);
        System.out.println("Dirección: " + direccion);
        System.out.println("Teléfono: " + telefono);
        System.out.println("Antigüedad: " + antiguedad + " años");
        System.out.println("Salario: " + salario);
        if (supervisor != null) {
            System.out.println("Supervisor: " + supervisor.nombre + " " + supervisor.apellidos);
        } else {
            System.out.println("Supervisor: (ninguno)");
        }
        System.out.println();
    }

    public void cambiarSupervisor(Empleado supervisor) {
        this.supervisor = supervisor;
    }

    public void incrementarSalario() {
        incrementarSalario(0);
    }

    protected void incrementarSalario(double porcentaje) {
        if (porcentaje > 0) {
            this.salario += this.salario * (porcentaje / 100.0);
        }
    }
}

