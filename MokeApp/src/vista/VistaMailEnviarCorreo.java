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

	private ControladorEnviarMail cliente;
	private FondoDegradado panelPrincipal;
	private JTextField destinatario;
	private JTextField asunto;
	private JTextArea cuerpo;
	private JLabel lblAsunto;
	private JLabel lblDestinatario;
	private JLabel lblCuerpo;
	private JButton btnEnviar;
	private JButton btnCancelar;
	private JButton btnAdjuntar;
	private JFileChooser jFileChooser;
	private ControladorAdjuntarArchivoGmail controladorAdjuntarArchivoGmail;
	private String ruta;
	private String nombreArchivo;
	private Modelo modelo;
	private JLabel lblAdjunto;

	public VistaMailEnviarCorreo(Conexion conexion, Modelo modelo) {
		this.controladorAdjuntarArchivoGmail = new ControladorAdjuntarArchivoGmail(this);
		this.modelo = modelo;
		this.cliente = new ControladorEnviarMail(this, conexion, modelo);
		this.jFileChooser = new JFileChooser();
		configurarVentana();
		configurarPanel();
		generarContenido();
	}

	private void configurarVentana() {
		this.setBounds(100, 100, 485, 532);
		this.setLocationRelativeTo(null);
		this.setIconImage(new ImageIcon("src/subiconos/enviar.png").getImage());
		this.setTitle("Redactar");
		this.setResizable(false);
	}

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
	
	public JLabel getLblAdjuntar() {
		return lblAdjunto;
	}
	
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

	private void configurarCuerpo() {
		// JTextField
		cuerpo = new JTextArea();
		cuerpo.setBounds(48, 222, 366, 166);
		panelPrincipal.add(cuerpo);
		
		// JLabel
		lblCuerpo = new JLabel("CUERPO");
		lblCuerpo.setForeground(Color.WHITE);
		lblCuerpo.setBounds(48, 199, 60, 13);
		panelPrincipal.add(lblCuerpo);
	}

	private void configurarAsunto() {
		// JTextField
		asunto = new JTextField();
		asunto.setBounds(48, 149, 366, 19);
		panelPrincipal.add(asunto);
		asunto.setColumns(10);
		
		// JLabel
		lblAsunto = new JLabel("ASUNTO");
		lblAsunto.setForeground(Color.WHITE);
		lblAsunto.setBounds(48, 126, 60, 13);
		panelPrincipal.add(lblAsunto);
	}

	private void configurarDestinatario() {
		// JTextField
		destinatario = new JTextField();
		destinatario.setBounds(48, 78, 366, 19);
		panelPrincipal.add(destinatario);
		destinatario.setColumns(10);
		
		// JLabel
		lblDestinatario = new JLabel("DESTINATARIO");
		lblDestinatario.setForeground(Color.WHITE);
		lblDestinatario.setBounds(48, 55, 89, 13);
		panelPrincipal.add(lblDestinatario);
	}

	private void configurarPanel() {
		panelPrincipal = new FondoDegradado();
		panelPrincipal.setColor2(new Color(8, 143, 173));
		panelPrincipal.setLayout(null);
		this.setContentPane(panelPrincipal);
	}

	public void configurarJFileChooser() {
		jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		jFileChooser.setDialogTitle("Selecciona un archivo");
	}

	public int mostrarJFileChooser() {
		return jFileChooser.showDialog(jFileChooser, "Cargar");
	}

	public JFileChooser getJFileChooser() {
		return jFileChooser;
	}
}
