package edu.ucc.inscripciones.api.controladores;

import edu.ucc.inscripciones.api.dto.PeticionProfesor;
import edu.ucc.inscripciones.profesores.ServicioProfesores;
import edu.ucc.inscripciones.plataforma_inscripcion_cursos.compartido.enums.EstadoProfesor;
import edu.ucc.inscripciones.plataforma_inscripcion_cursos.profesores.dominio.Profesor;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * API_GestionProfesoresAdmin.
 */
@RestController
@RequestMapping("/api/profesores")
public class ControladorProfesores {

    private final ServicioProfesores servicioProfesores;

    public ControladorProfesores(ServicioProfesores servicioProfesores) {
        this.servicioProfesores = servicioProfesores;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
    public ResponseEntity<Profesor> registrarProfesor(@Valid @RequestBody PeticionProfesor peticion) {
        Profesor profesor = servicioProfesores.registrarProfesor(
                peticion.getNombres(),
                peticion.getApellidos(),
                peticion.getIdentificacion(),
                peticion.getCorreoInstitucional(),
                peticion.getTelefonoContacto()
        );
        return ResponseEntity.ok(profesor);
    }

    @PutMapping("/{idProfesor}")
    @PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
    public ResponseEntity<Profesor> actualizarProfesor(@PathVariable String idProfesor,
                                                       @Valid @RequestBody PeticionProfesor peticion) {
        Profesor profesor = servicioProfesores.actualizarProfesor(
                idProfesor,
                peticion.getNombres(),
                peticion.getApellidos(),
                peticion.getTelefonoContacto()
        );
        return ResponseEntity.ok(profesor);
    }

    @PatchMapping("/{idProfesor}/estado")
    @PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
    public ResponseEntity<Profesor> cambiarEstadoProfesor(@PathVariable String idProfesor,
                                                          @RequestParam EstadoProfesor estado) {
        return ResponseEntity.ok(servicioProfesores.cambiarEstadoProfesor(idProfesor, estado));
    }
}
