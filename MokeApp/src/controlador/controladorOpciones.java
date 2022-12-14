/**
 * @author Samuel Acosta Fernandez
 * @date 09/02/2022
 * @version 01
 */

package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;

import org.apache.commons.net.ftp.FTPClient;

import modelo.modelo;
import vista.vista;
import vista.vistaOpciones;
import conexion.conexion;

public class controladorOpciones {
    
    private modelo modelo;
    private vista vista;
    private vistaOpciones vistaOpciones;
    private eventos eventos;
    private conexion conexion;
    private FTPClient cliente;
    
    public controladorOpciones(modelo modelo, vista vista, eventos eventos, conexion conexion, FTPClient cliente){
        this.modelo = modelo;
        this.vista = vista;
        this.eventos = eventos;
        vistaOpciones = new vistaOpciones(modelo,vista);
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
