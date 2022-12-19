package controlador;

/**
 * @author Daniel Jes�s Doblas Florido
 * @date 14/12/2022
 * @version 01
 */

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import conexion.Conexion;
import modelo.Modelo;
import vista.Vista;
import vista.VistaCrearCarpeta;
import vista.VistaSubirArchivo;

public class ControladorCrearCarpeta {

	  	private Modelo modelo;
	    private Vista vista;
	    private VistaCrearCarpeta vistaCrearCarpeta;
	    private FTPClient cliente;
	    private Eventos eventos;
	    private Conexion conexion;
	    private String nombreCarpeta;
	    
	    public ControladorCrearCarpeta(Modelo modelo, Vista vista, Eventos eventos, Conexion conexion, FTPClient cliente) {
	    	
	    	this.cliente = cliente;
	    	this.modelo = modelo;
	        this.vista = vista;
	        this.eventos = eventos;
	        vistaCrearCarpeta = new VistaCrearCarpeta(modelo);
	        this.conexion = conexion;
	        crearCarpeta();
			
	    }
	    
	    private void crearCarpeta() {
	    	
	    	try {
	    		nombreCarpeta = vistaCrearCarpeta.crearNombreCarpeta("Crear Carpeta", "Nombre de la carpeta");
				
				if(nombreCarpeta.isEmpty() || nombreCarpeta.equals("")) {
					vistaCrearCarpeta.mostrarMensajeEmergente("Nombre Carpeta", "El campo nombre no puede estar vacío");
				}
				else {
					try {
						String directorio = cliente.printWorkingDirectory();
		
						directorio += nombreCarpeta.trim(); //quita los espacios en blanco a der e izq.

						if(cliente.makeDirectory(directorio)) {
							String mensaje = nombreCarpeta.trim() + " se ha creado correctamente";
							vistaCrearCarpeta.mostrarMensajeEmergente("Crear Carpeta", mensaje);
							eventos.getControladorFTPPrincipal().actualizarContenido();
						}else {
							vistaCrearCarpeta.mostrarMensajeEmergente("Crear Carpeta", nombreCarpeta.trim()+ " no se ha podido crear");
						}
						
					}catch (Exception e) {
						vistaCrearCarpeta.mostrarMensajeEmergente("Crear Carpeta", nombreCarpeta.trim()+ " no se ha podido crear");
					}
				}
	    	}
	    	catch(Exception e) {}
	    }
	    
	    
}
