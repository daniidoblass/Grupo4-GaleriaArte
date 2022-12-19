/**
 * @author Samuel Acosta Fernandez
 * @date 09/02/2022
 * @version 01
 */

package controlador;

import java.sql.Connection;

import org.apache.commons.net.ftp.FTPClient;

import conexion.Conexion;
import modelo.Modelo;
import vista.Vista;

public class Controlador {
    
    private Modelo modelo;
    private Vista vista;
    private Eventos eventos;
    private Conexion conexion;
    private Connection resultadoConexion;
    private ControladorClienteFTP controladorClienteFTP;
    
    
    public Controlador(){
        modelo = new Modelo();
        conexion = new Conexion();
        vista = new Vista(modelo, conexion);
        controladorClienteFTP = new ControladorClienteFTP();
        FTPClient cliente = controladorClienteFTP.getFTPClient();
        eventos = new Eventos(modelo,vista,conexion,cliente);
        
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
        new ControladorLogin(modelo, vista, eventos, conexion, cliente);
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
    }
}
