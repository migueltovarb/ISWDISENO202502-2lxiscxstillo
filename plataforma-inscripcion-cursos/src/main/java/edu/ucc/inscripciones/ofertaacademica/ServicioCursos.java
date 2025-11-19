package edu.ucc.inscripciones.ofertaacademica;

import edu.ucc.inscripciones.plataforma_inscripcion_cursos.compartido.enums.EstadoCurso;
import edu.ucc.inscripciones.plataforma_inscripcion_cursos.compartido.enums.TipoCurso;
import edu.ucc.inscripciones.plataforma_inscripcion_cursos.compartido.excepciones.ExcepcionRecursoNoEncontrado;
import edu.ucc.inscripciones.plataforma_inscripcion_cursos.compartido.excepciones.ExcepcionValidacion;
import edu.ucc.inscripciones.plataforma_inscripcion_cursos.ofertaacademica.cursos.dominio.Curso;
import edu.ucc.inscripciones.plataforma_inscripcion_cursos.ofertaacademica.cursos.repositorio.RepositorioCurso;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * Servicio de dominio para la gestión de cursos.
 */
@Service
public class ServicioCursos {

    private final RepositorioCurso repositorioCurso;

    public ServicioCursos(RepositorioCurso repositorioCurso) {
        this.repositorioCurso = repositorioCurso;
    }

    public List<Curso> consultarCursosDisponibles() {
        return repositorioCurso.findByEstadoCurso(EstadoCurso.ACTIVO);
    }

    public Curso consultarCursoPorId(String idCurso) {
        return repositorioCurso.findById(idCurso)
                .orElseThrow(() -> new ExcepcionRecursoNoEncontrado("Curso no encontrado"));
    }

    @Transactional
    public Curso crearCurso(String codigo,
                            String nombre,
                            String descripcionGeneral,
                            TipoCurso tipoCurso,
                            BigDecimal valorCursoExtension) {
        validarDatosCurso(codigo, nombre, tipoCurso, valorCursoExtension);

        if (repositorioCurso.existsByCodigo(codigo)) {
            throw new ExcepcionValidacion("Ya existe un curso con el mismo código");
        }

        Curso curso = new Curso();
        curso.setCodigo(codigo);
        curso.setNombre(nombre);
        curso.setDescripcionGeneral(descripcionGeneral);
        curso.setTipoCurso(tipoCurso);
        curso.setValorCursoExtension(valorCursoExtension);
        curso.setEstadoCurso(EstadoCurso.ACTIVO);

        return repositorioCurso.save(curso);
    }

    @Transactional
    public Curso actualizarCurso(String idCurso,
                                 String nombre,
                                 String descripcionGeneral,
                                 TipoCurso tipoCurso,
                                 BigDecimal valorCursoExtension) {
        Curso curso = consultarCursoPorId(idCurso);
        validarDatosCurso(curso.getCodigo(), nombre, tipoCurso, valorCursoExtension);

        curso.setNombre(nombre);
        curso.setDescripcionGeneral(descripcionGeneral);
        curso.setTipoCurso(tipoCurso);
        curso.setValorCursoExtension(valorCursoExtension);

        return repositorioCurso.save(curso);
    }

    @Transactional
    public Curso cambiarEstadoCurso(String idCurso, EstadoCurso nuevoEstado) {
        Curso curso = consultarCursoPorId(idCurso);
        curso.setEstadoCurso(nuevoEstado);
        return repositorioCurso.save(curso);
    }

    private void validarDatosCurso(String codigo,
                                   String nombre,
                                   TipoCurso tipoCurso,
                                   BigDecimal valorCursoExtension) {
        if (!StringUtils.hasText(codigo) || !StringUtils.hasText(nombre) || tipoCurso == null) {
            throw new ExcepcionValidacion("Los datos del curso son obligatorios");
        }
        if (tipoCurso == TipoCurso.EXTENSION) {
            if (valorCursoExtension == null || valorCursoExtension.compareTo(BigDecimal.ZERO) <= 0) {
                throw new ExcepcionValidacion("El valor del curso de extensión debe ser mayor que cero");
            }
        }
    }
}

