/**
 * 
 * Clase EventosInfo
 * 
 * Permite realizar los eventos de los diferentes componentes
 * de las vistas. Principalmente permite establecer el contenido
 * de la opción seleccionada en una ventana independiente 
 * para informar al usuario sobre la función seleccionada
 * en el programa
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
import vista.VistaInfoPrincipal;

public class EventosInfo implements ActionListener, MouseListener {

	/**
	 * modelo - tipo Modelo - contiene textos del programa
	 */
    private Modelo modelo;
    
    /**
     * controladorFTPPrincipal - tipo ControladorFTPPrincipal - controlador FTP
     */
    private ControladorInfoPrincipal controladorInfoPrincipal;
    
    /**
     * vistaInfoPrincipal - tipo VistaInfoPrincipal - vista de información
     */
    private VistaInfoPrincipal vistaInfoPrincipal;

    /**
     * Constructor por defecto de eventos de información
     * @param modelo - tipo Modelo - contiene textos del programa
     * @param controladorInfoPrincipal - tipo ControladorFTPPrincipal - controlador FTP
     * @param vistaInfoPrincipal - tipo VistaInfoPrincipal - vista de información
     */
    public EventosInfo(Modelo modelo, ControladorInfoPrincipal controladorInfoPrincipal, VistaInfoPrincipal vistaInfoPrincipal){
        this.modelo = modelo;
        this.controladorInfoPrincipal = controladorInfoPrincipal;
        this.vistaInfoPrincipal= vistaInfoPrincipal; 
    }
    
    /**
     * Eventos de secciones de información
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        if(source instanceof JButton) { 
            
            JButton btn = (JButton)source;
            
            for(int i=0; i<modelo.getTextoOpcionesInfo().length; i++) {
            	if(btn.getText().contains(modelo.getTextoOpcionesInfo()[i])){ 
                	vistaInfoPrincipal.mostrarMensajeEmergente(modelo.getTextoOpcionesInfo()[i], modelo.getTextoDatosInfo()[i]);
                }
            }

        }
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
