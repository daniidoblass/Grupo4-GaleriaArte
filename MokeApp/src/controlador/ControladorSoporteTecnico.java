package controlador;

import org.apache.commons.net.ftp.FTPClient;

import conexion.Conexion;
import modelo.Modelo;
import vista.Vista;
import vista.VistaSoporteTecnico;

public class ControladorSoporteTecnico {

	private Modelo modelo;
	private Vista vista;
	private Eventos eventos;
	private Conexion conexion;
	private FTPClient cliente;
	private VistaSoporteTecnico vistaSoporteTecnico;
	
	public ControladorSoporteTecnico(Modelo modelo, Vista vista, Eventos eventos, Conexion conexion,
			FTPClient cliente) {
		this.modelo = modelo;
		this.vista = vista;
		this.eventos = eventos;
		this.conexion = conexion;
		this.cliente = cliente;
		vistaSoporteTecnico = new VistaSoporteTecnico(modelo);
	}

}
