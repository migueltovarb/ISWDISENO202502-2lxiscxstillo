package edu.ucc.inscripciones.plataforma_inscripcion_cursos.compartido.excepciones;

/**
 * Excepción para indicar que un recurso no fue encontrado.
 */
public class ExcepcionRecursoNoEncontrado extends ExcepcionNegocio {

    public ExcepcionRecursoNoEncontrado(String mensaje) {
        super(mensaje);
    }
}

