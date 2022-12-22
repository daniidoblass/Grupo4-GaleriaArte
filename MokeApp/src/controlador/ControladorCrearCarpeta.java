package controlador;

/**
 * @author Daniel Jesus Doblas Florido
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
	    		nombreCarpeta = vistaCrearCarpeta.crearNombreCarpeta(modelo.getTextosCrearCarpetas()[0], modelo.getTextosCrearCarpetas()[1]);
				
				if(nombreCarpeta.isEmpty() || nombreCarpeta.equals(modelo.getNada())) {
					vistaCrearCarpeta.mostrarMensajeEmergente( modelo.getTextosCrearCarpetas()[1],  modelo.getTextosCrearCarpetas()[4]);
				}
				else {
					try {
						
						// Eliminar espacios en nombre de carpeta
						nombreCarpeta = nombreCarpeta.replace(modelo.getEspacioEnBLanco(), modelo.getNada());
						
						// Sustituir guiones
						nombreCarpeta = nombreCarpeta.replace(modelo.getGuionMedio(), modelo.getGuionBajo());

						if(cliente.makeDirectory(nombreCarpeta)) {
							String mensaje = nombreCarpeta.trim() + modelo.getTextosCrearCarpetas()[2];
							vistaCrearCarpeta.mostrarMensajeEmergente(modelo.getTextosCrearCarpetas()[0], mensaje);
							eventos.getControladorFTPPrincipal().actualizarContenido();
						}else {
							vistaCrearCarpeta.mostrarMensajeEmergente(modelo.getTextosCrearCarpetas()[0], nombreCarpeta.trim()+ modelo.getTextosCrearCarpetas()[3]);
						}
						
					}catch (Exception e) {
						vistaCrearCarpeta.mostrarMensajeEmergente(modelo.getTextosCrearCarpetas()[0], nombreCarpeta.trim()+ modelo.getTextosCrearCarpetas()[3]);
					}
				}
	    	}
	    	catch(Exception e) {}
	    }
	    
	    
}
