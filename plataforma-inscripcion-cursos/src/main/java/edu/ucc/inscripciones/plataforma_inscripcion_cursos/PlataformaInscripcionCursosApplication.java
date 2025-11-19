package edu.ucc.inscripciones.plataforma_inscripcion_cursos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Punto de entrada de la aplicación backend de la plataforma de inscripción.
 */
@SpringBootApplication(scanBasePackages = "edu.ucc.inscripciones")
public class PlataformaInscripcionCursosApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlataformaInscripcionCursosApplication.class, args);
	}

}
