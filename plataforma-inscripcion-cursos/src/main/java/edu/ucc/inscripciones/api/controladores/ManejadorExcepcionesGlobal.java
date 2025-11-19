package edu.ucc.inscripciones.api.controladores;

import edu.ucc.inscripciones.plataforma_inscripcion_cursos.compartido.excepciones.ExcepcionNegocio;
import edu.ucc.inscripciones.plataforma_inscripcion_cursos.compartido.excepciones.ExcepcionRecursoNoEncontrado;
import edu.ucc.inscripciones.plataforma_inscripcion_cursos.compartido.excepciones.ExcepcionValidacion;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Manejo centralizado de errores para las APIs REST.
 */
@ControllerAdvice
public class ManejadorExcepcionesGlobal {

    @ExceptionHandler(ExcepcionRecursoNoEncontrado.class)
    public ResponseEntity<Map<String, String>> manejarNoEncontrado(ExcepcionRecursoNoEncontrado ex) {
        return construirRespuesta(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler({ExcepcionValidacion.class})
    public ResponseEntity<Map<String, String>> manejarValidacionNegocio(ExcepcionNegocio ex) {
        return construirRespuesta(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> manejarErroresValidacion(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errores.put(error.getField(), error.getDefaultMessage());
        }
        return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> manejarErrorGenerico(Exception ex) {
        return construirRespuesta(HttpStatus.INTERNAL_SERVER_ERROR, "Se ha producido un error en el sistema");
    }

    private ResponseEntity<Map<String, String>> construirRespuesta(HttpStatus estado, String mensaje) {
        Map<String, String> cuerpo = new HashMap<>();
        cuerpo.put("mensaje", mensaje);
        return new ResponseEntity<>(cuerpo, estado);
    }
}

