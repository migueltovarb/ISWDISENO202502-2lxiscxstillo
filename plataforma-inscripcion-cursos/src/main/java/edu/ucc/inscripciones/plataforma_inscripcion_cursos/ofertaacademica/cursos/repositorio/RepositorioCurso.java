package edu.ucc.inscripciones.plataforma_inscripcion_cursos.ofertaacademica.cursos.repositorio;

import edu.ucc.inscripciones.plataforma_inscripcion_cursos.compartido.enums.EstadoCurso;
import edu.ucc.inscripciones.plataforma_inscripcion_cursos.ofertaacademica.cursos.dominio.Curso;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio MongoDB para la entidad Curso.
 */
public interface RepositorioCurso extends MongoRepository<Curso, String> {

    boolean existsByCodigo(String codigo);

    Optional<Curso> findByCodigo(String codigo);

    List<Curso> findByEstadoCurso(EstadoCurso estadoCurso);
}

