package edu.ucc.inscripciones.api.controladores;

import edu.ucc.inscripciones.api.dto.PeticionAutenticacion;
import edu.ucc.inscripciones.autenticacion.ServicioAutenticacionAdministrador;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * API_AutenticacionAdministrador.
 */
@RestController
@RequestMapping("/api/autenticacion/administradores")
public class ControladorAutenticacionAdministradores {

    private final ServicioAutenticacionAdministrador servicioAutenticacionAdministrador;

    public ControladorAutenticacionAdministradores(ServicioAutenticacionAdministrador servicioAutenticacionAdministrador) {
        this.servicioAutenticacionAdministrador = servicioAutenticacionAdministrador;
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@Valid @RequestBody PeticionAutenticacion peticion,
                                      HttpServletRequest request) {
        servicioAutenticacionAdministrador.autenticarAdministrador(
                peticion.getCorreo(),
                peticion.getContrasena(),
                request
        );
        return ResponseEntity.ok().build();
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        servicioAutenticacionAdministrador.cerrarSesion(request);
        return ResponseEntity.ok().build();
    }
}

