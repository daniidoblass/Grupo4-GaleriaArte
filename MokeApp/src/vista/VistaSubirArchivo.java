/**
 * 
 * Clase VistaSubirArchivo
 * 
 * Muestra ventana emergente que permite
 * seleccionar un archivo local
 * 
 * @author Pablo Navarro Vázquez
 * @date 14/12/2022
 * @version 01
 */

package vista;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import filtros.ImageFileView;
import filtros.ImageFilter;
import filtros.ImagePreview;
import modelo.Modelo;

public class VistaSubirArchivo extends JFrame {

	/**
	 * modelo - tipo Modelo - contiene textos del programa
	 */
	private Modelo modelo;
	
	/**
	 * jFileChooser - tipo JFileChooser - ventana seleccionar archivo local
	 */
	private JFileChooser jFileChooser = new JFileChooser();
	
	/**
	 * Constructor por defecto de vista subir archivo
	 * @param modelo - tipo Modelo - contiene textos del programa
	 */
    public VistaSubirArchivo(Modelo modelo) {
    	this.modelo = modelo;
    	configurarJFileChooser();
    }
    
    /**
     * Configura JFileChooser
     */
    public void configurarJFileChooser() {
    	jFileChooser.setDialogTitle("Selecciona un archivo");
    	jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    	jFileChooser.addChoosableFileFilter(new ImageFilter());
        jFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("TXT Files",new String[]{"txt"}));
        jFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("PDF Files",new String[]{"pdf"}));
        jFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("DOCX Files",new String[]{"docx"}));
        jFileChooser.setFileView(new ImageFileView());
        jFileChooser.setAccessory(new ImagePreview(jFileChooser));
    }
    
    /**
     * Muestra JFileChooser
     * @return jFileChooser.showDialog - tipo int - opcion seleccionada de File Chooser
     */
    public int mostrarJFileChooser() {
    	return jFileChooser.showDialog(jFileChooser, "Cargar");
    }
    
    /**
     * Obtener jFileChooser
     * @return jFileChooser - tipo JFileChooser - ventana seleccionar archivo local
     */
    public JFileChooser getJFileChooser() {
    	return jFileChooser;
    }

}
