package edu.ucc.inscripciones.plataforma_inscripcion_cursos.pagos.dominio;

import edu.ucc.inscripciones.plataforma_inscripcion_cursos.compartido.enums.EstadoTransaccionPago;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Representa una transacción de pago para cursos de extensión.
 */
@Document(collection = "transaccionesPago")
public class TransaccionPago {

    @Id
    private String id;

    private String referenciaExterna;

    private String idEstudiante;

    private String idCurso;

    private BigDecimal monto;

    private LocalDateTime fechaTransaccion;

    private EstadoTransaccionPago estadoTransaccionPago;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReferenciaExterna() {
        return referenciaExterna;
    }

    public void setReferenciaExterna(String referenciaExterna) {
        this.referenciaExterna = referenciaExterna;
    }

    public String getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(String idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(String idCurso) {
        this.idCurso = idCurso;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public LocalDateTime getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(LocalDateTime fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public EstadoTransaccionPago getEstadoTransaccionPago() {
        return estadoTransaccionPago;
    }

    public void setEstadoTransaccionPago(EstadoTransaccionPago estadoTransaccionPago) {
        this.estadoTransaccionPago = estadoTransaccionPago;
    }
}

