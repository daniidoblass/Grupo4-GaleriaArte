/**
 * 
 * Clase Controlador
 * 
 * Clase para realizar el diseño de plantilla de la ventana
 * principal
 * 
 * @author Samuel Acosta Fernandez
 * @date 09/12/2022
 * @version 01
 */

package controlador;

import java.sql.Connection;

import org.apache.commons.net.ftp.FTPClient;

import conexion.Conexion;
import modelo.Modelo;
import vista.Vista;

public class Controlador {
    
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
     * conexion - tipo Conexion - conexion con base de datos
     */
    private Conexion conexion;
    
    /**
     * resultadoConexion - tipo Connection - obtiene conexión MySQL
     */
    private Connection resultadoConexion;
    
    /**
     * controladorClienteFTP - tipo ControladorClienteFTP - conexion Cliente a Servidor FTP
     */
    private ControladorClienteFTP controladorClienteFTP;
    
    /**
     * Crea vista por defecto y establece conexión FTP
     */
    public Controlador(){
        modelo = new Modelo();
        conexion = new Conexion(modelo);
        vista = new Vista(modelo, conexion);
        controladorClienteFTP = new ControladorClienteFTP(modelo);
        FTPClient cliente = controladorClienteFTP.getFTPClient();
        eventos = new Eventos(modelo,vista,conexion,cliente);
        
        // Realizar conexion con la base de datos
        conexion.realizarConexion();
        
        // Creacion de Paneles
        crearPanelesVentanaPrincipal();
        
        // Configuracion de Paneles
        configurarPaneles();
        
        // Creacion de lista de opciones
        crearOpcionesMenu();
        
        // Agregar icono a barra superior
        agregarIconoBarraSuperior();

        // Mostrar Ventana Principal
        vista.mostrarVentana();

        // Iniciar el panel Login
        new ControladorLogin(modelo, vista, eventos, conexion, cliente);
    }

    /**
     * Crea los paneles norte, central y oeste
     */
    private void crearPanelesVentanaPrincipal() {
        for(int i=0; i<modelo.getTextoPanelesVentanaPrincipal().length; i++) {
                vista.crearPaneles(i);
        }
        vista.ordenarPaneles();
    }

    /**
     * Configura los 3 paneles
     */
    private void configurarPaneles() {
        vista.configurarPanelNorte();
        vista.configurarPanelOeste();
        vista.configurarPanelCentral();
    }

    /**
     * Crea las opciones del panel oeste
     */
    private void crearOpcionesMenu() {
        for(int i=0; i<modelo.getTextoOpcionesMenu().length; i++) {
            vista.crearBotonMenu(i, modelo.getTextoOpcionesMenu()[i], modelo.getTextoOpcionesMenuImages()[i]);
            vista.getBotonesMenu().get(i).addMouseListener(eventos);
            vista.getBotonesMenu().get(i).addActionListener(eventos);
        }
    }
    
    /**
     * Agrega un icono a la barra superior (panel norte)
     */
    private void agregarIconoBarraSuperior() {
        vista.agregarIcono();
    }
}
