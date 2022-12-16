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

public class VistaFTPPrincipal extends JFrame{
    
    private Modelo modelo;
    private Vista vista;
    private Eventos eventos;

    private JPanel panelCentral = new JPanel();
    private ArrayList<JButton> caratulasProductos = new ArrayList<>();

    public VistaFTPPrincipal(Modelo modelo, Vista vista){
        this.modelo = modelo;
        this.vista = vista;
        actualizarVentanaPrincipal();
        configurarPanelCentral();
    }
    
    /*
     * Actualizamos la ventana principal
    */
    private void actualizarVentanaPrincipal() {
        vista.getPaneles().get(1).setVisible(true);
        vista.getPaneles().get(2).removeAll();
    }
    
    /*
     * Configuracion de Ventana Principal
     */
    public void configurarPanelCentral() {
        panelCentral.setLayout(new GridLayout(3,5));
        panelCentral.setOpaque(false);
        // Configurar Scroll
        JScrollPane scrollPanel = new JScrollPane(panelCentral, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    	scrollPanel.setBorder(null);
    	scrollPanel.setOpaque(false);
    	scrollPanel.getViewport().setOpaque(false);
    	panelCentral.setAutoscrolls(true);
    	// Agregar Scroll
    	vista.getPaneles().get(2).add(scrollPanel);
    }
    
    /*
     * Crear Caratulas de Ficheros
     */
    public void crearCaratulasFicheros(int i, String nombreFichero, String formato, String infoFichero) {
    	caratulasProductos.add(new JButton());
        caratulasProductos.get(i).setName(infoFichero);
        
        // ponerle icono
    	ImageIcon icon = new ImageIcon("src/formatos/" + formato + ".png");
    	caratulasProductos.get(i).setIcon(new ImageIcon(icon.getImage().getScaledInstance(120,120,java.awt.Image.SCALE_SMOOTH)));
    	
    	// ponerle texto
    	caratulasProductos.get(i).setText(nombreFichero);
    	
    	// Texto en el centro y debajo del icono
    	caratulasProductos.get(i).setHorizontalTextPosition( SwingConstants.CENTER );
    	caratulasProductos.get(i).setVerticalTextPosition( SwingConstants.BOTTOM );
    	
    	//ajustes adicionales
    	caratulasProductos.get(i).setBorderPainted(false);
    	caratulasProductos.get(i).setPreferredSize(new Dimension(200,200));
    	caratulasProductos.get(i).setOpaque(false);
    	caratulasProductos.get(i).setContentAreaFilled(false);
    	//caratulasProductos.get(i).setHorizontalAlignment(SwingConstants.LEFT);
    	
    	// Personalizar texto
    	caratulasProductos.get(i).setForeground(Color.WHITE);
    	caratulasProductos.get(i).setFont(new Font("arial",0,20));
    	
    	// agregar a panel
    	panelCentral.add(caratulasProductos.get(i));
    	
    	/*
    	ImageIcon icon = new ImageIcon("src/subiconos/enviar.png");
		
    	// ponerle texto e icono
        	cambiarTabla.setText("Redactar");
        	cambiarTabla.setIcon(new ImageIcon(icon.getImage().getScaledInstance(40,40,java.awt.Image.SCALE_SMOOTH)));
    		
    	// Texto en el centro y debajo del icono
        	cambiarTabla.setHorizontalTextPosition( SwingConstants.RIGHT );
        	cambiarTabla.setVerticalTextPosition( SwingConstants.CENTER );
    		
    	// Configurar propiedades del boton
        	cambiarTabla.setBackground(new Color(66, 120, 147));
        	cambiarTabla.setBorderPainted(false);
        	cambiarTabla.setPreferredSize(new Dimension(200, 60));
        	cambiarTabla.setHorizontalAlignment(SwingConstants.LEFT);
    		
    	// Personalizar texto
        	cambiarTabla.setForeground(Color.WHITE);
        	cambiarTabla.setFont(new Font("arial",0,20));*/
    }
    
    public ArrayList<JButton> getCaratulasProductos() {
        return caratulasProductos;
    }
    
}


