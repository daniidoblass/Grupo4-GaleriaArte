/**
 * @author Samuel Acosta Fernandez
 * @date 12/12/2022
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

public class EventosAdmin implements ActionListener, MouseListener {

    private Modelo modelo;
    private ControladorAdmin controladorAdmin;

    public EventosAdmin(Modelo modelo, ControladorAdmin controladorAdmin){
        this.modelo = modelo;
        this.controladorAdmin = controladorAdmin;
    }
    
    @Override
	public void actionPerformed(ActionEvent e) {
    	
    	Object source = e.getSource();
    	
    	if(source instanceof JButton) { 
            
            JButton btn = (JButton)source;
            
            if(btn.getText().contains(modelo.getTextoOpcionesAdmin()[0])){     
            	controladorAdmin.actualizarTabla("movimientos");
            }
            else if(btn.getText().contains(modelo.getTextoOpcionesAdmin()[1])){     
            	controladorAdmin.actualizarTabla("usuarios");
            }
            else if(btn.getText().contains(modelo.getTextoOpcionesAdmin()[2])){     
            	controladorAdmin.actualizarTabla("mensajes");
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
        }
    }

}