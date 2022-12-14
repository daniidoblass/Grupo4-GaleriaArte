/**
 * @author Samuel Acosta Fernandez
 * @date 09/02/2022
 * @version 01
 */

package controlador;

import modelo.modelo;
import vista.vista;
import vista.vistaInfoPrincipal;
import conexion.conexion;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;

public class controladorInfoPrincipal {
    
    private modelo modelo;
    private vista vista;
    private vistaInfoPrincipal vistaInfoPrincipal;
    private eventos eventos;
    private conexion conexion;
    
    
    public controladorInfoPrincipal(modelo modelo, vista vista, eventos eventos, conexion conexion){
        this.modelo = modelo;
        this.vista = vista;
        this.eventos = eventos;
        vistaInfoPrincipal = new vistaInfoPrincipal(modelo, vista);
        this.conexion = conexion;

        // Configurar título de la página
        configurarTitulo();
        
        // Actualizar ventana
        actualizarVentana();
    }

    private void configurarTitulo() {
		vista.setIcono("src/opcionesprincipal/3.png");
		vista.setTitulo("MOKE Info");
	}

    private void actualizarVentana() {
        vista.repaint();
        vista.pack();
        vista.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
	
}

