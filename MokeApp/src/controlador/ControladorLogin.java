/**
 * @author Samuel Acosta Fernandez
 * @date 09/12/2022
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
import java.util.ArrayList;

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
    private String directorioLimite = "";
    
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
		vista.setIcono("src/subiconos/usuario.png");
		vista.setTitulo("MOKE Login");
		eventos.setVentanaActual("LOGIN");
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
		ArrayList<String> datos = comprobarUsuario(usuario, password);
		
		// Comprobar si usuario registrado
		if(datos.size() > 0) {
			
			// Guardar datos del usuario
			modelo.setId(datos.get(0));
			modelo.setUsuario(usuario);
			modelo.setPasswordUsuario(password);
			modelo.setCategoria(datos.get(4));
			modelo.setCorreo(datos.get(2));
			modelo.setPasswordCorreo(datos.get(6));
			
			// Comprobar password
			if(datos.get(3).equals(password)) {
				String categoria = datos.get(4);
				// Comprobar categoria del usuario
				if(categoria.equals("admin")) {
					new ControladorAdmin(modelo, vista, eventos, conexion, cliente);
					modelo.setUsuario(usuario);
					conexion.registrarMovimiento("Iniciar Sesión", "si", "Inicio de sesión correcto");
				}
				else {
					// Establecer directorio limite
					if(categoria.equals("Responsable")) {
						directorioLimite = "/GaleriaDeArte/Responsables/" + usuario;
					}
					else {
						directorioLimite = "/GaleriaDeArte/Responsables/" + datos.get(5) + "/Marchantes/" + usuario;
					}
					eventos.setDirectorioLimite(directorioLimite);
					// Crear directorio si no existe
					try {
						cliente.makeDirectory(directorioLimite);
						cliente.changeWorkingDirectory(directorioLimite);
					} catch (Exception e1) {}
					// Registrar movimimiento
					conexion.registrarMovimiento("Iniciar Sesión", "si", "Inicio de sesión correcto");
					// Mostrar Opciones
					new ControladorOpciones(modelo, vista, eventos, conexion, cliente);
				}
			}
			else {
				vistaLogin.mostrarMensajeEmergente("ERROR AL INICIAR SESION", "Usuario o contraseña incorrectos, vuelva a intentarlo");
				conexion.registrarMovimiento("Iniciar Sesión", "no", "Password incorrecto");
			}
		}
		else {
			vistaLogin.mostrarMensajeEmergente("ERROR AL INICIAR SESION", "Usuario o contraseña incorrectos, vuelva a intentarlo");
		}
		
		
		try {
			// Volver a directorio raiz por cambio de usuario
			while(!cliente.printWorkingDirectory().equals("/")) {
				cliente.changeToParentDirectory();
			}
			// Ir a directorio asignado
			cliente.changeWorkingDirectory(directorioLimite);
		}
		catch(Exception e1) {}
		
		// Activar solo una vez el ActionListener del icono superior
		if(contadorActionListener == 0) {
			vista.getIcono().addActionListener(eventos);
			contadorActionListener++;
		}
	}
	
	public ArrayList<String> comprobarUsuario(String usuario, String password) {
		
		ArrayList<String> datos = new ArrayList<>();
		boolean comprobacion = false;
		
		try {
			ResultSet usuarios = conexion.realizarConsultaRS("SELECT * FROM usuarios");
			while(usuarios.next() && !comprobacion) {
				if(usuarios.getString(2).equals(usuario)) {
					comprobacion = true;
					datos.add(usuarios.getInt(1) + ""); // id
					datos.add(usuarios.getString(2));	// nombre
					datos.add(usuarios.getString(3));	// correo
					datos.add(usuarios.getString(4));	// password
					datos.add(usuarios.getString(5));	// categoria
					datos.add(usuarios.getString(6));	// responsable
				}
			}
			
			// Obtener password del correo
			if(comprobacion) {
				ResultSet correo = conexion.realizarConsultaRS("SELECT * FROM correos WHERE correo='" + datos.get(2) + "'");
				while(correo.next()) {
					datos.add(correo.getString(3));		// passwordCorreo
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		return datos;
	}
}

