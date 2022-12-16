package vista;

/**
 * @author Pablo P�rez Ferre
 * @date 15/12/2022
 * @version 01
 */
import javax.swing.*;

import org.apache.commons.net.ftp.FTPClient;

import modelo.Modelo;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class VistaEliminarCarpeta extends JFrame {

	private FTPClient cliente;
	private String nombreArchivo = "mokemamahuevo";

	public VistaEliminarCarpeta(FTPClient cliente) {

		this.cliente = cliente;

		try {
			eliminarCarpeta(nombreArchivo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void eliminarCarpeta(String nombreArchivo) throws HeadlessException, IOException {
		// TODO Auto-generated method stub
		int opcion = JOptionPane.showConfirmDialog(null, "Deseas eliminar la carpeta "+nombreArchivo);
		if (opcion == JOptionPane.YES_OPTION) {
			JOptionPane.showMessageDialog(null,nombreArchivo+" se ha elimindado correctamente");
		} else {

		}
	}

}