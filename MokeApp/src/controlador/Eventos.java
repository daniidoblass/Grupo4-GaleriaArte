/**
 * 
 * Clase Eventos
 * 
 * Permite realizar los eventos de los diferentes componentes
 * de las vistas. Principalmente entrelazar los controladores
 * de la ventana principal y las opciones de FTP
 * 
 * @author Samuel Acosta Fernandez
 * @date 09/12/2022
 * @version 01
 */

package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.mail.MessagingException;
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

	/**
	 * modelo - tipo Modelo - contiene textos del programa
	 */
    private Modelo modelo;
    
    /**
     * vista - tipo Vista - vista principal del programa
     */
    private Vista vista;
    
    /**
     * conexion - tipo Conexion - conexion con base de datos
     */
    private Conexion conexion;
    
    /**
     * cliente - tipo FTPClient - cliente FTP
     */
    private FTPClient cliente;
    
    /**
     * ventanaActual - tipo String - registra ventana actual
     */
    private String ventanaActual = "OPCIONES";
    
    /**
     * controladorFTPPrincipal - tipo ControladorFTPPrincipal - controlador FTP
     */
    private ControladorFTPPrincipal controladorFTPPrincipal = null;
    
    /**
     * directorioLimite - tipo String - directorio límite de usuario
     */
    private String directorioLimite = "";

    /**
     * Constructor por defecto de eventos
     * @param modelo - tipo Modelo - contiene textos del programa
     * @param vista - tipo Vista - vista principal del programa
     * @param conexion - tipo Conexion - conexion con base de datos
     * @param cliente - tipo FTPClient - cliente FTP
     */
    public Eventos(Modelo modelo, Vista vista, Conexion conexion, FTPClient cliente){
        this.modelo = modelo;
        this.vista = vista;
        this.conexion = conexion;
        this.cliente = cliente;
    }
    
    /**
     * Eventos principales del programa
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        if(source instanceof JButton) { 
            
            JButton btn = (JButton)source;
            
            if(btn.getName().contains(modelo.getTipoOpciones()[0])){                        // Pulsado Boton FTP Moke
                new ControladorFTPPrincipal(modelo, vista, this, conexion, cliente);
                ventanaActual = modelo.getTextosEventos()[0];
            }
            else if(btn.getName() == modelo.getTipoOpciones()[1]){                          // Pulsado Boton Mail Moke
                try {
					new ControladorMailPrincipal(modelo, vista, this, conexion, cliente);
				} catch (Exception e1) {}
                ventanaActual = modelo.getTextosEventos()[1];
            }
            else if(btn.getName() == modelo.getTipoOpciones()[2]){                          // Pulsado Boton Configuracion Moke
                new ControladorConfigPrincipal(modelo, vista, this, conexion, cliente);
                ventanaActual = modelo.getTextosEventos()[2];
            }
            else if(btn.getName() == modelo.getTipoOpciones()[3]){                          // Pulsado Boton Moke Info
                new ControladorInfoPrincipal(modelo, vista, this, conexion, cliente);
                ventanaActual = modelo.getTextosEventos()[3];
            }
            else if(btn.getName() == modelo.getTextoLogos()[0]){                            // Pulsado Icono Barra Superior
            	if(!ventanaActual.equals(modelo.getTextosEventos()[4]) && !ventanaActual.equals(modelo.getTextosEventos()[5]) 
            			&& !ventanaActual.equals(modelo.getTextosEventos()[6])) {
            		new ControladorOpciones(modelo, vista, this, conexion, cliente);
            		ventanaActual = modelo.getTextosEventos()[5];
            	}
            	else if(ventanaActual.equals(modelo.getTextosEventos()[5]) || ventanaActual.equals(modelo.getTextosEventos()[6])){
            		if(mostrarMensajeConfirmacion(modelo.getTextosEventos()[7], modelo.getTextosEventos()[8]) == 0) {
            			new ControladorLogin(modelo, vista, this, conexion, cliente);
            			ventanaActual = modelo.getTextosEventos()[4];
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
    
    /**
     * Inserta ventana actual
     * @param ventanaActual - tipo String - ventana actual
     */
    public void setVentanaActual(String ventanaActual) {
    	this.ventanaActual = ventanaActual;
    }

    /**
     * Obtener controlador de FTP
     * @return controladorFTPPrincipal - tipo ControladorFTPPrincipal - controlador FTP
     */
    public ControladorFTPPrincipal getControladorFTPPrincipal() {
    	return controladorFTPPrincipal;
    }
    
    /**
     * Insertar controlador FTP
     * @param controladorFTPPrincipal - tipo ControladorFTPPrincipal - controlador FTP
     */
    public void setControladorFTPPrincipal(ControladorFTPPrincipal controladorFTPPrincipal) {
    	this.controladorFTPPrincipal = controladorFTPPrincipal;
    }
    
    /**
     * Obtener directorio límite del usuario
     * @return directorioLimite - tipo String - directorio límite de usuario
     */
    public String getDirectorioLimite() {
		return directorioLimite;
	}

    /**
     * Insertar directorio limite del usuario
     * @param directorioLimite - tipo String - directorio límite de usuario
     */
	public void setDirectorioLimite(String directorioLimite) {
		this.directorioLimite = directorioLimite;
	}

    /**
     * Mensaje Emergente de confirmación
     * @param titulo - tipo String - titulo de ventana
     * @param mensaje - tipo String - mensaje de ventana
     * @return JOptionPane.showConfirmDialog - tipo int - boton pulsado
     */
    public int mostrarMensajeConfirmacion(String titulo, String mensaje) {
		return JOptionPane.showConfirmDialog(null, mensaje, titulo, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
	}

	/**
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

    /**
     * Efecto al ir encima del componente
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        Object source = e.getSource();
        if(source instanceof JButton) { 
            JButton btn = (JButton)source;
            btn.setOpaque(true);
            btn.setBackground(Color.decode("#193349"));
        }
    }

    /**
     * Efecto al salir del componente
     */
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