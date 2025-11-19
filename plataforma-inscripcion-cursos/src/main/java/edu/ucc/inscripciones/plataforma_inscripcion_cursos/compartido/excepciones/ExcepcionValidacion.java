package edu.ucc.inscripciones.plataforma_inscripcion_cursos.compartido.excepciones;

/**
 * Excepción para errores de validación de datos de entrada.
 */
public class ExcepcionValidacion extends ExcepcionNegocio {

    public ExcepcionValidacion(String mensaje) {
        super(mensaje);
    }
}

