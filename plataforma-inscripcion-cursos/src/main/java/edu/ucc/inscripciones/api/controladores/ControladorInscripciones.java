package edu.ucc.inscripciones.api.controladores;

import edu.ucc.inscripciones.api.dto.PeticionInscripcion;
import edu.ucc.inscripciones.inscripciones.ServicioInscripciones;
import edu.ucc.inscripciones.plataforma_inscripcion_cursos.inscripciones.dominio.Inscripcion;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * API_InscripcionesEstudiante.
 */
@RestController
@RequestMapping("/api/inscripciones")
public class ControladorInscripciones {

    private final ServicioInscripciones servicioInscripciones;

    public ControladorInscripciones(ServicioInscripciones servicioInscripciones) {
        this.servicioInscripciones = servicioInscripciones;
    }

    @PostMapping("/regular")
    @PreAuthorize("hasAuthority('ROLE_ESTUDIANTE')")
    public ResponseEntity<Inscripcion> inscribirCursoRegular(@Valid @RequestBody PeticionInscripcion peticion) {
        return ResponseEntity.ok(
                servicioInscripciones.inscribirEnCursoRegular(peticion.getIdEstudiante(), peticion.getIdHorario())
        );
    }

    @PostMapping("/extension")
    @PreAuthorize("hasAuthority('ROLE_ESTUDIANTE')")
    public ResponseEntity<Inscripcion> inscribirCursoExtension(@Valid @RequestBody PeticionInscripcion peticion) {
        return ResponseEntity.ok(
                servicioInscripciones.inscribirEnCursoExtensionConPago(peticion.getIdEstudiante(), peticion.getIdHorario())
        );
    }
}
