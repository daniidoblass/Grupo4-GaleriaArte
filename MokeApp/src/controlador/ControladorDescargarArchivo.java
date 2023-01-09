/**
 * 
 * Clase ControladorDescargarArchivo
 * 
 * Descarga el fichero seleccionado por el usuario
 * en el explorador FTP en la máquina local en la ruta
 * seleccionada por el usuario
 * 
 * @author Daniel Jesus Doblas Florido
 * @date 14/12/2022
 * @version 01
 */

package controlador;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import conexion.Conexion;
import modelo.Modelo;
import vista.Vista;
import vista.VistaDescargarArchivo;

public class ControladorDescargarArchivo {
	
	/**
     * cliente - tipo FTPClient - cliente FTP
     */
	private FTPClient cliente;
	
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
     * vistaDescargarArchivo - tipo VistaDescargarArchivo - vista de descargar archivo
     */
	private VistaDescargarArchivo vistaDescargarArchivo;

	/**
	 * Constructor por defecto para descargar archivo
	 * @param modelo - tipo Modelo - contiene textos del programa
	 * @param vista - tipo Vista - vista principal del programa
	 * @param eventos - tipo Eventos - eventos principales 
	 * @param conexion - tipo Conexion - conexion con base de datos
	 * @param cliente - tipo FTPClient - cliente FTP
	 */
	public ControladorDescargarArchivo(Modelo modelo, Vista vista, Eventos eventos, Conexion conexion,FTPClient cliente) {
		
		this.cliente = cliente;
		this.modelo = modelo;
		this.vista = vista;
		this.eventos = eventos;
		vistaDescargarArchivo = new VistaDescargarArchivo(modelo);
		this.conexion = conexion;
		descargarFichero();
		
	}
	
	/**
	 * Permite descargar fichero seleccionado a ordenador de usuario
	 */
	private void descargarFichero() {
		
		try {
			cliente.setFileType(FTP.BINARY_FILE_TYPE);

			String archivoDelServidor = eventos.getControladorFTPPrincipal().getInfoFicheroPulsado();
			if(archivoDelServidor.isEmpty() || archivoDelServidor.equals("") || archivoDelServidor.contains(modelo.getCarpetaGuion())) {
				vistaDescargarArchivo.mostrarMensajeEmergente(modelo.getTextosDescargarArchivos()[0], modelo.getTextosDescargarArchivos()[1]);
			}
			else {
				archivoDelServidor = archivoDelServidor.replace(modelo.getFicheroGuion(), modelo.getNada());
				if(vistaDescargarArchivo.mostrarMensajeConfirmacion(modelo.getTextosDescargarArchivos()[0], modelo.getTextosDescargarArchivos()[2] + archivoDelServidor + modelo.getSignoPregunta()) == 0) {
					int returnVal = vistaDescargarArchivo.mostrarJFileChooser();

			        if (returnVal == vistaDescargarArchivo.getjFileChooser().APPROVE_OPTION) {
			        	
			        	// obtener carpeta de destino
			        	File f = vistaDescargarArchivo.getjFileChooser().getSelectedFile();
			        	String carpetaDestino = (f.getAbsolutePath()).toString();
			        	
			        	// ubicacion donde se va a descargar
						BufferedOutputStream buffSalida = new BufferedOutputStream(
								new FileOutputStream(carpetaDestino + modelo.getDobleBarraInvertida() + archivoDelServidor));

						// Descargar el archivo
						boolean descargado = cliente.retrieveFile(archivoDelServidor, buffSalida); // Devuelve TRUE si se ha
																									// descargado
						if (descargado) {
							vistaDescargarArchivo.mostrarMensajeEmergente(modelo.getTextosDescargarArchivos()[0], modelo.getTextosDescargarArchivos()[3]);
							conexion.registrarMovimiento(modelo.getMovimientoDescargarArchivo()[0], modelo.getMovimientoExito()[0], modelo.getMovimientoDescargarArchivo()[1] + archivoDelServidor);
						} else {
							vistaDescargarArchivo.mostrarMensajeEmergente(modelo.getTextosDescargarArchivos()[0], modelo.getTextosDescargarArchivos()[4]);
							conexion.registrarMovimiento(modelo.getMovimientoDescargarArchivo()[0], modelo.getMovimientoExito()[1], modelo.getMovimientoDescargarArchivo()[2] + archivoDelServidor);
						}
						
						//Cerrar el buffer.
						buffSalida.close();
			        }
				}
			}
		}
		catch(Exception e) {
			vistaDescargarArchivo.mostrarMensajeEmergente(modelo.getTextosDescargarArchivos()[0], modelo.getTextosDescargarArchivos()[4]);
			conexion.registrarMovimiento(modelo.getMovimientoDescargarArchivo()[0], modelo.getMovimientoExito()[1], modelo.getMovimientoDescargarArchivo()[3]);
		}
	}
}
