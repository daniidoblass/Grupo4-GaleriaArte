/**
 * @author Samuel Acosta Fernandez
 * @date 22/03/2022
 * @version 01
 */
package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import org.apache.commons.net.ftp.FTPClient;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import modelo.Modelo;
import vista.Vista;
import conexion.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Eventos implements ActionListener, MouseListener {

    private Modelo modelo;
    private Vista vista;
    private Conexion conexion;
    private FTPClient cliente;
    private String ventanaActual = "OPCIONES";
    private String usuario = "";
    private ControladorFTPPrincipal controladorFTPPrincipal = null;
    private String directorioLimite = "";

    public Eventos(Modelo modelo, Vista vista, Conexion conexion, FTPClient cliente){
        this.modelo = modelo;
        this.vista = vista;
        this.conexion = conexion;
        this.cliente = cliente;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        if(source instanceof JButton) { 
            
            JButton btn = (JButton)source;
            
            if(btn.getName().contains(modelo.getTipoOpciones()[0])){                        // Pulsado Boton FTP Moke
                new ControladorFTPPrincipal(modelo, vista, this, conexion, cliente);
                ventanaActual = "FTP";
            }
            else if(btn.getName() == modelo.getTipoOpciones()[1]){                          // Pulsado Boton Mail Moke
                new ControladorMailPrincipal(modelo, vista, this, conexion, cliente);
                ventanaActual = "MAIL";
            }
            else if(btn.getName() == modelo.getTipoOpciones()[2]){                          // Pulsado Boton Configuracion Moke
                new ControladorConfigPrincipal(modelo, vista, this, conexion, cliente);
                ventanaActual = "CONFIGURACION";
            }
            else if(btn.getName() == modelo.getTipoOpciones()[3]){                          // Pulsado Boton Moke Info
                new ControladorInfoPrincipal(modelo, vista, this, conexion, cliente);
                ventanaActual = "INFO";
            }
            else if(btn.getName() == modelo.getTextoLogos()[0]){                            // Pulsado Icono Barra Superior
            	if(!ventanaActual.equals("LOGIN") && !ventanaActual.equals("OPCIONES") && !ventanaActual.equals("ADMIN")) {
            		new ControladorOpciones(modelo, vista, this, conexion, cliente);
            		ventanaActual = "OPCIONES";
            	}
            	else if(ventanaActual.equals("OPCIONES") || ventanaActual.equals("ADMIN")){
            		if(mostrarMensajeConfirmacion("Cerrar Sesión", "¿Seguro que quiere cerrar sesión?") == 0) {
            			new ControladorLogin(modelo, vista, this, conexion, cliente);
            			ventanaActual = "LOGIN";
            		}
            	}
            }
            else if(btn.getName() == modelo.getTextoOpcionesMenu()[0]){                     // Subir Archivo
                new ControladorSubirArchivo(modelo, vista, this, conexion, cliente);
            }
            else if(btn.getName() == modelo.getTextoOpcionesMenu()[1]){                     // Descargar Archivo
                new ControladorDescargarArchivo(modelo, vista, this, conexion, cliente);
            }
            else if(btn.getName() == modelo.getTextoOpcionesMenu()[2]){                     // Eliminar Archivo
            	new ControladorEliminarArchivo(modelo, vista, this, conexion, cliente);
            }
            else if(btn.getName() == modelo.getTextoOpcionesMenu()[3]){                     // Crear Carpeta
            	new ControladorCrearCarpeta(modelo, vista, this, conexion, cliente);
            }
            else if(btn.getName() == modelo.getTextoOpcionesMenu()[4]){                     // Eliminar Carpeta
            	new ControladorEliminarCarpeta(modelo, vista, this, conexion, cliente);
            }
            else if(btn.getName() == modelo.getTextoOpcionesMenu()[5]){                     // Renombrar
                new ControladorRenombrar(modelo, vista, this, conexion, cliente);
            }
        }
    }
    
    public void setVentanaActual(String ventanaActual) {
    	this.ventanaActual = ventanaActual;
    }
    
    public String getUsuario() {
    	return usuario;
    }
    
    public void setUsuario(String usuario) {
    	this.usuario = usuario;
    }
    
    public ControladorFTPPrincipal getControladorFTPPrincipal() {
    	return controladorFTPPrincipal;
    }
    
    public void setControladorFTPPrincipal(ControladorFTPPrincipal controladorFTPPrincipal) {
    	this.controladorFTPPrincipal = controladorFTPPrincipal;
    }
    
    public String getDirectorioLimite() {
		return directorioLimite;
	}

	public void setDirectorioLimite(String directorioLimite) {
		this.directorioLimite = directorioLimite;
	}

    /*
	 * Mensaje Emergente
	 */
    public int mostrarMensajeConfirmacion(String titulo, String mensaje) {
		return JOptionPane.showConfirmDialog(null, mensaje, titulo, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
	}

	/*
     * HOVER EN OPCIONES PRINCIPALES
     */
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Object source = e.getSource();
        if(source instanceof JButton) { 
            JButton btn = (JButton)source;
            btn.setOpaque(true);
            btn.setBackground(Color.decode("#193349"));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Object source = e.getSource();
        if(source instanceof JButton) { 
            JButton btn = (JButton)source;
            btn.setOpaque(false);
            btn.setBackground(Color.decode("#0c1823"));
        }
    }

}