package edu.ucc.inscripciones.plataforma_inscripcion_cursos.autenticacion.administradores.repositorio;

import edu.ucc.inscripciones.plataforma_inscripcion_cursos.autenticacion.administradores.dominio.Administrador;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * Repositorio MongoDB para la entidad Administrador.
 */
public interface RepositorioAdministrador extends MongoRepository<Administrador, String> {

    Optional<Administrador> findByCorreoInstitucional(String correoInstitucional);

    boolean existsByCorreoInstitucional(String correoInstitucional);
}

