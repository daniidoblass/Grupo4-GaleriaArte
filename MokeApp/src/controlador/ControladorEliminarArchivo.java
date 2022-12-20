package controlador;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * @author Daniel Jesus Doblas Florido
 * @date 14/12/2022
 * @version 01
 */

import java.io.IOException;
import java.net.SocketException;

import javax.swing.JFileChooser;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import conexion.Conexion;
import modelo.Modelo;
import vista.Vista;
import vista.VistaEliminarArchivo;

public class ControladorEliminarArchivo {
	
	private FTPClient cliente;
	private Modelo modelo;
	private Vista vista;
	private Eventos eventos;
	private Conexion conexion;
	private VistaEliminarArchivo vistaEliminarArchivo;

	public ControladorEliminarArchivo(Modelo modelo, Vista vista, Eventos eventos, Conexion conexion,FTPClient cliente) {
		
		this.cliente = cliente;
		this.modelo = modelo;
		this.vista = vista;
		this.eventos = eventos;
		vistaEliminarArchivo = new VistaEliminarArchivo(modelo);
		this.conexion = conexion;
		eliminarFichero();
		
	}
	
	private void eliminarFichero() {
		
		try {
			String archivoDelServidor = eventos.getControladorFTPPrincipal().getInfoFicheroPulsado();
			if(archivoDelServidor.isEmpty() || archivoDelServidor.equals("") || archivoDelServidor.contains("carpeta-")) {
				vistaEliminarArchivo.mostrarMensajeEmergente("Eliminar Archivo", "No hay seleccionado ningún archivo");
			}
			else {
				archivoDelServidor = archivoDelServidor.replace("fichero-", "");
				if(vistaEliminarArchivo.mostrarMensajeConfirmacion("Eliminar Archivo", "¿Desea eliminar " + archivoDelServidor + "?") == 0) {
					if(cliente.deleteFile(archivoDelServidor)) {
						eventos.getControladorFTPPrincipal().actualizarContenido();
						vistaEliminarArchivo.mostrarMensajeEmergente("Eliminar Archivo", "Archivo eliminado correctamente");
					}
					else {
						vistaEliminarArchivo.mostrarMensajeEmergente("Eliminar Archivo", "No se ha podido eliminar el archivo");
					}
				}
			}
		}
		catch(Exception e) {
			vistaEliminarArchivo.mostrarMensajeEmergente("Eliminar Archivo", "No se ha podido eliminar el archivo");
		}
	}
}
