/**
 * @author Samuel Acosta Fernandez
 * @date 09/02/2022
 * @version 01
 */

package controlador;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.apache.commons.net.ftp.FTPClient;

import conexion.Conexion;
import modelo.Modelo;
import vista.Vista;
import vista.VistaMailEnviarCorreo;
import vista.VistaMailPrincipal;

public class ControladorMailPrincipal implements ActionListener {

	private JButton button = new JButton();
	private Modelo modelo;
	private Vista vista;
	private VistaMailPrincipal vistaMailPrincipal;
	private Eventos eventos;
	private Conexion conexion;
	private FTPClient cliente;
	private ArrayList<String> nombreColumnas;
	private String nombreTabla;
	private Message[] mensajes;
	private TipoMensaje tipoMensaje = new TipoMensaje();
	private VistaMailEnviarCorreo vistaMailEnviarCorreo;
	private ControladorEnviarMail controladorenviarmail;
	private final int TIEMPO_REFRESCO = 60000;
	private Hilo hiloActualizar;

	public ControladorMailPrincipal(Modelo modelo, Vista vista, Eventos eventos, Conexion conexion, FTPClient cliente)
			throws MessagingException {
		this.modelo = modelo;
		this.vista = vista;
		this.eventos = eventos;
		controladorenviarmail = new ControladorEnviarMail(vistaMailEnviarCorreo);
		vistaMailPrincipal = new VistaMailPrincipal(modelo, vista);
		vistaMailEnviarCorreo = new VistaMailEnviarCorreo();
		tipoMensaje = new TipoMensaje();
		this.conexion = conexion;
		this.cliente = cliente;

		// Configurar titulo de la pagina
		configurarTitulo();

		// Configurar paneles admin
		configurarPanelesAdmin();

		// Configurar tabla
		nombreTabla = "usuarios";
		configurarTabla(nombreTabla);

		// Configurar boton cambiar tabla
		configurarBotonCambiarTabla();

		configurarBottonVerMensaje();

		configurarBotonActualizarTabla();

		hiloActualizar = new Hilo(this, TIEMPO_REFRESCO);
		hiloActualizar.start();
	}

	public synchronized void controladorGmail() throws MessagingException {
		rellenarDatos();
	}

	private void configurarBotonCambiarTabla() {
		vistaMailPrincipal.configurarBotonCambiarTabla();
		vistaMailPrincipal.getBotonCambiarTabla().addActionListener(this);
	}

	private void configurarBottonVerMensaje() {
		vistaMailPrincipal.configuracionBottonVerMensaje();
		vistaMailPrincipal.getBotonVerMensaje().addActionListener(this);
	}

	private void configurarBotonActualizarTabla() {
		vistaMailPrincipal.configurarBotonActualizar();
		vistaMailPrincipal.getBotonActualizarTabla().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());

		if (e.getActionCommand().equals("Ver mensaje")) {
			try {
				verMensaje();
			} catch (MessagingException e1) {
				e1.printStackTrace();
			}
		} else if (e.getActionCommand().equals("Actualizar")) {
			System.out.println(hiloActualizar.getContador());
			hiloActualizar.setContador(TIEMPO_REFRESCO);
		} else {
			vistaMailEnviarCorreo.setVisible(true);

//			if (nombreTabla.equals("usuarios")) {
//				nombreTabla = "movimientos";
//				try {
//					actualizarTabla(nombreTabla);
//				} catch (MessagingException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			} else {
//				nombreTabla = "usuarios";
//				try {
//					actualizarTabla(nombreTabla);
//				} catch (MessagingException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			}
		}

	}

	private void configurarTabla(String nombreTabla) throws MessagingException {
		// Rellenar Titulos
		rellenarTitulos(nombreTabla);

		// Configurar tabla con datos
		vistaMailPrincipal.configuracionJTable1(nombreColumnas.toArray(new String[nombreColumnas.size()]));

		// Rellenar Datos
		rellenarDatos();
	}

	private void actualizarTabla(String nombreTabla) throws MessagingException {
		// Rellenar Titulos
		rellenarTitulos(nombreTabla);

		// Actualizar tabla con datos
		vistaMailPrincipal.modificarModeloTabla(nombreColumnas.toArray(new String[nombreColumnas.size()]));

		// Rellenar Datos
		rellenarDatos();
	}

	private void rellenarTitulos(String nombreTablaSeleccionada) {

		nombreColumnas = new ArrayList<>();
		nombreColumnas.add("Destinatario");
		nombreColumnas.add("Asunto");
		nombreColumnas.add("Fecha");

	}

	private void rellenarDatos() {

		// Limpiamos los datos de la tabla
		vistaMailPrincipal.limpiarTabla();
		try {
			recibirCorreos();
			System.out.println(mensajes[0].getContent());

		} catch (MessagingException e) {

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {

			// Bucle para cada resultado en la consulta
			for (int j = 0; j < mensajes.length; j++) {

				// Se crea un array que ser� una de las filas de la tabla
				Object[] fila = new Object[nombreColumnas.size()];

				ArrayList<String> String = new ArrayList<>();

				String[] destinatario = mensajes[j].getFrom()[0].toString().split("<");
				destinatario[destinatario.length - 1] = destinatario[destinatario.length - 1].replace('>', ' ');

				String.add(destinatario[destinatario.length - 1]);
				String.add(mensajes[j].getSubject());
				String.add(mensajes[j].getSentDate().toString());
				String.add(mensajes[j].getContent().toString());

				for (int i = 0; i < nombreColumnas.size(); i++) {
					fila[i] = String.get(i); // El primer indice en rs es el 1, no el cero, por eso se suma 1.
				}

				// Se a�ade al modelo la fila completa.
				vistaMailPrincipal.insertRow(fila);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

//        try {
//            // Obtener datos de Tabla seleccionada
//            ResultSet rs = conexion.realizarConsultaRS(select);
//            
//            
//            // Bucle para cada resultado en la consulta
//            while (rs.next()) {
//                
//               // Se crea un array que ser� una de las filas de la tabla
//               Object[] fila = new Object[nombreColumnas.size()]; 
//
//               for (int i=0;i<nombreColumnas.size();i++){
//                   fila[i] = rs.getObject(i+1); // El primer indice en rs es el 1, no el cero, por eso se suma 1.
//               }
//                  
//
//               // Se a�ade al modelo la fila completa.
//               vistaMailPrincipal.insertRow(fila);
//            }
//            
//            
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }

	}

	private void configurarPanelesAdmin() {
		for (int i = 0; i < 3; i++) {
			vistaMailPrincipal.crearPanelesAdmin(i);
		}
		vistaMailPrincipal.ordenarPaneles();
		vistaMailPrincipal.configurarPanelAdminNorte();
		vistaMailPrincipal.configurarPanelAdminEste();
		vistaMailPrincipal.configurarPanelAdminCentral();
	}

	private void configurarTitulo() {
		vista.setIcono("src/opcionesprincipal/1.png");
		vista.setTitulo("Mail MOKE");
	}

	private void actualizarVentana() {
		vista.repaint();
		vista.pack();
		vista.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	public void recibirCorreos() throws MessagingException {
		Properties prop = new Properties();

		// Deshabilitamos TLS
		prop.setProperty("mail.pop3.starttls.enable", "false");

		// Hay que usar SSL
		prop.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		prop.setProperty("mail.pop3.socketFactory.fallback", "false");

		// Puerto 995 para conectarse.
		prop.setProperty("mail.pop3.port", "995");
		prop.setProperty("mail.pop3.socketFactory.port", "995");

		Session sesion = Session.getInstance(prop);
		// sesion.setDebug(true); // Esta linea es para mostrar mas informacion

		Store store = sesion.getStore("pop3");
		// System.out.println(Vlogin.getEmailText());
		String correo = "joseantoniogarciavegas.sanjose@alumnado.fundacionloyola.net";// Vlogin.getEmailText(); //correo
																						// de la persona que rebibe los
																						// correo para leerlo
		String password = "67842291";// Vlogin.getPassword().getText();// y su contraseña
		// System.out.println("hoal" + correo + password);
		store.connect("pop.gmail.com", correo, password);
		Folder folder = store.getFolder("INBOX");
		folder.open(Folder.READ_ONLY);
		mensajes = folder.getMessages();
		Collections.reverse(Arrays.asList(mensajes)); // darle la vuelta al array para que aparezaca el correo ultimo
														// recibido el primero
	}

	public void verMensaje() throws MessagingException {
		String mensaje = "";
		String[] destinatario = null;
		try {
			mensaje = TipoMensaje.getTextFromMessage(mensajes[vistaMailPrincipal.getFila()]);
			destinatario = mensajes[vistaMailPrincipal.getFila()].getFrom()[0].toString().split("<");
			destinatario[destinatario.length - 1] = destinatario[destinatario.length - 1].replace('>', ' ');

		} catch (MessagingException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String info = "INFO PERSONA\n";
		info += "Remitente: " + destinatario[destinatario.length - 1] + "\n" + "" + "\n";
		info += "Asunto: " + mensajes[vistaMailPrincipal.getFila()].getSubject() + "\n";
		info += "Fecha: " + mensajes[vistaMailPrincipal.getFila()].getSentDate().toString() + "\n" + "" + "\n";
		info += "Mensaje: " + mensaje + "\n" + "" + "\n";

		JTextArea textArea = new JTextArea();
		textArea.setText(info);
		JScrollPane scrollPane = new JScrollPane(textArea);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		scrollPane.setPreferredSize(new Dimension(500, 500));
		JOptionPane.showMessageDialog(null, scrollPane, "Correo", JOptionPane.CLOSED_OPTION);
	}
}
