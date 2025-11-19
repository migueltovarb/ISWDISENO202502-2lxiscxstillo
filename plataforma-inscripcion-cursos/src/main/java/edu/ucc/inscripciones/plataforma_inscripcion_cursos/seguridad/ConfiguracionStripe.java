package edu.ucc.inscripciones.plataforma_inscripcion_cursos.seguridad;

import com.stripe.Stripe;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración central para la integración con Stripe en modo de prueba.
 * Lee las claves desde application.properties y configura la librería cliente.
 */
@Configuration
public class ConfiguracionStripe {

    @Value("${stripe.api.secret-key}")
    private String claveSecretaStripe;

    @Value("${stripe.api.public-key}")
    private String clavePublicaStripe;

    @Value("${stripe.return-url}")
    private String urlRetornoStripe;

    @PostConstruct
    public void inicializarStripe() {
        Stripe.apiKey = claveSecretaStripe;
    }

    /**
     * Devuelve la clave pública configurada de Stripe.
     * Puede usarse si en el futuro se expone a un cliente web.
     */
    public String obtenerClavePublicaStripe() {
        return clavePublicaStripe;
    }

    /**
     * Devuelve la URL de retorno configurada para Stripe.
     */
    public String obtenerUrlRetornoStripe() {
        return urlRetornoStripe;
    }
}

