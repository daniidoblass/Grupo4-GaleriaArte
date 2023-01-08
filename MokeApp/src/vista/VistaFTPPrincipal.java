/**
 * 
 * Clase VistaFTPPrincipal
 * 
 * Muestra explorador de archivos de servidor FTP,
 * admitiendo imagenes identificativas y exploración
 * entre carpetas
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

public class VistaFTPPrincipal extends JFrame{
    
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
     * caratulasProductos - tipo ArrayList<JButton> - lista carátulas
     */
    private ArrayList<JButton> caratulasProductos = new ArrayList<>();

    /**
     * Constructor por defecto para vista de FTP
     * @param modelo
     * @param vista
     */
    public VistaFTPPrincipal(Modelo modelo, Vista vista){
        this.modelo = modelo;
        this.vista = vista;
        actualizarVentanaPrincipal();
        configurarPanelCentral();
    }
    
    /**
     * Actualizamos la ventana principal
     */
    private void actualizarVentanaPrincipal() {
        vista.getPaneles().get(1).setVisible(true);
        vista.getPaneles().get(2).removeAll();
    }
    
    /**
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
    
    /**
     * Crear Caratulas de Ficheros
     * @param nombreFichero - tipo String - nombre del fichero
     * @param formato - tipo String - formato del fichero
     * @param infoFichero - tipo String - información de fichero
     */
    public void crearCaratulasFicheros(String nombreFichero, String formato, String infoFichero) {
    	caratulasProductos.add(new JButton());
        caratulasProductos.get(caratulasProductos.size() - 1).setName(infoFichero);
        
        // ponerle icono
    	ImageIcon icon = new ImageIcon("src/formatos/" + formato + ".png");
    	caratulasProductos.get(caratulasProductos.size() - 1).setIcon(new ImageIcon(icon.getImage().getScaledInstance(120,120,java.awt.Image.SCALE_SMOOTH)));
    	
    	// ponerle texto
    	caratulasProductos.get(caratulasProductos.size() - 1).setText(nombreFichero);
    	
    	// Texto en el centro y debajo del icono
    	caratulasProductos.get(caratulasProductos.size() - 1).setHorizontalTextPosition( SwingConstants.CENTER );
    	caratulasProductos.get(caratulasProductos.size() - 1).setVerticalTextPosition( SwingConstants.BOTTOM );
    	
    	//ajustes adicionales
    	caratulasProductos.get(caratulasProductos.size() - 1).setBorderPainted(false);
    	caratulasProductos.get(caratulasProductos.size() - 1).setPreferredSize(new Dimension(200,200));
    	caratulasProductos.get(caratulasProductos.size() - 1).setOpaque(false);
    	caratulasProductos.get(caratulasProductos.size() - 1).setContentAreaFilled(false);
    	//caratulasProductos.get(i).setHorizontalAlignment(SwingConstants.LEFT);
    	
    	// Personalizar texto
    	caratulasProductos.get(caratulasProductos.size() - 1).setForeground(Color.WHITE);
    	caratulasProductos.get(caratulasProductos.size() - 1).setFont(new Font("arial",0,20));
    	
    	// agregar a panel
    	panelCentral.add(caratulasProductos.get(caratulasProductos.size() - 1));
    }
    
    /**
     * Obtener carátulas de productos
     * @return caratulasProductos - tipo ArrayList<JButton> - carátulas productos
     */
    public ArrayList<JButton> getCaratulasProductos() {
        return caratulasProductos;
    }
    
    /**
     * Limpiar explorador FTP
     */
    public void limpiarPanelCentral() {
    	panelCentral.removeAll();
    	panelCentral.revalidate();
    	panelCentral.repaint();
    	caratulasProductos.clear();
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


