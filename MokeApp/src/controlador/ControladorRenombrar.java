package controlador;
/**
 * @author Daniel Jesús Doblas Florido
 * @date 15/12/2022
 * @version 01
 */
import java.awt.event.ActionEvent;

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
	
	public ControladorRenombrar(Modelo modelo, Vista vista, Eventos eventos, Conexion conexion, FTPClient cliente) {
		
		this.modelo = modelo;
		this.vista = vista;
		this.eventos = eventos;
		this.conexion = conexion;
		this.cliente = cliente;
		vistaRenombrarArchivo = new VistaRenombrarArchivo(cliente);
		
	}
}
