package edu.ucc.inscripciones.inscripciones;

import edu.ucc.inscripciones.autenticacion.ServicioAutenticacionEstudiante;
import edu.ucc.inscripciones.ofertaacademica.ServicioCursos;
import edu.ucc.inscripciones.ofertaacademica.ServicioHorarios;
import edu.ucc.inscripciones.pagos.ServicioPagosOnline;
import edu.ucc.inscripciones.plataforma_inscripcion_cursos.autenticacion.estudiantes.dominio.Estudiante;
import edu.ucc.inscripciones.plataforma_inscripcion_cursos.autenticacion.estudiantes.repositorio.RepositorioEstudiante;
import edu.ucc.inscripciones.plataforma_inscripcion_cursos.compartido.enums.EstadoCurso;
import edu.ucc.inscripciones.plataforma_inscripcion_cursos.compartido.enums.EstadoHorario;
import edu.ucc.inscripciones.plataforma_inscripcion_cursos.compartido.enums.EstadoInscripcion;
import edu.ucc.inscripciones.plataforma_inscripcion_cursos.compartido.enums.EstadoTransaccionPago;
import edu.ucc.inscripciones.plataforma_inscripcion_cursos.compartido.enums.TipoCurso;
import edu.ucc.inscripciones.plataforma_inscripcion_cursos.compartido.excepciones.ExcepcionRecursoNoEncontrado;
import edu.ucc.inscripciones.plataforma_inscripcion_cursos.compartido.excepciones.ExcepcionSinCuposDisponibles;
import edu.ucc.inscripciones.plataforma_inscripcion_cursos.compartido.excepciones.ExcepcionValidacion;
import edu.ucc.inscripciones.plataforma_inscripcion_cursos.inscripciones.dominio.Inscripcion;
import edu.ucc.inscripciones.plataforma_inscripcion_cursos.inscripciones.repositorio.RepositorioInscripcion;
import edu.ucc.inscripciones.plataforma_inscripcion_cursos.notificacionescorreo.servicio.ServicioNotificacionesCorreo;
import edu.ucc.inscripciones.plataforma_inscripcion_cursos.ofertaacademica.cursos.dominio.Curso;
import edu.ucc.inscripciones.plataforma_inscripcion_cursos.ofertaacademica.cursos.repositorio.RepositorioCurso;
import edu.ucc.inscripciones.plataforma_inscripcion_cursos.ofertaacademica.horarios.dominio.Horario;
import edu.ucc.inscripciones.plataforma_inscripcion_cursos.ofertaacademica.horarios.repositorio.RepositorioHorario;
import edu.ucc.inscripciones.plataforma_inscripcion_cursos.pagos.dominio.TransaccionPago;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Servicio de dominio para gestionar inscripciones de estudiantes.
 */
@Service
public class ServicioInscripciones {

    private final RepositorioInscripcion repositorioInscripcion;
    private final RepositorioEstudiante repositorioEstudiante;
    private final RepositorioCurso repositorioCurso;
    private final RepositorioHorario repositorioHorario;
    private final ServicioPagosOnline servicioPagosOnline;
    private final ServicioNotificacionesCorreo servicioNotificacionesCorreo;
    private final MongoTemplate mongoTemplate;

    public ServicioInscripciones(RepositorioInscripcion repositorioInscripcion,
                                 RepositorioEstudiante repositorioEstudiante,
                                 RepositorioCurso repositorioCurso,
                                 RepositorioHorario repositorioHorario,
                                 ServicioPagosOnline servicioPagosOnline,
                                 ServicioNotificacionesCorreo servicioNotificacionesCorreo,
                                 MongoTemplate mongoTemplate) {
        this.repositorioInscripcion = repositorioInscripcion;
        this.repositorioEstudiante = repositorioEstudiante;
        this.repositorioCurso = repositorioCurso;
        this.repositorioHorario = repositorioHorario;
        this.servicioPagosOnline = servicioPagosOnline;
        this.servicioNotificacionesCorreo = servicioNotificacionesCorreo;
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * Inscribe a un estudiante en un curso regular, validando cupos y estados.
     */
    @Transactional
    public Inscripcion inscribirEnCursoRegular(String idEstudiante, String idHorario) {
        Estudiante estudiante = repositorioEstudiante.findById(idEstudiante)
                .orElseThrow(() -> new ExcepcionRecursoNoEncontrado("Estudiante no encontrado"));
        Horario horario = repositorioHorario.findById(idHorario)
                .orElseThrow(() -> new ExcepcionRecursoNoEncontrado("Horario no encontrado"));
        Curso curso = repositorioCurso.findById(horario.getIdCurso())
                .orElseThrow(() -> new ExcepcionRecursoNoEncontrado("Curso no encontrado"));

        if (curso.getEstadoCurso() != EstadoCurso.ACTIVO || horario.getEstadoHorario() != EstadoHorario.ACTIVO) {
            throw new ExcepcionValidacion("El curso u horario no se encuentra activo");
        }
        if (curso.getTipoCurso() != TipoCurso.REGULAR) {
            throw new ExcepcionValidacion("El curso no es de tipo regular");
        }
        if (repositorioInscripcion.existsByIdEstudianteAndIdHorario(idEstudiante, idHorario)) {
            throw new ExcepcionValidacion("El estudiante ya está inscrito en este horario");
        }

        decrementarCupoDisponible(horario.getId());

        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setIdEstudiante(estudiante.getId());
        inscripcion.setIdCurso(curso.getId());
        inscripcion.setIdHorario(horario.getId());
        inscripcion.setFechaInscripcion(LocalDateTime.now());
        inscripcion.setEstadoInscripcion(EstadoInscripcion.CONFIRMADA);

        Inscripcion guardada = repositorioInscripcion.save(inscripcion);
        servicioNotificacionesCorreo.enviarConfirmacionInscripcion(estudiante, curso, horario);
        return guardada;
    }

    /**
     * Inscribe a un estudiante en un curso de extensión, procesando el pago.
     */
    @Transactional
    public Inscripcion inscribirEnCursoExtensionConPago(String idEstudiante, String idHorario) {
        Estudiante estudiante = repositorioEstudiante.findById(idEstudiante)
                .orElseThrow(() -> new ExcepcionRecursoNoEncontrado("Estudiante no encontrado"));
        Horario horario = repositorioHorario.findById(idHorario)
                .orElseThrow(() -> new ExcepcionRecursoNoEncontrado("Horario no encontrado"));
        Curso curso = repositorioCurso.findById(horario.getIdCurso())
                .orElseThrow(() -> new ExcepcionRecursoNoEncontrado("Curso no encontrado"));

        if (curso.getEstadoCurso() != EstadoCurso.ACTIVO || horario.getEstadoHorario() != EstadoHorario.ACTIVO) {
            throw new ExcepcionValidacion("El curso u horario no se encuentra activo");
        }
        if (curso.getTipoCurso() != TipoCurso.EXTENSION) {
            throw new ExcepcionValidacion("El curso no es de tipo extensión");
        }
        if (repositorioInscripcion.existsByIdEstudianteAndIdHorario(idEstudiante, idHorario)) {
            throw new ExcepcionValidacion("El estudiante ya está inscrito en este horario");
        }

        TransaccionPago transaccionPago = servicioPagosOnline.procesarPago(
                estudiante.getId(),
                curso.getId(),
                curso.getValorCursoExtension()
        );

        if (transaccionPago.getEstadoTransaccionPago() != EstadoTransaccionPago.APROBADA) {
            throw new ExcepcionValidacion("El pago fue rechazado");
        }

        decrementarCupoDisponible(horario.getId());

        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setIdEstudiante(estudiante.getId());
        inscripcion.setIdCurso(curso.getId());
        inscripcion.setIdHorario(horario.getId());
        inscripcion.setFechaInscripcion(LocalDateTime.now());
        inscripcion.setEstadoInscripcion(EstadoInscripcion.CONFIRMADA);
        inscripcion.setIdTransaccionPago(transaccionPago.getId());

        Inscripcion guardada = repositorioInscripcion.save(inscripcion);
        servicioNotificacionesCorreo.enviarConfirmacionInscripcion(estudiante, curso, horario);
        return guardada;
    }

    /**
     * Disminuye de forma atómica un cupo disponible en un horario.
     * Si no hay cupos disponibles, lanza una excepción de negocio.
     */
    private void decrementarCupoDisponible(String idHorario) {
        Query query = new Query()
                .addCriteria(Criteria.where("id").is(idHorario))
                .addCriteria(Criteria.where("cuposDisponibles").gt(0));
        Update update = new Update().inc("cuposDisponibles", -1);
        Horario horarioActualizado = mongoTemplate.findAndModify(query, update, Horario.class);
        if (horarioActualizado == null) {
            throw new ExcepcionSinCuposDisponibles("Sin cupos disponibles para el horario seleccionado");
        }
    }
}

