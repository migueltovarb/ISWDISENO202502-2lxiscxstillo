package edu.ucc.inscripciones.plataforma_inscripcion_cursos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Punto de entrada de la aplicación backend de la plataforma de inscripción.
 */
@SpringBootApplication(scanBasePackages = "edu.ucc.inscripciones")
public class PlataformaInscripcionCursosApplication {

	public static void main(String[] args) {
		// Fuerza el uso de TLS 1.2 para la comunicación con MongoDB Atlas.
		// Esto evita errores de handhsake SSL del tipo "Received fatal alert: internal_error"
		// que pueden aparecer con la negociación por defecto de TLS en algunas combinaciones
		// de JDK / sistema operativo / red.
		System.setProperty("jdk.tls.client.protocols", "TLSv1.2");

		SpringApplication.run(PlataformaInscripcionCursosApplication.class, args);
	}

}
