package edu.ucc.inscripciones.plataforma_inscripcion_cursos.compartido.excepciones;

/**
 * Excepción que indica que un horario no tiene cupos disponibles.
 */
public class ExcepcionSinCuposDisponibles extends ExcepcionNegocio {

    public ExcepcionSinCuposDisponibles(String mensaje) {
        super(mensaje);
    }
}

