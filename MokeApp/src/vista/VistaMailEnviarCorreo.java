package vista;

import java.awt.Color;
import java.awt.Font;
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

import controlador.ControladorAdjuntarArchivoGmail;
import controlador.ControladorEnviarMail;

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
	private JButton adjuntarArchivo;
	private JFileChooser jFileChooser;
	private ControladorAdjuntarArchivoGmail controladorAdjuntarArchivoGmail;
	private String ruta;
	private String nombreArchivo;

	public VistaMailEnviarCorreo() {
		this.controladorAdjuntarArchivoGmail = new ControladorAdjuntarArchivoGmail(this);
		this.cliente = new ControladorEnviarMail(this);
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
		lblAsunto.setFont(new Font("arial",0,12));
		lblAsunto.setBounds(48, 126, 60, 13);
		contentPane.add(lblAsunto);

		lblDestinatario = new JLabel("DESTINATARIO");
		lblDestinatario.setForeground(Color.WHITE);
		lblDestinatario.setFont(new Font("arial",0,12));
		lblDestinatario.setBounds(48, 55, 89, 13);
		contentPane.add(lblDestinatario);

		lblCuerpo = new JLabel("CUERPO");
		lblCuerpo.setForeground(Color.WHITE);
		lblCuerpo.setFont(new Font("arial",0,12));
		lblCuerpo.setBounds(48, 199, 60, 13);
		contentPane.add(lblCuerpo);

		btnEnviar = new JButton("Enviar");
		btnEnviar.setForeground(Color.WHITE);
		btnEnviar.setFont(new Font("calibri",0,12));
		btnEnviar.setBackground(Color.decode("#193349"));
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
				} catch (Exception e1) {}
			}
		});
		btnEnviar.setBounds(329, 441, 85, 21);
		contentPane.add(btnEnviar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setFont(new Font("calibri",0,12));
		btnCancelar.setBackground(Color.decode("#193349"));
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

		adjuntarArchivo = new JButton("Adjuntar");
		adjuntarArchivo.setForeground(Color.WHITE);
		adjuntarArchivo.setFont(new Font("calibri",0,12));
		adjuntarArchivo.setBackground(Color.decode("#193349"));
		adjuntarArchivo.setBounds(190, 441, 85, 21);
		adjuntarArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladorAdjuntarArchivoGmail.obtenerFicheroSeleccionado();
			}

		});
		contentPane.add(adjuntarArchivo);
	}

	private void propiedades() {
		setBounds(100, 100, 485, 532);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(8, 143, 173));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setIconImage(new ImageIcon("src/subiconos/enviar.png").getImage());
		setTitle("Redactar");
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
