package controlador;

import java.awt.HeadlessException;
/**
 * @author Daniel Jes�s Doblas Florido
 * @date 15/12/2022
 * @version 01
 */
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.apache.commons.net.ftp.FTPClient;

import conexion.Conexion;
import modelo.Modelo;
import vista.Vista;
import vista.VistaRenombrarArchivo;

public class ControladorRenombrar {

	private Modelo modelo;
	private Vista vista;
	private Eventos eventos;
	private Conexion conexion;
	private FTPClient cliente;
	private VistaRenombrarArchivo vistaRenombrarArchivo;
	private String nombreArchivo = "";
	private String sufijo = "";
	private boolean carpeta = false;

	public ControladorRenombrar(Modelo modelo, Vista vista, Eventos eventos, Conexion conexion, FTPClient cliente) {

		this.modelo = modelo;
		this.vista = vista;
		this.eventos = eventos;
		this.conexion = conexion;
		this.cliente = cliente;
		vistaRenombrarArchivo = new VistaRenombrarArchivo();

		nombreArchivo = eventos.getControladorFTPPrincipal().getInfoFicheroPulsado();

		if (nombreArchivo.contains("carpeta-")) {
			nombreArchivo = nombreArchivo.replace("carpeta-", "");
			carpeta = true;
		} else {
			nombreArchivo = nombreArchivo.replace("fichero-", "");
			sufijo = nombreArchivo.substring(nombreArchivo.lastIndexOf("."));
		}
		try {
			renombrarArchivos(nombreArchivo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		eventos.getControladorFTPPrincipal().actualizarContenido();
	}

	private void renombrarArchivos(String nombreArchivo) throws HeadlessException, IOException {
		String nuevoNombre = JOptionPane.showInputDialog(null, "Introduce nuevo nombre del archivo", nombreArchivo);
		if (nuevoNombre != null) {
			if (carpeta) {
				nuevoNombre = nuevoNombre.replace(".", "");
			} else {

				if (nuevoNombre.contains(".")) {

					if (nuevoNombre.substring(nuevoNombre.lastIndexOf(".")).equals(sufijo)) {
						sufijo = "";
					} else {
						vistaRenombrarArchivo.mostrarMensajeEmergente("FICHERO INCORRECTO",
								"La extension indicada no coincide\nSe establecera la extension anterior");
						nuevoNombre = nuevoNombre.substring(0, nuevoNombre.lastIndexOf("."));
					}
				}

			}
			nuevoNombre = nuevoNombre.replace("-", "_");
			if (cliente.rename(nombreArchivo, nuevoNombre + sufijo)) {
				vistaRenombrarArchivo.mostrarMensajeEmergente("Renombrar",
						"Se ha renombrado el archivo " + nombreArchivo + " a " + nuevoNombre+sufijo);
			} else {
				vistaRenombrarArchivo.mostrarMensajeEmergente("Renombrar",
						"No se ha podido renombrar el archivo a " + nuevoNombre);
			}
		}
	}
}
