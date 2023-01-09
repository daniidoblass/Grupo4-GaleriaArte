/**
 * 
 * Clase VistaDescargarArchivo
 * 
 * Muestra ventana emergente donde descargar 
 * archivo en local
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

public class VistaDescargarArchivo extends JFrame{

	/**
	 * modelo - tipo Modelo - contiene textos del programa
	 */
	private Modelo modelo;
	
	/**
	 * jFileChooser - tipo JFileChooser - ventana obtener archivo local
	 */
	private JFileChooser jFileChooser;
	
	/**
	 * Constructor por defecto de descargar archivo
	 * @param modelo - tipo Modelo - contiene textos del programa
	 */
	public VistaDescargarArchivo(Modelo modelo) {
		this.modelo = modelo;
	}
	
	/**
	 * Configuracion de JFileChooser
	 * @return jFileChooser.showDialog - tipo int - opcion seleccionada
	 */
	public int mostrarJFileChooser() {
		jFileChooser = new JFileChooser();
        jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jFileChooser.setDialogTitle(modelo.getTituloDescarga());
        return jFileChooser.showDialog(null, "Descargar");
	}

	/**
	 * Obtener file chooser
	 * @return jFileChooser - tipo JFileChooser - ventana obtener archivo local
	 */
	public JFileChooser getjFileChooser() {
		return jFileChooser;
	}

	/**
	 * Insertar jFileChooser
	 * @param jFileChooser - tipo JFileChooser - ventana obtener archivo local
	 */
	public void setjFileChooser(JFileChooser jFileChooser) {
		this.jFileChooser = jFileChooser;
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
