/**
 * 
 * Clase VistaMailPrincipal
 * 
 * Muestra ventana con correos y opciones para redactar,
 * ver mensaje de correo y actualizar correos
 * 
 * @author Javier Jimenez Torres
 * @date 09/12/2022
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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.Eventos;
import modelo.Modelo;

public class VistaMailPrincipal extends JFrame implements MouseListener{

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
	 * fila - tipo int - numero de filas
	 */
	private int fila;
	
	/**
	 * panelCentral - tipo JPanel - panel central de mail
	 */
	private JPanel panelCentral = new JPanel();
	
	/**
	 * panelesAdmin - tipo ArrayList<JPanel> - paneles mail
	 */
	private ArrayList<JPanel> panelesAdmin = new ArrayList<>();
	
	/**
	 * modeloTabla - tipo DefaultTableModel - modelo de tabla
	 */
	private DefaultTableModel modeloTabla = new DefaultTableModel();
	
	/**
	 * tabla - tipo JTable - tabla de correos recibidos
	 */
	private JTable tabla = new JTable();
	
	/**
	 * cambiarTabla - tipo JButton - boton de redactar
	 */
	private JButton cambiarTabla = new JButton();
	
	/**
	 * verMensaje - tipo JButton - boton para ver mensaje
	 */
	private JButton verMensaje = new JButton();
	
	/**
	 * actualizarTabla - tipo JButton - boton para actualizar correos
	 */
	private JButton actualizarTabla = new JButton();

	/**
	 * Constructor por defecto de vista mail
	 * @param modelo - tipo Modelo - contiene textos del programa
	 * @param vista - tipo Vista - vista principal del programa
	 */
	public VistaMailPrincipal(Modelo modelo, Vista vista) {
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
		panelCentral.setLayout(new BorderLayout());
		panelCentral.setOpaque(false);
		vista.getPaneles().get(2).add(panelCentral);
	}

	/**
	 * Configuracion de paneles admin
	 * @param i - tipo int - contador de paneles
	 */
	public void crearPanelesAdmin(int i) {
		panelesAdmin.add(new JPanel());
		panelesAdmin.get(i).setVisible(true);
		panelesAdmin.get(i).setOpaque(false);
		panelCentral.add(panelesAdmin.get(i));
	}

	/**
	 * Ordena paneles
	 */
	public void ordenarPaneles() {
		panelCentral.add(panelesAdmin.get(0), BorderLayout.NORTH);
		panelCentral.add(panelesAdmin.get(1), BorderLayout.WEST);
		panelCentral.add(panelesAdmin.get(2), BorderLayout.CENTER);
	}

	/**
	 * Configurar panel norte
	 */
	public void configurarPanelAdminNorte() {
		panelesAdmin.get(0).setLayout(new FlowLayout(FlowLayout.LEFT));
		panelesAdmin.get(0).setPreferredSize(new Dimension(640, 70));
	}

	/**
	 * Configurar panel este
	 */
	public void configurarPanelAdminEste() {
		panelesAdmin.get(1).setLayout(new GridLayout(5, 1));
		panelesAdmin.get(1).setPreferredSize(new Dimension(250, 600));
		panelesAdmin.get(1).setAlignmentY(Component.LEFT_ALIGNMENT);
	}

	/**
	 * Configurar panel central
	 */
	public void configurarPanelAdminCentral() {
		panelesAdmin.get(2).setLayout(new GridLayout(1, 1));
	}

	/**
	 * Obtener paneles mail
	 * @return panelesAdmin - tipo ArrayList<JPanel> - paneles mail
	 */
	public ArrayList<JPanel> getPanelesAdmin() {
		return panelesAdmin;
	}

	/**
	 * Configuracion Tabla
	 * @param nombreColumnas - tipo String[] - nombre columnas de tabla
	 */
	public void configuracionJTable1(String[] nombreColumnas) {
		panelesAdmin.get(2).add(tabla); // Anadimos la tabla a la ventana
		JScrollPane scroll;

		modeloTabla.setColumnIdentifiers(nombreColumnas); // Anadimos las columnas al DefaultTableModel
		tabla.setModel(modeloTabla); // Anadimos el modelo (columnas) a la tabla
		tabla.setBackground(new Color(255, 255, 255));
		scroll = new JScrollPane(tabla); // Anadimos un scroll a la tabla
		scroll.setPreferredSize(new Dimension(600, 500));
		scroll.setOpaque(false);
		Border empty = new EmptyBorder(0, 0, 0, 0);
		scroll.setBorder(empty);
		scroll.getViewport().setBackground(new Color(8, 143, 173, 90));

		// Personalizacion JTable
		tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
		tabla.getTableHeader().setOpaque(false);
		tabla.addMouseListener(this);
		tabla.getTableHeader().setBackground(new Color(66, 120, 147));
		tabla.getTableHeader().setForeground(new Color(255, 255, 255));
		tabla.setRowHeight(25);
		tabla.setDefaultEditor(Object.class, null);

		panelesAdmin.get(2).add(scroll);
	}

	/**
	 * Modifica modelo de tabla
	 * @param nombreColumnas - tipo String[] - nombre de columnas
	 */
	public void modificarModeloTabla(String[] nombreColumnas) {
		modeloTabla.setColumnIdentifiers(nombreColumnas);
	}

	/**
	 *  Inserta una fila
	 * @param fila - tipo Object[] - fila de tabla
	 */
	public void insertRow(Object[] fila) {
		modeloTabla.addRow(fila);
	}
	
	/**
	 *  Elimina los datos de la tabla
	 */
	public void limpiarTabla() {
		modeloTabla.setRowCount(0);
	}

	/**
	 * Configurar Boton Redactar
	 */
	public void configurarBotonRedactar() {
		ImageIcon icon = new ImageIcon("src/subiconos/redactar.png");

		// ponerle texto e icono
		cambiarTabla.setText("Redactar");
		cambiarTabla.setIcon(new ImageIcon(icon.getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)));

		// Texto en el centro y debajo del icono
		cambiarTabla.setHorizontalTextPosition(SwingConstants.RIGHT);
		cambiarTabla.setVerticalTextPosition(SwingConstants.CENTER);

		// Configurar propiedades del boton
		cambiarTabla.setBackground(Color.decode("#193349"));
		cambiarTabla.setBorderPainted(false);
		cambiarTabla.setPreferredSize(new Dimension(200, 60));
		cambiarTabla.setHorizontalAlignment(SwingConstants.LEFT);

		// Personalizar texto
		cambiarTabla.setForeground(Color.WHITE);
		cambiarTabla.setFont(new Font("arial", 0, 20));

		panelesAdmin.get(1).add(cambiarTabla);
	}
	
	/**
	 * Configura boton de ver mensaje
	 */
	public void configuracionBottonVerMensaje() {
		ImageIcon icon = new ImageIcon("src/subiconos/abrirmensaje.png");

		// ponerle texto e icono
		verMensaje.setText("Ver mensaje");
		verMensaje.setIcon(new ImageIcon(icon.getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)));

		// Texto en el centro y debajo del icono
		verMensaje.setHorizontalTextPosition(SwingConstants.RIGHT);
		verMensaje.setVerticalTextPosition(SwingConstants.CENTER);

		// Configurar propiedades del boton
		verMensaje.setOpaque(false);
		verMensaje.setBorderPainted(false);
		verMensaje.setContentAreaFilled(false);
		verMensaje.setPreferredSize(new Dimension(200, 60));
		verMensaje.setHorizontalAlignment(SwingConstants.LEFT);
		

		// Personalizar texto
		verMensaje.setForeground(Color.WHITE);
		verMensaje.setFont(new Font("arial", 0, 20));

		panelesAdmin.get(1).add(verMensaje);
	}
	
	/**
	 * Configura boton de actualizar
	 */
	public void configurarBotonActualizar() {
		ImageIcon icon = new ImageIcon("src/subiconos/actualizar.png");

		// ponerle texto e icono
		actualizarTabla.setText("Actualizar");
		actualizarTabla.setIcon(new ImageIcon(icon.getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)));

		// Texto en el centro y debajo del icono
		actualizarTabla.setHorizontalTextPosition(SwingConstants.RIGHT);
		actualizarTabla.setVerticalTextPosition(SwingConstants.CENTER);

		// Configurar propiedades del boton
		actualizarTabla.setOpaque(false);
		actualizarTabla.setBorderPainted(false);
		actualizarTabla.setContentAreaFilled(false);
		actualizarTabla.setPreferredSize(new Dimension(200, 60));
		actualizarTabla.setHorizontalAlignment(SwingConstants.LEFT);

		// Personalizar texto
		actualizarTabla.setForeground(Color.WHITE);
		actualizarTabla.setFont(new Font("arial", 0, 20));

		panelesAdmin.get(1).add(actualizarTabla);
	}

	/**
	 * Obtener boton redactar
	 * @return cambiarTabla - tipo JButton - boton redactar
	 */
	public JButton getBotonCambiarTabla() {
		return cambiarTabla;
	}
	
	/**
	 * Obtener boton actualizar
	 * @return actualizarTabla - tipo JButton - boton actualizar
	 */
	public JButton getBotonActualizarTabla() {
		return actualizarTabla;
	}
	
	/**
	 * Obtener boton ver mensaje
	 * @return verMensaje - tipo JButton - boton ver mensaje
	 */
	public JButton getBotonVerMensaje() {
		return verMensaje;
	}

	/**
	 * Obtener fila seleccionada
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		fila = tabla.rowAtPoint(e.getPoint());	
	}

	/**
	 * Obtener fila tabla
	 * @return fila - tipo int - numero de fila
	 */
	public int getFila() {
		return fila;
	}

	/**
	 * Insertar numero de fila
	 * @param fila - tipo int - numero de fila
	 */
	public void setFila(int fila) {
		this.fila = fila;
	}

	/**
	 * Métodos al pasar puntero encima de componente
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
