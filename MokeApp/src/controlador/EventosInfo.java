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

    private Modelo modelo;
    private ControladorInfoPrincipal controladorInfoPrincipal;
    private VistaInfoPrincipal vistaInfoPrincipal;

    public EventosInfo(Modelo modelo, ControladorInfoPrincipal controladorInfoPrincipal, VistaInfoPrincipal vistaInfoPrincipal){
        this.modelo = modelo;
        this.controladorInfoPrincipal = controladorInfoPrincipal;
        this.vistaInfoPrincipal= vistaInfoPrincipal; 
    }
    
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
