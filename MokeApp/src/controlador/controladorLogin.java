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
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;

import org.apache.commons.net.ftp.FTPClient;

public class ControladorLogin implements ActionListener {
    
    private Modelo modelo;
    private Vista vista;
    private VistaLogin vistaLogin;
    private Eventos eventos;
    private Conexion conexion;
    private FTPClient cliente;
    
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
		if(comprobarUsuario(usuario, password)) {
			new ControladorOpciones(modelo, vista, eventos, conexion, cliente);
		}
		else {
			vistaLogin.mostrarMensajeEmergente("ERROR AL INICIAR SESION", "Usuario o contraseña incorrectos, vuelva a intentarlo");
		}
	}
	
	public boolean comprobarUsuario(String usuario, String password) {
		
		boolean comprobacion = false;
		
		try {
			ResultSet usuarios = conexion.realizarConsultaRS("SELECT * FROM usuarios");
			while(usuarios.next() && !comprobacion) {
				if(usuarios.getString(2).equals(usuario) && usuarios.getString(4).equals(password)) {
					comprobacion = true;
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		return comprobacion;
	}
}

