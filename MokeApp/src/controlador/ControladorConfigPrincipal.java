/**
 * @author Samuel Acosta Fernandez
 * @date 09/02/2022
 * @version 01
 */

package controlador;

import modelo.Modelo;
import vista.Vista;
import vista.VistaConfigPrincipal;
import conexion.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;

import org.apache.commons.net.ftp.FTPClient;

public class ControladorConfigPrincipal {
    
    private Modelo modelo;
    private Vista vista;
    private VistaConfigPrincipal vistaConfigPrincipal;
    private Eventos eventos;
    private Conexion conexion;
    private FTPClient cliente;
    
    public ControladorConfigPrincipal(Modelo modelo, Vista vista, Eventos eventos, Conexion conexion, FTPClient cliente){
        this.modelo = modelo;
        this.vista = vista;
        this.eventos = eventos;
        vistaConfigPrincipal = new VistaConfigPrincipal(modelo, vista);
        this.conexion = conexion;
        this.cliente = cliente;

        // Configurar titulo de la pagina
        configurarTitulo();
        
        // Creacion de lista de opciones
        crearOpcionesMenu();
        
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
    
    private void crearOpcionesMenu() {
        for(int i=0; i<modelo.getTextoOpcionesConfig().length; i++) {
        	vistaConfigPrincipal.crearBotonMenu(i, modelo.getTextoOpcionesConfig()[i], modelo.getTextoOpcionesConfigImages()[i]);
        	vistaConfigPrincipal.getBotonesMenu().get(i).addMouseListener(eventos);
        	vistaConfigPrincipal.getBotonesMenu().get(i).addActionListener(eventos);
        }
    }
	
}

