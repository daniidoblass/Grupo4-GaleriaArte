/**
 * 
 * Clase ControladorEliminarCarpeta
 * 
 * Elimina el contenido de la carpeta seleccionada de forma
 * recursiva para posteriormente eliminar la carpeta
 * seleccionada por el usuario del Servidor FTP
 * 
 * @author Pablo Perez Ferre
 * @date 15/12/2022
 * @version 01
 */

package controlador;

import java.awt.HeadlessException;
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

	/**
	 * modelo - tipo Modelo - contiene textos del programa
	 */
	private Modelo modelo;
	
	/**
     * vista - tipo Vista - vista principal del programa
     */
	private Vista vista;
	
	/**
     * eventos - tipo Eventos - eventos principales 
     */
	private Eventos eventos;
	
	/**
     * conexion - tipo Conexion - conexion con base de datos
     */
	private Conexion conexion;
	
	/**
     * cliente - tipo FTPClient - cliente FTP
     */
	private FTPClient cliente;
	
	/**
     * vistaEliminarCarpeta - tipo VistaEliminarCarpeta - vista de eliminar carpeta
     */
	private VistaEliminarCarpeta vistaEliminarCarpeta;
	
	/**
	 * nombreArchivo - tipo String - nombre de fichero seleccionado
	 */
	private String nombreArchivo = "";
	
	/**
	 * directorioActual - tipo String - directorio actual de FTP
	 */
	private String directorioActual = "";
	
	/**
	 * carpetaSeleccionada - tipo String - carpeta seleccionada
	 */
	private String carpetaSeleccionada = "";
	
	/**
	 * Constructor por defecto. Comprueba si el usuario desea eliminar 
	 * y realiza la acción de eliminar la carpeta recursivamente por 
	 * el contenido de la misma
	 * @param modelo - tipo Modelo - contiene textos del programa
	 * @param vista - tipo Vista - vista principal del programa
	 * @param eventos - tipo Eventos - eventos principales 
	 * @param conexion - tipo Conexion - conexion con base de datos
	 * @param cliente - tipo FTPClient - cliente FTP
	 */
public ControladorEliminarCarpeta(Modelo modelo, Vista vista, Eventos eventos, Conexion conexion, FTPClient cliente) {
		
		this.modelo = modelo;
		this.vista = vista;
		this.eventos = eventos;
		this.conexion = conexion;
		this.cliente = cliente;
		vistaEliminarCarpeta = new VistaEliminarCarpeta(cliente,modelo,eventos);
		
		
		nombreArchivo = eventos.getControladorFTPPrincipal().getInfoFicheroPulsado();

		try {
			
			if (nombreArchivo.contains(modelo.getCarpetaNombre())) {

				if(nombreArchivo.contains(" ")) {
					JOptionPane.showMessageDialog(null, modelo.getTextosEliminarCarpetas()[0]);
				}
				else {
					nombreArchivo = nombreArchivo.replace(modelo.getCarpetaGuion(), "");
					carpetaSeleccionada = nombreArchivo;
					int opcion = vistaEliminarCarpeta.mostrarMensajeConfirmacion(nombreArchivo);
					
					if (opcion == JOptionPane.YES_OPTION) {
						if (cliente.printWorkingDirectory().equals("/")) {
							nombreArchivo = cliente.printWorkingDirectory() + nombreArchivo;
						} else {
							nombreArchivo = cliente.printWorkingDirectory() + "/" + nombreArchivo;
						}
						eliminarCarpeta(cliente, nombreArchivo, directorioActual);
						if(comprobarEliminacion()) {
							JOptionPane.showMessageDialog(null, modelo.getTextoErrorEliminar() + carpetaSeleccionada);
							conexion.registrarMovimiento(modelo.getMovimientoEliminarCarpeta()[0], modelo.getMovimientoExito()[1], modelo.getMovimientoEliminarCarpeta()[1] 
									+ nombreArchivo.substring(nombreArchivo.lastIndexOf("/")+1) + modelo.getMovimientoEliminarCarpeta()[2]);
						}
						else {
							JOptionPane.showMessageDialog(null, nombreArchivo.substring(nombreArchivo.lastIndexOf("/")+1) + modelo.getTextoCorrectoEliminar());
							conexion.registrarMovimiento(modelo.getMovimientoEliminarCarpeta()[0], modelo.getMovimientoExito()[0], modelo.getMovimientoEliminarCarpeta()[3] 
									+ nombreArchivo);
						}
					}
				}
			} 
			else {
				vistaEliminarCarpeta.mostrarMensajeEmergente(modelo.getTextoCarpetaNoSeleccionada());
			}
		} catch (Exception e) {
			conexion.registrarMovimiento(modelo.getMovimientoEliminarCarpeta()[0], modelo.getMovimientoExito()[1], modelo.getMovimientoEliminarCarpeta()[4]);
		}
		
		eventos.getControladorFTPPrincipal().actualizarContenido();
	}
	
	/**
	 * Comprueba si se ha eliminado
	 * @return comprobacion - tipo boolean - devuelve true si se elimina 
	 */
	private boolean comprobarEliminacion() {
		boolean comprobacion = false;
		try {
			FTPFile[] subFicheros = cliente.listFiles(cliente.printWorkingDirectory());
			for(int i=0; i<subFicheros.length; i++) {
				if(subFicheros[i].getName().toString().equals(carpetaSeleccionada)) {
					comprobacion = true;
				}
			}
		}
		catch(Exception e) {}
		return comprobacion;
	}

	/**
	 * Elimina la carpeta seleccionada y subficheros
	 * @param cliente - tipo FTPClient - cliente ftp
	 * @param nombreArchivo - tipo String - nombre del archivo
	 * @param directorioActual - tipo String - directorio actual FTP
	 * @throws HeadlessException
	 * @throws IOException
	 */
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

		if (subFicheros != null || subFicheros.length > 0) {
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
					// remove the sub directory
					eliminarCarpeta(cliente, filePath, directorioActual);
				} else {
					// delete the file
					boolean deleted = cliente.deleteFile(filePath);
				}
			}
			cliente.changeWorkingDirectory(nombreArchivo);
			cliente.changeToParentDirectory();
			// finally, remove the directory itself
			boolean removed = cliente.removeDirectory(listaDirectorios);

		}
	}
}
