/**
 * @author Samuel Acosta Fernandez
 * @date 09/02/2022
 * @version 01
 */

package controlador;

import modelo.modelo;
import vista.vista;
import vista.vistaMailPrincipal;
import conexion.conexion;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;

public class controladorMailPrincipal {
    
    private modelo modelo;
    private vista vista;
    private vistaMailPrincipal vistaMailPrincipal;
    private eventos eventos;
    private conexion conexion;
    
    
    public controladorMailPrincipal(modelo modelo, vista vista, eventos eventos, conexion conexion){
        this.modelo = modelo;
        this.vista = vista;
        this.eventos = eventos;
        vistaMailPrincipal = new vistaMailPrincipal(modelo, vista);
        this.conexion = conexion;

        // Configurar título de la página
        configurarTitulo();
        
        // Actualizar ventana
        actualizarVentana();
    }

    private void configurarTitulo() {
		vista.setIcono("src/opcionesprincipal/1.png");
		vista.setTitulo("Mail MOKE");
	}

    private void actualizarVentana() {
        vista.repaint();
        vista.pack();
        vista.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
	
}

