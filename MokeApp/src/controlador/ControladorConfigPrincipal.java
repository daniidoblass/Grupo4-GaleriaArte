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
        
        // Crear paneles de Opciones
        crearPanelesOpciones();
        
        // Configurar paneles de Opciones
        vistaConfigPrincipal.configurarPanelesOpciones();
        
        // Configurar Texto de Opciones
        vistaConfigPrincipal.crearEtiquetaOpciones();
        
        // Agregar perfiles de Opciones
        agregarOpciones();

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
    private void crearPanelesOpciones() {
        for(int i=0; i<4; i++) {
        	vistaConfigPrincipal.crearPanelesOpciones(i);
        }
    }

    private void agregarOpciones() {

        for(int i=0; i<3; i++) {
        	vistaConfigPrincipal.crearEtiquetas(i);
        	vistaConfigPrincipal.crearPerfilesOpciones(i);
        	vistaConfigPrincipal.getPerfilesOpciones().get(i).addMouseListener(eventos);
        	vistaConfigPrincipal.getPerfilesOpciones().get(i).addActionListener(eventos);
        }
    }
}

