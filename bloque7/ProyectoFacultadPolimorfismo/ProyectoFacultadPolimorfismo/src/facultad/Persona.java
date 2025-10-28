package facultad;

public class Persona {
    protected String nombre;
    protected String apellidos;
    protected String identificacion;
    protected String estadoCivil;

    public Persona(String nombre, String apellidos, String identificacion, String estadoCivil) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.identificacion = identificacion;
        this.estadoCivil = estadoCivil;
    }

    public void cambiarEstadoCivil(String nuevoEstado) {
        this.estadoCivil = nuevoEstado;
    }

    public void imprimir() {
        System.out.println("Nombre: " + nombre + " " + apellidos);
        System.out.println("Identificación: " + identificacion);
        System.out.println("Estado civil: " + estadoCivil);
    }
}

