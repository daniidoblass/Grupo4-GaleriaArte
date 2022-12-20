<<<<<<< HEAD
=======
/**
 * @author Samuel Acosta Fernandez
 * @date 22/03/2022
 * @version 01
 */
>>>>>>> ramaLogin
package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
<<<<<<< HEAD
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import org.apache.commons.net.ftp.FTPClient;

import conexion.Conexion;
import modelo.Modelo;
import vista.Vista;

<<<<<<<< HEAD:MokeApp/src/controlador/Eventos.java
=======
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import org.apache.commons.net.ftp.FTPClient;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

>>>>>>> ramaLogin
import modelo.Modelo;
import vista.Vista;
import conexion.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Eventos implements ActionListener, MouseListener {

<<<<<<< HEAD
========
public class EventosFTP implements ActionListener, MouseListener {

>>>>>>>> ramaLogin:MokeApp/src/controlador/EventosFTP.java
=======
>>>>>>> ramaLogin
    private Modelo modelo;
    private Vista vista;
    private Conexion conexion;
    private FTPClient cliente;
    private String ventanaActual = "OPCIONES";
<<<<<<< HEAD
<<<<<<<< HEAD:MokeApp/src/controlador/Eventos.java

    public Eventos(Modelo modelo, Vista vista, Conexion conexion, FTPClient cliente){
========
    private String infoFicheroPulsado = "";
    private ControladorFTPPrincipal controladorFTPPrincipal;

    public EventosFTP(Modelo modelo, Vista vista, Conexion conexion, FTPClient cliente, ControladorFTPPrincipal controladorFTPPrincipal){
>>>>>>>> ramaLogin:MokeApp/src/controlador/EventosFTP.java
=======
    private String usuario = "";
    private ControladorFTPPrincipal controladorFTPPrincipal = null;

    public Eventos(Modelo modelo, Vista vista, Conexion conexion, FTPClient cliente){
>>>>>>> ramaLogin
        this.modelo = modelo;
        this.vista = vista;
        this.conexion = conexion;
        this.cliente = cliente;
<<<<<<< HEAD
        this.controladorFTPPrincipal = controladorFTPPrincipal;
=======
>>>>>>> ramaLogin
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        if(source instanceof JButton) { 
            
            JButton btn = (JButton)source;
            
<<<<<<< HEAD
<<<<<<<< HEAD:MokeApp/src/controlador/Eventos.java
            if(btn.getName().contains(modelo.getTipoOpciones()[0])){                        // Pulsado Boton FTP Moke
                new ControladorFTPPrincipal(modelo, vista, this, conexion, cliente);
=======
            if(btn.getName().contains(modelo.getTipoOpciones()[0])){                        // Pulsado Boton FTP Moke
                try {
					new ControladorFTPPrincipal(modelo, vista, this, conexion, cliente);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
>>>>>>> ramaLogin
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
<<<<<<< HEAD
            	if(!ventanaActual.equals("LOGIN") && !ventanaActual.equals("OPCIONES")) {
            		new ControladorOpciones(modelo, vista, this, conexion, cliente);
            		ventanaActual = "OPCIONES";
========
            if(btn.getName().contains("fichero-")){          						// Pulsado fichero FTP
            	infoFicheroPulsado = btn.getName();
            	this.controladorFTPPrincipal.setInfoFicheroPulsado(infoFicheroPulsado);
            }
            else if(btn.getName().contains("carpeta-")){          						// Pulsada carpeta FTP
            	if(btn.getName().contains("Volver")) {
            		controladorFTPPrincipal.cambiarDirectorioPadre();
            	}
            	else {
            		infoFicheroPulsado = btn.getName();
            		controladorFTPPrincipal.cambiarDirectorioHijo(infoFicheroPulsado);
            		this.controladorFTPPrincipal.setInfoFicheroPulsado(infoFicheroPulsado);
>>>>>>>> ramaLogin:MokeApp/src/controlador/EventosFTP.java
            	}
            }
            
            /*
            else if(btn.getName() == modelo.getTextoOpcionesMenu()[0]){                     // Subir Archivo
<<<<<<<< HEAD:MokeApp/src/controlador/Eventos.java
                new ControladorSubirArchivo(modelo, vista, this, conexion);
========
                //new ControladorSubirArchivo(modelo, vista, this, conexion);
>>>>>>>> ramaLogin:MokeApp/src/controlador/EventosFTP.java
            }
            else if(btn.getName() == modelo.getTextoOpcionesMenu()[1]){                     // Descargar Archivo
                //new controladorOpciones(modelo, vista, this, conexion);
            }
            else if(btn.getName() == modelo.getTextoOpcionesMenu()[2]){                     // Eliminar Archivo
                //new controladorOpciones(modelo, vista, this, conexion);
            }
            else if(btn.getName() == modelo.getTextoOpcionesMenu()[3]){                     // Crear Carpeta
                //new controladorOpciones(modelo, vista, this, conexion);
            }
            else if(btn.getName() == modelo.getTextoOpcionesMenu()[4]){                     // Eliminar Carpeta
<<<<<<<< HEAD:MokeApp/src/controlador/Eventos.java
            	new ControladorLogin(modelo, vista, this, conexion, cliente);
            }
========
            	//new ControladorLogin(modelo, vista, this, conexion, cliente);
            }*/
>>>>>>>> ramaLogin:MokeApp/src/controlador/EventosFTP.java

        }
    }
=======
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
            	//new ControladorLogin(modelo, vista, this, conexion, cliente);
            }
            else if(btn.getName() == modelo.getTextoOpcionesMenu()[5]){                     // Renombrar
                //new controladorOpciones(modelo, vista, this, conexion);
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

    /*
	 * Mensaje Emergente
	 */
    public int mostrarMensajeConfirmacion(String titulo, String mensaje) {
		return JOptionPane.showConfirmDialog(null, mensaje, titulo, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
	}
>>>>>>> ramaLogin

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

<<<<<<< HEAD
}
=======
}
>>>>>>> ramaLogin
