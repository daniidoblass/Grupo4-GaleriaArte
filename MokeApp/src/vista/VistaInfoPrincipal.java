/**
 * 
 * Clase VistaInfoPrincipal
 * 
 * Muestra secciones de ayuda para el usuario
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

public class VistaInfoPrincipal extends JFrame{
    
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
     * panelCentral - tipo JPanel - panel central de ventana
     */
    private JPanel panelCentral = new JPanel();
    
    /**
     * botonesMenu - tipo ArrayList<JButton> - botones del menú
     */
    private ArrayList<JButton> botonesMenu = new ArrayList<>();

    /**
     * Constructor por defecto de vista de información
     * @param modelo - tipo Modelo - contiene textos del programa
     * @param vista - tipo Vista - vista principal del programa
     */
    public VistaInfoPrincipal(Modelo modelo, Vista vista){
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
        panelCentral.setLayout(new GridLayout(6,1));
        panelCentral.setOpaque(false);
        vista.getPaneles().get(2).add(panelCentral);
    }
    
    /**
     * Botones de Opciones
     * @param i - tipo int - contador de boton
     * @param nombreOpcion - tipo String - nombre de opcion
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
     * @return botonesMenu - tipo ArrayList<JButton> - botones del menu
     */
    public ArrayList<JButton> getBotonesMenu() {
    	return botonesMenu;
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


