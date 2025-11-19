package edu.ucc.inscripciones.plataforma_inscripcion_cursos.inscripciones.repositorio;

import edu.ucc.inscripciones.plataforma_inscripcion_cursos.inscripciones.dominio.Inscripcion;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Repositorio MongoDB para la entidad Inscripcion.
 */
public interface RepositorioInscripcion extends MongoRepository<Inscripcion, String> {

    List<Inscripcion> findByIdHorario(String idHorario);

    boolean existsByIdEstudianteAndIdHorario(String idEstudiante, String idHorario);
}

