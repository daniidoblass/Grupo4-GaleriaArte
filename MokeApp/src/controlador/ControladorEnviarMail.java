package controlador;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

import conexion.Conexion;
import modelo.Modelo;
import vista.VistaMailEnviarCorreo;

/**
 * 
 * @author Javier Jimenez Torres
 * @date 09/12/2022
 * @version 01
 */
public class ControladorEnviarMail {
	
	private VistaMailEnviarCorreo vistaMailEnviarCorreo;
	private Conexion conexion;
	private Modelo modelo;

	/**
	 * Contructor
	 * @param conexion 
	 * @param modelo 
	 * 
	 * @param Vlogin es la vista del login que me paso a esta clase para utilizarla
	 */
	@SuppressWarnings("static-access")
	public ControladorEnviarMail(VistaMailEnviarCorreo vistaMailEnviarCorreo, Conexion conexion, Modelo modelo) {
		this.vistaMailEnviarCorreo = vistaMailEnviarCorreo;
		this.conexion = conexion;
		this.modelo = modelo;
	}

	/**
	 * Metodo que se encarga de enviar el correo electronico
	 * 
	 * @param destinatarios el que va a recibir el correo que se cogera del
	 *                      textField
	 * @param asunto        del mensaje
	 * @param cuerpo        del mensaje
	 * @return
	 * @throws MessagingException 
	 */
	public String enviarConGMail(String destinatarios, String asunto, String cuerpo, String ruta, String nombreArchivo) throws MessagingException {
		String mensaje; // Variable para determinar si un correo es enviado o no
		String remitente = modelo.getCorreo(); // remitente del mensaje que
																							// lo cogo de la vista
		@SuppressWarnings("deprecation")
		String clave = modelo.getPasswordCorreo(); // clave del usuario
		Properties props = System.getProperties();
		props.put("mail.smtp.host", "smtp.gmail.com"); // El servidor SMTP de Google
		props.put("mail.smtp.user", remitente); // remitente de la cuenta
		props.put("mail.smtp.clave", clave); // La clave de la cuenta
		props.put("mail.smtp.auth", "true"); // Usar autenticaci�n mediante usuario y clave
		props.put("mail.smtp.starttls.enable", "true"); // Para conectar de manera segura al servidor SMTP
		props.put("mail.smtp.port", "587"); // El puerto SMTP seguro de Google
		
		MimeMultipart multiParte = new MimeMultipart();
		
		if(ruta != null) {
			BodyPart texto = new MimeBodyPart();
			texto.setText(asunto);
			BodyPart adjunto = new MimeBodyPart();
			adjunto.setDataHandler(new DataHandler(new FileDataSource(ruta)));
			adjunto.setFileName(nombreArchivo);
			multiParte.addBodyPart(texto);
			multiParte.addBodyPart(adjunto);
		}
		
		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress(remitente));
			message.addRecipients(Message.RecipientType.TO, destinatarios); // Se podrian añadir varios de la misma
																			// manera
			if(ruta != null) {
				message.setSubject(asunto);
				message.setContent(multiParte);
				
			} else {
				message.setSubject(asunto);
				message.setText(cuerpo);
			}
			Transport transport = session.getTransport("smtp");
			transport.connect("smtp.gmail.com", remitente, clave);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			mensaje = "Correo Enviado";
			JOptionPane.showMessageDialog(null, mensaje, "", JOptionPane.INFORMATION_MESSAGE);//mensaje enviado
			conexion.registrarMovimiento("Enviar Mail", "si", "Correo enviado a " + remitente);
			return mensaje;

		} catch (MessagingException me) {
			mensaje = "El correo no existe";
			JOptionPane.showMessageDialog(null, mensaje, "", JOptionPane.ERROR_MESSAGE); //correo inexistente
			conexion.registrarMovimiento("Enviar Mail", "no", "El correo " + remitente + " no existe");
			return mensaje;

		} catch (NullPointerException e) {
			mensaje = "No puedes dejar campos vacios";
			JOptionPane.showMessageDialog(null, mensaje, "", JOptionPane.ERROR_MESSAGE);//campos vacios
			conexion.registrarMovimiento("Enviar Mail", "no", "No puedes dejar campos vacios");
			return mensaje;
		}
	}
}
