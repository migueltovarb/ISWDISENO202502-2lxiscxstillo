package edu.ucc.inscripciones.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * DTO para solicitudes de autenticación (estudiante y administrador).
 */
public class PeticionAutenticacion {

    @NotBlank
    @Email
    private String correo;

    @NotBlank
    private String contrasena;

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}

