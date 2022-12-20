package vista;

/**
 * @author Pablo Pï¿½rez Ferre
 * @date 15/12/2022
 * @version 01
 */
import javax.swing.*;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import controlador.Eventos;
import modelo.Modelo;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class VistaEliminarCarpeta extends JFrame {

	private FTPClient cliente;
	private String nombreArchivo = "";
	private String currentDir = "";
	private Eventos eventos;

	public VistaEliminarCarpeta(FTPClient cliente, Modelo modelo, Eventos eventos) {
		
		this.cliente = cliente;
		this.eventos = eventos;
		nombreArchivo = eventos.getControladorFTPPrincipal().getInfoFicheroPulsado();
		
		
		
		try {
			if (nombreArchivo.contains("carpeta")) {

				nombreArchivo = nombreArchivo.replace("carpeta-", "");
				
				int opcion = JOptionPane.showConfirmDialog(null,
						"¿Deseas eliminar la carpeta " + nombreArchivo + "?",
						"Eliminar Carpeta", JOptionPane.YES_NO_OPTION);
				if (opcion == JOptionPane.YES_OPTION) {

					if (cliente.printWorkingDirectory().equals("/")) {
						nombreArchivo = cliente.printWorkingDirectory() + nombreArchivo;
					} else {
						nombreArchivo = cliente.printWorkingDirectory() + "/" + nombreArchivo;
					}
					eliminarCarpeta(cliente, nombreArchivo, currentDir);
					JOptionPane.showMessageDialog(null, nombreArchivo + " se ha elimindado correctamente");
				} else {
				}
			} 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void eliminarCarpeta(FTPClient cliente, String nombreArchivo, String currentDir)
			throws HeadlessException, IOException {
		cliente.changeWorkingDirectory(nombreArchivo);
		String dirToList = nombreArchivo;
		if (!currentDir.equals("")) {
			dirToList += "/" + currentDir;
		}
		FTPFile[] subFiles = cliente.listFiles(dirToList);
		if (subFiles != null && subFiles.length > 0) {
			for (FTPFile aFile : subFiles) {
				String currentFileName = aFile.getName();
				if (currentFileName.equals(".") || currentFileName.equals("..")) {
					// skip parent directory and the directory itself
					continue;
				}
				String filePath = nombreArchivo + "/" + currentFileName;
				if (currentDir.equals("")) {
					filePath = nombreArchivo + "/" + currentFileName;
				}
				if (aFile.isDirectory()) {
					// remove the sub directory
					eliminarCarpeta(cliente, filePath, currentDir);
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
			cliente.changeWorkingDirectory(nombreArchivo);
			cliente.changeToParentDirectory();
			// finally, remove the directory itself
			boolean removed = cliente.removeDirectory(dirToList);
			if (removed) {
				System.out.println("REMOVED the directory: " + dirToList);
			} else {
				System.out.println("CANNOT remove the directory: " + dirToList);
			}

		}
	}

}