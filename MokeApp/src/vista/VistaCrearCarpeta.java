/**
 * 
 * Clase VistaCrearCarpeta
 * 
 * Muestra ventana emergente para introducir carpeta
 * 
 * @author Daniel Jesús Doblas Florido
 * @date 14/12/2022
 * @version 01
 */

package vista;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import modelo.Modelo;

public class VistaCrearCarpeta extends JFrame{
	
	/**
	 * nombreCarpeta - tipo String - nombre de carpeta
	 */
	private String nombreCarpeta;
	
	/**
	 * modelo - tipo Modelo - contiene textos del programa
	 */
	private Modelo modelo;

	/**
	 * Constructor por defecto de crear carpeta
	 * @param modelo - tipo Modelo - contiene textos del programa
	 */
	public VistaCrearCarpeta(Modelo modelo) {
		this.modelo = modelo;
	}
	
	 /**
	 * Mensaje Emergente para crear carpeta
	 * @param titulo - tipo String - titulo de ventana
	 * @param mensaje - tipo String - mensaje de ventana
	 */
	public String crearNombreCarpeta(String titulo, String mensaje) {
		 return JOptionPane.showInputDialog(null, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
	}

	 /**
	 * Mensaje Emergente
	 * @param titulo - tipo String - titulo de ventana
	 * @param mensaje - tipo String - mensaje de ventana
	 */
	public void mostrarMensajeEmergente(String titulo, String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
	}
}