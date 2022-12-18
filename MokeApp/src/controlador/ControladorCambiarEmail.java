package controlador;

/**
 * @author Pablo P�rez Ferre
 * @date 15/12/2022
 * @version 01
 */
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
		this.modelo = modelo;
		this.vista = vista;
		this.eventos = eventos;
		this.conexion = conexion;
		this.cliente = cliente;
		vistaCambiarEmail = new VistaCambiarEmail(conexion);
	}

}
