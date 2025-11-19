package edu.ucc.inscripciones.plataforma_inscripcion_cursos.pagos.repositorio;

import edu.ucc.inscripciones.plataforma_inscripcion_cursos.pagos.dominio.TransaccionPago;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repositorio MongoDB para la entidad TransaccionPago.
 */
public interface RepositorioTransaccionPago extends MongoRepository<TransaccionPago, String> {
}

