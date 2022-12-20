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
		String categoria = comprobarUsuario(usuario, password);
		if(categoria.equals("admin")) {
			new ControladorAdmin(modelo, vista, eventos, conexion, cliente);
		}
		else if(categoria.equals("null")) {
			vistaLogin.mostrarMensajeEmergente("ERROR AL INICIAR SESION", "Usuario o contraseña incorrectos, vuelva a intentarlo");
		}
		else {
			new ControladorOpciones(modelo, vista, eventos, conexion, cliente);
		}
	}
	
	public String comprobarUsuario(String usuario, String password) {
		
		String categoria = "null";
		
		try {
			ResultSet usuarios = conexion.realizarConsultaRS("SELECT * FROM usuarios");
			while(usuarios.next() && categoria.equals("null")) {
				if(usuarios.getString(2).equals(usuario) && usuarios.getString(4).equals(password)) {
					categoria = usuarios.getString(5);
				}
			}
		}
		catch(Exception e) {
		if (categoria.equals("admin")) {
			new ControladorAdmin(modelo, vista, eventos, conexion, cliente);
			eventos.setUsuario(usuario);
		} else if (categoria.equals("null")) {
			vistaLogin.mostrarMensajeEmergente("ERROR AL INICIAR SESION",
					"Usuario o contraseña incorrectos, vuelva a intentarlo");
		} else {
			if (categoria.equals("Marchante")) {

				String dir = "/Galeria de Arte/Marchantes/" + usuario;

				try {
					cliente.changeWorkingDirectory(dir);

					if (cliente.printWorkingDirectory().equals("/")) {

						cliente.makeDirectory(dir);
						cliente.changeWorkingDirectory(dir);
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				try {
					System.out.println(cliente.printWorkingDirectory());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}else if(categoria.equals("Responsable")) {
				
				String dir = "/Galeria de Arte/Responsables/" + usuario;

				try {
				
					cliente.makeDirectory(dir);
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				try {
					System.out.println(cliente.printWorkingDirectory());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			new ControladorOpciones(modelo, vista, eventos, conexion, cliente);
			eventos.setUsuario(usuario);
		}

		// Activar solo una vez el ActionListener del icono superior
		if (contadorActionListener == 0) {
			vista.getIcono().addActionListener(eventos);
			contadorActionListener++;
		}
	}

}
}

