/**
 * 
 * Clase ControladorCrearCarpeta
 * 
 * Crea una carpeta en la ruta actual del explorador FTP
 * con el nombre indicado por el usuario
 * 
 * @author Daniel Jesus Doblas Florido
 * @date 14/12/2022
 * @version 01
 */

package controlador;

import org.apache.commons.net.ftp.FTPClient;
import conexion.Conexion;
import modelo.Modelo;
import vista.Vista;
import vista.VistaCrearCarpeta;

public class ControladorCrearCarpeta {

	/**
	 * modelo - tipo Modelo - contiene textos del programa
	 */
  	private Modelo modelo;
  	
  	/**
     * vista - tipo Vista - vista principal del programa
     */
    private Vista vista;
    
    /**
     * vistaCrearCarpeta - tipo VistaCrearCarpeta - vista de Crear Carpeta
     */
    private VistaCrearCarpeta vistaCrearCarpeta;
    
    /**
     * cliente - tipo FTPClient - cliente FTP
     */
    private FTPClient cliente;
    
    /**
     * eventos - tipo Eventos - eventos principales 
     */
    private Eventos eventos;
    
    /**
     * conexion - tipo Conexion - conexion con base de datos
     */
    private Conexion conexion;
    
    /**
     * nombreCarpeta - tipo String - nombre asignado a carpeta
     */
    private String nombreCarpeta;
    
    /**
     * Construtor por defecto para crear carpeta
     * @param modelo - tipo Modelo - contiene textos del programa
     * @param vista - tipo Vista - vista principal del programa
     * @param eventos - tipo Eventos - eventos principales 
     * @param conexion - tipo Conexion - conexion con base de datos
     * @param cliente - tipo FTPClient - cliente FTP
     */
    public ControladorCrearCarpeta(Modelo modelo, Vista vista, Eventos eventos, Conexion conexion, FTPClient cliente) {
    	
    	this.cliente = cliente;
    	this.modelo = modelo;
        this.vista = vista;
        this.eventos = eventos;
        vistaCrearCarpeta = new VistaCrearCarpeta(modelo);
        this.conexion = conexion;
        crearCarpeta();
		
    }
    
    /**
     * Método para crear una carpeta en Servidor FTP
     */
    private void crearCarpeta() {
    	
    	try {
    		nombreCarpeta = vistaCrearCarpeta.crearNombreCarpeta(modelo.getTextosCrearCarpetas()[0], modelo.getTextosCrearCarpetas()[1]);
			
			if(nombreCarpeta.isEmpty() || nombreCarpeta.equals(modelo.getNada())) {
				vistaCrearCarpeta.mostrarMensajeEmergente(modelo.getTextosCrearCarpetas()[1], modelo.getTextosCrearCarpetas()[4]);
			}
			else {
				try {
					
					// Eliminar espacios en nombre de carpeta
					nombreCarpeta = nombreCarpeta.replace(" ", "");
					
					// Eliminar puntos en nombre de carpeta
					nombreCarpeta = nombreCarpeta.replace("\\.", "");
					
					// Sustituir guiones
					nombreCarpeta = nombreCarpeta.replace("-", "_");

					// Crear el directorio
					if(cliente.makeDirectory(nombreCarpeta)) {
						String mensaje = nombreCarpeta.trim() + modelo.getTextosCrearCarpetas()[2];
						vistaCrearCarpeta.mostrarMensajeEmergente(modelo.getTextosCrearCarpetas()[0], mensaje);
						conexion.registrarMovimiento(modelo.getMovimientoCrearCarpeta()[0], modelo.getMovimientoExito()[0], 
								modelo.getMovimientoCrearCarpeta()[1] + nombreCarpeta);
						eventos.getControladorFTPPrincipal().actualizarContenido();
					}else {
						vistaCrearCarpeta.mostrarMensajeEmergente(modelo.getTextosCrearCarpetas()[0], nombreCarpeta.trim()+ modelo.getTextosCrearCarpetas()[3]);
						conexion.registrarMovimiento(modelo.getMovimientoCrearCarpeta()[0], modelo.getMovimientoExito()[1], modelo.getMovimientoCrearCarpeta()[2] + nombreCarpeta);
					}
					
				}catch (Exception e) {
					vistaCrearCarpeta.mostrarMensajeEmergente(modelo.getTextosCrearCarpetas()[0], nombreCarpeta.trim()+ modelo.getTextosCrearCarpetas()[3]);
					conexion.registrarMovimiento(modelo.getMovimientoCrearCarpeta()[0], modelo.getMovimientoExito()[1], modelo.getMovimientoCrearCarpeta()[3] + nombreCarpeta);
				}
			}
    	}
    	catch(Exception e) {
    		conexion.registrarMovimiento(modelo.getMovimientoCrearCarpeta()[0], modelo.getMovimientoExito()[1], modelo.getMovimientoCrearCarpeta()[3]);
    	}
    }
	    
	    
}
