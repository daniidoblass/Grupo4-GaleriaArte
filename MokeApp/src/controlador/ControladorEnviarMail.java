package controlador;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

import vista.VistaMailEnviarCorreo;
import vista.VistaLogin;

/**
 * 
 * @author Javier Jim�nez Torres
 *
 */
public class ControladorEnviarMail {
	private VistaMailEnviarCorreo vCorreo;

	/**
	 * Contructor
	 * 
	 * @param Vlogin es la vista del login que me paso a esta clase para utilizarla
	 */
	@SuppressWarnings("static-access")
	public ControladorEnviarMail(VistaMailEnviarCorreo vCorreo) {
		this.vCorreo = vCorreo;
	}
	/**
	 * M�todo que se encarga de enviar el correo electronico
	 * 
	 * @param destinatarios el que va a recibir el correo que se coger� del
	 *                      textField
	 * @param asunto        del mensaje
	 * @param cuerpo        del mensaje
	 * @return
	 */
	public String enviarConGMail(String destinatarios, String asunto, String cuerpo) {
		String mensaje; // Variable para determinar si un correo es enviado o no
		String remitente = "joseantoniogarciavegas.sanjose@alumnado.fundacionloyola.net"; // remitente del mensaje que lo cogo de la vista
		@SuppressWarnings("deprecation")
		String clave = "67842291"; // clave del usuario
		Properties props = System.getProperties();
		props.put("mail.smtp.host", "smtp.gmail.com"); // El servidor SMTP de Google
		props.put("mail.smtp.user", remitente); // remitente de la cuenta
		props.put("mail.smtp.clave", clave); // La clave de la cuenta
		props.put("mail.smtp.auth", "true"); // Usar autenticaci�n mediante usuario y clave
		props.put("mail.smtp.starttls.enable", "true"); // Para conectar de manera segura al servidor SMTP
		props.put("mail.smtp.port", "587"); // El puerto SMTP seguro de Google

		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress(remitente));
			message.addRecipients(Message.RecipientType.TO, destinatarios); // Se podr�an a�adir varios de la misma
																			// manera
			message.setSubject(asunto);
			message.setText(cuerpo);
			Transport transport = session.getTransport("smtp");
			transport.connect("smtp.gmail.com", remitente, clave);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			mensaje = "Correo Enviado";
			JOptionPane.showMessageDialog(null, mensaje, "", JOptionPane.INFORMATION_MESSAGE); // JoptionPane para
																								// ense�ar al usuario
																								// que su correo se ha
																								// enviado
			return mensaje;

		} catch (MessagingException me) {
			mensaje = "El correo no existe";
			JOptionPane.showMessageDialog(null, mensaje, "", JOptionPane.ERROR_MESSAGE); // JoptionPane para ense�ar al
																							// usuario que al correo que
																							// intenta enviar el correo
																							// no existe
			return mensaje;

		} catch (NullPointerException e) {
			mensaje = "No puedes dejar campos vacios";
			JOptionPane.showMessageDialog(null, mensaje, "", JOptionPane.ERROR_MESSAGE); // Un mensaje de aviso para
																							// avisar al usuario que no
																							// puede dejar campos vacios
			return mensaje;
		}
	}
}
