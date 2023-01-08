/**
 * 
 * Clase VistaLogin
 * 
 * Muestra ventana para inicio de sesión
 * 
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
    
	/**
	 * modelo - tipo Modelo - contiene textos del programa
	 */
    private Modelo modelo;
    
    /**
     * vista - tipo Vista - vista principal del programa
     */
    private Vista vista;
    
    /**
     * eventos - tipo Eventos - eventos principales 
     */
    private Eventos eventos;
    
    /**
     * panelLogin - tipo JPanel - panel de login
     */
    private JPanel panelLogin = new JPanel();
    
    /**
     * panelLogo - tipo JPanel - panel de logo de login
     */
    private JPanel panelLogo = new JPanel();
    
    /**
     * todosPaneles - tipo ArrayList<JPanel> - paneles login
     */
    private ArrayList<JPanel> todosPaneles = new ArrayList<>();
    
    /**
     * botonLogin - tipo JButton - boton de login
     */
	private JButton botonLogin = new JButton("LOGIN");
	
	/**
	 * todasLabels - tipo ArrayList<JLabel> - textos de login
	 */
	private ArrayList<JLabel> todasLabels = new ArrayList<>();
	
	/**
	 * usuario - tipo JTextField - introducir texto
	 */
	private JTextField usuario = new JTextField(30);
	
	/**
	 * password - tipo JPasswordField - introducir password
	 */
	private JPasswordField password = new JPasswordField(30);
	
	/**
	 * icono - tipo JButton - icono de login
	 */
	private JButton icono = new JButton();

	/**
	 * panelCentral - tipo JPanel - panel central de login
	 */
    private JPanel panelCentral = new JPanel();

    /**
     * Constructor por defecto de login
     * @param modelo - tipo Modelo - contiene textos del programa
     * @param vista - tipo Vista - vista principal del programa
     */
    public VistaLogin(Modelo modelo, Vista vista){
        this.modelo = modelo;
        this.vista = vista;
        actualizarVentanaPrincipal();
        configurarPanelCentral();
        crearLogin();
    }

	/**
     * Actualizamos la ventana principal
     */
    private void actualizarVentanaPrincipal() {
        vista.getPaneles().get(1).setVisible(false);
        vista.getPaneles().get(2).removeAll();
    }
    
    /**
     * Configuracion de Ventana Principal
     */
    public void configurarPanelCentral() {
        panelCentral.setLayout(new GridLayout(3,5));
        panelCentral.setOpaque(false);
        vista.getPaneles().get(2).add(panelCentral);
    }
    
    /**
     * Configurar panel de login
     */
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
    
	/**
	 * Configurar icono de login
	 */
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

	/**
	 * Agregar componentes login en paneles
	 */
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

	/**
	 * Crear paneles login
	 */
	private void crearPaneles() {
		for (int i = 0; i < 6; i++) {
			JPanel paneles = new JPanel();
			paneles.setOpaque(false);
			todosPaneles.add(paneles);
		}
	}

	/**
	 * Configurar labels
	 */
	private void crearLabels() {
		
		JLabel label1 = new JLabel(modelo.getTextoAcceso());
		label1.setForeground(Color.WHITE);
		label1.setFont(new Font("arial",0,20));
		todasLabels.add(label1);
		
		
		JLabel label2 = new JLabel(modelo.getTextoUsuario());
		label2.setForeground(Color.WHITE);
		label2.setFont(new Font("arial",0,20));
		todasLabels.add(label2);
		
		JLabel label3 = new JLabel(modelo.getTextoPassword());
		label3.setForeground(Color.WHITE);
		label3.setFont(new Font("arial",0,20));
		todasLabels.add(label3);
	}

	/**
	 * Configurar boton de login
	 */
	private void configurarBotonLogin() {
		botonLogin.setName(modelo.getTextosGenerales()[6]);
		botonLogin.setForeground(Color.WHITE);
		botonLogin.setFont(new Font("calibri",0,19));
		botonLogin.setBackground(Color.decode("#193349"));
	}
	
	/**
	 * Obtener boton de login
	 * @return botonLogin - tipo JButton - boton de login
	 */
	public JButton getBotonLogin() {
		return botonLogin;
	}

	/**
	 * Configurar vista de login
	 */
	private void propiedadesVista() {
		panelLogo.setLayout(new GridLayout(1,1));
		panelLogo.setOpaque(false);
		panelLogin.setLayout(new GridLayout(6,1));
		panelLogin.setOpaque(false);
		panelCentral.add(panelLogo);
        panelCentral.add(panelLogin);
	}

	/**
	 * Obtener usuario
	 * @return usuario - tipo JTextField - introducir usuario
	 */
	public JTextField getUsuario() {
		return usuario;
	}
    
	/**
	 * Obtener password
	 * @return password - tipo JPasswordField - introducir password
	 */
	public JPasswordField getPassword() {
		return password;
	}
	
	/**
	 * Mensaje Emergente
	 * @param titulo - tipo String - titulo de ventana
	 * @param mensaje - tipo String - mensaje de ventana
	 */
	public void mostrarMensajeEmergente(String titulo, String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
	}
}


