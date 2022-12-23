/**
 * @author Samuel Acosta Fernandez
 * @date 09/12/2022
 * @version 01
 */

package controlador;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;

import org.apache.commons.net.ftp.FTPClient;

import modelo.Modelo;
import vista.Vista;
import vista.VistaOpciones;
import conexion.Conexion;

public class ControladorOpciones {
    
    private Modelo modelo;
    private Vista vista;
    private VistaOpciones vistaOpciones;
    private Eventos eventos;
    private Conexion conexion;
    private FTPClient cliente;
    
    public ControladorOpciones(Modelo modelo, Vista vista, Eventos eventos, Conexion conexion, FTPClient cliente){
        this.modelo = modelo;
        this.vista = vista;
        this.eventos = eventos;
        vistaOpciones = new VistaOpciones(modelo,vista);
        this.conexion = conexion;
        this.cliente = cliente;

        // Configurar titulo de la pagina
        configurarTitulo();
        
        // Crear paneles de Opciones
        crearPanelesOpciones();
        
        // Configurar paneles de Opciones
        vistaOpciones.configurarPanelesOpciones();
        
        // Configurar Texto de Opciones
        vistaOpciones.crearEtiquetaOpciones();
        
        // Agregar perfiles de Opciones
        agregarOpciones();

        // Actualizar ventana
        actualizarVentana();
    }
    
    private void configurarTitulo() {
		vista.setIcono("src/iconos/app.png");
		vista.setTitulo("MOKE APP");
		eventos.setVentanaActual("OPCIONES");
	}

	private void crearPanelesOpciones() {
        for(int i=0; i<modelo.getTextoPanelesVentanaPrincipal().length; i++) {
            vistaOpciones.crearPanelesOpciones(i);
        }
    }

    private void agregarOpciones() {

        for(int i=0; i<modelo.getTipoOpciones().length; i++) {
        	vistaOpciones.crearPerfilesOpciones(i);
            vistaOpciones.getPerfilesOpciones().get(i).addMouseListener(eventos);
            vistaOpciones.getPerfilesOpciones().get(i).addActionListener(eventos);
        }
        
    }

    private void actualizarVentana() {
        vista.repaint();
        vista.pack();
        vista.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    
	
}
