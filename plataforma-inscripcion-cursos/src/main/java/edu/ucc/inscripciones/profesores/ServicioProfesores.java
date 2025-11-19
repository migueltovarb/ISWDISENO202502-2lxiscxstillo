package edu.ucc.inscripciones.profesores;

import edu.ucc.inscripciones.plataforma_inscripcion_cursos.compartido.enums.EstadoProfesor;
import edu.ucc.inscripciones.plataforma_inscripcion_cursos.compartido.excepciones.ExcepcionRecursoNoEncontrado;
import edu.ucc.inscripciones.plataforma_inscripcion_cursos.compartido.excepciones.ExcepcionValidacion;
import edu.ucc.inscripciones.plataforma_inscripcion_cursos.profesores.dominio.Profesor;
import edu.ucc.inscripciones.plataforma_inscripcion_cursos.profesores.repositorio.RepositorioProfesor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * Servicio de dominio para la gestión de profesores.
 */
@Service
public class ServicioProfesores {

    private final RepositorioProfesor repositorioProfesor;

    public ServicioProfesores(RepositorioProfesor repositorioProfesor) {
        this.repositorioProfesor = repositorioProfesor;
    }

    public Profesor consultarPorId(String idProfesor) {
        return repositorioProfesor.findById(idProfesor)
                .orElseThrow(() -> new ExcepcionRecursoNoEncontrado("Profesor no encontrado"));
    }

    @Transactional
    public Profesor registrarProfesor(String nombres,
                                      String apellidos,
                                      String identificacion,
                                      String correoInstitucional,
                                      String telefonoContacto) {
        validarDatosProfesor(nombres, apellidos, identificacion, correoInstitucional);

        if (repositorioProfesor.existsByIdentificacion(identificacion)) {
            throw new ExcepcionValidacion("Ya existe un profesor con la misma identificación");
        }
        if (repositorioProfesor.existsByCorreoInstitucional(correoInstitucional)) {
            throw new ExcepcionValidacion("Ya existe un profesor con el mismo correo institucional");
        }

        Profesor profesor = new Profesor();
        profesor.setNombres(nombres);
        profesor.setApellidos(apellidos);
        profesor.setIdentificacion(identificacion);
        profesor.setCorreoInstitucional(correoInstitucional);
        profesor.setTelefonoContacto(telefonoContacto);
        profesor.setEstadoProfesor(EstadoProfesor.ACTIVO);

        return repositorioProfesor.save(profesor);
    }

    @Transactional
    public Profesor actualizarProfesor(String idProfesor,
                                       String nombres,
                                       String apellidos,
                                       String telefonoContacto) {
        Profesor profesor = consultarPorId(idProfesor);
        if (!StringUtils.hasText(nombres) || !StringUtils.hasText(apellidos)) {
            throw new ExcepcionValidacion("Los nombres y apellidos son obligatorios");
        }
        profesor.setNombres(nombres);
        profesor.setApellidos(apellidos);
        profesor.setTelefonoContacto(telefonoContacto);
        return repositorioProfesor.save(profesor);
    }

    @Transactional
    public Profesor cambiarEstadoProfesor(String idProfesor, EstadoProfesor nuevoEstado) {
        Profesor profesor = consultarPorId(idProfesor);
        profesor.setEstadoProfesor(nuevoEstado);
        return repositorioProfesor.save(profesor);
    }

    private void validarDatosProfesor(String nombres,
                                      String apellidos,
                                      String identificacion,
                                      String correoInstitucional) {
        if (!StringUtils.hasText(nombres) || !StringUtils.hasText(apellidos)
                || !StringUtils.hasText(identificacion) || !StringUtils.hasText(correoInstitucional)) {
            throw new ExcepcionValidacion("Todos los datos del profesor son obligatorios");
        }
    }
}

