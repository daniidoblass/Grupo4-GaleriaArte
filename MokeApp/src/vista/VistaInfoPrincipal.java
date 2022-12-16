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
    private ArrayList<JLabel> labelsInfo = new ArrayList<>();

    private JPanel panelCentral = new JPanel();

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
        panelCentral.setLayout(new GridLayout(5,5));
        panelCentral.setOpaque(false);
        vista.getPaneles().get(2).add(panelCentral);
    }
    
}


