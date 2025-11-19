package edu.ucc.inscripciones.pagos;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.PaymentIntentConfirmParams;
import edu.ucc.inscripciones.plataforma_inscripcion_cursos.compartido.enums.EstadoTransaccionPago;
import edu.ucc.inscripciones.plataforma_inscripcion_cursos.compartido.excepciones.ExcepcionValidacion;
import edu.ucc.inscripciones.plataforma_inscripcion_cursos.pagos.dominio.TransaccionPago;
import edu.ucc.inscripciones.plataforma_inscripcion_cursos.pagos.repositorio.RepositorioTransaccionPago;
import edu.ucc.inscripciones.plataforma_inscripcion_cursos.seguridad.ConfiguracionStripe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Servicio encargado de integrar el backend con el proveedor externo de pagos (Stripe).
 */
@Service
public class ServicioPagosOnline {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServicioPagosOnline.class);

    private final RepositorioTransaccionPago repositorioTransaccionPago;
    private final ConfiguracionStripe configuracionStripe;

    public ServicioPagosOnline(RepositorioTransaccionPago repositorioTransaccionPago,
                               ConfiguracionStripe configuracionStripe) {
        this.repositorioTransaccionPago = repositorioTransaccionPago;
        this.configuracionStripe = configuracionStripe;
    }

    /**
     * Procesa un pago para un curso de extensión utilizando Stripe en modo de prueba.
     * Interpreta la respuesta del proveedor y registra la transacción (EC016).
     */
    public TransaccionPago procesarPago(String idEstudiante,
                                        String idCurso,
                                        BigDecimal monto) {
        if (monto == null || monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ExcepcionValidacion("El valor del curso de extensión debe ser mayor que cero");
        }

        TransaccionPago transaccion = new TransaccionPago();
        transaccion.setIdEstudiante(idEstudiante);
        transaccion.setIdCurso(idCurso);
        transaccion.setMonto(monto);
        transaccion.setFechaTransaccion(LocalDateTime.now());
        transaccion.setEstadoTransaccionPago(EstadoTransaccionPago.PENDIENTE);

        // Se persiste inicialmente como pendiente para cumplir con el registro de la operación.
        transaccion = repositorioTransaccionPago.save(transaccion);

        try {
            long montoEnCentavos = monto.multiply(BigDecimal.valueOf(100)).longValueExact();

            // 1. Crear el PaymentIntent sin confirmar.
            PaymentIntentCreateParams parametrosCreacion = PaymentIntentCreateParams.builder()
                    .setAmount(montoEnCentavos)
                    .setCurrency("usd")
                    .setPaymentMethod("pm_card_visa")
                    .setDescription("Pago curso de extensión (" + idCurso + ")")
                    .build();

            PaymentIntent intent = PaymentIntent.create(parametrosCreacion);

            // 2. Confirmar el PaymentIntent incluyendo la URL de retorno.
            PaymentIntentConfirmParams parametrosConfirmacion = PaymentIntentConfirmParams.builder()
                    .setReturnUrl(configuracionStripe.obtenerUrlRetornoStripe())
                    .build();

            intent = intent.confirm(parametrosConfirmacion);

            transaccion.setReferenciaExterna(intent.getId());

            EstadoTransaccionPago nuevoEstado;
            if ("succeeded".equalsIgnoreCase(intent.getStatus())) {
                nuevoEstado = EstadoTransaccionPago.APROBADA;
            } else {
                nuevoEstado = EstadoTransaccionPago.RECHAZADA;
            }
            transaccion.setEstadoTransaccionPago(nuevoEstado);
            return repositorioTransaccionPago.save(transaccion);
        } catch (StripeException ex) {
            LOGGER.error("Error al procesar pago con Stripe: {}", ex.getMessage(), ex);
            transaccion.setEstadoTransaccionPago(EstadoTransaccionPago.RECHAZADA);
            repositorioTransaccionPago.save(transaccion);
            throw new ExcepcionValidacion("Error al procesar el pago con la pasarela de pagos. Inténtelo de nuevo más tarde.");
        }
    }
}

