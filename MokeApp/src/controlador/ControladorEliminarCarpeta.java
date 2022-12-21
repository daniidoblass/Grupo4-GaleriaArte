package controlador;
import java.awt.HeadlessException;
/**
 * @author Daniel Jesus Doblas Florido
 * @date 15/12/2022
 * @version 01
 */
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import conexion.Conexion;
import modelo.Modelo;
import vista.Vista;
import vista.VistaEliminarCarpeta;

public class ControladorEliminarCarpeta {

	private Modelo modelo;
	private Vista vista;
	private Eventos eventos;
	private Conexion conexion;
	private FTPClient cliente;
	private VistaEliminarCarpeta vistaEliminarCarpeta;
	private String nombreArchivo = "";
	private String directorioActual = "";
	
	public ControladorEliminarCarpeta(Modelo modelo, Vista vista, Eventos eventos, Conexion conexion, FTPClient cliente) {
		
		this.modelo = modelo;
		this.vista = vista;
		this.eventos = eventos;
		this.conexion = conexion;
		this.cliente = cliente;
		vistaEliminarCarpeta = new VistaEliminarCarpeta(cliente,modelo,eventos);
		
		
		nombreArchivo = eventos.getControladorFTPPrincipal().getInfoFicheroPulsado();
		
		try {
			
			if (nombreArchivo.contains("carpeta")) {

				if(nombreArchivo.contains(" ")) {
					JOptionPane.showMessageDialog(null, "ERROR: la carpeta seleccionada contiene espacios");
				}
				else {
					nombreArchivo = nombreArchivo.replace("carpeta-", "");
					
					int opcion = JOptionPane.showConfirmDialog(null,
							"¿Deseas eliminar la carpeta " + nombreArchivo + "?",
							"Eliminar Carpeta", JOptionPane.YES_NO_OPTION);
					
					if (opcion == JOptionPane.YES_OPTION) {
						System.out.println("seleccionada la opcion si");
						if (cliente.printWorkingDirectory().equals("/")) {
							nombreArchivo = cliente.printWorkingDirectory() + nombreArchivo;
						} else {
							nombreArchivo = cliente.printWorkingDirectory() + "/" + nombreArchivo;
						}
						eliminarCarpeta(cliente, nombreArchivo, directorioActual);
						JOptionPane.showMessageDialog(null, nombreArchivo + " se ha eliminado correctamente");
					}
				}
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		eventos.getControladorFTPPrincipal().actualizarContenido();
	}
	

	private void eliminarCarpeta(FTPClient cliente, String nombreArchivo, String directorioActual)
			throws HeadlessException, IOException {
		
		// entramos al directorio
		cliente.changeWorkingDirectory(nombreArchivo);
		
		// guardamos el nombre de la carpeta
		String listaDirectorios = nombreArchivo;
		if (!directorioActual.equals("")) {
			listaDirectorios += "/" + directorioActual;
		}
		
		
		FTPFile[] subFicheros = cliente.listFiles(listaDirectorios);
		if (subFicheros != null && subFicheros.length > 0) {
			for (FTPFile aFile : subFicheros) {
				String currentFileName = aFile.getName();
				if (currentFileName.equals(".") || currentFileName.equals("..")) {
					// skip parent directory and the directory itself
					continue;
				}
				String filePath = nombreArchivo + "/" + currentFileName;
				if (directorioActual.equals("")) {
					filePath = nombreArchivo + "/" + currentFileName;
				}
				if (aFile.isDirectory()) {
					System.out.println("el fichero es un directorio");
					// remove the sub directory
					eliminarCarpeta(cliente, filePath, directorioActual);
				} else {
					// delete the file
					boolean deleted = cliente.deleteFile(filePath);
					if (deleted) {
						System.out.println("DELETED the file: " + filePath);
					} else {
						System.out.println("CANNOT delete the file: " + filePath);
					}
				}
			}
			//cliente.changeWorkingDirectory(nombreArchivo);
			cliente.changeToParentDirectory();
			// finally, remove the directory itself
			boolean removed = cliente.removeDirectory(listaDirectorios);
			if (removed) {
				System.out.println("REMOVED the directory: " + listaDirectorios);
			} else {
				System.out.println("CANNOT remove the directory: " + listaDirectorios);
			}

		}
	}
}
