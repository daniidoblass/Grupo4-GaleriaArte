/**
 * 
 * Clase EventosAdmin
 * 
 * Permite realizar los eventos de los distintos componentes
 * de la ventana de administración. Principalmente cambia el 
 * contenido de la tabla a la seleccionada por el usuario
 * 
 * @author Samuel Acosta Fernandez
 * @date 12/12/2022
 * @version 01
 */

package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import modelo.Modelo;

public class EventosAdmin implements ActionListener, MouseListener {

	/**
	 * modelo - tipo Modelo - contiene textos del programa
	 */
    private Modelo modelo;
    
    /**
     * controladorAdmin - tipo ControladorAdmin - controlador del administrador
     */
    private ControladorAdmin controladorAdmin;

    /**
     * Constructor por defecto de eventos del administrador
     * @param modelo
     * @param controladorAdmin
     */
    public EventosAdmin(Modelo modelo, ControladorAdmin controladorAdmin){
        this.modelo = modelo;
        this.controladorAdmin = controladorAdmin;
    }
    
    /**
     * Eventos de botones del administrador
     */
    @Override
	public void actionPerformed(ActionEvent e) {
    	
    	Object source = e.getSource();
    	
    	if(source instanceof JButton) { 
            
            JButton btn = (JButton)source;
            
            if(btn.getText().contains(modelo.getTextoOpcionesAdmin()[0])){     
            	controladorAdmin.actualizarTabla(modelo.getTextosEventosAdmin()[0]);
            }
            else if(btn.getText().contains(modelo.getTextoOpcionesAdmin()[1])){     
            	controladorAdmin.actualizarTabla(modelo.getTextosEventosAdmin()[1]);
            }
            else if(btn.getText().contains(modelo.getTextoOpcionesAdmin()[2])){     
            	controladorAdmin.actualizarTabla(modelo.getTextosEventosAdmin()[2]);
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
        }
    }

}