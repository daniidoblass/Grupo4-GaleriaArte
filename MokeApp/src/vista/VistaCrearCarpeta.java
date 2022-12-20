package vista;

/**
 * @author Daniel Jesús Doblas Florido
 * @date 14/12/2022
 * @version 01
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import conexion.Conexion;
import controlador.Eventos;

import javax.swing.*;

import modelo.Modelo;

public class VistaCrearCarpeta extends JFrame{
	
	private String nombreCarpeta;
	private Modelo modelo;

	public VistaCrearCarpeta(Modelo modelo) {
		this.modelo = modelo;
	}
	
	/*
	 * Mensaje Emergente
	 */
	public String crearNombreCarpeta(String titulo, String mensaje) {
		 return JOptionPane.showInputDialog(null, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
	}

	public void mostrarMensajeEmergente(String titulo, String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
	}
}