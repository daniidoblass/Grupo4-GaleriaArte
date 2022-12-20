package vista;

/**
 * @author Daniel Jes�s Doblas Florido
 * @date 14/12/2022
 * @version 01
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import conexion.Conexion;
import controlador.Eventos;
import controlador.ControladorCrearCarpeta;

import javax.swing.*;

import modelo.Modelo;

public class VistaCrearCarpeta extends JFrame{
	
	private String nombreCarpeta;
	private ControladorCrearCarpeta controladorCrearCarpeta;
	private Modelo modelo;

	public VistaCrearCarpeta(Modelo modelo) throws IOException {
		
		this.modelo = modelo;

	}
	
	public String crearNombreCarpeta() {
		
		 return JOptionPane.showInputDialog(null,"Introducir nombre carpeta", "carpeta");
	}

	
}