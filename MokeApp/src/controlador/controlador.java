/**
 * @author Samuel Acosta Fernandez
 * @date 09/02/2022
 * @version 01
 */

package controlador;

import java.sql.Connection;

import org.apache.commons.net.ftp.FTPClient;

import conexion.conexion;
import modelo.modelo;
import vista.vista;

public class controlador {
    
    private modelo modelo;
    private vista vista;
    private eventos eventos;
    private conexion conexion;
    private Connection resultadoConexion;
    private controladorClienteFTP controladorClienteFTP;
    
    
    public controlador(){
        modelo = new modelo();
        conexion = new conexion();
        vista = new vista(modelo, conexion);
        controladorClienteFTP = new controladorClienteFTP();
        FTPClient cliente = controladorClienteFTP.getFTPClient();
        eventos = new eventos(modelo,vista,conexion,cliente);
        
        // Realizar conexion con la base de datos
        conexion.realizarConexion();
        
        // Creacion de Paneles
        crearPanelesVentanaPrincipal();
        
        // Configuracion de Paneles
        configurarPaneles();
        
        // Creacion de lista de opciones
        crearOpcionesMenu();
        
        // Agregar icono a barra superior
        agregarIconoBarraSuperior();

        // Mostrar Ventana Principal
        vista.mostrarVentana();

        // Iniciar el panel Login
        new controladorOpciones(modelo, vista, eventos, conexion, cliente);
    }

    private void crearPanelesVentanaPrincipal() {
        for(int i=0; i<modelo.getTextoPanelesVentanaPrincipal().length; i++) {
                vista.crearPaneles(i);
        }
        vista.ordenarPaneles();
    }

    private void configurarPaneles() {
        vista.configurarPanelNorte();
        vista.configurarPanelOeste();
        vista.configurarPanelCentral();
    }

    private void crearOpcionesMenu() {
        for(int i=0; i<modelo.getTextoOpcionesMenu().length; i++) {
            vista.crearBotonMenu(i, modelo.getTextoOpcionesMenu()[i], modelo.getTextoOpcionesMenuImages()[i]);
            vista.getBotonesMenu().get(i).addMouseListener(eventos);
            vista.getBotonesMenu().get(i).addActionListener(eventos);
        }
    }
    
    private void agregarIconoBarraSuperior() {
        vista.agregarIcono();
        vista.getIcono().addActionListener(eventos);
    }
	
}
