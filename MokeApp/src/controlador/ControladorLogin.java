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
		String categoria = comprobarUsuario(usuario, password);
		
		try {
			// Volver a directorio raiz
			while(!cliente.printWorkingDirectory().equals("/")) {
				cliente.changeToParentDirectory();
			}
		}
		catch(Exception e1) {}
		
		if(categoria.equals("admin")) {
			new ControladorAdmin(modelo, vista, eventos, conexion, cliente);
			eventos.setUsuario(usuario);
		}
		else if(categoria.equals("null")) {
			vistaLogin.mostrarMensajeEmergente("ERROR AL INICIAR SESION", "Usuario o contraseña incorrectos, vuelva a intentarlo");
		}
		else if(categoria.equals("Responsable")) {
			new ControladorOpciones(modelo, vista, eventos, conexion, cliente);
			eventos.setUsuario(usuario);
			eventos.setDirectorioLimite("/GaleriaDeArte/Responsables/"+usuario);
			try {
				cliente.makeDirectory("/GaleriaDeArte/Responsables/" + usuario);
				cliente.changeWorkingDirectory(eventos.getDirectorioLimite());
			} catch (Exception e1) {}
		}
		else {
			
			//Creamos la consulta para obtener el nombre del responsable asignado.
			String consulta = "SELECT ResponsableAsignado FROM `usuarios` WHERE nombre LIKE '"+usuario+"'";
			ResultSet rs = conexion.realizarConsultaRS(consulta);
			String responsableAsignado = "";
			try {
				while(rs.next()) {
					
					responsableAsignado = rs.getString(1);
				}
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			new ControladorOpciones(modelo, vista, eventos, conexion, cliente);
			eventos.setUsuario(usuario);
			eventos.setDirectorioLimite("/GaleriaDeArte/Responsables/"+responsableAsignado+"/Marchantes/" + usuario);
			try {
				cliente.makeDirectory(eventos.getDirectorioLimite());
				cliente.changeWorkingDirectory(eventos.getDirectorioLimite());
			} catch (Exception e1) {}
		}
		
		// Activar solo una vez el ActionListener del icono superior
		if(contadorActionListener == 0) {
			vista.getIcono().addActionListener(eventos);
			contadorActionListener++;
		}
	}
	
	public String comprobarUsuario(String usuario, String password) {
		
		String categoria = "null";
		
		try {
			ResultSet usuarios = conexion.realizarConsultaRS("SELECT categoria FROM `usuarios` WHERE password like PASSWORD('"+password+"') AND nombre like '"+usuario+"'");

			if(usuarios.next()) {
				categoria = usuarios.getString(1);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		return categoria;
	}
}

