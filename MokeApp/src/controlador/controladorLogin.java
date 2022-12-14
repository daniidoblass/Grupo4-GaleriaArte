/**
 * @author Samuel Acosta Fernandez
 * @date 09/02/2022
 * @version 01
 */

package controlador;

import modelo.modelo;
import vista.vista;
import vista.vistaLogin;
import conexion.conexion;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;

public class controladorLogin {
    
    private modelo modelo;
    private vista vista;
    private vistaLogin vistaLogin;
    private eventos eventos;
    private conexion conexion;
    
    
    public controladorLogin(modelo modelo, vista vista, eventos eventos, conexion conexion){
        this.modelo = modelo;
        this.vista = vista;
        this.eventos = eventos;
        vistaLogin = new vistaLogin(modelo, vista);
        this.conexion = conexion;

        // Configurar título de la página
        configurarTitulo();
        
        // Actualizar ventana
        actualizarVentana();
    }

    private void configurarTitulo() {
		vista.setIcono("src/opcionesprincipal/2.png");
		vista.setTitulo("Configuracion MOKE");
	}

    private void actualizarVentana() {
        vista.repaint();
        vista.pack();
        vista.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
	
}

