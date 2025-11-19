package edu.ucc.inscripciones.api.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO para solicitudes de inscripción de un estudiante a un horario.
 */
public class PeticionInscripcion {

    @NotBlank
    private String idEstudiante;

    @NotBlank
    private String idHorario;

    public String getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(String idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(String idHorario) {
        this.idHorario = idHorario;
    }
}

