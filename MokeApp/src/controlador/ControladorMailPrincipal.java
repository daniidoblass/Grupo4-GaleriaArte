/**
 * 
 * Clase ControladorMailPrincipal
 * 
 * Permite mostrar una tabla con correos recibidos
 * y opciones del usuario para enviar mensaje
 * y mostrar el contenido de aquellos recibidos
 * 
 * @author Samuel Acosta Fernandez
 * @date 09/12/2022
 * @version 01
 */

package controlador;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
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

	/**
	 * modelo - tipo Modelo - contiene textos del programa
	 */
	private Modelo modelo;
	
	/**
     * vista - tipo Vista - vista principal del programa
     */
	private Vista vista;
	
	/**
     * vistaMailPrincipal - tipo VistaMailPrincipal - vista de mail
     */
	private VistaMailPrincipal vistaMailPrincipal;
	
	/**
     * eventos - tipo Eventos - eventos principales 
     */
	private Eventos eventos;
	
	/**
     * conexion - tipo Conexion - conexion con base de datos
     */
	private Conexion conexion;
	
	/**
     * cliente - tipo FTPClient - cliente FTP
     */
	private FTPClient cliente;
	
	/**
     * nombreColumnas - tipo ArrayList<String> - columnas de tabla
     */
	private ArrayList<String> nombreColumnas;
	
	/**
     * nombreTabla - tipo String - nombre de la tabla
     */
	private String nombreTabla;
	
	/**
	 * mensajes - tipo Message[] - mensajes recibidos
	 */
	private Message[] mensajes;
	
	/**
	 * tipoMensaje - tipo TipoMensaje - tipo del mensaje
	 */
	private TipoMensaje tipoMensaje = new TipoMensaje();
	
	/**
	 * vistaMailEnviarCorreo - tipo VistaMailEnviarCorreo - vista de mail
	 */
	private VistaMailEnviarCorreo vistaMailEnviarCorreo;
	
	/**
	 * controladorEnviarMail - tipo ControladorEnviarMail - permite enviar mail
	 */
	private ControladorEnviarMail controladorEnviarMail;
	
	/**
	 * TIEMPO_REFRESCO - tipo int - tiempo de refresco
	 */
	private final int TIEMPO_REFRESCO = 60000;
	
	/**
	 * hiloActualizar - tipo Hilo - actualiza contenido mail
	 */
	private Hilo hiloActualizar;

	/**
	 * Constructor por defecto. Permite crear opciones de mail junto a tabla
	 * para visualizar correos recibidos
	 * @param modelo - tipo Modelo - contiene textos del programa
	 * @param vista - tipo Vista - vista principal del programa
	 * @param eventos - tipo Eventos - eventos principales 
	 * @param conexion - tipo Conexion - conexion con base de datos
	 * @param cliente - tipo FTPClient - cliente FTP
	 * @throws MessagingException
	 */
	public ControladorMailPrincipal(Modelo modelo, Vista vista, Eventos eventos, Conexion conexion, FTPClient cliente)
			throws MessagingException {
		this.modelo = modelo;
		this.vista = vista;
		this.eventos = eventos;
		this.conexion = conexion;
		this.cliente = cliente;
		vistaMailPrincipal = new VistaMailPrincipal(modelo, vista);
		controladorEnviarMail = new ControladorEnviarMail(vistaMailEnviarCorreo, conexion, modelo);
		vistaMailEnviarCorreo = new VistaMailEnviarCorreo(conexion, modelo);
		tipoMensaje = new TipoMensaje();
		

		// Configurar titulo de la pagina
		configurarTitulo();

		// Configurar paneles admin
		configurarPanelesAdmin();

		// Configurar tabla
		nombreTabla = modelo.getTextosGenerales()[9];
		configurarTabla(nombreTabla);

		// Configurar boton cambiar tabla
		configurarBotonCambiarTabla();

		configurarBottonVerMensaje();

		configurarBotonActualizarTabla();

		hiloActualizar = new Hilo(this, TIEMPO_REFRESCO);
		hiloActualizar.start();
	}

	/**
	 * Rellena datos de correos en la tabla
	 * @throws MessagingException
	 */
	public synchronized void controladorGmail() throws MessagingException {
		rellenarDatos();
	}

	/**
	 * Establece botón de redactar mail
	 */
	private void configurarBotonCambiarTabla() {
		vistaMailPrincipal.configurarBotonRedactar();
		vistaMailPrincipal.getBotonCambiarTabla().addActionListener(this);
	}

	/**
	 * Configura botón para ver correo seleccionado
	 */
	private void configurarBottonVerMensaje() {
		vistaMailPrincipal.configuracionBottonVerMensaje();
		vistaMailPrincipal.getBotonVerMensaje().addMouseListener(eventos);
		vistaMailPrincipal.getBotonVerMensaje().addActionListener(this);
	}

	/**
	 * Actualiza el contenido de la tabla
	 */
	private void configurarBotonActualizarTabla() {
		vistaMailPrincipal.configurarBotonActualizar();
		vistaMailPrincipal.getBotonActualizarTabla().addMouseListener(eventos);
		vistaMailPrincipal.getBotonActualizarTabla().addActionListener(this);
	}

	/**
	 * Eventos de las opciones de mail
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals(modelo.getTextosGenerales()[10])) {
			try {
				verMensaje();
			} catch (MessagingException e1) {
				e1.printStackTrace();
			}
		} else if (e.getActionCommand().equals(modelo.getTextosGenerales()[11])) {
			hiloActualizar.setContador(TIEMPO_REFRESCO);
		} else {
			vistaMailEnviarCorreo.setVisible(true);
		}

	}

	/**
	 * Configura contenido de la tabla
	 * @param nombreTabla - tipo String - nombre de la tabla
	 * @throws MessagingException
	 */
	private void configurarTabla(String nombreTabla) throws MessagingException {
		// Rellenar Titulos
		rellenarTitulos(nombreTabla);

		// Configurar tabla con datos
		vistaMailPrincipal.configuracionJTable1(nombreColumnas.toArray(new String[nombreColumnas.size()]));

		// Rellenar Datos
		rellenarDatos();
	}

	/**
	 * Actualiza el contenido de la tabla
	 * @param nombreTabla - tipo String - nombre de la tabla
	 * @throws MessagingException
	 */
	private void actualizarTabla(String nombreTabla) throws MessagingException {
		// Rellenar Titulos
		rellenarTitulos(nombreTabla);

		// Actualizar tabla con datos
		vistaMailPrincipal.modificarModeloTabla(nombreColumnas.toArray(new String[nombreColumnas.size()]));

		// Rellenar Datos
		rellenarDatos();
	}

	/**
	 * Rellena los títulos de la tabla
	 * @param nombreTablaSeleccionada - tipo String - nombre tabla
	 */
	private void rellenarTitulos(String nombreTablaSeleccionada) {
		nombreColumnas = new ArrayList<>();
		nombreColumnas.add(modelo.getNombresColumns()[0]);
		nombreColumnas.add(modelo.getNombresColumns()[1]);
		nombreColumnas.add(modelo.getNombresColumns()[2]);
	}

	/**
	 * Rellena datos de la tabla
	 */
	private void rellenarDatos() {

		// Limpiamos los datos de la tabla
		vistaMailPrincipal.limpiarTabla();

		try {
			
			recibirCorreos();
			
			// Bucle para cada resultado en la consulta
			for (int j = 0; j < mensajes.length; j++) {

				// Se crea un array que sera una de las filas de la tabla
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

				// Se añade al modelo la fila completa.
				vistaMailPrincipal.insertRow(fila);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Configura los paneles de mail
	 */
	private void configurarPanelesAdmin() {
		for (int i = 0; i < 3; i++) {
			vistaMailPrincipal.crearPanelesAdmin(i);
		}
		vistaMailPrincipal.ordenarPaneles();
		vistaMailPrincipal.configurarPanelAdminNorte();
		vistaMailPrincipal.configurarPanelAdminEste();
		vistaMailPrincipal.configurarPanelAdminCentral();
	}

	/**
	 * Configura título de barra superior
	 */
	private void configurarTitulo() {
		vista.setIcono("src/opcionesprincipal/1.png");
		vista.setTitulo("Mail MOKE");
	}

	/**
	 * Actualiza el contenido de la ventana
	 */
	private void actualizarVentana() {
		vista.repaint();
		vista.pack();
		vista.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	/**
	 * Obtener datos de correos recibidos
	 * @throws MessagingException
	 */
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
		String correo = modelo.getCorreo();// Vlogin.getEmailText(); //correo
																						// de la persona que rebibe los
																						// correo para leerlo
		String password = modelo.getPasswordCorreo();		// Vlogin.getPassword().getText();// y su contraseña
		store.connect("pop.gmail.com", correo, password);
		Folder folder = store.getFolder("INBOX");
		folder.open(Folder.READ_ONLY);
		mensajes = folder.getMessages();
		Collections.reverse(Arrays.asList(mensajes)); // darle la vuelta al array para que aparezaca el correo ultimo
														// recibido el primero
	}

	/**
	 * Muestra ventana emergente con contenido de correo
	 * @throws MessagingException
	 */
	public void verMensaje() throws MessagingException {
		String mensaje = "";
		String[] destinatario = null;
		try {
			mensaje = TipoMensaje.getTextFromMessage(mensajes[vistaMailPrincipal.getFila()]);
			destinatario = mensajes[vistaMailPrincipal.getFila()].getFrom()[0].toString().split("<");
			destinatario[destinatario.length - 1] = destinatario[destinatario.length - 1].replace('>', ' ');

		} catch (Exception e) {}
		String info = modelo.getContextoMail()[0] + "\n";
		info += modelo.getContextoMail()[1] + destinatario[destinatario.length - 1] + "\n" + "" + "\n";
		info += modelo.getContextoMail()[2] + mensajes[vistaMailPrincipal.getFila()].getSubject() + "\n";
		info += modelo.getContextoMail()[3] + mensajes[vistaMailPrincipal.getFila()].getSentDate().toString() + "\n" + "" + "\n";
		info += modelo.getContextoMail()[4] + mensaje + "\n" + "" + "\n";

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
