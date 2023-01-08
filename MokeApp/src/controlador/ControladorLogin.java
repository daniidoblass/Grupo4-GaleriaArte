/**
 * 
 * Clase ControladorLogin
 * 
 * Permite iniciar sesión mediante usuario y
 * contraseña comprobados a través de la 
 * base de datos MySQL
 * 
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
    
	/**
	 * modelo - tipo Modelo - contiene textos del programa
	 */
    private Modelo modelo;
    
    /**
     * vista - tipo Vista - vista principal del programa
     */
    private Vista vista;
    
    /**
     * vistaLogin - tipo VistaLogin - vista de login
     */
    private VistaLogin vistaLogin;
    
    /**
     * eventos - tipo Eventos - eventos principales 
     */
    private Eventos eventos;
    
    /**
     * conexion - tipo Conexion - conexion con base de datos
     */
    private Conexion conexion;
    
    /**
     * cliente - tipo FTPClient - cliente FTP
     */
    private FTPClient cliente;
    
    /**
     * contadorActionListener - tipo int - cuenta listeners de icono 
     */
    private static int contadorActionListener = 0;
    
    /**
     * directorioLimite - tipo String - directorio limite del usuario
     */
    private String directorioLimite = "";
    
    /**
     * Constructor por defecto. Permite iniciar sesión a usuario y registrar directorio ftp
     * @param modelo - tipo Modelo - contiene textos del programa
	 * @param vista - tipo Vista - vista principal del programa
	 * @param eventos - tipo Eventos - eventos principales 
	 * @param conexion - tipo Conexion - conexion con base de datos
	 * @param cliente - tipo FTPClient - cliente FTP
     */
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

    /**
	 * Configura título de barra superior
	 */
    private void configurarTitulo() {
		vista.setIcono("src/subiconos/usuario.png");
		vista.setTitulo("MOKE Login");
		eventos.setVentanaActual("LOGIN");
	}
    
    /**
	 * Configura botón login
	 */
    private void configurarBotonLogin() {
		vistaLogin.getBotonLogin().addActionListener(this);
	}

    /**
	 * Actualiza el contenido de la ventana
	 */
    private void actualizarVentana() {
        vista.repaint();
        vista.pack();
        vista.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    /**
     * Comprueba si el usuario está registrado en la base de
     * datos, obteniendo sus datos, movimientos y accediendo
     * a su directorio en servidor ftp
     */
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
					conexion.registrarMovimiento(modelo.getMovimientoLogin()[0], modelo.getMovimientoExito()[0], modelo.getMovimientoLogin()[1]);
				}
				else {
					// Establecer directorio limite
					if(categoria.equals(modelo.getCategorias()[2])) {
						directorioLimite = modelo.getRutasUsers()[0] + usuario;
					}
					else {
						directorioLimite = modelo.getRutasUsers()[0] + datos.get(5) + modelo.getRutasUsers()[1] + usuario;
					}
					eventos.setDirectorioLimite(directorioLimite);
					// Crear directorio si no existe
					try {
						cliente.makeDirectory(directorioLimite);
						cliente.changeWorkingDirectory(directorioLimite);
					} catch (Exception e1) {}
					// Registrar movimimiento
					conexion.registrarMovimiento(modelo.getMovimientoLogin()[0], modelo.getMovimientoExito()[0], modelo.getMovimientoLogin()[2]);
					// Mostrar Opciones
					new ControladorOpciones(modelo, vista, eventos, conexion, cliente);
				}
			}
			else {
				vistaLogin.mostrarMensajeEmergente(modelo.getTextosGenerales()[7], modelo.getTextosGenerales()[8]);
				conexion.registrarMovimiento(modelo.getMovimientoLogin()[0], modelo.getMovimientoExito()[1], modelo.getMovimientoLogin()[3]);
			}
		}
		else {
			vistaLogin.mostrarMensajeEmergente(modelo.getTextosGenerales()[7], modelo.getTextosGenerales()[8]);
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
	
	/**
	 * Obtiene datos del usuario según base de datos
	 * @param usuario - tipo String - nombre del usuario
	 * @param password - tipo String - contraseña introducida
	 * @return datos - tipo ArrayList<String> - datos del usuario
	 */
	public ArrayList<String> comprobarUsuario(String usuario, String password) {
		
		ArrayList<String> datos = new ArrayList<>();
		boolean comprobacion = false;
		
		try {
			ResultSet usuarios = conexion.realizarConsultaRS(modelo.getConsultaPasswordEncriptado02());
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
				ResultSet correo = conexion.realizarConsultaRS(modelo.getConsultaPasswordEncriptado01() + datos.get(2) + 
						modelo.getComillaSimple());
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

