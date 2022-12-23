/**
 * @author Samuel Acosta Fernandez
 * @date 09/12/2022
 * @version 01
 */

package controlador;

import modelo.Modelo;
import vista.Vista;
import vista.VistaInfoPrincipal;
import conexion.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;

import org.apache.commons.net.ftp.FTPClient;

public class ControladorInfoPrincipal {
    
    private Modelo modelo;
    private Vista vista;
    private VistaInfoPrincipal vistaInfoPrincipal;
    private Eventos eventos;
    private Conexion conexion;
    private FTPClient cliente;
    private EventosInfo eventosInfo;
    
    public ControladorInfoPrincipal(Modelo modelo, Vista vista, Eventos eventos, Conexion conexion, FTPClient cliente){
        this.modelo = modelo;
        this.vista = vista;
        this.eventos = eventos;
        vistaInfoPrincipal = new VistaInfoPrincipal(modelo, vista);
        this.conexion = conexion;
        this.cliente = cliente;
        eventosInfo = new EventosInfo(modelo, this, vistaInfoPrincipal);

        // Configurar titulo de la pagina
        configurarTitulo();
        
        // Creacion de lista de opciones
        crearOpcionesMenu();
        
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
    
    private void crearOpcionesMenu() {
        for(int i=0; i<modelo.getTextoOpcionesInfo().length; i++) {
        	vistaInfoPrincipal.crearBotonMenu(i, modelo.getTextoOpcionesInfo()[i], modelo.getTextoOpcionesInfoImages()[i]);
        	vistaInfoPrincipal.getBotonesMenu().get(i).addMouseListener(eventosInfo);
        	vistaInfoPrincipal.getBotonesMenu().get(i).addActionListener(eventosInfo);
        }
    }
	
}

