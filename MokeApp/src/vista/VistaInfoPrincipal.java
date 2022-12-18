/**
 * @author Samuel Acosta Fernandez
 * @date 09/02/2022
 * @version 01
 */
package vista;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import modelo.Modelo;
import controlador.Eventos;

public class VistaInfoPrincipal extends JFrame{
    
    private Modelo modelo;
    private Vista vista;
    private Eventos eventos;
<<<<<<< Updated upstream
    private ArrayList<JLabel> labelsInfo = new ArrayList<>();

=======
    
>>>>>>> Stashed changes
    private JPanel panelCentral = new JPanel();
    private ArrayList<JButton> botonesMenu = new ArrayList<>();

    public VistaInfoPrincipal(Modelo modelo, Vista vista){
        this.modelo = modelo;
        this.vista = vista;
        crearLabels();
        actualizarVentanaPrincipal();
        configurarPanelCentral();
    }
    

	/*
     * Crear Labels de la ventana Info
     */
    private void crearLabels() {
    	
    	for(int i = 0; i<modelo.getTextoDatosInfo().length; i++) {
    		 
    		JLabel labels = new JLabel(modelo.getTextoDatosInfo()[i]);
    		labels.setForeground(Color.WHITE);
    		labels.setFont(new Font("Microsoft Himalaya",0,35));
    		labelsInfo.add(labels);
    		panelCentral.add(labelsInfo.get(i));
    	}
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
<<<<<<< Updated upstream
        panelCentral.setLayout(new GridLayout(5,5));
=======
        panelCentral.setLayout(new GridLayout(5,1));
>>>>>>> Stashed changes
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
    
}


