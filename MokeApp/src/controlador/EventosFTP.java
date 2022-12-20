package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import org.apache.commons.net.ftp.FTPClient;

import conexion.Conexion;
import modelo.Modelo;
import vista.Vista;

<<<<<<< HEAD
public class EventosFTP implements ActionListener, MouseListener {

	private Modelo modelo;
	private Vista vista;
	private Conexion conexion;
	private FTPClient cliente;
	private String ventanaActual = "OPCIONES";
	private String infoFicheroPulsado = "";
	private ControladorFTPPrincipal controladorFTPPrincipal;

	public EventosFTP(Modelo modelo, Vista vista, Conexion conexion, FTPClient cliente,
			ControladorFTPPrincipal controladorFTPPrincipal) {
		this.modelo = modelo;
		this.vista = vista;
		this.conexion = conexion;
		this.cliente = cliente;
		this.controladorFTPPrincipal = controladorFTPPrincipal;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//        Object source = e.getSource();
//        
//        if(source instanceof JButton) { 
//            
//            JButton btn = (JButton)source;
//            
//            if(btn.getName().contains("fichero-")){          						// Pulsado fichero FTP
//            	infoFicheroPulsado = btn.getName();
//            }
//            else if(btn.getName().contains("carpeta-")){          						// Pulsada carpeta FTP
//            	if(btn.getName().contains("Volver")) {
//            		controladorFTPPrincipal.cambiarDirectorioPadre();
//            	}
//            	else {
//            		infoFicheroPulsado = btn.getName();
//            		controladorFTPPrincipal.cambiarDirectorioHijo(infoFicheroPulsado);
//            	}
//            }
//            
		/*
		 * else if(btn.getName() == modelo.getTextoOpcionesMenu()[0]){ // Subir Archivo
		 * //new ControladorSubirArchivo(modelo, vista, this, conexion); } else
		 * if(btn.getName() == modelo.getTextoOpcionesMenu()[1]){ // Descargar Archivo
		 * //new controladorOpciones(modelo, vista, this, conexion); } else
		 * if(btn.getName() == modelo.getTextoOpcionesMenu()[2]){ // Eliminar Archivo
		 * //new controladorOpciones(modelo, vista, this, conexion); } else
		 * if(btn.getName() == modelo.getTextoOpcionesMenu()[3]){ // Crear Carpeta //new
		 * controladorOpciones(modelo, vista, this, conexion); } else if(btn.getName()
		 * == modelo.getTextoOpcionesMenu()[4]){ // Eliminar Carpeta //new
		 * ControladorLogin(modelo, vista, this, conexion, cliente); }
		 */

		// }
	}

	/*
	 * HOVER EN OPCIONES PRINCIPALES
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		Object source = e.getSource();

		if (source instanceof JButton) {
			JButton btn = (JButton) source;
			modelo.setArchivoClicado(btn.getName().substring(8, btn.getName().length()));
			if (btn.getName().contains("fichero-")) { // Pulsado fichero FTP
				infoFicheroPulsado = btn.getName();
			} else if (btn.getName().contains("carpeta-")) { // Pulsada carpeta FTP
				if (btn.getName().contains("Volver")) {
					controladorFTPPrincipal.cambiarDirectorioPadre();
				} else {
					if (e.getClickCount() == 2) {
						infoFicheroPulsado = btn.getName();
						controladorFTPPrincipal.cambiarDirectorioHijo(infoFicheroPulsado);
					}

				}
			}
		}
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
		if (source instanceof JButton) {
			JButton btn = (JButton) source;
			btn.setOpaque(true);
			btn.setBackground(Color.decode("#193349"));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		Object source = e.getSource();
		if (source instanceof JButton) {
			JButton btn = (JButton) source;
			btn.setOpaque(false);
			btn.setBackground(Color.decode("#0c1823"));
		}
	}
=======
<<<<<<<< HEAD:MokeApp/src/controlador/Eventos.java
import modelo.Modelo;
import vista.Vista;
import conexion.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Eventos implements ActionListener, MouseListener {

========
public class EventosFTP implements ActionListener, MouseListener {

>>>>>>>> ramaLogin:MokeApp/src/controlador/EventosFTP.java
    private Modelo modelo;
    private Vista vista;
    private Conexion conexion;
    private FTPClient cliente;
    private String ventanaActual = "OPCIONES";
<<<<<<<< HEAD:MokeApp/src/controlador/Eventos.java

    public Eventos(Modelo modelo, Vista vista, Conexion conexion, FTPClient cliente){
========
    private String infoFicheroPulsado = "";
    private ControladorFTPPrincipal controladorFTPPrincipal;

    public EventosFTP(Modelo modelo, Vista vista, Conexion conexion, FTPClient cliente, ControladorFTPPrincipal controladorFTPPrincipal){
>>>>>>>> ramaLogin:MokeApp/src/controlador/EventosFTP.java
        this.modelo = modelo;
        this.vista = vista;
        this.conexion = conexion;
        this.cliente = cliente;
        this.controladorFTPPrincipal = controladorFTPPrincipal;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        if(source instanceof JButton) { 
            
            JButton btn = (JButton)source;
            
<<<<<<<< HEAD:MokeApp/src/controlador/Eventos.java
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
>>>>>>> ramaLogin

}
