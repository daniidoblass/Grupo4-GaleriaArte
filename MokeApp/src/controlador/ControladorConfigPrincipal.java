/**
 * 
 * Clase ControladorConfigPrincipal
 * 
 * Configura la ventana principal de la configuración
 * con las opciones de restablecer contraseña, cambiar
 * correo corporativo y soporte técnico
 * 
 * @author Samuel Acosta Fernandez
 * @date 09/12/2022
 * @version 01
 */

package controlador;

import java.sql.ResultSet;

import javax.swing.JFrame;

import org.apache.commons.net.ftp.FTPClient;

import conexion.Conexion;
import modelo.Modelo;
import vista.Vista;
import vista.VistaConfigPrincipal;

public class ControladorConfigPrincipal {
    
	/**
	 * modelo - tipo Modelo - contiene textos del programa
	 */
    private Modelo modelo;
    
    /**
     * vista - tipo Vista - vista principal del programa
     */
    private Vista vista;
    
    /**
     * vistaConfigPrincipal - tipo VistaConfigPrincipal - vista de configuración
     */
    private VistaConfigPrincipal vistaConfigPrincipal;
    
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
     * eventosConfig - tipo EventosConfig - eventos de la configuración
     */
    private EventosConfig eventosConfig;
    
    /**
     * Constructor por defecto para crear configuración
     * @param modelo - tipo Modelo - contiene textos del programa
     * @param vista - tipo Vista - vista principal del programa
     * @param eventos - tipo Eventos - eventos principales 
     * @param conexion - tipo Conexion - conexion con base de datos
     * @param cliente - tipo FTPClient - cliente FTP
     */
    public ControladorConfigPrincipal(Modelo modelo, Vista vista, Eventos eventos, Conexion conexion, FTPClient cliente){
        this.modelo = modelo;
        this.vista = vista;
        this.eventos = eventos;
        vistaConfigPrincipal = new VistaConfigPrincipal(modelo, vista);
        this.conexion = conexion;
        this.cliente = cliente;
        eventosConfig = new EventosConfig(modelo, eventos, conexion, this, vistaConfigPrincipal);

        // Configurar titulo de la pagina
        configurarTitulo();
        
        // Creacion de lista de opciones
        crearOpcionesMenu();
        
        // Configurar Opciones Correo Corporativo
        configurarEntradaCorreo();
        
        // Actualizar ventana
        actualizarVentana();
    }

    /**
     * Establece combo box con correos configurados
     */
    private void configurarEntradaCorreo() {
		try {
			ResultSet rs = conexion.realizarConsultaRS(modelo.getSqlComboCorreos());
			while(rs.next()) {
				vistaConfigPrincipal.agregarCorreoComboBox(rs.getString(2));
			}
		}
		catch(Exception e) {}
	}

    /**
	 * Configura título de barra superior
	 */
	private void configurarTitulo() {
		vista.setIcono(modelo.getRutasIconos()[1]);
		vista.setTitulo(modelo.getTipoOpciones()[2]);
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
     * Crea opciones de password, correo y soporte
     */
    private void crearOpcionesMenu() {
        for(int i=0; i<modelo.getTextoOpcionesConfig().length; i++) {
        	vistaConfigPrincipal.crearBotonMenu(i, modelo.getTextoOpcionesConfig()[i], modelo.getTextoOpcionesConfigImages()[i]);
        	vistaConfigPrincipal.getBotonesMenu().get(i).addMouseListener(eventosConfig);
        	vistaConfigPrincipal.getBotonesMenu().get(i).addActionListener(eventosConfig);
        }
    }
	
}

