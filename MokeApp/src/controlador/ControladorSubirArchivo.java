/**
 * 
 * Clase ControladorSubirArchivo
 * 
 * Permite subir un archivo seleccionado del 
 * ordenador al servidor FTP
 * 
 * @author Pablo Navarro
 * @date 15/12/2022
 * @version 01
 */

package controlador;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import conexion.Conexion;
import modelo.Modelo;
import vista.Vista;
import vista.VistaSubirArchivo;

public class ControladorSubirArchivo {

	/**
	 * modelo - tipo Modelo - contiene textos del programa
	 */
	private Modelo modelo;
	
	/**
     * eventos - tipo Eventos - eventos principales 
     */
	private Eventos eventos;
	
	/**
     * conexion - tipo Conexion - conexion con base de datos
     */
	private Conexion conexion;
	
	/**
     * vistaSubirArchivo - tipo VistaSubirArchivo - vista para subir archivo
     */
	private VistaSubirArchivo vistaSubirArchivo;
	
	/**
     * cliente - tipo FTPClient - cliente FTP
     */
	private FTPClient cliente;

	/**
	 * Constructor por defecto. Permite subir archivo 
	 * @param modelo - tipo Modelo - contiene textos del programa
	 * @param vista - tipo Vista - vista principal del programa
	 * @param eventos - tipo Eventos - eventos principales 
	 * @param conexion - tipo Conexion - conexion con base de datos
	 * @param cliente - tipo FTPClient - cliente FTP
	 */
	public ControladorSubirArchivo(Modelo modelo, Vista vista, Eventos eventos, Conexion conexion, FTPClient cliente) {
		this.modelo = modelo;
		this.eventos = eventos;
		this.conexion = conexion;
		vistaSubirArchivo = new VistaSubirArchivo(modelo);
		this.cliente = cliente;

		obtenerFicheroSeleccionado();
	}

	/**
	 * Obtiene fichero seleccionado y lo sube a servidor FTP
	 */
	public void obtenerFicheroSeleccionado() {
		if (vistaSubirArchivo.mostrarJFileChooser() == JFileChooser.APPROVE_OPTION) {
			File f = vistaSubirArchivo.getJFileChooser().getSelectedFile();
			String archivo = f.getAbsolutePath();
			String nombreArchivo = f.getName();
			
			if(nombreArchivo.contains(".")) {
				try {
					if (subirFichero(archivo, nombreArchivo)) {
						JOptionPane.showMessageDialog(vistaSubirArchivo.getJFileChooser(),
								modelo.getTextosSubirArchivo()[0] + nombreArchivo);
						conexion.registrarMovimiento(modelo.getMovimientoSubirArchivo()[0], modelo.getMovimientoExito()[0], modelo.getMovimientoSubirArchivo()[1] + nombreArchivo);
						eventos.getControladorFTPPrincipal().actualizarContenido();
					} else {
						JOptionPane.showMessageDialog(vistaSubirArchivo.getJFileChooser(),
								modelo.getTextosSubirArchivo()[1] + nombreArchivo);
						conexion.registrarMovimiento(modelo.getMovimientoSubirArchivo()[0], modelo.getMovimientoExito()[1], modelo.getMovimientoSubirArchivo()[2] + nombreArchivo);
					}
				} catch (Exception el) {
					conexion.registrarMovimiento(modelo.getMovimientoSubirArchivo()[0], modelo.getMovimientoExito()[1], modelo.getMovimientoSubirArchivo()[2] + nombreArchivo);
				}
			}
			else {
				conexion.registrarMovimiento(modelo.getMovimientoSubirArchivo()[0], modelo.getMovimientoExito()[1], modelo.getMovimientoSubirArchivo()[3]);
			}
		}
	}

	/**
	 * Sube fichero pasado por parámetro al servidor FTP
	 * @param archivo - tipo String - archivo seleccionado de ordenador
	 * @param nombreArchivo - tipo String - nombre de archivo 
	 */
	private boolean subirFichero(String archivo, String nombreArchivo) throws IOException {
		cliente.setFileType(FTP.BINARY_FILE_TYPE);
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(archivo));
		boolean operacion = cliente.storeFile(nombreArchivo, in);
		in.close();
		return operacion;
	}

}
