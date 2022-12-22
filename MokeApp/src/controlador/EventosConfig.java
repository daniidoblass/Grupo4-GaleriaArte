package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;

import conexion.Conexion;
import modelo.Modelo;
import vista.Vista;
import vista.VistaConfigPrincipal;

public class EventosConfig implements ActionListener, MouseListener {

	private Modelo modelo;
	private Eventos eventos;
	private Conexion conexion;
	private ControladorConfigPrincipal controladorConfigPrincipal;
	private VistaConfigPrincipal vistaConfigPrincipal;

	public EventosConfig(Modelo modelo, Eventos eventos, Conexion conexion,
			ControladorConfigPrincipal controladorConfigPrincipal, VistaConfigPrincipal vistaConfigPrincipal) {
		this.modelo = modelo;
		this.eventos = eventos;
		this.conexion = conexion;
		this.controladorConfigPrincipal = controladorConfigPrincipal;
		this.vistaConfigPrincipal = vistaConfigPrincipal;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source instanceof JButton) {

			JButton btn = (JButton) source;

			if (btn.getText().contains(modelo.getTextoOpcionesConfig()[0])) { // Reestablecer Contraseña
				if (comprobarPassword()) {
					int opcionComprobar = 0;
					int opcion = 0;
					String contraNueva = "";
					while (opcionComprobar == 0) {
						opcionComprobar = vistaConfigPrincipal.mostrarMensajePassword(
								modelo.getTextoOpcionesConfig()[0], modelo.getTextoDatosConfig()[0]);
						if (opcionComprobar == 0) {
							contraNueva = vistaConfigPrincipal.recogerContra();
							opcionComprobar = excepcionesPassword(contraNueva,opcionComprobar);
						}else {
							opcion=1;
						}
					}
					while (opcion == 0) {
						opcion = vistaConfigPrincipal.mostrarMensajePassword(modelo.getTextoOpcionesConfig()[0],
								modelo.getTextoDatosConfig()[3]);
						if (opcion == 0) {
							if (vistaConfigPrincipal.recogerContra().equals(contraNueva)) {
								reestablecerPassword(contraNueva);
								opcion = 1;
							} else {
								vistaConfigPrincipal.mostrarMensajeEmergente(modelo.getTextoOpcionesConfig()[0],
										modelo.getTextoDatosConfig()[4]);
							}
						}
					}
				} else {
					vistaConfigPrincipal.mostrarMensajeEmergente(modelo.getTextoContraexcepciones()[8],modelo.getTextoContraexcepciones()[9]);
				}
			} else if (btn.getText().contains(modelo.getTextoOpcionesConfig()[1]))
			{ // Cambio Correo Corporativo
//				if (comprobarPassword()) {
					vistaConfigPrincipal.mostrarMensajeEmergente(modelo.getTextoOpcionesConfig()[1],
							modelo.getTextoDatosConfig()[5]);
//					cambiarCorreo(respuesta);
//				} else {
//					vistaConfigPrincipal.mostrarMensajeEmergente("Password Incorrecta",
//							"Password introducida incorrecta");
//				}
			} else if (btn.getText().contains(modelo.getTextoOpcionesConfig()[2])) { // Soporte Técnico
				String respuesta = vistaConfigPrincipal.mostrarInputEmergente(modelo.getTextoOpcionesConfig()[2],
						modelo.getTextoDatosConfig()[2]);
				enviarMensajeSoporte(respuesta);
			}
		}
	}

	private int excepcionesPassword(String respuesta, int opcion) {
		int comprobado = 1;
		if (opcion == 1) {
		} else {
			if (respuesta.equals("") || respuesta.isEmpty()) {
				vistaConfigPrincipal.mostrarMensajeEmergente(modelo.getTextoContraexcepciones()[0],modelo.getTextoContraexcepciones()[1]);
				comprobado = 0;
			} else if (respuesta.contains(" ")) {
				vistaConfigPrincipal.mostrarMensajeEmergente(modelo.getTextoContraexcepciones()[2],modelo.getTextoContraexcepciones()[3]);
				comprobado = 0;
			} else if (respuesta.length() < 6) {
				vistaConfigPrincipal.mostrarMensajeEmergente(modelo.getTextoContraexcepciones()[4],modelo.getTextoContraexcepciones()[5]);
				comprobado = 0;
			} else if (respuesta.length() > 30) {
				vistaConfigPrincipal.mostrarMensajeEmergente(modelo.getTextoContraexcepciones()[6],modelo.getTextoContraexcepciones()[7]);
				comprobado = 0;
			}
		}
		return comprobado;
	}

	private boolean comprobarPassword() {
		boolean comprobacion = false;
		try {
			int opcion = vistaConfigPrincipal.mostrarMensajePassword(modelo.getTextoDatosConfig()[6],modelo.getTextoDatosConfig()[7]);
			if (opcion == 0) {
				String passwordActual = vistaConfigPrincipal.recogerContra();
				if (!passwordActual.equals("") && !passwordActual.isEmpty()) {
					String usuario = eventos.getUsuario();
					ResultSet rs = conexion
							.realizarConsultaRS("SELECT password FROM usuarios WHERE nombre='" + usuario + "'");
					try {
						while (rs.next()) {
							if (passwordActual.equals(rs.getString(1))) {
								comprobacion = true;
							}
						}
					} catch (Exception e) {
					}
				}
			}
		} catch (Exception e1) {
		}
		return comprobacion;
	}

	private void reestablecerPassword(String respuesta) {
		try {
			String usuario = eventos.getUsuario();
			int num = conexion.realizarUpdateStatement(
					"UPDATE usuarios SET password='" + respuesta + "' WHERE nombre = '" + usuario + "'");
			if (num > 0) {
				vistaConfigPrincipal.mostrarMensajeEmergente(modelo.getTextoRestablecerPassword()[0],modelo.getTextoRestablecerPassword()[1]);
			} else {
				vistaConfigPrincipal.mostrarMensajeEmergente(modelo.getTextoRestablecerPassword()[2],modelo.getTextoRestablecerPassword()[3]);
			}
		} catch (Exception e) {
		}
	}

	private void cambiarCorreo(String respuesta) {
		try {
			// Patrón para validar el email
			Pattern pattern = Pattern.compile(
					"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

			// El email a validar
			Matcher mather = pattern.matcher(respuesta);

			if (respuesta.equals("") || respuesta.isEmpty()) {
				vistaConfigPrincipal.mostrarMensajeEmergente(modelo.getTextoCorreo()[0],modelo.getTextoCorreo()[1]);
			} else if (respuesta.contains(" ")) {
				vistaConfigPrincipal.mostrarMensajeEmergente(modelo.getTextoCorreo()[2],modelo.getTextoCorreo()[3]);
			} else if (!mather.find()) {
				vistaConfigPrincipal.mostrarMensajeEmergente(modelo.getTextoCorreo()[4],modelo.getTextoCorreo()[5]);
			} else {
				String usuario = eventos.getUsuario();
				int num = conexion.realizarUpdateStatement(
						"UPDATE usuarios SET correo='" + respuesta + "' WHERE nombre = '" + usuario + "'");
				if (num > 0) {
					vistaConfigPrincipal.mostrarMensajeEmergente(modelo.getTextoCorreo()[6],modelo.getTextoCorreo()[7]);
				} else {
					vistaConfigPrincipal.mostrarMensajeEmergente(modelo.getTextoCorreo()[8],modelo.getTextoCorreo()[9]);
				}
			}
		} catch (Exception e) {
			vistaConfigPrincipal.mostrarMensajeEmergente(modelo.getTextoCorreo()[4],modelo.getTextoCorreo()[5]);
		}
	}

	private void enviarMensajeSoporte(String respuesta) {
		try {
			if (respuesta.equals("") || respuesta.isEmpty()) {
				vistaConfigPrincipal.mostrarMensajeEmergente(modelo.getTextoSoporteTecnico()[0],modelo.getTextoSoporteTecnico()[1]);
			} else {
				int id = 0;
				String usuario = eventos.getUsuario();
				String categoria = "";
				SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
				Date date = new Date();
				try {

					ResultSet usuarios = conexion.realizarConsultaRS("SELECT * FROM usuarios");
					while (usuarios.next() && id == 0) {
						if (usuarios.getString(2).equals(usuario)) {
							id = usuarios.getInt(1);
							categoria = usuarios.getString(5);
						}
					}

					int num = conexion.realizarUpdateStatement(
							"INSERT INTO mensajes (idUsuario, nombreUsuario, categoriaUsuario, mensaje, fecha, hora) "
									+ "VALUES(" + id + ", '" + usuario + "', '" + categoria + "', '" + respuesta
									+ "', '" + fecha.format(date) + "', '" + hora.format(date) + "')");
					if (num > 0) {
						vistaConfigPrincipal.mostrarMensajeEmergente(modelo.getTextoSoporteTecnico()[2],modelo.getTextoSoporteTecnico()[3]);
					} else {
						vistaConfigPrincipal.mostrarMensajeEmergente(modelo.getTextoSoporteTecnico()[4],modelo.getTextoSoporteTecnico()[5]);
					}
				} catch (Exception e) {
					vistaConfigPrincipal.mostrarMensajeEmergente(modelo.getTextoSoporteTecnico()[6],modelo.getTextoSoporteTecnico()[7]);
				}

			}
		} catch (Exception e) {
		}
	}

	/*
	 * HOVER EN OPCIONES PRINCIPALES
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		Object source = e.getSource();
		if (source instanceof JButton) {
			JButton btn = (JButton) source;
			btn.setOpaque(true);
			btn.setBackground(Color.decode("#193349"));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		Object source = e.getSource();
		if (source instanceof JButton) {
			JButton btn = (JButton) source;
			btn.setOpaque(false);
			btn.setBackground(Color.decode("#0c1823"));
		}
	}

}
