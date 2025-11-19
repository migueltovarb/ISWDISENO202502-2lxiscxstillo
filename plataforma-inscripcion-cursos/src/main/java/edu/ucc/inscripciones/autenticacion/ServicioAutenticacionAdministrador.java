package edu.ucc.inscripciones.autenticacion;

import edu.ucc.inscripciones.plataforma_inscripcion_cursos.autenticacion.administradores.repositorio.RepositorioAdministrador;
import edu.ucc.inscripciones.plataforma_inscripcion_cursos.compartido.excepciones.ExcepcionValidacion;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Servicio de dominio para autenticación de administradores.
 */
@Service
public class ServicioAutenticacionAdministrador {

    private final RepositorioAdministrador repositorioAdministrador;
    private final AuthenticationManager authenticationManager;

    public ServicioAutenticacionAdministrador(RepositorioAdministrador repositorioAdministrador,
                                              AuthenticationManager authenticationManager) {
        this.repositorioAdministrador = repositorioAdministrador;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Autentica a un administrador. Siempre devuelve mensajes de error genéricos
     * para no revelar información sensible, alineado con EC005.
     */
    public void autenticarAdministrador(String correoInstitucional,
                                        String contrasena,
                                        HttpServletRequest request) {
        try {
            Authentication autenticacion = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(correoInstitucional, contrasena)
            );
            SecurityContextHolder.getContext().setAuthentication(autenticacion);
            HttpSession sesion = request.getSession(true);
            sesion.setAttribute("USUARIO_AUTENTICADO", correoInstitucional);
        } catch (BadCredentialsException ex) {
            throw new ExcepcionValidacion("Credenciales inválidas");
        }
    }

    /**
     * Cierra la sesión actual del administrador.
     */
    public void cerrarSesion(HttpServletRequest request) {
        HttpSession sesion = request.getSession(false);
        if (sesion != null) {
            sesion.invalidate();
        }
        SecurityContextHolder.clearContext();
    }
}

