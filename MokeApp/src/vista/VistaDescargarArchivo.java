package vista;
/**
 * @author Daniel Jesús Doblas Florido
 * @date 14/12/2022
 * @version 01
 */
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import modelo.Modelo;

public class VistaDescargarArchivo extends JFrame{

	private FTPClient cliente;
	public static String usuario = "b3_33188433";
	public static String contrasena = "2wknvh9m";
	public static String directorioTrabajo = "/";
	
	public  VistaDescargarArchivo (FTPClient cliente) {

		this.cliente = cliente;
		try {
			DescargarFichero();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void DescargarFichero() throws SocketException, IOException {
		
			cliente.setFileType(FTP.BINARY_FILE_TYPE);

			String archivoDelServidor = "Documento.docx";
			
	        JFileChooser jFileChooser = new JFileChooser();
	        jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	        jFileChooser.setDialogTitle("Seleccione el Directorio donde DESCARGAR el fichero");
	        int returnVal = jFileChooser.showDialog(null, "Descargar");

	        if (returnVal == JFileChooser.APPROVE_OPTION) {
	        	File f = jFileChooser.getSelectedFile();
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
