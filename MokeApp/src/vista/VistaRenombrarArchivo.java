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

public class VistaRenombrarArchivo extends JFrame {

	public VistaRenombrarArchivo() {


	}

	public void mostrarMensajeEmergente(String titulo, String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje);
	}
	
	public String mostrarMensajeInput(String titulo, String mensaje) {
		return JOptionPane.showInputDialog(null, mensaje);
	}


}