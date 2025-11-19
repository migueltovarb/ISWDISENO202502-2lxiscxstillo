package edu.ucc.inscripciones.plataforma_inscripcion_cursos.profesores.repositorio;

import edu.ucc.inscripciones.plataforma_inscripcion_cursos.profesores.dominio.Profesor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * Repositorio MongoDB para la entidad Profesor.
 */
public interface RepositorioProfesor extends MongoRepository<Profesor, String> {

    boolean existsByIdentificacion(String identificacion);

    boolean existsByCorreoInstitucional(String correoInstitucional);

    Optional<Profesor> findByIdentificacion(String identificacion);
}

