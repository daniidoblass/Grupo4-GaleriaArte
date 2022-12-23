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

public class VistaConfigPrincipal extends JFrame{
    
    private Modelo modelo;
    private Vista vista;
   
    private JPanel panelCentral = new JPanel();
    private ArrayList<JButton> botonesMenu = new ArrayList<>();
    private JComboBox<String> comboCorreos = new JComboBox<String>();
    private JPasswordField password;

    public VistaConfigPrincipal(Modelo modelo, Vista vista){
        this.modelo = modelo;
        this.vista = vista;
        actualizarVentanaPrincipal();
        configurarPanelCentral();
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
        panelCentral.setLayout(new GridLayout(3,1));
        panelCentral.setOpaque(false);
        vista.getPaneles().get(2).add(panelCentral);
    }
    
    /*
     * Botones de Opciones
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
    
    public ArrayList<JButton> getBotonesMenu() {
    	return botonesMenu;
    }
    
    /*
     * Configurar ComboBox Correo
     */
    public void agregarCorreoComboBox(String texto) {
    	comboCorreos.addItem(texto);
    }
    
    /*
	 * Mensaje Emergente
	 */
    public String mostrarInputEmergente(String titulo, String mensaje) {
		return JOptionPane.showInputDialog(null, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
	}
    
    public void mostrarMensajeEmergente(String titulo, String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
	}
    
    public String mostrarComboBoxEmergente(String titulo, String mensaje) {
		JOptionPane.showMessageDialog(null, comboCorreos, titulo, JOptionPane.INFORMATION_MESSAGE);
		return comboCorreos.getSelectedItem().toString();
	}
    
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
    
    public String getPassword() {
		return String.valueOf(password.getPassword());
	}
}


