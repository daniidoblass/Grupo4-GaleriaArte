/**
 * @author Samuel Acosta Fernandez
 * @date 12/12/2022
 * @version 01
 */
package vista;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import modelo.Modelo;
import controlador.Eventos;

public class VistaLogin extends JFrame{
    
    private Modelo modelo;
    private Vista vista;
    private Eventos eventos;
    
    private JPanel panelLogin = new JPanel();
    private JPanel panelLogo = new JPanel();
    private ArrayList<JPanel> todosPaneles = new ArrayList<>();
	private JButton botonLogin = new JButton("LOGIN");
	private ArrayList<JLabel> todasLabels = new ArrayList<>();
	private JTextField usuario = new JTextField(30);
	private JPasswordField password = new JPasswordField(30);
	private JButton icono = new JButton();

    private JPanel panelCentral = new JPanel();

    public VistaLogin(Modelo modelo, Vista vista){
        this.modelo = modelo;
        this.vista = vista;
        actualizarVentanaPrincipal();
        configurarPanelCentral();
        crearLogin();
    }

	/*
     * Actualizamos la ventana principal
    */
    private void actualizarVentanaPrincipal() {
        vista.getPaneles().get(1).setVisible(false);
        vista.getPaneles().get(2).removeAll();
    }
    
    /*
     * Configuracion de Ventana Principal
     */
    public void configurarPanelCentral() {
        panelCentral.setLayout(new GridLayout(3,5));
        panelCentral.setOpaque(false);
        vista.getPaneles().get(2).add(panelCentral);
    }
    
    private void crearLogin() {
    	// Crear paneles.
		crearPaneles();

		// Creacion de botones.
		configurarBotonLogin();

		// Creacion de labels.
		crearLabels();

		//Crear icono.
		crearIcono();

		// Poner propiedades
		propiedadesVista();

		// Añadir datos a la ventana.
		agregarDatos();
	}
    

	private void crearIcono() {
		// TODO Auto-generated method stub
		ImageIcon icon = new ImageIcon("src/subiconos/usuario.png");
		icono.setIcon(new ImageIcon(icon.getImage().getScaledInstance(100,100,java.awt.Image.SCALE_SMOOTH)));
    	icono.setOpaque(false);
        icono.setContentAreaFilled(false);
    	icono.setBorderPainted(false);
    	icono.setPreferredSize(new Dimension(60, 60));
    	panelLogo.add(icono);
	}

	private void agregarDatos() {
		todosPaneles.get(1).add(todasLabels.get(1));
		todosPaneles.get(2).add(usuario);
		todosPaneles.get(3).add(todasLabels.get(2));
		todosPaneles.get(4).add(password);
		todosPaneles.get(5).add(botonLogin);

		for (int i = 0; i < todosPaneles.size(); i++) {
			panelLogin.add(todosPaneles.get(i));
		}
	}

	private void crearPaneles() {
		for (int i = 0; i < 6; i++) {
			JPanel paneles = new JPanel();
			paneles.setOpaque(false);
			todosPaneles.add(paneles);
		}
	}

	private void crearLabels() {
		
		JLabel label1 = new JLabel("ACCESO");
		label1.setForeground(Color.WHITE);
		label1.setFont(new Font("arial",0,20));
		todasLabels.add(label1);
		
		
		JLabel label2 = new JLabel("USUARIO");
		label2.setForeground(Color.WHITE);
		label2.setFont(new Font("arial",0,20));
		todasLabels.add(label2);
		
		JLabel label3 = new JLabel("CONTRASEÑA");
		label3.setForeground(Color.WHITE);
		label3.setFont(new Font("arial",0,20));
		todasLabels.add(label3);
	}

	private void configurarBotonLogin() {
		botonLogin.setName("LOGIN");
		botonLogin.setForeground(Color.WHITE);
		botonLogin.setFont(new Font("calibri",0,19));
		botonLogin.setBackground(Color.decode("#193349"));
	}
	
	public JButton getBotonLogin() {
		return botonLogin;
	}

	private void propiedadesVista() {
		panelLogo.setLayout(new GridLayout(1,1));
		panelLogo.setOpaque(false);
		panelLogin.setLayout(new GridLayout(6,1));
		panelLogin.setOpaque(false);
		panelCentral.add(panelLogo);
        panelCentral.add(panelLogin);
	}

	public JTextField getUsuario() {
		return usuario;
	}
    
	public JPasswordField getPassword() {
		return password;
	}
	
	/*
	 * Mensaje Emergente
	 */
	public void mostrarMensajeEmergente(String titulo, String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
	}
}


