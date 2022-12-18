package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import conexion.Conexion;
import modelo.Modelo;
import vista.Vista;
import vista.VistaConfigPrincipal;

public class EventosConfig implements ActionListener, MouseListener {

    private Modelo modelo;
    private Conexion conexion;
    private ControladorConfigPrincipal controladorConfigPrincipal;
    private VistaConfigPrincipal vistaConfigPrincipal;

    public EventosConfig(Modelo modelo, Conexion conexion, ControladorConfigPrincipal controladorConfigPrincipal, VistaConfigPrincipal vistaConfigPrincipal){
        this.modelo = modelo;
        this.conexion = conexion;
        this.controladorConfigPrincipal = controladorConfigPrincipal;
        this.vistaConfigPrincipal = vistaConfigPrincipal; 
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        if(source instanceof JButton) { 
            
            JButton btn = (JButton)source;
            
        	if(btn.getText().contains(modelo.getTextoOpcionesConfig()[0])){ 		// Reestablecer Contraseña
        		String respuesta = vistaConfigPrincipal.mostrarMensajeEmergente(modelo.getTextoOpcionesConfig()[0], modelo.getTextoDatosConfig()[0]);
            }
        	else if(btn.getText().contains(modelo.getTextoOpcionesConfig()[1])){ 	// Cambio Correo Corporativo
        		String respuesta = vistaConfigPrincipal.mostrarMensajeEmergente(modelo.getTextoOpcionesConfig()[1], modelo.getTextoDatosConfig()[1]);
            }
        	else if(btn.getText().contains(modelo.getTextoOpcionesConfig()[2])){ 	// Soporte Técnico
        		String respuesta = vistaConfigPrincipal.mostrarMensajeEmergente(modelo.getTextoOpcionesConfig()[2], modelo.getTextoDatosConfig()[2]);
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
