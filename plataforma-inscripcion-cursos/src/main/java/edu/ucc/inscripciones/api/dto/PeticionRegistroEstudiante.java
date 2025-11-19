package edu.ucc.inscripciones.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO para solicitud de registro de estudiante.
 */
public class PeticionRegistroEstudiante {

    @NotBlank
    private String nombres;

    @NotBlank
    private String apellidos;

    @NotBlank
    private String numeroEstudiante;

    @NotBlank
    @Email
    private String correoInstitucional;

    @NotBlank
    @Size(min = 8)
    private String contrasena;

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

    public String getNumeroEstudiante() {
        return numeroEstudiante;
    }

    public void setNumeroEstudiante(String numeroEstudiante) {
        this.numeroEstudiante = numeroEstudiante;
    }

    public String getCorreoInstitucional() {
        return correoInstitucional;
    }

    public void setCorreoInstitucional(String correoInstitucional) {
        this.correoInstitucional = correoInstitucional;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}

