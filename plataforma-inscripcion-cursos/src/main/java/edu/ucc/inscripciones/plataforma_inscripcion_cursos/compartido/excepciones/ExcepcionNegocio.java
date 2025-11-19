package edu.ucc.inscripciones.plataforma_inscripcion_cursos.compartido.excepciones;

/**
 * Excepción base para errores de lógica de negocio.
 */
public class ExcepcionNegocio extends RuntimeException {

    public ExcepcionNegocio(String mensaje) {
        super(mensaje);
    }
}

