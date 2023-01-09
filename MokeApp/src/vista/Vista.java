/**
 * 
 * Clase Vista
 * 
 * Vista por defecto del programa
 * 
 * @author Samuel Acosta Fernandez
 * @date 12/12/2022
 * @version 01
 */
package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import conexion.Conexion;
import modelo.Modelo;

public class Vista extends JFrame{
    
	/**
	 * modelo - tipo Modelo - contiene textos del programa
	 */
    private Modelo modelo;
    
    /**
     * conexion - tipo Conexion - conexion con base de datos
     */
    private Conexion conexion;
    
    /**
     * paneles - tipo ArrayList<FondoDegradado> - paneles con fondo degradado
     */
    private ArrayList<FondoDegradado> paneles = new ArrayList<FondoDegradado>();
    
    /**
     * botonesMenu - tipo ArrayList<JButton> - botones del menu
     */
    private ArrayList<JButton> botonesMenu = new ArrayList<>();
    
    /**
     * icono - tipo JButton - icono barra superior
     */
    private JButton icono = new JButton();
    
    /**
     * titulo - tipo JLabel - título de barra superior
     */
    private JLabel titulo;

    /**
     * Constructor por defecto de vista
     * @param modelo - tipo Modelo - contiene textos del programa
     * @param conexion - tipo Conexion - conexion con base de datos
     */
    public Vista(Modelo modelo, Conexion conexion){
        this.modelo = modelo;
        propiedadesVentana();
    }
    
    /**
     * Configuracion de Ventana Principal
     */
    private void propiedadesVentana() {
        this.setTitle(modelo.getTextoVentanaPrincipal()[0]);
        this.setLayout(new BorderLayout());
        this.setSize(800, 600);
        this.requestFocus();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(100, 100);
		this.setIconImage(new ImageIcon("src/iconos/app_icono.png").getImage());
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    
    /**
     * Ordenar paneles principales
     */
    public void ordenarPaneles() {
        this.add(paneles.get(0), BorderLayout.NORTH);
        this.add(paneles.get(1), BorderLayout.WEST);
        this.add(paneles.get(2), BorderLayout.CENTER);
    }
    
    /**
     * Mostrar la ventana
     */
    public void mostrarVentana() {
    	this.setVisible(true);
    }
    
    /**
     * Configuracion Paneles de Ventana Principal
     * @param i - tipo int - número de panel
     */
    public void crearPaneles(int i){
        paneles.add(new FondoDegradado());
        paneles.get(i).setVisible(true);
        this.add(paneles.get(i));
    }
    
    /**
     * Obtener paneles degradados
     * @return paneles - tipo ArrayList<FondoDegradado> - lista de paneles
     */
    public ArrayList<FondoDegradado> getPaneles(){
        return paneles;
    }
    
    /**
     * Configurar panel norte
     */
    public void configurarPanelNorte() {
    	paneles.get(0).setColor2(new Color(58, 175, 201));
    	paneles.get(0).setLayout(new FlowLayout(FlowLayout.LEFT));
    	paneles.get(0).setPreferredSize(new Dimension(640, 70));
    }
    
    /**
     * Configurar panel oeste
     */
    public void configurarPanelOeste() {
    	paneles.get(1).setColor2(new Color(8, 143, 173));
    	paneles.get(1).setLayout(new GridLayout(6,1));
    	paneles.get(1).setPreferredSize(new Dimension(250, 600));
    	paneles.get(1).setAlignmentY(Component.LEFT_ALIGNMENT);
    }
    
    /**
     * Configurar panel central
     */
    public void configurarPanelCentral() {
    	paneles.get(2).setLayout(new GridLayout(1,1));
    }
    
    /**
     * Icono Aplicacion
     */
    public void agregarIcono() {
        
        // Configuracion de imagen
        icono.setName(modelo.getTextoLogos()[0]);
    	ImageIcon icon = new ImageIcon("src/iconos/app.png");
    	icono.setIcon(new ImageIcon(icon.getImage().getScaledInstance(50,50,java.awt.Image.SCALE_SMOOTH)));
    	icono.setOpaque(false);
        icono.setContentAreaFilled(false);
    	icono.setBorderPainted(false);
    	icono.setPreferredSize(new Dimension(60, 60));
    	paneles.get(0).add(icono);
    	
        // Configuracion de texto
    	titulo = new JLabel(modelo.getTextoVentanaPrincipal()[0]);
    	titulo.setForeground(Color.WHITE);
    	titulo.setFont(new Font("arial",0,30));
    	paneles.get(0).add(titulo);
    }
    
    /**
     * Obtener icono barra superior
     * @return icono - tipo JButton - icono barra superior
     */
    public JButton getIcono(){
        return icono;
    }
    
    /**
     * Obtener título barra superior
     * @return titulo - tipo JLabel - título barra superior
     */
    public JLabel getTitulo() {
    	return titulo;
    }
    
    /**
     * Insertar icono barra superior
     * @param nuevoIcono - tipo JButton - icono barra superior
     */
    public void setIcono(String nuevoIcono) {
    	ImageIcon icon = new ImageIcon(nuevoIcono);
    	icono.setIcon(new ImageIcon(icon.getImage().getScaledInstance(50,50,java.awt.Image.SCALE_SMOOTH)));
    }
    
    /**
     * Insertar título barra superior
     * @param nuevoTitulo - tipo JLabel - título barra superior
     */
    public void setTitulo(String nuevoTitulo) {
    	titulo.setText(nuevoTitulo);
    }
    
    /**
     * Botones del Menu Lateral Izquierdo
     * @param i - tipo int - contador de botones
     * @param nombreOpcion - tipo String - nombre de opción
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
		
	paneles.get(1).add(botonesMenu.get(i));
    }
    
    /**
     * Obtener lista de botones del menu
     * @return botonesMenu - tipo ArrayList<JButton> - lista botones
     */
    public ArrayList<JButton> getBotonesMenu() {
    	return botonesMenu;
    }
    
}
