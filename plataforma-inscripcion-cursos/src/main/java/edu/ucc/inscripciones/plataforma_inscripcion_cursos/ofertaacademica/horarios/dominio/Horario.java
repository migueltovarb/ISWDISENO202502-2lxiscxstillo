package edu.ucc.inscripciones.plataforma_inscripcion_cursos.ofertaacademica.horarios.dominio;

import edu.ucc.inscripciones.plataforma_inscripcion_cursos.compartido.enums.EstadoHorario;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.DayOfWeek;
import java.time.LocalTime;

/**
 * Representa un horario específico para un curso.
 */
@Document(collection = "horarios")
public class Horario {

    @Id
    private String id;

    @Indexed
    private String idCurso;

    @Indexed
    private String idProfesor;

    private DayOfWeek diaSemana;

    private LocalTime horaInicio;

    private LocalTime horaFin;

    /**
     * Cupo máximo configurado para el horario.
     */
    private int cupoMaximo;

    /**
     * Cupos disponibles calculados y actualizados de forma concurrente.
     */
    private int cuposDisponibles;

    private EstadoHorario estadoHorario = EstadoHorario.ACTIVO;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(String idCurso) {
        this.idCurso = idCurso;
    }

    public String getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(String idProfesor) {
        this.idProfesor = idProfesor;
    }

    public DayOfWeek getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(DayOfWeek diaSemana) {
        this.diaSemana = diaSemana;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(int cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }

    public int getCuposDisponibles() {
        return cuposDisponibles;
    }

    public void setCuposDisponibles(int cuposDisponibles) {
        this.cuposDisponibles = cuposDisponibles;
    }

    public EstadoHorario getEstadoHorario() {
        return estadoHorario;
    }

    public void setEstadoHorario(EstadoHorario estadoHorario) {
        this.estadoHorario = estadoHorario;
    }
}

