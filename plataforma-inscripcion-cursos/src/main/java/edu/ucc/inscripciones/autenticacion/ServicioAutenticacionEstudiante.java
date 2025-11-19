package edu.ucc.inscripciones.autenticacion;

import edu.ucc.inscripciones.plataforma_inscripcion_cursos.autenticacion.estudiantes.dominio.Estudiante;
import edu.ucc.inscripciones.plataforma_inscripcion_cursos.autenticacion.estudiantes.repositorio.RepositorioEstudiante;
import edu.ucc.inscripciones.plataforma_inscripcion_cursos.compartido.excepciones.ExcepcionRecursoNoEncontrado;
import edu.ucc.inscripciones.plataforma_inscripcion_cursos.compartido.excepciones.ExcepcionValidacion;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Servicio de dominio para registro y autenticación de estudiantes.
 */
@Service
public class ServicioAutenticacionEstudiante {

    private static final String DOMINIO_CORREO_INSTITUCIONAL = "@ucc.edu.co";

    private final RepositorioEstudiante repositorioEstudiante;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public ServicioAutenticacionEstudiante(RepositorioEstudiante repositorioEstudiante,
                                           PasswordEncoder passwordEncoder,
                                           AuthenticationManager authenticationManager) {
        this.repositorioEstudiante = repositorioEstudiante;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Registra un nuevo estudiante validando unicidad y formato de correo.
     */
    public Estudiante registrarEstudiante(String nombres,
                                          String apellidos,
                                          String numeroEstudiante,
                                          String correoInstitucional,
                                          String contrasenaPlano) {
        validarDatosRegistro(nombres, apellidos, numeroEstudiante, correoInstitucional, contrasenaPlano);

        if (repositorioEstudiante.existsByNumeroEstudiante(numeroEstudiante)) {
            throw new ExcepcionValidacion("El número de estudiante ya se encuentra registrado");
        }
        if (repositorioEstudiante.existsByCorreoInstitucional(correoInstitucional)) {
            throw new ExcepcionValidacion("El correo institucional ya se encuentra registrado");
        }

        Estudiante estudiante = new Estudiante();
        estudiante.setNombres(nombres);
        estudiante.setApellidos(apellidos);
        estudiante.setNumeroEstudiante(numeroEstudiante);
        estudiante.setCorreoInstitucional(correoInstitucional);
        estudiante.setContrasenaCifrada(passwordEncoder.encode(contrasenaPlano));
        estudiante.setActivo(true);

        return repositorioEstudiante.save(estudiante);
    }

    /**
     * Autentica a un estudiante, creando sesión HTTP gestionada por Spring Session.
     */
    public void autenticarEstudiante(String correoInstitucional,
                                     String contrasena,
                                     HttpServletRequest request) {

        repositorioEstudiante.findByCorreoInstitucional(correoInstitucional)
                .orElseThrow(() -> new ExcepcionRecursoNoEncontrado("El usuario no existe"));

        try {
            Authentication autenticacion = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(correoInstitucional, contrasena)
            );
            SecurityContextHolder.getContext().setAuthentication(autenticacion);
            HttpSession sesion = request.getSession(true);
            sesion.setAttribute("USUARIO_AUTENTICADO", correoInstitucional);
        } catch (BadCredentialsException ex) {
            throw new ExcepcionValidacion("Usuario o contraseña incorrectos");
        }
    }

    /**
     * Cierra la sesión actual del estudiante.
     */
    public void cerrarSesion(HttpServletRequest request) {
        HttpSession sesion = request.getSession(false);
        if (sesion != null) {
            sesion.invalidate();
        }
        SecurityContextHolder.clearContext();
    }

    private void validarDatosRegistro(String nombres,
                                      String apellidos,
                                      String numeroEstudiante,
                                      String correoInstitucional,
                                      String contrasenaPlano) {
        if (!StringUtils.hasText(nombres) || !StringUtils.hasText(apellidos)
                || !StringUtils.hasText(numeroEstudiante)
                || !StringUtils.hasText(correoInstitucional)
                || !StringUtils.hasText(contrasenaPlano)) {
            throw new ExcepcionValidacion("Todos los campos obligatorios deben estar diligenciados");
        }
        if (!correoInstitucional.endsWith(DOMINIO_CORREO_INSTITUCIONAL)) {
            throw new ExcepcionValidacion("El correo debe pertenecer al dominio institucional");
        }
        if (contrasenaPlano.length() < 8) {
            throw new ExcepcionValidacion("La contraseña debe tener al menos 8 caracteres");
        }
    }
}

