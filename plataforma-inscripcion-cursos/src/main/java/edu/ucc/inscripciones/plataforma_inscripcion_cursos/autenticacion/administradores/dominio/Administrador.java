package edu.ucc.inscripciones.plataforma_inscripcion_cursos.autenticacion.administradores.dominio;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Representa a un administrador de la plataforma.
 */
@Document(collection = "administradores")
public class Administrador {

    @Id
    private String id;

    private String nombres;

    private String apellidos;

    @Indexed(unique = true)
    private String correoInstitucional;

    /**
     * Contraseña almacenada de forma cifrada/hasheada.
     */
    private String contrasenaCifrada;

    private boolean activo = true;

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

    public String getCorreoInstitucional() {
        return correoInstitucional;
    }

    public void setCorreoInstitucional(String correoInstitucional) {
        this.correoInstitucional = correoInstitucional;
    }

    public String getContrasenaCifrada() {
        return contrasenaCifrada;
    }

    public void setContrasenaCifrada(String contrasenaCifrada) {
        this.contrasenaCifrada = contrasenaCifrada;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}

