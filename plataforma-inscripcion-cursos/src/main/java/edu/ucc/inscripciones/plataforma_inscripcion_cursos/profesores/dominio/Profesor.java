package edu.ucc.inscripciones.plataforma_inscripcion_cursos.profesores.dominio;

import edu.ucc.inscripciones.plataforma_inscripcion_cursos.compartido.enums.EstadoProfesor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Representa a un profesor encargado de dictar cursos.
 */
@Document(collection = "profesores")
public class Profesor {

    @Id
    private String id;

    private String nombres;

    private String apellidos;

    @Indexed(unique = true)
    private String identificacion;

    @Indexed(unique = true)
    private String correoInstitucional;

    private String telefonoContacto;

    private EstadoProfesor estadoProfesor = EstadoProfesor.ACTIVO;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getCorreoInstitucional() {
        return correoInstitucional;
    }

    public void setCorreoInstitucional(String correoInstitucional) {
        this.correoInstitucional = correoInstitucional;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public EstadoProfesor getEstadoProfesor() {
        return estadoProfesor;
    }

    public void setEstadoProfesor(EstadoProfesor estadoProfesor) {
        this.estadoProfesor = estadoProfesor;
    }
}

