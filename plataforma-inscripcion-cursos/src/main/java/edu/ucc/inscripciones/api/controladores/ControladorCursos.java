package edu.ucc.inscripciones.api.controladores;

import edu.ucc.inscripciones.api.dto.PeticionCurso;
import edu.ucc.inscripciones.ofertaacademica.ServicioCursos;
import edu.ucc.inscripciones.plataforma_inscripcion_cursos.compartido.enums.EstadoCurso;
import edu.ucc.inscripciones.plataforma_inscripcion_cursos.ofertaacademica.cursos.dominio.Curso;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * API_ConsultaCursos y API_GestionCursosAdmin.
 */
@RestController
@RequestMapping("/api/cursos")
public class ControladorCursos {

    private final ServicioCursos servicioCursos;

    public ControladorCursos(ServicioCursos servicioCursos) {
        this.servicioCursos = servicioCursos;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ESTUDIANTE')")
    public ResponseEntity<List<Curso>> consultarCursosDisponibles() {
        return ResponseEntity.ok(servicioCursos.consultarCursosDisponibles());
    }

    @GetMapping("/{idCurso}")
    @PreAuthorize("hasAuthority('ROLE_ESTUDIANTE')")
    public ResponseEntity<Curso> consultarDetalleCurso(@PathVariable String idCurso) {
        return ResponseEntity.ok(servicioCursos.consultarCursoPorId(idCurso));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
    public ResponseEntity<Curso> crearCurso(@Valid @RequestBody PeticionCurso peticion) {
        Curso curso = servicioCursos.crearCurso(
                peticion.getCodigo(),
                peticion.getNombre(),
                peticion.getDescripcionGeneral(),
                peticion.getTipoCurso(),
                peticion.getValorCursoExtension()
        );
        return ResponseEntity.ok(curso);
    }

    @PutMapping("/{idCurso}")
    @PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
    public ResponseEntity<Curso> actualizarCurso(@PathVariable String idCurso,
                                                 @Valid @RequestBody PeticionCurso peticion) {
        Curso curso = servicioCursos.actualizarCurso(
                idCurso,
                peticion.getNombre(),
                peticion.getDescripcionGeneral(),
                peticion.getTipoCurso(),
                peticion.getValorCursoExtension()
        );
        return ResponseEntity.ok(curso);
    }

    @PatchMapping("/{idCurso}/estado")
    @PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
    public ResponseEntity<Curso> cambiarEstadoCurso(@PathVariable String idCurso,
                                                    @RequestParam EstadoCurso estado) {
        return ResponseEntity.ok(servicioCursos.cambiarEstadoCurso(idCurso, estado));
    }
}
