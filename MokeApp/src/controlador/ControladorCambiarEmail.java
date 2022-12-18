package controlador;

import java.sql.ResultSet;

import javax.swing.JOptionPane;

import org.apache.commons.net.ftp.FTPClient;

import conexion.Conexion;
import modelo.Modelo;
import vista.Vista;
import vista.VistaCambiarEmail;

public class ControladorCambiarEmail {

	private Modelo modelo;
	private Vista vista;
	private Eventos eventos;
	private Conexion conexion;
	private FTPClient cliente;
	private VistaCambiarEmail vistaCambiarEmail;
	
	public ControladorCambiarEmail(Modelo modelo, Vista vista, Eventos eventos, Conexion conexion, FTPClient cliente) {
		
	}

}
