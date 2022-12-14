/**
 * @author Samuel Acosta Fernandez
 * @date 09/02/2022
 * @version 01
 */

package controlador;

import modelo.Modelo;
import vista.Vista;
import vista.VistaMailPrincipal;
import conexion.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;

import org.apache.commons.net.ftp.FTPClient;

public class ControladorMailPrincipal {
    
    private Modelo modelo;
    private Vista vista;
    private VistaMailPrincipal vistaMailPrincipal;
    private Eventos eventos;
    private Conexion conexion;
    private FTPClient cliente;
    
    public ControladorMailPrincipal(Modelo modelo, Vista vista, Eventos eventos, Conexion conexion, FTPClient cliente){
        this.modelo = modelo;
        this.vista = vista;
        this.eventos = eventos;
        vistaMailPrincipal = new VistaMailPrincipal(modelo, vista);
        this.conexion = conexion;
        this.cliente = cliente;

        // Configurar t�tulo de la p�gina
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

