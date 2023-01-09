/**
 * 
 * Clase ControladorEliminarArchivo
 * 
 * Elimina el archivo seleccionado por el usuario
 * del Servidor FTP
 * 
 * @author Daniel Jesus Doblas Florido
 * @date 14/12/2022
 * @version 01
 */

package controlador;

import org.apache.commons.net.ftp.FTPClient;

import conexion.Conexion;
import modelo.Modelo;
import vista.Vista;
import vista.VistaEliminarArchivo;

public class ControladorEliminarArchivo {
	
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
     * vistaEliminarArchivo - tipo VistaEliminarArchivo - vista de eliminar archivo
     */
	private VistaEliminarArchivo vistaEliminarArchivo;

	/**
	 * Constructor por defecto para eliminar archivo
	 * @param modelo - tipo Modelo - contiene textos del programa
	 * @param vista - tipo Vista - vista principal del programa
	 * @param eventos - tipo Eventos - eventos principales 
	 * @param conexion - tipo Conexion - conexion con base de datos
	 * @param cliente - tipo FTPClient - cliente FTP
	 */
	public ControladorEliminarArchivo(Modelo modelo, Vista vista, Eventos eventos, Conexion conexion,FTPClient cliente) {
		
		this.cliente = cliente;
		this.modelo = modelo;
		this.vista = vista;
		this.eventos = eventos;
		vistaEliminarArchivo = new VistaEliminarArchivo(modelo);
		this.conexion = conexion;
		eliminarFichero();
		
	}
	
	/**
	 * Permite eliminar archivo seleccionado
	 */
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
						conexion.registrarMovimiento(modelo.getMovimientoEliminarArchivo()[0], modelo.getMovimientoExito()[0], modelo.getMovimientoEliminarArchivo()[1] + archivoDelServidor);
					}
					else {
						vistaEliminarArchivo.mostrarMensajeEmergente(modelo.getTextosEliminarArchivos()[0], modelo.getTextosEliminarArchivos()[4]);
						conexion.registrarMovimiento(modelo.getMovimientoEliminarArchivo()[0], modelo.getMovimientoExito()[1], modelo.getMovimientoEliminarArchivo()[2] + archivoDelServidor);
					}
				}
			}
		}
		catch(Exception e) {
			vistaEliminarArchivo.mostrarMensajeEmergente(modelo.getTextosEliminarArchivos()[0], modelo.getTextosEliminarArchivos()[4]);
			conexion.registrarMovimiento(modelo.getMovimientoEliminarArchivo()[0], modelo.getMovimientoExito()[1], modelo.getMovimientoEliminarArchivo()[3]);
		}
	}
}
