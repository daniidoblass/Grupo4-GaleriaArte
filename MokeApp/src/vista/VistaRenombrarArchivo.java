/**
 * 
 * Clase VistaRenombrarArchivo
 * 
 * Muestra ventanas emergentes para introducir
 * el nombre a renombrar
 * 
 * @author Pablo Pérez Ferre
 * @date 15/12/2022
 * @version 01
 */

package vista;

import javax.swing.*;

import org.apache.commons.net.ftp.FTPClient;

import modelo.Modelo;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class VistaRenombrarArchivo extends JFrame {

	/**
	 * Constructor por defecto de vista renombrar
	 */
	public VistaRenombrarArchivo() {

	}

	/**
	 * Mensaje Emergente
	 * @param titulo - tipo String - titulo de ventana
	 * @param mensaje - tipo String - mensaje de ventana
	 */
	public void mostrarMensajeEmergente(String titulo, String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje);
	}
	
	/**
	 * Mensaje Emergente para input
	 * @param titulo - tipo String - titulo de ventana
	 * @param mensaje - tipo String - mensaje de ventana
	 */
	public String mostrarMensajeInput(String titulo, String mensaje) {
		return JOptionPane.showInputDialog(null, mensaje);
	}


}