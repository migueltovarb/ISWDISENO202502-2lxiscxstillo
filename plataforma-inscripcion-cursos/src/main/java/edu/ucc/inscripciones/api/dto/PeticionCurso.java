package edu.ucc.inscripciones.api.dto;

import edu.ucc.inscripciones.plataforma_inscripcion_cursos.compartido.enums.TipoCurso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

/**
 * DTO para creación y actualización de cursos.
 */
public class PeticionCurso {

    @NotBlank
    private String codigo;

    @NotBlank
    private String nombre;

    private String descripcionGeneral;

    @NotNull
    private TipoCurso tipoCurso;

    private BigDecimal valorCursoExtension;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcionGeneral() {
        return descripcionGeneral;
    }

    public void setDescripcionGeneral(String descripcionGeneral) {
        this.descripcionGeneral = descripcionGeneral;
    }

    public TipoCurso getTipoCurso() {
        return tipoCurso;
    }

    public void setTipoCurso(TipoCurso tipoCurso) {
        this.tipoCurso = tipoCurso;
    }

    public BigDecimal getValorCursoExtension() {
        return valorCursoExtension;
    }

    public void setValorCursoExtension(BigDecimal valorCursoExtension) {
        this.valorCursoExtension = valorCursoExtension;
    }
}

