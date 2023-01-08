/**
 * 
 * Clase EventosFTP
 * 
 * Permite realizar los eventos de los diferentes componentes
 * de las vistas. Principalmente informa del fichero
 * seleccionado por el usuario y realiza la navegación
 * entre las diferentes carpetas del Servidor FTP
 * 
 * @author Samuel Acosta Fernandez
 * @date 09/12/2022
 * @version 01
 */

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

public class EventosFTP implements ActionListener, MouseListener {

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
     * infoFicheroPulsado - tipo String - información de fichero pulsado
     */
    private String infoFicheroPulsado = "";
    
    /**
     * controladorFTPPrincipal - tipo ControladorFTPPrincipal - controlador FTP
     */
    private ControladorFTPPrincipal controladorFTPPrincipal;

    /**
     * Constructor por defecto de eventos de FTP
     * @param modelo - tipo Modelo - contiene textos del programa
     * @param vista - tipo Vista - vista principal del programa
     * @param conexion - tipo Conexion - conexion con base de datos
     * @param cliente - tipo FTPClient - cliente FTP
     * @param controladorFTPPrincipal - tipo ControladorFTPPrincipal - controlador FTP
     */
    public EventosFTP(Modelo modelo, Vista vista, Conexion conexion, FTPClient cliente, ControladorFTPPrincipal controladorFTPPrincipal){
        this.modelo = modelo;
        this.vista = vista;
        this.conexion = conexion;
        this.cliente = cliente;
        this.controladorFTPPrincipal = controladorFTPPrincipal;
    }
    
    /**
     * Eventos al pulsar fichero y opciones
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        if(source instanceof JButton) { 
            
            JButton btn = (JButton)source;
            
            if(btn.getName().contains(modelo.getFicheroGuion())){          						// Pulsado fichero FTP
            	infoFicheroPulsado = btn.getName();
            	this.controladorFTPPrincipal.setInfoFicheroPulsado(infoFicheroPulsado);
            }
            else if(btn.getName().contains(modelo.getCarpetaGuion())){          				// Pulsada carpeta FTP
            	if(btn.getName().contains(modelo.getTextosGenerales()[3])) {
            		controladorFTPPrincipal.cambiarDirectorioPadre();
            	}
            	else {
            		infoFicheroPulsado = btn.getName();
            		this.controladorFTPPrincipal.setInfoFicheroPulsado(infoFicheroPulsado);
            	}
            }
        }
    }

    /**
     * HOVER EN OPCIONES PRINCIPALES
     */
    @Override
    public void mouseClicked(MouseEvent e) {
    	Object source = e.getSource();

		if (source instanceof JButton) {
			JButton btn = (JButton) source;

			if (btn.getName().contains(modelo.getFicheroGuion())) { 		// Pulsado fichero FTP
				infoFicheroPulsado = btn.getName();
			} 
			else if (btn.getName().contains(modelo.getCarpetaGuion())) { 	// Pulsada carpeta FTP
				if (btn.getName().contains(modelo.getTextosGenerales()[3])) {
					controladorFTPPrincipal.cambiarDirectorioPadre();
				} 
				else {
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
