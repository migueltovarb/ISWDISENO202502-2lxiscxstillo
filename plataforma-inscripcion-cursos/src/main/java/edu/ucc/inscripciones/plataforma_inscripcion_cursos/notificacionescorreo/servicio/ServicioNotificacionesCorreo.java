package edu.ucc.inscripciones.plataforma_inscripcion_cursos.notificacionescorreo.servicio;

import edu.ucc.inscripciones.plataforma_inscripcion_cursos.autenticacion.estudiantes.dominio.Estudiante;
import edu.ucc.inscripciones.plataforma_inscripcion_cursos.ofertaacademica.cursos.dominio.Curso;
import edu.ucc.inscripciones.plataforma_inscripcion_cursos.ofertaacademica.horarios.dominio.Horario;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Servicio encargado de enviar correos electrónicos utilizando la configuración
 * existente de Mailtrap.
 */
@Service
public class ServicioNotificacionesCorreo {

    private final JavaMailSender mailSender;

    public ServicioNotificacionesCorreo(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * Envía un correo de confirmación de inscripción a un estudiante.
     */
    public void enviarConfirmacionInscripcion(Estudiante estudiante, Curso curso, Horario horario) {
        // TODO parametrizar dirección de correo del remitente desde configuración
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(estudiante.getCorreoInstitucional());
        mensaje.setSubject("Confirmación de inscripción a curso");
        mensaje.setText("Hola " + estudiante.getNombres() + ",\n\n"
                + "Tu inscripción al curso '" + curso.getNombre() + "' en el horario del día "
                + horario.getDiaSemana() + " a las " + horario.getHoraInicio() + " ha sido registrada correctamente.\n\n"
                + "Atentamente,\nPlataforma de Inscripción de Cursos");
        mailSender.send(mensaje);
    }
}

