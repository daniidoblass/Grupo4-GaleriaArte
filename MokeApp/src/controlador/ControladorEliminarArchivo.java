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
			if(archivoDelServidor.isEmpty() || archivoDelServidor.equals(modelo.getNada()) || archivoDelServidor.contains(modelo.getCarpetaGuion())) {
				vistaEliminarArchivo.mostrarMensajeEmergente(modelo.getTextosEliminarArchivos()[0], modelo.getTextosEliminarArchivos()[1]);
			}
			else {
				archivoDelServidor = archivoDelServidor.replace(modelo.getFicheroGuion(), modelo.getNada());
				if(vistaEliminarArchivo.mostrarMensajeConfirmacion(modelo.getTextosEliminarArchivos()[0], modelo.getTextosEliminarArchivos()[2]+ archivoDelServidor + modelo.getSignoPregunta()) == 0) {
					if(cliente.deleteFile(archivoDelServidor)) {
						eventos.getControladorFTPPrincipal().actualizarContenido();
						vistaEliminarArchivo.mostrarMensajeEmergente(modelo.getTextosEliminarArchivos()[0], modelo.getTextosEliminarArchivos()[3]);
					}
					else {
						vistaEliminarArchivo.mostrarMensajeEmergente(modelo.getTextosEliminarArchivos()[0], modelo.getTextosEliminarArchivos()[4]);
					}
				}
			}
		}
		catch(Exception e) {
			vistaEliminarArchivo.mostrarMensajeEmergente(modelo.getTextosEliminarArchivos()[0], modelo.getTextosEliminarArchivos()[4]);
		}
	}
}
