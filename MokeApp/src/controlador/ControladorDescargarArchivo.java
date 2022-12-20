package controlador;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * @author Daniel Jesús Doblas Florido
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

	public ControladorDescargarArchivo(Modelo modelo, Vista vista, Eventos eventos, Conexion conexion,FTPClient cliente) throws SocketException, IOException {
		
		this.cliente = cliente;
		this.modelo = modelo;
		this.vista = vista;
		this.eventos = eventos;
		vistaDescargarArchivo = new VistaDescargarArchivo(modelo);
		this.conexion = conexion;
		descargarFichero();
		
	}
	
	private void descargarFichero() throws SocketException, IOException {
		
		cliente.setFileType(FTP.BINARY_FILE_TYPE);

		String archivoDelServidor = "Documento.docx";
		
        int returnVal = vistaDescargarArchivo.mostrarJFileChooser();

        if (returnVal == vistaDescargarArchivo.getjFileChooser().APPROVE_OPTION) {
        	File f = vistaDescargarArchivo.getjFileChooser().getSelectedFile();
        	// obtener carpeta de destino
        	String carpetaDestino = (f.getAbsolutePath()).toString();
        	System.out.println("destino:" + carpetaDestino);
        	// ubicación donde se va a descargar
			BufferedOutputStream buffSalida = new BufferedOutputStream(
					new FileOutputStream(carpetaDestino + "\\"+archivoDelServidor));

			// Descargar el archivo.
			boolean descargado = cliente.retrieveFile(archivoDelServidor, buffSalida); // Devuelve TRUE si se ha
																						// descargado.

			if (descargado) {

				System.out.println("Se ha podido descargar.");
				
			} else {

				System.out.println("No se ha podido descargar");
			}
			
			//Cerrar el buffer.
			buffSalida.close();
        }
}
}
