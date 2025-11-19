package edu.ucc.inscripciones.ofertaacademica;

import edu.ucc.inscripciones.plataforma_inscripcion_cursos.compartido.enums.EstadoHorario;
import edu.ucc.inscripciones.plataforma_inscripcion_cursos.compartido.excepciones.ExcepcionRecursoNoEncontrado;
import edu.ucc.inscripciones.plataforma_inscripcion_cursos.compartido.excepciones.ExcepcionValidacion;
import edu.ucc.inscripciones.plataforma_inscripcion_cursos.ofertaacademica.horarios.dominio.Horario;
import edu.ucc.inscripciones.plataforma_inscripcion_cursos.ofertaacademica.horarios.repositorio.RepositorioHorario;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

/**
 * Servicio de dominio para la gestión de horarios de cursos.
 */
@Service
public class ServicioHorarios {

    private final RepositorioHorario repositorioHorario;

    public ServicioHorarios(RepositorioHorario repositorioHorario) {
        this.repositorioHorario = repositorioHorario;
    }

    public List<Horario> consultarHorariosActivosPorCurso(String idCurso) {
        return repositorioHorario.findByIdCursoAndEstadoHorario(idCurso, EstadoHorario.ACTIVO);
    }

    public Horario consultarPorId(String idHorario) {
        return repositorioHorario.findById(idHorario)
                .orElseThrow(() -> new ExcepcionRecursoNoEncontrado("Horario no encontrado"));
    }

    @Transactional
    public Horario crearHorario(String idCurso,
                                String idProfesor,
                                DayOfWeek diaSemana,
                                LocalTime horaInicio,
                                LocalTime horaFin,
                                int cupoMaximo) {
        validarHorario(diaSemana, horaInicio, horaFin, cupoMaximo);

        Horario horario = new Horario();
        horario.setIdCurso(idCurso);
        horario.setIdProfesor(idProfesor);
        horario.setDiaSemana(diaSemana);
        horario.setHoraInicio(horaInicio);
        horario.setHoraFin(horaFin);
        horario.setCupoMaximo(cupoMaximo);
        horario.setCuposDisponibles(cupoMaximo);
        horario.setEstadoHorario(EstadoHorario.ACTIVO);
        // TODO validar choques de horarios para profesor y curso

        return repositorioHorario.save(horario);
    }

    @Transactional
    public Horario actualizarHorario(String idHorario,
                                     DayOfWeek diaSemana,
                                     LocalTime horaInicio,
                                     LocalTime horaFin,
                                     int cupoMaximo) {
        Horario horario = consultarPorId(idHorario);
        validarHorario(diaSemana, horaInicio, horaFin, cupoMaximo);

        horario.setDiaSemana(diaSemana);
        horario.setHoraInicio(horaInicio);
        horario.setHoraFin(horaFin);
        horario.setCupoMaximo(cupoMaximo);
        if (horario.getCuposDisponibles() > cupoMaximo) {
            horario.setCuposDisponibles(cupoMaximo);
        }

        return repositorioHorario.save(horario);
    }

    @Transactional
    public Horario cambiarEstadoHorario(String idHorario, EstadoHorario nuevoEstado) {
        Horario horario = consultarPorId(idHorario);
        horario.setEstadoHorario(nuevoEstado);
        return repositorioHorario.save(horario);
    }

    private void validarHorario(DayOfWeek diaSemana,
                                LocalTime horaInicio,
                                LocalTime horaFin,
                                int cupoMaximo) {
        if (diaSemana == null || horaInicio == null || horaFin == null) {
            throw new ExcepcionValidacion("Día y horas del horario son obligatorios");
        }
        if (!horaFin.isAfter(horaInicio)) {
            throw new ExcepcionValidacion("La hora de fin debe ser posterior a la hora de inicio");
        }
        if (cupoMaximo <= 0) {
            throw new ExcepcionValidacion("El cupo máximo debe ser mayor que cero");
        }
    }
}

