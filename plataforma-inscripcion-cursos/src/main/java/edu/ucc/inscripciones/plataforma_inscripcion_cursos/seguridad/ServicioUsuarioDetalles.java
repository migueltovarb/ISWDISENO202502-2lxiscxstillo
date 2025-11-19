package edu.ucc.inscripciones.plataforma_inscripcion_cursos.seguridad;

import edu.ucc.inscripciones.plataforma_inscripcion_cursos.autenticacion.administradores.dominio.Administrador;
import edu.ucc.inscripciones.plataforma_inscripcion_cursos.autenticacion.administradores.repositorio.RepositorioAdministrador;
import edu.ucc.inscripciones.plataforma_inscripcion_cursos.autenticacion.estudiantes.dominio.Estudiante;
import edu.ucc.inscripciones.plataforma_inscripcion_cursos.autenticacion.estudiantes.repositorio.RepositorioEstudiante;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * Adaptador de usuarios de dominio (estudiantes y administradores) a UserDetails de Spring Security.
 */
@Service
public class ServicioUsuarioDetalles implements UserDetailsService {

    private final RepositorioEstudiante repositorioEstudiante;
    private final RepositorioAdministrador repositorioAdministrador;

    public ServicioUsuarioDetalles(RepositorioEstudiante repositorioEstudiante,
                                   RepositorioAdministrador repositorioAdministrador) {
        this.repositorioEstudiante = repositorioEstudiante;
        this.repositorioAdministrador = repositorioAdministrador;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Administrador administrador = repositorioAdministrador.findByCorreoInstitucional(username).orElse(null);
        if (administrador != null && administrador.isActivo()) {
            return construirUsuarioAdministrador(administrador);
        }

        Estudiante estudiante = repositorioEstudiante.findByCorreoInstitucional(username).orElse(null);
        if (estudiante != null && estudiante.isActivo()) {
            return construirUsuarioEstudiante(estudiante);
        }

        throw new UsernameNotFoundException("Usuario no encontrado");
    }

    private UserDetails construirUsuarioAdministrador(Administrador administrador) {
        Collection<? extends GrantedAuthority> autoridades =
                List.of(new SimpleGrantedAuthority("ROLE_ADMINISTRADOR"));
        return new User(administrador.getCorreoInstitucional(),
                administrador.getContrasenaCifrada(),
                administrador.isActivo(), true, true, true,
                autoridades);
    }

    private UserDetails construirUsuarioEstudiante(Estudiante estudiante) {
        Collection<? extends GrantedAuthority> autoridades =
                List.of(new SimpleGrantedAuthority("ROLE_ESTUDIANTE"));
        return new User(estudiante.getCorreoInstitucional(),
                estudiante.getContrasenaCifrada(),
                estudiante.isActivo(), true, true, true,
                autoridades);
    }
}

