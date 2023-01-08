/**
 * 
 * Clase ControladorAdjuntarArchivoGmail
 * 
 * Clase que permite seleccionar fichero de máquina local
 * 
 * @author Javier Jimenez Torres
 * @date 09/12/2022
 * @version 01
 */
 
package controlador;

import java.io.File;

import javax.swing.JFileChooser;

import modelo.Modelo;
import vista.VistaMailEnviarCorreo;

public class ControladorAdjuntarArchivoGmail {
	
	/**
	 * modelo - tipo Modelo - contiene textos del programa
	 */
    private Modelo modelo;
	
	/**
	 * vistaMailEnviarCorreo - tipo VistaMailEnviarCorreo - vista de correo a enviar
	 */
	private VistaMailEnviarCorreo vistaMailEnviarCorreo;
	
	/**
	 * ruta - tipo String - ruta del archivo a enviar
	 */
	private String ruta;
	
	/**
	 * nombreArchivo - tipo String - nombre del archivo a enviar
	 */
	private String nombreArchivo;

	/**
	 * Constructor por defecto
	 * @param vistaMailEnviarCorreo - tipo VistaMailEnviarCorreo - vista de correo a enviar
	 */
	public ControladorAdjuntarArchivoGmail(VistaMailEnviarCorreo vistaMailEnviarCorreo) {
		modelo = new Modelo();
		this.vistaMailEnviarCorreo = vistaMailEnviarCorreo;
	}

	/**
	 * Permite obtener fichero seleccionado del ordenador
	 */
	public void obtenerFicheroSeleccionado() {
		if (vistaMailEnviarCorreo.mostrarJFileChooser() == JFileChooser.APPROVE_OPTION) {
			File f = vistaMailEnviarCorreo.getJFileChooser().getSelectedFile();
			ruta = f.getAbsolutePath();
			nombreArchivo = f.getName();
			vistaMailEnviarCorreo.getLblAdjuntar().setText(modelo.getAdjuntado() + nombreArchivo);
			vistaMailEnviarCorreo.getLblAdjuntar().setVisible(true);
		}
	}

	/**
	 * Obtener ruta del archivo
	 * @return ruta - tipo String - ruta del archivo
	 */
	public String getRuta() {
		return ruta;
	}

	/**
	 * Insertar ruta
	 * @param ruta- tipo String - ruta del archivo
	 */
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	/**
	 * Obtener nombre del archivo
	 * @return nombreArchivo - tipo String - nombre del archivo
	 */
	public String getNombreArchivo() {
		return nombreArchivo;
	}

	/**
	 * Insertar nombre del archivo
	 * @param nombreArchivo - tipo String - nombre del archivo
	 */
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
}
