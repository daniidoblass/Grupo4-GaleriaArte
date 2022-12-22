/**
 * @author Samuel Acosta Fernandez
 * @date 09/02/2022
 * @version 01
 */

package controlador;

import modelo.Modelo;
import vista.Vista;
import vista.VistaLogin;
import conexion.Conexion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;

import org.apache.commons.net.ftp.FTPClient;

public class ControladorLogin implements ActionListener {

	private Modelo modelo;
	private Vista vista;
	private VistaLogin vistaLogin;
	private Eventos eventos;
	private Conexion conexion;
	private FTPClient cliente;
	private static int contadorActionListener = 0;

	public ControladorLogin(Modelo modelo, Vista vista, Eventos eventos, Conexion conexion, FTPClient cliente) {
		this.modelo = modelo;
		this.vista = vista;
		this.eventos = eventos;
		vistaLogin = new VistaLogin(modelo, vista);
		this.conexion = conexion;
		this.cliente = cliente;

		// Configurar titulo de la pagina
		configurarTitulo();

		// Configurar Boton Login
		configurarBotonLogin();

		// Actualizar ventana
		actualizarVentana();
	}

	private void configurarTitulo() {
		vista.setIcono(modelo.getRutasIconos()[4]);
		vista.setTitulo(modelo.getTextoLogos()[7]);
		eventos.setVentanaActual(modelo.getTextosGenerales()[6]);
	}

	private void configurarBotonLogin() {
		vistaLogin.getBotonLogin().addActionListener(this);
	}

	private void actualizarVentana() {
		vista.repaint();
		vista.pack();
		vista.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String usuario = vistaLogin.getUsuario().getText().toString();
		String password = String.valueOf(vistaLogin.getPassword().getPassword());
		String categoria = comprobarUsuario(usuario, password);

		try {
			// Volver a directorio raiz
			while (!cliente.printWorkingDirectory().equals(modelo.getBarra())) {
				cliente.changeToParentDirectory();
			}
		} catch (Exception e1) {
		}

		if (categoria.equals(modelo.getCategorias()[0])) {
			new ControladorAdmin(modelo, vista, eventos, conexion, cliente);
			eventos.setUsuario(usuario);
		} else if (categoria.equals(modelo.getCategorias()[1])) {
			vistaLogin.mostrarMensajeEmergente(modelo.getTextosGenerales()[7], modelo.getTextosGenerales()[8]);
		} else if (categoria.equals(modelo.getCategorias()[2])) {
			new ControladorOpciones(modelo, vista, eventos, conexion, cliente);
			eventos.setUsuario(usuario);
			eventos.setDirectorioLimite(modelo.getRutasUsers()[0] + usuario);
			try {
				cliente.makeDirectory(modelo.getRutasUsers()[0] + usuario);
				cliente.changeWorkingDirectory(eventos.getDirectorioLimite());
			} catch (Exception e1) {
			}
		} else {

			// Creamos la consulta para obtener el nombre del responsable asignado.
			String consulta = modelo.getConsultaResponsableAsignado() + usuario + modelo.getComillaSimple();
			ResultSet rs = conexion.realizarConsultaRS(consulta);
			String responsableAsignado = modelo.getNada();
			try {
				while (rs.next()) {

					responsableAsignado = rs.getString(1);
				}
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			new ControladorOpciones(modelo, vista, eventos, conexion, cliente);
			eventos.setUsuario(usuario);
			eventos.setDirectorioLimite(
					modelo.getRutasUsers()[0] + responsableAsignado + modelo.getRutasUsers()[1] + usuario);
			try {
				cliente.makeDirectory(eventos.getDirectorioLimite());
				cliente.changeWorkingDirectory(eventos.getDirectorioLimite());
			} catch (Exception e1) {
			}
		}

		// Activar solo una vez el ActionListener del icono superior
		if (contadorActionListener == 0) {
			vista.getIcono().addActionListener(eventos);
			contadorActionListener++;
		}
	}

	public String comprobarUsuario(String usuario, String password) {

		String categoria = "null";

		try {
			ResultSet usuarios = conexion.realizarConsultaRS(modelo.getConsultaPasswordEncriptado01() + password
					+ modelo.getConsultaPasswordEncriptado02() + usuario + modelo.getComillaSimple());

			if (usuarios.next()) {
				categoria = usuarios.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return categoria;
	}
}
