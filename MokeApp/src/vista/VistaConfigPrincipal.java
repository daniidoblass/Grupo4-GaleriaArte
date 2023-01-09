/**
 * 
 * Clase VistaConfigPrincipal
 * 
 * Vista de la configuración del usuario
 * 
 * @author Samuel Acosta Fernandez
 * @date 12/12/2022
 * @version 01
 */

package vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import modelo.Modelo;

public class VistaConfigPrincipal extends JFrame{
    
	/**
	 * modelo - tipo Modelo - contiene textos del programa
	 */
    private Modelo modelo;
    
    /**
     * vista - tipo Vista - vista principal del programa
     */
    private Vista vista;
   
    /**
     * panelCentral - tipo JPanel - panel central de ventana
     */
    private JPanel panelCentral = new JPanel();
    
    /**
     * botonesMenu - tipo ArrayList<JButton> - botones de configuración
     */
    private ArrayList<JButton> botonesMenu = new ArrayList<>();
    
    /**
     * comboCorreos - tipo JComboBox<String> - combobox de correos
     */
    private JComboBox<String> comboCorreos = new JComboBox<String>();
    
    /**
     * password - tipo JPasswordField - introducir password
     */
    private JPasswordField password;

    /**
     * Constructor por defecto de vista de configuración
     * @param modelo - tipo Modelo - contiene textos del programa
     * @param vista - tipo Vista - vista principal del programa
     */
    public VistaConfigPrincipal(Modelo modelo, Vista vista){
        this.modelo = modelo;
        this.vista = vista;
        actualizarVentanaPrincipal();
        configurarPanelCentral();
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
        panelCentral.setLayout(new GridLayout(3,1));
        panelCentral.setOpaque(false);
        vista.getPaneles().get(2).add(panelCentral);
    }
    
    /**
     * Botones de Opciones
     * @param i - tipo int - número botón
     * @param nombreOpcion - tipo String - nombre de opción
     * @param nombreImagen - tipo String - nombre de imagen
     */
    public void crearBotonMenu(int i, String nombreOpcion, String nombreImagen) {
    	botonesMenu.add(new JButton());
        botonesMenu.get(i).setName(modelo.getTextoOpcionesMenu()[i]);
    	ImageIcon icon = new ImageIcon("src/iconos/" + nombreImagen + ".png");
		
	// ponerle texto e icono
    	botonesMenu.get(i).setText(nombreOpcion);
    	botonesMenu.get(i).setIcon(new ImageIcon(icon.getImage().getScaledInstance(40,40,java.awt.Image.SCALE_SMOOTH)));
		
	// Texto en el centro y debajo del icono
    	botonesMenu.get(i).setHorizontalTextPosition( SwingConstants.RIGHT );
    	botonesMenu.get(i).setVerticalTextPosition( SwingConstants.CENTER );
		
	// Configurar propiedades del boton
    	botonesMenu.get(i).setOpaque(false);
        botonesMenu.get(i).setContentAreaFilled(false);
    	botonesMenu.get(i).setBorderPainted(false);
    	botonesMenu.get(i).setPreferredSize(new Dimension(200, 60));
    	botonesMenu.get(i).setHorizontalAlignment(SwingConstants.LEFT);
		
	// Personalizar texto
    	botonesMenu.get(i).setForeground(Color.WHITE);
    	botonesMenu.get(i).setFont(new Font("arial",0,20));
		
    	panelCentral.add(botonesMenu.get(i));
    }
    
    /**
     * Obtener botones del menu
     * @return botonesMenu - tipo ArrayList<JButton> - botones del menú
     */
    public ArrayList<JButton> getBotonesMenu() {
    	return botonesMenu;
    }
    
    /**
     * Configurar ComboBox Correo
     * @param texto - tipo String - texto de correo
     */
    public void agregarCorreoComboBox(String texto) {
    	comboCorreos.addItem(texto);
    }
    
    /**
	 * Mensaje Emergente para input
	 * @param titulo - tipo String - titulo de ventana
	 * @param mensaje - tipo String - mensaje de ventana
	 */
    public String mostrarInputEmergente(String titulo, String mensaje) {
		return JOptionPane.showInputDialog(null, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
	}
    
    /**
	 * Mensaje Emergente
	 * @param titulo - tipo String - titulo de ventana
	 * @param mensaje - tipo String - mensaje de ventana
	 */
    public void mostrarMensajeEmergente(String titulo, String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
	}
    
    /**
	 * Mensaje Emergente con combo box
	 * @param titulo - tipo String - titulo de ventana
	 * @param mensaje - tipo String - mensaje de ventana
	 */
    public String mostrarComboBoxEmergente(String titulo, String mensaje) {
		JOptionPane.showMessageDialog(null, comboCorreos, titulo, JOptionPane.INFORMATION_MESSAGE);
		return comboCorreos.getSelectedItem().toString();
	}
    
    /**
	 * Mensaje Emergente para password
	 * @param titulo - tipo String - titulo de ventana
	 * @param mensaje - tipo String - mensaje de ventana
	 */
    public String mostrarMensajePassword(String titulo, String mensaje) {
    	JPanel panel = new JPanel();
    	panel.setPreferredSize(new Dimension(200,50));
    	panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    	JLabel label = new JLabel(mensaje);
    	label.setAlignmentX(Component.LEFT_ALIGNMENT);
    	password = new JPasswordField(30);
    	password.setPreferredSize(new Dimension(50,10));
    	password.setAlignmentX(Component.LEFT_ALIGNMENT);
    	panel.add(label);
    	panel.add(password);
    	String[] options = new String[]{"Aceptar", "Cancelar"};
    	JOptionPane.showOptionDialog(null, panel, titulo,
    	                         JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
    	                         null, options, options[1]);
		return String.valueOf(password.getPassword());
    }
    
    /**
     * Obtener password insertada
     * @return password - tipo String - password insertada
     */
    public String getPassword() {
		return String.valueOf(password.getPassword());
	}
}


