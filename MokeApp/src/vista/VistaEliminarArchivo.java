package vista;
/**
 * @author Daniel Jesús Doblas Florido
 * @date 14/12/2022
 * @version 01
 */
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import controlador.ControladorDescargarArchivo;
import modelo.Modelo;

public class VistaEliminarArchivo extends JFrame{

	private Modelo modelo;
	private JFileChooser jFileChooser;
	
	public  VistaEliminarArchivo(Modelo modelo) {
		this.modelo = modelo;
	}
	
	/*
	 * Mensaje Emergente
	 */
	public void mostrarMensajeEmergente(String titulo, String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public int mostrarMensajeConfirmacion(String titulo, String mensaje) {
		return JOptionPane.showConfirmDialog(null, mensaje, titulo, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
	}
	
}
