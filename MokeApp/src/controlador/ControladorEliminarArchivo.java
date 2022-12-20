package controlador;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.apache.commons.net.ftp.FTPClient;

import conexion.Conexion;
import modelo.Modelo;
import vista.Vista;
import vista.VistaEliminarArchivo;
import vista.VistaSubirArchivo;

public class ControladorEliminarArchivo {
	private Modelo modelo;
	private Vista vista;
	private VistaEliminarArchivo vistaEliminarArchivo;
	private Eventos eventos;
	private Conexion conexion;
	private FTPClient cliente;

	public ControladorEliminarArchivo(Modelo modelo, Vista vista, Eventos eventos, Conexion conexion, FTPClient cliente)
			throws IOException {
		this.modelo = modelo;
		this.vista = vista;
		this.eventos = eventos;
		vistaEliminarArchivo = new VistaEliminarArchivo(modelo);
		this.conexion = conexion;
		this.cliente = cliente;

		buscarArchivo();

	}

	public void buscarArchivo() {
		try {

			if (vistaEliminarArchivo.mostrarJFileChooser() == JFileChooser.APPROVE_OPTION) {
				File f = vistaEliminarArchivo.getJFileChooser().getSelectedFile();
				String dir = f.getAbsolutePath();
				String nombreArchivo = f.getName();

				System.out.println(dir);
				System.out.println(nombreArchivo);

				int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro que quieres borrar el archivo?",
						"Borrar archivo", JOptionPane.YES_NO_OPTION);
				
				if (resp == 0) {
					if (eliminarArchivo(dir)) {
						System.out.println("Fichero eliminado... ");

					} else {
						System.out.println("No se ha podido eliminar Fichero... ");
					}
				}
			}
		} catch (Exception e) {

		}
	}

	public boolean eliminarArchivo(String direc) throws IOException {
		if (cliente.deleteFile(direc)) {
			return true;
		} else {
			return false;
		}
	}
}
