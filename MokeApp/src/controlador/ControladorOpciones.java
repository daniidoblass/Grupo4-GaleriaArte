/**
 * 
 * Clase ControladorOpciones
 * 
 * Muestra las opciones principales del programa:
 * FTP, Mail, Configuración e Información
 * 
 * @author Samuel Acosta Fernandez
 * @date 09/12/2022
 * @version 01
 */

package controlador;

import javax.swing.JFrame;

import org.apache.commons.net.ftp.FTPClient;

import conexion.Conexion;
import modelo.Modelo;
import vista.Vista;
import vista.VistaOpciones;

public class ControladorOpciones {
    
	/**
	 * modelo - tipo Modelo - contiene textos del programa
	 */
    private Modelo modelo;
    
    /**
     * vista - tipo Vista - vista principal del programa
     */
    private Vista vista;
    
    /**
     * vistaOpciones - tipo VistaOpciones - vista de las opciones
     */
    private VistaOpciones vistaOpciones;
    
    /**
     * eventos - tipo Eventos - eventos principales 
     */
    private Eventos eventos;
    
    /**
     * conexion - tipo Conexion - conexion con base de datos
     */
    private Conexion conexion;
    
    /**
     * cliente - tipo FTPClient - cliente FTP
     */
    private FTPClient cliente;
    
    /**
     * Constructor por defecto para mostrar opciones principales
	 * @param modelo - tipo Modelo - contiene textos del programa
	 * @param vista - tipo Vista - vista principal del programa
	 * @param eventos - tipo Eventos - eventos principales 
	 * @param conexion - tipo Conexion - conexion con base de datos
	 * @param cliente - tipo FTPClient - cliente FTP
     */
    public ControladorOpciones(Modelo modelo, Vista vista, Eventos eventos, Conexion conexion, FTPClient cliente){
        this.modelo = modelo;
        this.vista = vista;
        this.eventos = eventos;
        vistaOpciones = new VistaOpciones(modelo,vista);
        this.conexion = conexion;
        this.cliente = cliente;

        // Configurar titulo de la pagina
        configurarTitulo();
        
        // Crear paneles de Opciones
        crearPanelesOpciones();
        
        // Configurar paneles de Opciones
        vistaOpciones.configurarPanelesOpciones();
        
        // Configurar Texto de Opciones
        vistaOpciones.crearEtiquetaOpciones();
        
        // Agregar perfiles de Opciones
        agregarOpciones();

        // Actualizar ventana
        actualizarVentana();
    }
    
    /**
	 * Configura título de barra superior
	 */
    private void configurarTitulo() {
		vista.setIcono("src/iconos/app.png");
		vista.setTitulo("MOKE APP");
		eventos.setVentanaActual("OPCIONES");
	}

    /**
     * Crea paneles de configuración
     */
	private void crearPanelesOpciones() {
        for(int i=0; i<modelo.getTextoPanelesVentanaPrincipal().length; i++) {
            vistaOpciones.crearPanelesOpciones(i);
        }
    }

	/**
	 * Configura opciones y sus eventos
	 */
    private void agregarOpciones() {

        for(int i=0; i<modelo.getTipoOpciones().length; i++) {
        	vistaOpciones.crearPerfilesOpciones(i);
            vistaOpciones.getPerfilesOpciones().get(i).addMouseListener(eventos);
            vistaOpciones.getPerfilesOpciones().get(i).addActionListener(eventos);
        }
        
    }

    /**
	 * Actualiza el contenido de la ventana
	 */
    private void actualizarVentana() {
        vista.repaint();
        vista.pack();
        vista.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    
	
}
