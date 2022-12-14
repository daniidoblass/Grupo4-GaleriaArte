/**
 * @author Samuel Acosta Fernandez
 * @date 09/02/2022
 * @version 01
 */

package controlador;

import modelo.modelo;
import vista.vista;
import vista.vistaConfigPrincipal;
import conexion.conexion;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;

public class controladorConfigPrincipal {
    
    private modelo modelo;
    private vista vista;
    private vistaConfigPrincipal vistaConfigPrincipal;
    private eventos eventos;
    private conexion conexion;
    
    
    public controladorConfigPrincipal(modelo modelo, vista vista, eventos eventos, conexion conexion){
        this.modelo = modelo;
        this.vista = vista;
        this.eventos = eventos;
        vistaConfigPrincipal = new vistaConfigPrincipal(modelo, vista);
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

