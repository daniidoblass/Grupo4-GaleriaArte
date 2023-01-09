/**
 * 
 * Clase VistaEliminarCarpeta
 * 
 * Muestra ventana emergente de confirmación
 * para eliminar directorio seleccionado
 * 
 * @author Pablo Perez Ferre
 * @date 15/12/2022
 * @version 01
 */

package vista;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.commons.net.ftp.FTPClient;

import controlador.Eventos;
import modelo.Modelo;

public class VistaEliminarCarpeta extends JFrame {

	/**
	 * modelo - tipo Modelo - contiene textos del programa
	 */
	private Modelo modelo;
	
	/**
     * cliente - tipo FTPClient - cliente FTP
     */
	private FTPClient cliente;
	
	/**
     * eventos - tipo Eventos - eventos principales 
     */
	private Eventos eventos;

	/**
	 * Constructor por defecto para eliminar carpeta
	 * @param cliente - tipo FTPClient - cliente FTP
	 * @param modelo - tipo Modelo - contiene textos del programa
	 * @param eventos - tipo Eventos - eventos principales 
	 */
	public VistaEliminarCarpeta(FTPClient cliente, Modelo modelo, Eventos eventos) {
		this.cliente = cliente;
		this.modelo = modelo;
		this.eventos = eventos;
	}
	
	public void mostrarMensajeEmergente(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje);
	}

	public int mostrarMensajeConfirmacion(String nombreArchivo) {
		return JOptionPane.showConfirmDialog(null,
				modelo.getTextosEliminarCarpetas()[1] + nombreArchivo + modelo.getSignoPregunta(),
				modelo.getTextosEliminarCarpetas()[2], JOptionPane.YES_NO_OPTION);
	}

}