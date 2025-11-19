package edu.ucc.inscripciones.plataforma_inscripcion_cursos.inscripciones.dominio;

import edu.ucc.inscripciones.plataforma_inscripcion_cursos.compartido.enums.EstadoInscripcion;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * Representa la inscripción de un estudiante a un curso en un horario específico.
 */
@Document(collection = "inscripciones")
public class Inscripcion {

    @Id
    private String id;

    @Indexed
    private String idEstudiante;

    @Indexed
    private String idCurso;

    @Indexed
    private String idHorario;

    private String idTransaccionPago;

    private LocalDateTime fechaInscripcion;

    private EstadoInscripcion estadoInscripcion;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(String idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(String idCurso) {
        this.idCurso = idCurso;
    }

    public String getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(String idHorario) {
        this.idHorario = idHorario;
    }

    public String getIdTransaccionPago() {
        return idTransaccionPago;
    }

    public void setIdTransaccionPago(String idTransaccionPago) {
        this.idTransaccionPago = idTransaccionPago;
    }

    public LocalDateTime getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDateTime fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public EstadoInscripcion getEstadoInscripcion() {
        return estadoInscripcion;
    }

    public void setEstadoInscripcion(EstadoInscripcion estadoInscripcion) {
        this.estadoInscripcion = estadoInscripcion;
    }
}

