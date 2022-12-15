package vista;

import javax.swing.*;

import org.apache.commons.net.ftp.FTPClient;

import modelo.Modelo;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class VistaRenombrarArchivo extends JFrame  {

	private FTPClient cliente;
	private String nombreArchivo = "mokemamahuevo";
	
	public VistaRenombrarArchivo(FTPClient cliente) {

		this.cliente = cliente;
		
		try {
			renombrarArchivos(nombreArchivo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void renombrarArchivos(String nombreArchivo) throws HeadlessException, IOException {
		// TODO Auto-generated method stub

		String nuevoNombre = JOptionPane.showInputDialog(null, "Introduce nuevo nombre del archivo");
		if (nuevoNombre != null) {
			if ( cliente.rename(nombreArchivo, nuevoNombre) ) {
	                JOptionPane.showMessageDialog(null, "Se ha renombrado el archivo " + nombreArchivo + " a " + nuevoNombre);
				JOptionPane.showMessageDialog(null, "Se ha renombrado el archivo a " + nuevoNombre);
			} else {
	                JOptionPane.showMessageDialog(null, "No se ha podido renombrar el archivo " + nombreArchivo + " a " + nombreArchivo);
				JOptionPane.showMessageDialog(null, "No se ha podido renombrar el archivo a " + nuevoNombre);
			}
		}
	}
	
	


}