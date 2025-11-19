package edu.ucc.inscripciones.plataforma_inscripcion_cursos.ofertaacademica.cursos.dominio;

import edu.ucc.inscripciones.plataforma_inscripcion_cursos.compartido.enums.EstadoCurso;
import edu.ucc.inscripciones.plataforma_inscripcion_cursos.compartido.enums.TipoCurso;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

/**
 * Representa un curso dentro de la oferta académica.
 */
@Document(collection = "cursos")
public class Curso {

    @Id
    private String id;

    @Indexed(unique = true)
    private String codigo;

    private String nombre;

    private String descripcionGeneral;

    private TipoCurso tipoCurso;

    /**
     * Costo del curso cuando es de extensión.
     */
    private BigDecimal valorCursoExtension;

    private EstadoCurso estadoCurso = EstadoCurso.ACTIVO;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public EstadoCurso getEstadoCurso() {
        return estadoCurso;
    }

    public void setEstadoCurso(EstadoCurso estadoCurso) {
        this.estadoCurso = estadoCurso;
    }
}

