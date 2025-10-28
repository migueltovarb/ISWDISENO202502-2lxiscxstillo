package facultad;

public class Estudiante extends Persona {
    private String curso;

    public Estudiante(String nombre, String apellidos, String identificacion, String estadoCivil, String curso) {
        super(nombre, apellidos, identificacion, estadoCivil);
        this.curso = curso;
    }

    public void matricularNuevoCurso(String nuevoCurso) {
        this.curso = nuevoCurso;
    }

    @Override
    public void imprimir() {
        super.imprimir();
        System.out.println("Curso matriculado: " + curso);
    }
}

