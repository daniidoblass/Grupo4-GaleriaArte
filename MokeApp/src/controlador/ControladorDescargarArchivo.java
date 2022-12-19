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
import vista.VistaDescargarArchivo;
import vista.VistaSubirArchivo;

public class ControladorDescargarArchivo {
	
	private FTPClient cliente;
	private Modelo modelo;
	private Vista vista;
	private Eventos eventos;
	private Conexion conexion;
	private VistaDescargarArchivo vistaDescargarArchivo;

	public ControladorDescargarArchivo(Modelo modelo, Vista vista, Eventos eventos, Conexion conexion,FTPClient cliente) {
		
		this.cliente = cliente;
		this.modelo = modelo;
		this.vista = vista;
		this.eventos = eventos;
		vistaDescargarArchivo = new VistaDescargarArchivo(modelo);
		this.conexion = conexion;
		descargarFichero();
		
	}
	
	private void descargarFichero() {
		
		try {
			cliente.setFileType(FTP.BINARY_FILE_TYPE);

			String archivoDelServidor = eventos.getControladorFTPPrincipal().getInfoFicheroPulsado();
			if(archivoDelServidor.isEmpty() || archivoDelServidor.equals("") || archivoDelServidor.contains("carpeta-")) {
				vistaDescargarArchivo.mostrarMensajeEmergente("Descargar Archivo", "No hay seleccionado ningún archivo");
			}
			else {
				archivoDelServidor = archivoDelServidor.replace("fichero-", "");
				if(vistaDescargarArchivo.mostrarMensajeConfirmacion("Descargar Archivo", "¿Desea descargar " + archivoDelServidor + "?") == 0) {
					int returnVal = vistaDescargarArchivo.mostrarJFileChooser();

			        if (returnVal == vistaDescargarArchivo.getjFileChooser().APPROVE_OPTION) {
			        	
			        	// obtener carpeta de destino
			        	File f = vistaDescargarArchivo.getjFileChooser().getSelectedFile();
			        	String carpetaDestino = (f.getAbsolutePath()).toString();
			        	
			        	// ubicacion donde se va a descargar
						BufferedOutputStream buffSalida = new BufferedOutputStream(
								new FileOutputStream(carpetaDestino + "\\"+archivoDelServidor));

						// Descargar el archivo.
						boolean descargado = cliente.retrieveFile(archivoDelServidor, buffSalida); // Devuelve TRUE si se ha
																									// descargado
						if (descargado) {
							vistaDescargarArchivo.mostrarMensajeEmergente("Descargar Archivo", "Se ha descargado correctamente");
							
						} else {
							vistaDescargarArchivo.mostrarMensajeEmergente("Descargar Archivo", "No se ha podido descargar el archivo");
						}
						
						//Cerrar el buffer.
						buffSalida.close();
			        }
				}
			}
		}
		catch(Exception e) {
			vistaDescargarArchivo.mostrarMensajeEmergente("Descargar Archivo", "No se ha podido descargar el archivo");
		}
	}
}
