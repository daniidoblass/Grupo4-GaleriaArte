package controlador;

import java.io.File;

import javax.swing.JFileChooser;

import vista.VistaMailEnviarCorreo;

public class ControladorAdjuntarArchivoGmail {
	private VistaMailEnviarCorreo vistaMailEnviarCorreo;
	private String ruta;
	private String nombreArchivo;

	public ControladorAdjuntarArchivoGmail(VistaMailEnviarCorreo vistaMailEnviarCorreo) {
		this.vistaMailEnviarCorreo = vistaMailEnviarCorreo;
	}

	public void  obtenerFicheroSeleccionado() {
		if (vistaMailEnviarCorreo.mostrarJFileChooser() == JFileChooser.APPROVE_OPTION) {
			File f = vistaMailEnviarCorreo.getJFileChooser().getSelectedFile();
			ruta = f.getAbsolutePath();
			nombreArchivo = f.getName();

			System.out.println("Ruta archivo seleccionada --> " + ruta);
			System.out.println("Archivo seleccionado --> " + nombreArchivo);
		}
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
}
