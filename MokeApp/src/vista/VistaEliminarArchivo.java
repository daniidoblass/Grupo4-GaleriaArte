/**
 * 
 * Clase VistaEliminarArchivo
 * 
 * Muestra ventana emergente de confirmación
 * para eliminar archivo seleccionado
 * 
 * @author Daniel Jesús Doblas Florido
 * @date 14/12/2022
 * @version 01
 */

package vista;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import modelo.Modelo;

public class VistaEliminarArchivo extends JFrame{

	/**
	 * modelo - tipo Modelo - contiene textos del programa
	 */
	private Modelo modelo;
	
	/**
	 * jFileChooser - tipo JFileChooser - ventana obtener archivo local
	 */
	private JFileChooser jFileChooser;
	
	/**
	 * Construtor por defecto de eliminar archivo
	 * @param modelo - tipo Modelo - contiene textos del programa
	 */
	public  VistaEliminarArchivo(Modelo modelo) {
		this.modelo = modelo;
	}
	
	/**
	 * Mensaje Emergente
	 * @param titulo - tipo String - titulo de ventana
	 * @param mensaje - tipo String - mensaje de ventana
	 */
	public void mostrarMensajeEmergente(String titulo, String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Mensaje Emergente de confirmacion
	 * @param titulo - tipo String - titulo de ventana
	 * @param mensaje - tipo String - mensaje de ventana
	 */
	public int mostrarMensajeConfirmacion(String titulo, String mensaje) {
		return JOptionPane.showConfirmDialog(null, mensaje, titulo, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
	}
	
}
