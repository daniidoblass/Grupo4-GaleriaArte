/**
 * 
 * Clase VistaOpciones
 * 
 * Muestra las opciones principales
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

public class VistaOpciones extends JFrame{
    
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
     * panelesOpciones - tipo ArrayList<JPanel> - paneles de opciones
     */
    private ArrayList<JPanel> panelesOpciones = new ArrayList<>();
    
    /**
     * perfilesOpciones - tipo ArrayList<JButton> - botones de opciones
     */
    private ArrayList<JButton> perfilesOpciones = new ArrayList<>();

	/**
	 * Constructor por defecto de vista de opciones
	 * @param modelo - tipo Modelo - contiene textos del programa
     * @param vista - tipo Vista - vista principal del programa
	 */
    public VistaOpciones(Modelo modelo, Vista vista){
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
     * Configuracion de Paneles de Opciones
     */
    public void crearPanelesOpciones(int i) {
        panelesOpciones.add(new JPanel());
        panelesOpciones.get(i).setOpaque(false);
        panelCentral.add(panelesOpciones.get(i));
    }
    
    /**
     * Configura panel de opciones
     */
    public void configurarPanelesOpciones(){
        panelesOpciones.get(0).setLayout(new BorderLayout());
        panelesOpciones.get(1).setLayout(new FlowLayout(FlowLayout.CENTER));
        panelesOpciones.get(2).setLayout(new FlowLayout(FlowLayout.CENTER));
    }

    /**
     * Crear Perfiles de Opciones
     * @param i - tipo int - contador de opciones 
     */
    public void crearPerfilesOpciones(int i) {
    	perfilesOpciones.add(new JButton());
        perfilesOpciones.get(i).setName(modelo.getTipoOpciones()[i]);
    	ImageIcon icon = new ImageIcon("src/opcionesprincipal/" + i + ".png");
    	perfilesOpciones.get(i).setIcon(new ImageIcon(icon.getImage().getScaledInstance(150,150,java.awt.Image.SCALE_SMOOTH)));
    	perfilesOpciones.get(i).setBorderPainted(false);
    	perfilesOpciones.get(i).setPreferredSize(new Dimension(160, 160));
    	perfilesOpciones.get(i).setOpaque(false);
    	perfilesOpciones.get(i).setContentAreaFilled(false);
        perfilesOpciones.get(i).setAlignmentY(Component.CENTER_ALIGNMENT);
    	perfilesOpciones.get(i).setVerticalTextPosition(SwingConstants.CENTER);
        perfilesOpciones.get(i).setForeground(Color.WHITE);
    	panelesOpciones.get(1).add(perfilesOpciones.get(i));
    }
    
    /**
     * Obtener opciones
     * @return perfilesOpciones - tipo ArrayList<JButton> - botones de opciones
     */
    public ArrayList<JButton> getPerfilesOpciones() {
        return perfilesOpciones;
    }
    
    /**
     * Crear etiqueta Opcion
    */
    public void crearEtiquetaOpciones(){
        JLabel etiquetaOpciones = new JLabel(modelo.getTextoOpciones());
    	etiquetaOpciones.setForeground(Color.WHITE);
    	etiquetaOpciones.setFont(new Font("arial",0,30));
        JPanel centrar = new JPanel();
        centrar.setLayout(new FlowLayout(FlowLayout.CENTER));
        centrar.setOpaque(false);
        centrar.add(etiquetaOpciones);
    	panelesOpciones.get(0).add(centrar, BorderLayout.SOUTH);
    }

}

