package controlador;

import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;

import conexion.Conexion;
import modelo.Modelo;
import vista.Vista;
import vista.VistaDescargarArchivo;
import vista.VistaSubirArchivo;

public class ControladorDescargarArchivo {
	
	private Modelo modelo;
	private Vista vista;
	private Eventos eventos;
	private Conexion conexion;
	private VistaDescargarArchivo vistaDescargarArchivo;

	public ControladorDescargarArchivo(Modelo modelo, Vista vista, Eventos eventos, Conexion conexion,FTPClient cliente) throws SocketException, IOException {
		
		this.modelo = modelo;
		this.vista = vista;
		this.eventos = eventos;
		vistaDescargarArchivo = new VistaDescargarArchivo(modelo,cliente);
		this.conexion = conexion;
		
	}
}
