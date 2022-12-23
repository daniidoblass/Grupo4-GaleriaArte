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
    
    public ArrayList<JButton> getCaratulasProductos() {
        return caratulasProductos;
    }
    
    public void limpiarPanelCentral() {
    	panelCentral.removeAll();
    	panelCentral.revalidate();
    	panelCentral.repaint();
    	caratulasProductos.clear();
    }
    
    /*
	 * Mensaje Emergente
	 */
	public void mostrarMensajeEmergente(String titulo, String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
	}
}


