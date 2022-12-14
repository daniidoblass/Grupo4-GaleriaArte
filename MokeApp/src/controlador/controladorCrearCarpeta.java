package controlador;

/**
 * @author Daniel Jesús Doblas Florido
 * @date 14/12/2022
 * @version 01
 */

import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;

import conexion.Conexion;
import modelo.Modelo;
import vista.Vista;
import vista.VistaCrearCarpeta;
import vista.VistaSubirArchivo;

public class controladorCrearCarpeta {

	  	private Modelo modelo;
	    private Vista vista;
	    private VistaCrearCarpeta vistaCrearCarpeta;
	    private FTPClient cliente;
	    private Eventos eventos;
	    private Conexion conexion;
	    
	    public controladorCrearCarpeta(Modelo modelo, Vista vista, Eventos eventos, Conexion conexion, FTPClient cliente) throws IOException {
	    	
	    	this.modelo = modelo;
	        this.vista = vista;
	        this.eventos = eventos;
	        vistaCrearCarpeta = new VistaCrearCarpeta(modelo,cliente);
	        this.conexion = conexion;

	    }
}
