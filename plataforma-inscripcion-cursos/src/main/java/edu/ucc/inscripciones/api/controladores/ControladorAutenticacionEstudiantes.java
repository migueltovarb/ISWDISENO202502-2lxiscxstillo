package edu.ucc.inscripciones.api.controladores;

import edu.ucc.inscripciones.api.dto.PeticionAutenticacion;
import edu.ucc.inscripciones.api.dto.PeticionRegistroEstudiante;
import edu.ucc.inscripciones.autenticacion.ServicioAutenticacionEstudiante;
import edu.ucc.inscripciones.plataforma_inscripcion_cursos.autenticacion.estudiantes.dominio.Estudiante;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * API_AutenticacionEstudiante: registro e inicio/cierre de sesión de estudiantes.
 */
@RestController
@RequestMapping("/api/autenticacion/estudiantes")
public class ControladorAutenticacionEstudiantes {

    private final ServicioAutenticacionEstudiante servicioAutenticacionEstudiante;

    public ControladorAutenticacionEstudiantes(ServicioAutenticacionEstudiante servicioAutenticacionEstudiante) {
        this.servicioAutenticacionEstudiante = servicioAutenticacionEstudiante;
    }

    @PostMapping("/registro")
    public ResponseEntity<Estudiante> registrar(@Valid @RequestBody PeticionRegistroEstudiante peticion) {
        Estudiante estudiante = servicioAutenticacionEstudiante.registrarEstudiante(
                peticion.getNombres(),
                peticion.getApellidos(),
                peticion.getNumeroEstudiante(),
                peticion.getCorreoInstitucional(),
                peticion.getContrasena()
        );
        return ResponseEntity.ok(estudiante);
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@Valid @RequestBody PeticionAutenticacion peticion,
                                      HttpServletRequest request) {
        servicioAutenticacionEstudiante.autenticarEstudiante(
                peticion.getCorreo(),
                peticion.getContrasena(),
                request
        );
        return ResponseEntity.ok().build();
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        servicioAutenticacionEstudiante.cerrarSesion(request);
        return ResponseEntity.ok().build();
    }
}

