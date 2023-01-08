/**
 * 
 * Clase VistaMailEnviarCorreo
 * 
 * Muestra ventana emergente para redactar correo
 * 
 * @author Javier Jimenez Torres
 * @date 09/12/2022
 * @version 01
 */

package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.mail.MessagingException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import conexion.Conexion;
import controlador.ControladorAdjuntarArchivoGmail;
import controlador.ControladorEnviarMail;
import modelo.Modelo;

public class VistaMailEnviarCorreo extends JFrame {

	/**
	 * cliente - tipo ControladorEnviarMail - controlador de enviar mail
	 */
	private ControladorEnviarMail cliente;
	
	/**
	 * panelPrincipal - tipo FondoDegradado - panel fondo degradado
	 */
	private FondoDegradado panelPrincipal;
	
	/**
	 * destinatario - tipo JTextField - introducir destinatario
	 */
	private JTextField destinatario;
	
	/**
	 * asunto - tipo JTextField - introducir asunto
	 */
	private JTextField asunto;
	
	/**
	 * cuerpo - tipo JTextArea - introducir cuerpo mensaje
	 */
	private JTextArea cuerpo;
	
	/**
	 * lblAsunto - tipo JLabel - texto asunto
	 */
	private JLabel lblAsunto;
	
	/**
	 * lblDestinatario - tipo JLabel - texto destinatario
	 */
	private JLabel lblDestinatario;
	
	/**
	 * lblCuerpo - tipo JLabel - texto cuerpo
	 */
	private JLabel lblCuerpo;
	
	/**
	 * btnEnviar - tipo JButton - boton para enviar
	 */
	private JButton btnEnviar;
	
	/**
	 * btnCancelar - tipo JButton - boton para cancelar
	 */
	private JButton btnCancelar;
	
	/**
	 * btnAdjuntar - tipo JButton - boton para adjuntar
	 */
	private JButton btnAdjuntar;
	
	/**
	 * jFileChooser - tipo JFileChooser - ventana para obtener archivo local
	 */
	private JFileChooser jFileChooser;
	
	/**
	 * controladorAdjuntarArchivoGmail - tipo ControladorAdjuntarArchivoGmail - controlador adjuntar
	 */
	private ControladorAdjuntarArchivoGmail controladorAdjuntarArchivoGmail;
	
	/**
	 * ruta - tipo String - ruta de archivo local
	 */
	private String ruta;
	
	/**
	 * nombreArchivo - tipo String - nombre de archivo local
	 */
	private String nombreArchivo;
	
	/**
	 * modelo - tipo Modelo - contiene textos del programa
	 */
	private Modelo modelo;
	
	/**
	 * lblAdjunto - tipo JLabel - texto de archivo adjunto
	 */
	private JLabel lblAdjunto;

	/**
	 * Constructor por defecto de vista enviar correo
	 * @param conexion - tipo Conexion - conexion con base de datos
	 * @param modelo - tipo Modelo - contiene textos del programa
	 */
	public VistaMailEnviarCorreo(Conexion conexion, Modelo modelo) {
		this.controladorAdjuntarArchivoGmail = new ControladorAdjuntarArchivoGmail(this);
		this.modelo = modelo;
		this.cliente = new ControladorEnviarMail(this, conexion, modelo);
		this.jFileChooser = new JFileChooser();
		configurarVentana();
		configurarPanel();
		generarContenido();
	}

	/**
	 * Configurar ventana 
	 */
	private void configurarVentana() {
		this.setBounds(100, 100, 485, 532);
		this.setLocationRelativeTo(null);
		this.setIconImage(new ImageIcon("src/subiconos/enviar.png").getImage());
		this.setTitle("Redactar");
		this.setResizable(false);
	}

	/**
	 * Configurar componentes
	 */
	private void generarContenido() {
		// Configurar Destinatario
		configurarDestinatario();

		// Configurar Asunto
		configurarAsunto();
		
		// Configurar Cuerpo
		configurarCuerpo();
		
		// Configurar Enviar
		configurarEnviar();
		
		// Configurar Adjuntar
		configurarAdjuntar();
	}

	/**
	 * Configurar adjuntar
	 */
	private void configurarAdjuntar() {
		// JLabel
		lblAdjunto = new JLabel("Sin archivo adjunto");
		lblAdjunto.setForeground(Color.WHITE);
		lblAdjunto.setBounds(48, 441, 200, 45);
		lblAdjunto.setMaximumSize(new Dimension(180, 100));
		lblAdjunto.setVisible(false);
		panelPrincipal.add(lblAdjunto);
				
		// JButton
		btnAdjuntar = new JButton();
		ImageIcon icon = new ImageIcon("src/subiconos/adjuntar.png");
		btnAdjuntar.setIcon(new ImageIcon(icon.getImage().getScaledInstance(35,35,java.awt.Image.SCALE_SMOOTH)));
		btnAdjuntar.setForeground(Color.WHITE);
		btnAdjuntar.setBackground(new Color(27, 79, 142));
		btnAdjuntar.setBounds(320, 441, 45, 45);
		btnAdjuntar.setOpaque(false);
		btnAdjuntar.setBorderPainted(false);
		btnAdjuntar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladorAdjuntarArchivoGmail.obtenerFicheroSeleccionado();
			}

		});
		panelPrincipal.add(btnAdjuntar);
	}
	
	/**
	 * Obtener texto adjunto
	 * @return lblAdjunto - tipo String - texto adjuntar
	 */
	public JLabel getLblAdjuntar() {
		return lblAdjunto;
	}
	
	/**
	 * Configurar enviar
	 */
	private void configurarEnviar() {
		btnEnviar = new JButton();
		ImageIcon icon = new ImageIcon("src/subiconos/enviar.png");
		btnEnviar.setIcon(new ImageIcon(icon.getImage().getScaledInstance(40,40,java.awt.Image.SCALE_SMOOTH)));
		btnEnviar.setForeground(Color.WHITE);
		btnEnviar.setBackground(new java.awt.Color(27, 79, 142, 255));
		btnEnviar.setBounds(370, 441, 45, 45);
		btnEnviar.setOpaque(false);
		btnEnviar.setBorderPainted(false);
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (cliente.enviarConGMail(destinatario.getText(), asunto.getText(), cuerpo.getText(),
							controladorAdjuntarArchivoGmail.getRuta(),
							controladorAdjuntarArchivoGmail.getNombreArchivo()).equals("Correo Enviado")) {
						destinatario.setText("");
						asunto.setText("");
						cuerpo.setText("");
						dispose();
					}
				} catch (MessagingException e1) {
					e1.printStackTrace();
				}
			}
		});
		panelPrincipal.add(btnEnviar);
	}

	/**
	 * Configurar cuerpo
	 */
	private void configurarCuerpo() {
		// JTextField
		cuerpo = new JTextArea();
		cuerpo.setBounds(48, 222, 366, 166);
		panelPrincipal.add(cuerpo);
		
		// JLabel
		lblCuerpo = new JLabel(modelo.getTextoCuerpo());
		lblCuerpo.setForeground(Color.WHITE);
		lblCuerpo.setBounds(48, 199, 60, 13);
		panelPrincipal.add(lblCuerpo);
	}

	/**
	 * Configurar asunto
	 */
	private void configurarAsunto() {
		// JTextField
		asunto = new JTextField();
		asunto.setBounds(48, 149, 366, 19);
		panelPrincipal.add(asunto);
		asunto.setColumns(10);
		
		// JLabel
		lblAsunto = new JLabel(modelo.getTextoAsunto());
		lblAsunto.setForeground(Color.WHITE);
		lblAsunto.setBounds(48, 126, 60, 13);
		panelPrincipal.add(lblAsunto);
	}

	/**
	 * Configurar destinatario
	 */
	private void configurarDestinatario() {
		// JTextField
		destinatario = new JTextField();
		destinatario.setBounds(48, 78, 366, 19);
		panelPrincipal.add(destinatario);
		destinatario.setColumns(10);
		
		// JLabel
		lblDestinatario = new JLabel(modelo.getTextoDestinatario());
		lblDestinatario.setForeground(Color.WHITE);
		lblDestinatario.setBounds(48, 55, 89, 13);
		panelPrincipal.add(lblDestinatario);
	}

	/**
	 * Configurar panel enviar correo
	 */
	private void configurarPanel() {
		panelPrincipal = new FondoDegradado();
		panelPrincipal.setColor2(new Color(8, 143, 173));
		panelPrincipal.setLayout(null);
		this.setContentPane(panelPrincipal);
	}

	/**
	 * Configurar JFileChooser
	 */
	public void configurarJFileChooser() {
		jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		jFileChooser.setDialogTitle(modelo.getTextoTituloEnviarCorreo());
	}

	/**
	 * Mostrar JFileChooser
	 * @return jFileChooser.showDialog - tipo int - respuesta file chooser
	 */
	public int mostrarJFileChooser() {
		return jFileChooser.showDialog(jFileChooser, "Cargar");
	}

	/**
	 * Obtener JFileChooser
	 * @return jFileChooser - tipo JFileChooser - ventana seleccionar archivo local
	 */
	public JFileChooser getJFileChooser() {
		return jFileChooser;
	}
}
