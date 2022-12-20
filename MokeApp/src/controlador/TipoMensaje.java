package controlador;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMultipart;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * 
 * @author Javier Jim�nez Torres
 *
 */
public class TipoMensaje {
	
	/**
	 * M�todo para desencriptar los mensaje si es texto plano o multipart 
	 * @param message recibe el mensaje de ese correo y lo desencripta
	 * @return
	 * @throws MessagingException
	 * @throws IOException
	 */
	public static String getTextFromMessage(Message message) throws MessagingException, IOException {
		String result = "";
		if (message.isMimeType("text/plain")) {
			result = message.getContent().toString();
			
		} else if (message.isMimeType("multipart/*")) {
			MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
			result = getTextFromMimeMultipart(mimeMultipart);
		}
		return result;
	}

	/**
	 * Entra en este m�todo si el mensaje es multipart
	 * @param mimeMultipart
	 * @return
	 * @throws MessagingException
	 * @throws IOException
	 */
	public static String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws MessagingException, IOException {
		String result = "";
		int count = mimeMultipart.getCount();

		for (int i = 0; i < count; i++) {
			BodyPart bodyPart = mimeMultipart.getBodyPart(i);
			if (bodyPart.isMimeType("text/plain")) {
				result = result + "\n" + bodyPart.getContent();
				break;

			} else if (bodyPart.isMimeType("text/html")) {
				String html = (String) bodyPart.getContent();
				result = result + "\n" + org.jsoup.Jsoup.parse(html).text();

			} else if (bodyPart.getContent() instanceof MimeMultipart) {
				result = result + getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent());
			}

			else if (bodyPart.isMimeType("image/*")) { // para abrir en un JFrame aparte si hay alguna imagen
				JFrame v = new JFrame();
				ImageIcon icono = new ImageIcon(ImageIO.read(bodyPart.getInputStream()));
				JLabel l = new JLabel(icono);
				v.getContentPane().add(l);
				v.pack();
				v.setVisible(true);
			}
		}

		return result;
	}
}
