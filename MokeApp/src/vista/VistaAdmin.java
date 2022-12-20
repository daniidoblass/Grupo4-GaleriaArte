/**
 * @author Samuel Acosta Fernandez
 * @date 09/02/2022
 * @version 01
 */
package vista;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Modelo;
import controlador.Eventos;

public class VistaAdmin extends JFrame{
    
    private Modelo modelo;
    private Vista vista;
    private Eventos eventos;

    private JPanel panelCentral = new JPanel();
    private ArrayList<JPanel> panelesAdmin = new ArrayList<>();
    private DefaultTableModel modeloTabla = new DefaultTableModel();
    private JTable tabla = new JTable();
    private JButton cambiarTabla = new JButton();

    public VistaAdmin(Modelo modelo, Vista vista){
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
        panelCentral.setLayout(new BorderLayout());
        panelCentral.setOpaque(false);
        vista.getPaneles().get(2).add(panelCentral);
    }
    
    /*
     * Configuracion de paneles admin
     */
    public void crearPanelesAdmin(int i) {
    	panelesAdmin.add(new JPanel());
    	panelesAdmin.get(i).setVisible(true);
    	panelesAdmin.get(i).setOpaque(false);
    	panelCentral.add(panelesAdmin.get(i));  
    }
    
    public void ordenarPaneles() {
    	panelCentral.add(panelesAdmin.get(0), BorderLayout.NORTH);
    	panelCentral.add(panelesAdmin.get(1), BorderLayout.EAST);
    	panelCentral.add(panelesAdmin.get(2), BorderLayout.CENTER);
    }
    
    public void configurarPanelAdminNorte() {
    	panelesAdmin.get(0).setLayout(new FlowLayout(FlowLayout.LEFT));
    	panelesAdmin.get(0).setPreferredSize(new Dimension(640, 70));
    }
    
    public void configurarPanelAdminEste() {
    	panelesAdmin.get(1).setLayout(new GridLayout(5,1));
    	panelesAdmin.get(1).setPreferredSize(new Dimension(250, 600));
    	panelesAdmin.get(1).setAlignmentY(Component.RIGHT_ALIGNMENT);
    }
    
    public void configurarPanelAdminCentral() {
    	panelesAdmin.get(2).setLayout(new GridLayout(1,1));
    }
    
    public ArrayList<JPanel> getPanelesAdmin(){
    	return panelesAdmin;
    }
    
    
    /*
     * Configuracion Tabla
     */
    public void configuracionJTable1(String[] nombreColumnas){
    	panelesAdmin.get(2).add(tabla);                                     // Anadimos la tabla a la ventana
        JScrollPane scroll;
        
        modeloTabla.setColumnIdentifiers(nombreColumnas);                   // Anadimos las columnas al DefaultTableModel
        tabla.setModel(modeloTabla);                                        // Anadimos el modelo (columnas) a la tabla
        tabla.setBackground(new Color(255,255,255));
        scroll = new JScrollPane(tabla);                                    // Anadimos un scroll a la tabla
        scroll.setPreferredSize(new Dimension(600, 500));
        scroll.setOpaque(false);
        Border empty = new EmptyBorder(0,0,0,0);
        scroll.setBorder(empty);
        scroll.getViewport().setBackground(new Color(8, 143, 173, 90));
        
        //Personalizacion JTable
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tabla.getTableHeader().setOpaque(false);
        tabla.getTableHeader().setBackground(new Color(66, 120, 147));
        tabla.getTableHeader().setForeground(new Color(255, 255, 255));
        tabla.setRowHeight(25);

        panelesAdmin.get(2).add(scroll);
    }
    
    public void modificarModeloTabla(String[] nombreColumnas) {
    	modeloTabla.setColumnIdentifiers(nombreColumnas);
    }
    
    // Inserta una fila
    public void insertRow(Object[] fila) {
        modeloTabla.addRow(fila);
    }
    
    // Elimina los datos de la tabla
    public void limpiarTabla(){
        modeloTabla.setRowCount(0);
    }
    
    /*
     * Configurar Boton Cambiar Tabla
     */
    public void configurarBotonCambiarTabla() {
    	ImageIcon icon = new ImageIcon("src/subiconos/editar.png");
		
    	// ponerle texto e icono
        	cambiarTabla.setText("Cambiar Vista");
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
        	cambiarTabla.setFont(new Font("arial",0,20));
        	
    	panelesAdmin.get(1).add(cambiarTabla);
	}
	
	public JButton getBotonCambiarTabla() {
		return cambiarTabla;
	}
}


