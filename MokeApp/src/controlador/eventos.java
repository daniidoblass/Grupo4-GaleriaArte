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

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import modelo.modelo;
import vista.vista;
import conexion.conexion;
import java.sql.ResultSet;
import java.sql.SQLException;

public class eventos implements ActionListener, MouseListener {

    private modelo modelo;
    private vista vista;
    private conexion conexion;

    public eventos(modelo modelo, vista vista, conexion conexion){
        this.modelo = modelo;
        this.vista = vista;
        this.conexion = conexion;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        if(source instanceof JButton) { 
            
            JButton btn = (JButton)source;
            
            if(btn.getName().contains(modelo.getTipoOpciones()[0])){                        // Pulsado Bot�n FTP Moke
                new controladorFTPPrincipal(modelo, vista, this, conexion);
            }
            else if(btn.getName() == modelo.getTipoOpciones()[1]){                          // Pulsado Bot�n Mail Moke
                new controladorMailPrincipal(modelo, vista, this, conexion);
            }
            else if(btn.getName() == modelo.getTipoOpciones()[2]){                          // Pulsado Bot�n Configuracion Moke
                new controladorConfigPrincipal(modelo, vista, this, conexion);
            }
            else if(btn.getName() == modelo.getTipoOpciones()[3]){                          // Pulsado Bot�n Moke Info
                new controladorInfoPrincipal(modelo, vista, this, conexion);
            }
            else if(btn.getName() == modelo.getTextoLogos()[0]){                            // Pulsado Icono Barra Superior
                new controladorOpciones(modelo, vista, this, conexion);
            }
            else if(btn.getName() == modelo.getTextoOpcionesMenu()[0]){                     // Subir Archivo
                new controladorSubirArchivo(modelo, vista, this, conexion);
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
                //new controladorOpciones(modelo, vista, this, conexion);
            	new controladorLogin(modelo, vista, this, conexion);
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