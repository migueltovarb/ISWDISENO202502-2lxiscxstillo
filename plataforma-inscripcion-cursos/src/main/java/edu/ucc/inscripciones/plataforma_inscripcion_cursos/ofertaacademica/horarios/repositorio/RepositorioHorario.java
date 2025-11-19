package edu.ucc.inscripciones.plataforma_inscripcion_cursos.ofertaacademica.horarios.repositorio;

import edu.ucc.inscripciones.plataforma_inscripcion_cursos.compartido.enums.EstadoHorario;
import edu.ucc.inscripciones.plataforma_inscripcion_cursos.ofertaacademica.horarios.dominio.Horario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Repositorio MongoDB para la entidad Horario.
 */
public interface RepositorioHorario extends MongoRepository<Horario, String> {

    List<Horario> findByIdCursoAndEstadoHorario(String idCurso, EstadoHorario estadoHorario);
}

