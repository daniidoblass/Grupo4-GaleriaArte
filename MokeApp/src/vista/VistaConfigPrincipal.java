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

public class VistaConfigPrincipal extends JFrame{
    
    private Modelo modelo;
    private Vista vista;
    private Eventos eventos;
    private ArrayList<JPanel> panelesOpciones = new ArrayList<>();
    private ArrayList<JButton> perfilesOpciones = new ArrayList<>();
    private ArrayList<JLabel> etiquetasOpciones = new ArrayList<>();
    private JPanel panelCentral = new JPanel();

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
    	panelCentral.setLayout(new GridLayout(5,1));
        panelCentral.setOpaque(false);
        vista.getPaneles().get(2).add(panelCentral);
    }
    
    /*
     * Configuracion de Paneles de Opciones
    */
    public void crearPanelesOpciones(int i) {
        panelesOpciones.add(new JPanel());
        panelesOpciones.get(i).setOpaque(false);
        panelCentral.add(panelesOpciones.get(i));
    }
    
    /*
     * Configuracion de Paneles de Opciones
    */
    public void crearEtiquetas(int i) {
    	etiquetasOpciones.add(new JLabel(modelo.getTextoConfiguracion()[i]));
    	etiquetasOpciones.get(i).setFont(new Font("arial",0,30));
    	etiquetasOpciones.get(i).setOpaque(false);
    	etiquetasOpciones.get(i).setPreferredSize(new Dimension(500, 50));
    	etiquetasOpciones.get(i).setOpaque(false);
    	etiquetasOpciones.get(i).setAlignmentY(Component.CENTER_ALIGNMENT);
    	etiquetasOpciones.get(i).setVerticalTextPosition(SwingConstants.CENTER);
    	etiquetasOpciones.get(i).setForeground(Color.WHITE);
    }
    /*
     * Crear Perfiles de Opciones
     */
    public void crearPerfilesOpciones(int i) {
    	perfilesOpciones.add(new JButton());
        perfilesOpciones.get(i).setName(modelo.getTipoOpciones()[i]);
    	ImageIcon icon = new ImageIcon("src/opcionesprincipal/" + i + ".png");
    	perfilesOpciones.get(i).setIcon(new ImageIcon(icon.getImage().getScaledInstance(150,150,java.awt.Image.SCALE_REPLICATE)));
    	perfilesOpciones.get(i).setBorderPainted(false);
    	perfilesOpciones.get(i).setPreferredSize(new Dimension(160, 160));
    	perfilesOpciones.get(i).setOpaque(false);
    	perfilesOpciones.get(i).setContentAreaFilled(false);
        perfilesOpciones.get(i).setAlignmentY(Component.CENTER_ALIGNMENT);
    	perfilesOpciones.get(i).setVerticalTextPosition(SwingConstants.CENTER);
        perfilesOpciones.get(i).setForeground(Color.WHITE);
    	panelesOpciones.get(i+1).add(perfilesOpciones.get(i));
    	panelesOpciones.get(i+1).add(etiquetasOpciones.get(i));
    }
    
    public void configurarPanelesOpciones(){
        panelesOpciones.get(0).setLayout(new BorderLayout());
        panelesOpciones.get(2).setLayout(new FlowLayout(FlowLayout.LEFT));
        panelesOpciones.get(3).setLayout(new FlowLayout(FlowLayout.LEFT));
        panelesOpciones.get(1).setLayout(new FlowLayout(FlowLayout.LEFT));
    }
    /*
     * Crear etiqueta Opcion
    */
    public void crearEtiquetaOpciones(){
        JLabel etiquetaOpciones = new JLabel(modelo.getTextoOpciones());
    	etiquetaOpciones.setForeground(Color.WHITE);
    	etiquetaOpciones.setFont(new Font("arial",0,30));
    	panelesOpciones.get(0).add(etiquetaOpciones);
    }

	public ArrayList<JPanel> getPanelesOpciones() {
		return panelesOpciones;
	}

	public void setPanelesOpciones(ArrayList<JPanel> panelesOpciones) {
		this.panelesOpciones = panelesOpciones;
	}

	public ArrayList<JButton> getPerfilesOpciones() {
		return perfilesOpciones;
	}

	public void setPerfilesOpciones(ArrayList<JButton> perfilesOpciones) {
		this.perfilesOpciones = perfilesOpciones;
	}   

}


