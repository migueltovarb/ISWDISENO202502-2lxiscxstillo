package edu.ucc.inscripciones.plataforma_inscripcion_cursos.autenticacion.estudiantes.repositorio;

import edu.ucc.inscripciones.plataforma_inscripcion_cursos.autenticacion.estudiantes.dominio.Estudiante;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * Repositorio MongoDB para la entidad Estudiante.
 */
public interface RepositorioEstudiante extends MongoRepository<Estudiante, String> {

    Optional<Estudiante> findByCorreoInstitucional(String correoInstitucional);

    Optional<Estudiante> findByNumeroEstudiante(String numeroEstudiante);

    boolean existsByCorreoInstitucional(String correoInstitucional);

    boolean existsByNumeroEstudiante(String numeroEstudiante);
}

