/**
 * 
 * Clase ControladorInfoPrincipal
 * 
 * Permite informar al usuario sobre las opciones
 * disponibles que se encuentra en el programa
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
import vista.VistaInfoPrincipal;

public class ControladorInfoPrincipal {
    
	/**
	 * modelo - tipo Modelo - contiene textos del programa
	 */
    private Modelo modelo;
    
    /**
     * vista - tipo Vista - vista principal del programa
     */
    private Vista vista;
    
    /**
     * vistaInfoPrincipal - tipo VistaInfoPrincipal - vista del panel de información
     */
    private VistaInfoPrincipal vistaInfoPrincipal;
    
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
     * eventosInfo - tipo EventosInfo - eventos para mostrar información
     */
    private EventosInfo eventosInfo;
    
    /**
     * Constructor por defecto. Crea secciones para mostrar su información
     * @param modelo - tipo Modelo - contiene textos del programa
	 * @param vista - tipo Vista - vista principal del programa
	 * @param eventos - tipo Eventos - eventos principales 
	 * @param conexion - tipo Conexion - conexion con base de datos
	 * @param cliente - tipo FTPClient - cliente FTP
     */
    public ControladorInfoPrincipal(Modelo modelo, Vista vista, Eventos eventos, Conexion conexion, FTPClient cliente){
        this.modelo = modelo;
        this.vista = vista;
        this.eventos = eventos;
        vistaInfoPrincipal = new VistaInfoPrincipal(modelo, vista);
        this.conexion = conexion;
        this.cliente = cliente;
        eventosInfo = new EventosInfo(modelo, this, vistaInfoPrincipal);

        // Configurar titulo de la pagina
        configurarTitulo();
        
        // Creacion de lista de opciones
        crearOpcionesMenu();
        
        // Actualizar ventana
        actualizarVentana();
    }

    /**
	 * Configura título de barra superior
	 */
    private void configurarTitulo() {
		vista.setIcono("src/opcionesprincipal/3.png");
		vista.setTitulo("MOKE Info");
	}

    /**
	 * Actualiza el contenido de la ventana
	 */
    private void actualizarVentana() {
        vista.repaint();
        vista.pack();
        vista.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    
    /**
     * Crea opciones de información
     */
    private void crearOpcionesMenu() {
        for(int i=0; i<modelo.getTextoOpcionesInfo().length; i++) {
        	vistaInfoPrincipal.crearBotonMenu(i, modelo.getTextoOpcionesInfo()[i], modelo.getTextoOpcionesInfoImages()[i]);
        	vistaInfoPrincipal.getBotonesMenu().get(i).addMouseListener(eventosInfo);
        	vistaInfoPrincipal.getBotonesMenu().get(i).addActionListener(eventosInfo);
        }
    }
	
}

