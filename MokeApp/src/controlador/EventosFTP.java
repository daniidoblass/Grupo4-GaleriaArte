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

    private Modelo modelo;
    private Vista vista;
    private Conexion conexion;
    private FTPClient cliente;
    private String ventanaActual = "OPCIONES";
    private String infoFicheroPulsado = "";
    private ControladorFTPPrincipal controladorFTPPrincipal;

    public EventosFTP(Modelo modelo, Vista vista, Conexion conexion, FTPClient cliente, ControladorFTPPrincipal controladorFTPPrincipal){
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
            		//controladorFTPPrincipal.cambiarDirectorioHijo(infoFicheroPulsado);
            		this.controladorFTPPrincipal.setInfoFicheroPulsado(infoFicheroPulsado);
            	}
            }

        }
    }

	/*
     * HOVER EN OPCIONES PRINCIPALES
     */
    @Override
    public void mouseClicked(MouseEvent e) {
    	Object source = e.getSource();

		if (source instanceof JButton) {
			JButton btn = (JButton) source;
			modelo.setArchivoClicado(btn.getName());
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
		try {
			System.out.println(modelo.getArchivoClicado());
			System.out.println(cliente.printWorkingDirectory());
		} catch (Exception e1) {
			e1.printStackTrace();
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
