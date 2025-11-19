package edu.ucc.inscripciones.api.controladores;

import edu.ucc.inscripciones.api.dto.PeticionHorario;
import edu.ucc.inscripciones.ofertaacademica.ServicioHorarios;
import edu.ucc.inscripciones.plataforma_inscripcion_cursos.compartido.enums.EstadoHorario;
import edu.ucc.inscripciones.plataforma_inscripcion_cursos.ofertaacademica.horarios.dominio.Horario;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

/**
 * API_ConsultaHorariosYCupos y API_GestionHorariosAdmin.
 */
@RestController
@RequestMapping("/api/horarios")
public class ControladorHorarios {

    private final ServicioHorarios servicioHorarios;

    public ControladorHorarios(ServicioHorarios servicioHorarios) {
        this.servicioHorarios = servicioHorarios;
    }

    @GetMapping("/curso/{idCurso}")
    @PreAuthorize("hasAuthority('ROLE_ESTUDIANTE')")
    public ResponseEntity<List<Horario>> consultarHorariosPorCurso(@PathVariable String idCurso) {
        return ResponseEntity.ok(servicioHorarios.consultarHorariosActivosPorCurso(idCurso));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
    public ResponseEntity<Horario> crearHorario(@Valid @RequestBody PeticionHorario peticion) {
        Horario horario = servicioHorarios.crearHorario(
                peticion.getIdCurso(),
                peticion.getIdProfesor(),
                DayOfWeek.valueOf(peticion.getDiaSemana()),
                LocalTime.parse(peticion.getHoraInicio()),
                LocalTime.parse(peticion.getHoraFin()),
                peticion.getCupoMaximo()
        );
        return ResponseEntity.ok(horario);
    }

    @PutMapping("/{idHorario}")
    @PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
    public ResponseEntity<Horario> actualizarHorario(@PathVariable String idHorario,
                                                     @Valid @RequestBody PeticionHorario peticion) {
        Horario horario = servicioHorarios.actualizarHorario(
                idHorario,
                DayOfWeek.valueOf(peticion.getDiaSemana()),
                LocalTime.parse(peticion.getHoraInicio()),
                LocalTime.parse(peticion.getHoraFin()),
                peticion.getCupoMaximo()
        );
        return ResponseEntity.ok(horario);
    }

    @PatchMapping("/{idHorario}/estado")
    @PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
    public ResponseEntity<Horario> cambiarEstadoHorario(@PathVariable String idHorario,
                                                        @RequestParam EstadoHorario estado) {
        return ResponseEntity.ok(servicioHorarios.cambiarEstadoHorario(idHorario, estado));
    }
}
