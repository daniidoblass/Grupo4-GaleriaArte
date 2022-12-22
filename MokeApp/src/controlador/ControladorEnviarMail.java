package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
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
 * @author Javier Jim�nez Torres
 *
 */
public class ControladorEnviarMail {
	private Conexion conexion;
	private Eventos eventos;
	private Modelo modelo;
	private VistaMailEnviarCorreo vCorreo;

	/**
	 * Contructor
	 * 
	 * @param Vlogin es la vista del login que me paso a esta clase para utilizarla
	 */
	@SuppressWarnings("static-access")
	public ControladorEnviarMail(VistaMailEnviarCorreo vCorreo,Conexion conexion, Eventos eventos,Modelo modelo) {
		this.vCorreo = vCorreo;
		this.modelo = modelo;
		this.conexion = conexion;
		this.eventos = eventos;
	}

	/**
	 * M�todo que se encarga de enviar el correo electronico
	 * 
	 * @param destinatarios el que va a recibir el correo que se coger� del
	 *                      textField
	 * @param asunto        del mensaje
	 * @param cuerpo        del mensaje
	 * @return
	 * @throws MessagingException
	 */
	public String enviarConGMail(String destinatarios, String asunto, String cuerpo, String ruta, String nombreArchivo)
			throws MessagingException {

		// Crear las variables y obtener el usuario que se ha registrado.
		String usuario = eventos.getUsuario().toString();
		String correo = "";
		String password = "";

		// Consulta para obtener el correo y la password del usuario que ha iniciado
		// sesion
		String consulta = modelo.getConsultaCorreoPassword() + usuario + modelo.getComillaSimple();
		ResultSet rs = conexion.realizarConsultaRS(consulta);

		try {

			while (rs.next()) {

				correo = rs.getString(1);
				password = rs.getString(2);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String mensaje; // Variable para determinar si un correo es enviado o no
		@SuppressWarnings("deprecation")

		Properties props = System.getProperties();
		props.put(modelo.getTextosSmtp()[0], modelo.getTextosSmtp()[1]); // El servidor SMTP de Google
		props.put(modelo.getTextosSmtp()[2], correo); // remitente de la cuenta
		props.put(modelo.getTextosSmtp()[3], password); // La clave de la cuenta
		props.put(modelo.getTextosSmtp()[4], modelo.getTextosSmtp()[5]); // Usar autenticaci�n mediante usuario y clave
		props.put(modelo.getTextosSmtp()[6], modelo.getTextosSmtp()[5]); // Para conectar de manera segura al servidor SMTP
		props.put(modelo.getTextosSmtp()[7], modelo.getTextosSmtp()[8]); // El puerto SMTP seguro de Google

		MimeMultipart multiParte = new MimeMultipart();

		if (ruta != null) {
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
			message.setFrom(new InternetAddress(correo));
			message.addRecipients(Message.RecipientType.TO, destinatarios); // Se podr�an a�adir varios de la misma
																			// manera
			if (ruta != null) {
				message.setSubject(asunto);
				message.setContent(multiParte);

			} else {
				message.setSubject(asunto);
				message.setText(cuerpo);
			}
			Transport transport = session.getTransport(modelo.getTextosSmtp()[9]);
			transport.connect(modelo.getTextosSmtp()[1], correo, password);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			mensaje = modelo.getTextosSmtp()[10];
			JOptionPane.showMessageDialog(null, mensaje, modelo.getNada(), JOptionPane.INFORMATION_MESSAGE);// mensaje enviado
			return mensaje;

		} catch (MessagingException me) {
			mensaje = modelo.getTextosSmtp()[11];
			JOptionPane.showMessageDialog(null, mensaje, modelo.getNada(), JOptionPane.ERROR_MESSAGE); // correo inexistente
			return mensaje;

		} catch (NullPointerException e) {
			mensaje = modelo.getTextosSmtp()[12];
			JOptionPane.showMessageDialog(null, mensaje, modelo.getNada(), JOptionPane.ERROR_MESSAGE);// campos vacios
			return mensaje;
		}
	}
}
