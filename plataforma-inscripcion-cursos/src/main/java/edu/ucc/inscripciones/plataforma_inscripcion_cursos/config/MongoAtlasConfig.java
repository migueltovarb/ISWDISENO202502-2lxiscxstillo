package edu.ucc.inscripciones.plataforma_inscripcion_cursos.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

/**
 * Configuración explícita de MongoClient para MongoDB Atlas.
 * <p>
 * Importante: esta configuración confía en todos los certificados SSL únicamente
 * para evitar problemas de handshake en entornos de desarrollo/academia donde
 * la cadena de certificados de Atlas puede ser interceptada o modificada.
 * No debe usarse tal cual en producción.
 */
@Configuration
public class MongoAtlasConfig {

    private static final Logger logger = LoggerFactory.getLogger(MongoAtlasConfig.class);

    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;

    @Bean
    public MongoClient mongoClient() throws Exception {
        // TrustManager que acepta cualquier certificado (sólo para desarrollo).
        X509TrustManager permissiveTrustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) {
                // no-op
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) {
                // no-op
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, new TrustManager[]{permissiveTrustManager}, new SecureRandom());

        ConnectionString connectionString = new ConnectionString(mongoUri);

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .applyToSslSettings(builder -> builder.enabled(true).context(sslContext))
                .build();

        logger.info("Inicializando MongoClient para MongoDB Atlas con configuración TLS personalizada. Hosts: {}", connectionString.getHosts());

        return MongoClients.create(settings);
    }
}

