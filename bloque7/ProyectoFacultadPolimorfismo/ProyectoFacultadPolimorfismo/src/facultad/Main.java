package facultad;

public class Main {
    public static void main(String[] args) {
        Estudiante est = new Estudiante("Laura", "García", "123A", "Soltera", "Matemáticas");
        Profesor prof = new Profesor("Carlos", "Pérez", "456B", "Casado", 2015, 101, "Arquitectura");
        PersonalServicio ps = new PersonalServicio("Ana", "López", "789C", "Casada", 2010, 205, "Biblioteca");

        System.out.println("===== Datos iniciales =====");
        est.imprimir();
        System.out.println();
        prof.imprimir();
        System.out.println();
        ps.imprimir();

        System.out.println("\n===== Cambios =====");
        est.matricularNuevoCurso("Informática");
        prof.cambiarDepartamento("Matemáticas");
        ps.cambiarSeccion("Decanato");
        est.cambiarEstadoCivil("Casada");
        prof.reasignarDespacho(202);

        System.out.println("\n===== Datos actualizados =====");
        est.imprimir();
        System.out.println();
        prof.imprimir();
        System.out.println();
        ps.imprimir();
    }
}
