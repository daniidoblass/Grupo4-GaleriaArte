package vista;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.mail.MessagingException;
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
import controlador.Eventos;
import modelo.Modelo;

public class VistaMailEnviarCorreo extends JFrame {

	private ControladorEnviarMail cliente;
	private JPanel contentPane;
	private JTextField destinatario;
	private JTextField asunto;
	private JTextArea cuerpo;
	private JLabel lblAsunto;
	private JLabel lblDestinatario;
	private JLabel lblCuerpo;
	private JButton btnEnviar;
	private JButton btnCancelar;
	private JButton AdjuntarArchivo;
	private JFileChooser jFileChooser;
	private ControladorAdjuntarArchivoGmail controladorAdjuntarArchivoGmail;
	private String ruta;
	private String nombreArchivo;
	private Conexion conexion ;
	private Modelo modelo;
	private Eventos eventos;

	public VistaMailEnviarCorreo(Eventos eventos, Conexion conexion) {
		this.controladorAdjuntarArchivoGmail = new ControladorAdjuntarArchivoGmail(this);
		this.conexion = conexion;
		this.modelo = modelo;
		this.eventos = eventos;
		this.cliente = new ControladorEnviarMail(this,conexion,eventos);
		this.jFileChooser = new JFileChooser();
		propiedades();
		generarContenido();
		propiedades();
		generarContenido();
	}

	private void generarContenido() {
		destinatario = new JTextField();
		destinatario.setBounds(48, 78, 366, 19);
		contentPane.add(destinatario);
		destinatario.setColumns(10);

		asunto = new JTextField();
		asunto.setBounds(48, 149, 366, 19);
		contentPane.add(asunto);
		asunto.setColumns(10);

		cuerpo = new JTextArea();
		cuerpo.setBounds(48, 222, 366, 166);
		contentPane.add(cuerpo);

		lblAsunto = new JLabel("ASUNTO");
		lblAsunto.setForeground(Color.WHITE);
		lblAsunto.setBounds(48, 126, 60, 13);
		contentPane.add(lblAsunto);

		lblDestinatario = new JLabel("DESTINATARIO");
		lblDestinatario.setForeground(Color.WHITE);
		lblDestinatario.setBounds(48, 55, 89, 13);
		contentPane.add(lblDestinatario);

		lblCuerpo = new JLabel("CUERPO");
		lblCuerpo.setForeground(Color.WHITE);
		lblCuerpo.setBounds(48, 199, 60, 13);
		contentPane.add(lblCuerpo);

		btnEnviar = new JButton("Enviar");
		btnEnviar.setForeground(Color.WHITE);
		btnEnviar.setBackground(new java.awt.Color(27, 79, 142, 255));
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
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEnviar.setBounds(329, 441, 85, 21);
		contentPane.add(btnEnviar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBackground(new java.awt.Color(27, 79, 142, 255));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				destinatario.setText("");
				asunto.setText("");
				cuerpo.setText("");
				setVisible(false);
			}
		});
		btnCancelar.setBounds(48, 441, 85, 21);
		contentPane.add(btnCancelar);

		AdjuntarArchivo = new JButton("Enviar Adjunto");
		AdjuntarArchivo.setForeground(Color.WHITE);
		AdjuntarArchivo.setBackground(new Color(27, 79, 142));
		AdjuntarArchivo.setBounds(190, 441, 85, 21);
		AdjuntarArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladorAdjuntarArchivoGmail.obtenerFicheroSeleccionado();
			}

		});
		contentPane.add(AdjuntarArchivo);
	}

	private void propiedades() {
		setBounds(100, 100, 485, 532);
		contentPane = new JPanel();
		contentPane.setBackground(new java.awt.Color(61, 116, 181, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
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
