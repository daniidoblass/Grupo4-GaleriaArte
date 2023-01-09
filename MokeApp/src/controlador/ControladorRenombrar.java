/**
 * 
 * Clase ControladorRenombrar
 * 
 * Permite renombrar tanto archivos como carpetas
 * seleccionados en el explorador FTP
 * 
 * @author Pablo Perez Ferre
 * @date 15/12/2022
 * @version 01
 */

package controlador;

import java.awt.HeadlessException;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.apache.commons.net.ftp.FTPClient;

import conexion.Conexion;
import modelo.Modelo;
import vista.Vista;
import vista.VistaRenombrarArchivo;

public class ControladorRenombrar {

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
     * vistaRenombrarArchivo - tipo VistaRenombrarArchivo - vista de renombrar fichero
     */
	private VistaRenombrarArchivo vistaRenombrarArchivo;
	
	/**
	 * nombreArchivo - tipo String - nombre del fichero
	 */
	private String nombreArchivo = "";
	
	/**
	 * sufijo - tipo String - formato de fichero seleccionado
	 */
	private String sufijo = "";
	
	/**
	 * carpeta - tipo boolean - comprueba si selecciona carpeta
	 */
	private boolean carpeta = false;

	/**
	 * Constructor por defecto. Configura vista para renombrar fichero
	 * @param modelo - tipo Modelo - contiene textos del programa
	 * @param vista - tipo Vista - vista principal del programa
	 * @param eventos - tipo Eventos - eventos principales 
	 * @param conexion - tipo Conexion - conexion con base de datos
	 * @param cliente - tipo FTPClient - cliente FTP
	 */
	public ControladorRenombrar(Modelo modelo, Vista vista, Eventos eventos, Conexion conexion, FTPClient cliente) {

		this.modelo = modelo;
		this.vista = vista;
		this.eventos = eventos;
		this.conexion = conexion;
		this.cliente = cliente;
		vistaRenombrarArchivo = new VistaRenombrarArchivo();

		nombreArchivo = eventos.getControladorFTPPrincipal().getInfoFicheroPulsado();

		if (nombreArchivo.contains(modelo.getCarpetaGuion())) {
			nombreArchivo = nombreArchivo.replace(modelo.getCarpetaGuion(), "");
			carpeta = true;
		} else {
			nombreArchivo = nombreArchivo.replace(modelo.getFicheroGuion(), "");
			sufijo = nombreArchivo.substring(nombreArchivo.lastIndexOf("."));
		}
		try {
			renombrarArchivos(nombreArchivo);
		} catch (Exception e) {
			conexion.registrarMovimiento(modelo.getMovimientoRenombrar()[0], modelo.getMovimientoExito()[1], modelo.getMovimientoRenombrar()[1]);
		}

		eventos.getControladorFTPPrincipal().actualizarContenido();
	}

	/**
	 * Renombra el fichero seleccionado
	 * @param nombreArchivo - tipo String - nombre del fichero
	 * @throws HeadlessException
	 * @throws IOException
	 */
	private void renombrarArchivos(String nombreArchivo) throws HeadlessException, IOException {
		String nuevoNombre = JOptionPane.showInputDialog(null, modelo.getTextosRenombrar()[0], nombreArchivo);
		if (nuevoNombre != null) {
			if (carpeta) {
				nuevoNombre = nuevoNombre.replace(".", "");
			} else {

				if (nuevoNombre.contains(".")) {

					if (nuevoNombre.substring(nuevoNombre.lastIndexOf(".")).equals(sufijo)) {
						sufijo = "";
					} else {
						vistaRenombrarArchivo.mostrarMensajeEmergente(modelo.getTextosRenombrar()[1],
								modelo.getTextosRenombrar()[2]);
						nuevoNombre = nuevoNombre.substring(0, nuevoNombre.lastIndexOf("."));
					}
				}

			}
			nuevoNombre = nuevoNombre.replace(" ", "");
			nuevoNombre = nuevoNombre.replace("-", "_");
			if (cliente.rename(nombreArchivo, nuevoNombre + sufijo)) {
				vistaRenombrarArchivo.mostrarMensajeEmergente(modelo.getTextosRenombrar()[3],
						modelo.getTextosRenombrar()[4] + nombreArchivo + modelo.getTextosRenombrar()[5] + nuevoNombre + sufijo);
				conexion.registrarMovimiento(modelo.getMovimientoRenombrar()[0], modelo.getMovimientoExito()[0], modelo.getMovimientoRenombrar()[2]);
			} else {
				vistaRenombrarArchivo.mostrarMensajeEmergente(modelo.getTextosRenombrar()[3],
						modelo.getTextosRenombrar()[6] + nuevoNombre);
				conexion.registrarMovimiento(modelo.getMovimientoRenombrar()[0], modelo.getMovimientoExito()[1], modelo.getMovimientoRenombrar()[3]);
			}
		}
	}
}
