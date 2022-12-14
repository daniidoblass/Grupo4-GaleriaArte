/**
 * @author Samuel Acosta Fernandez
 * @date 09/02/2022
 * @version 01
 */
package vista;

import conexion.conexion;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import modelo.modelo;
import controlador.eventos;

public class vista extends JFrame{
    
    private modelo modelo;
    private conexion conexion;
    private ArrayList<fondoDegradado> paneles = new ArrayList<fondoDegradado>();
    private ArrayList<JButton> botonesMenu = new ArrayList<>();
    private JButton icono = new JButton();
    private JLabel titulo;

    public vista(modelo modelo, conexion conexion){
        this.modelo = modelo;
        propiedadesVentana();
    }
    
    /*
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
    
    public void ordenarPaneles(){
        this.add(paneles.get(0), BorderLayout.NORTH);
        this.add(paneles.get(1), BorderLayout.WEST);
        this.add(paneles.get(2), BorderLayout.CENTER);
    }
    
    public void mostrarVentana() {
    	this.setVisible(true);
    }
    
    /*
     * Configuracion Paneles de Ventana Principal
    */
    public void crearPaneles(int i){
        paneles.add(new fondoDegradado());
        paneles.get(i).setVisible(true);
        this.add(paneles.get(i));
    }
    
    public ArrayList<fondoDegradado> getPaneles(){
        return paneles;
    }
    
    public void configurarPanelNorte() {
    	paneles.get(0).setColor2(new Color(58, 175, 201));
    	paneles.get(0).setLayout(new FlowLayout(FlowLayout.LEFT));
    	paneles.get(0).setPreferredSize(new Dimension(640, 70));
    }
    
    public void configurarPanelOeste() {
    	paneles.get(1).setColor2(new Color(8, 143, 173));
    	paneles.get(1).setLayout(new GridLayout(5,1));
    	paneles.get(1).setPreferredSize(new Dimension(250, 600));
    	paneles.get(1).setAlignmentY(Component.LEFT_ALIGNMENT);
    }
    
    public void configurarPanelCentral() {
    	paneles.get(2).setLayout(new GridLayout(1,1));
    }
    
    /*
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
    
    public JButton getIcono(){
        return icono;
    }
    
    public JLabel getTitulo() {
    	return titulo;
    }
    
    public void setIcono(String nuevoIcono) {
    	ImageIcon icon = new ImageIcon(nuevoIcono);
    	icono.setIcon(new ImageIcon(icon.getImage().getScaledInstance(50,50,java.awt.Image.SCALE_SMOOTH)));
    }
    
    public void setTitulo(String nuevoTitulo) {
    	titulo.setText(nuevoTitulo);
    }
    
    /*
     * Botones del Menu Lateral Izquierdo
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
    
    public ArrayList<JButton> getBotonesMenu() {
    	return botonesMenu;
    }
    
}
