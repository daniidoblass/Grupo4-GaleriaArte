package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;

import conexion.Conexion;
import modelo.Modelo;
import vista.Vista;
import vista.VistaConfigPrincipal;

public class EventosConfig implements ActionListener, MouseListener {

    private Modelo modelo;
    private Eventos eventos;
    private Conexion conexion;
    private ControladorConfigPrincipal controladorConfigPrincipal;
    private VistaConfigPrincipal vistaConfigPrincipal;

    public EventosConfig(Modelo modelo, Eventos eventos, Conexion conexion, ControladorConfigPrincipal controladorConfigPrincipal, VistaConfigPrincipal vistaConfigPrincipal){
        this.modelo = modelo;
        this.eventos = eventos;
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
        		String respuesta = vistaConfigPrincipal.mostrarInputEmergente(modelo.getTextoOpcionesConfig()[0], modelo.getTextoDatosConfig()[0]);
        		reestablecerPassword(respuesta);
            }
        	else if(btn.getText().contains(modelo.getTextoOpcionesConfig()[1])){ 	// Cambio Correo Corporativo
        		String respuesta = vistaConfigPrincipal.mostrarInputEmergente(modelo.getTextoOpcionesConfig()[1], modelo.getTextoDatosConfig()[1]);
        		cambiarCorreo(respuesta);
            }
        	else if(btn.getText().contains(modelo.getTextoOpcionesConfig()[2])){ 	// Soporte Técnico
        		String respuesta = vistaConfigPrincipal.mostrarInputEmergente(modelo.getTextoOpcionesConfig()[2], modelo.getTextoDatosConfig()[2]);
        		enviarMensajeSoporte(respuesta);
            }
        }
    }
    
    private void reestablecerPassword(String respuesta) {
    	try {
    		if(respuesta.equals("") || respuesta.isEmpty()) {
        		vistaConfigPrincipal.mostrarMensajeEmergente("Texto Vacío", "El campo contraseña no puede estar vacío");
        	}
        	else if(respuesta.contains(" ")) {
        		vistaConfigPrincipal.mostrarMensajeEmergente("Texto Con Espacios", "La contraseña no puede contener espacios");
        	}
        	else if(respuesta.length() < 6) {
        		vistaConfigPrincipal.mostrarMensajeEmergente("Texto Demasiado Corto", "El campo contraseña debe tener entre 6 y 30 caracteres");
        	}
        	else if(respuesta.length() > 30) {
        		vistaConfigPrincipal.mostrarMensajeEmergente("Texto Demasiado Largo", "El campo contraseña debe tener entre 6 y 30 caracteres");
        	}
        	else {
        		String usuario = eventos.getUsuario();
        		int num = conexion.realizarUpdateStatement("UPDATE usuarios SET password='" + respuesta + "' WHERE nombre = '" + usuario + "'");
        		if(num > 0) {
        			vistaConfigPrincipal.mostrarMensajeEmergente("Reestablecer Contraseña", "Contraseña cambiada correctamente");
        		}
        		else {
        			vistaConfigPrincipal.mostrarMensajeEmergente("ERROR Reestablecer Contraseña", "La contraseña no se ha podido modificar");
        		}
        	}
    	}
    	catch(Exception e) {}
    }
    
    private void cambiarCorreo(String respuesta) {
    	try {
    		if(respuesta.equals("") || respuesta.isEmpty()) {
        		vistaConfigPrincipal.mostrarMensajeEmergente("Texto Vacío", "El campo correo no puede estar vacío");
        	}
        	else if(respuesta.contains(" ")) {
        		vistaConfigPrincipal.mostrarMensajeEmergente("Texto Con Espacios", "El correo corporativo no puede contener espacios");
        	}
        	else if(!respuesta.contains("@")) {
        		vistaConfigPrincipal.mostrarMensajeEmergente("Correo Inválido", "No se ha reconocido como correo válido");
        	}
        	else {
        		String usuario = eventos.getUsuario();
        		int num = conexion.realizarUpdateStatement("UPDATE usuarios SET correo='" + respuesta + "' WHERE nombre = '" + usuario + "'");
        		if(num > 0) {
        			vistaConfigPrincipal.mostrarMensajeEmergente("Cambio Correo Corporativo", "Correo Corporativo cambiado correctamente");
        		}
        		else {
        			vistaConfigPrincipal.mostrarMensajeEmergente("ERROR Correo Corporativo", "El correo no se ha podido modificar");
        		}
        	}
    	}
    	catch(Exception e) {}
    }
    
    private void enviarMensajeSoporte(String respuesta) {
    	try {
    		if(respuesta.equals("") || respuesta.isEmpty()) {
        		vistaConfigPrincipal.mostrarMensajeEmergente("Texto Vacío", "El campo mensaje no puede estar vacío");
        	}
        	else {
        		int id = 0;
        		String usuario = eventos.getUsuario();
        		String categoria = "";
        		SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd"); 
        		SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss"); 
        		Date date = new Date(); 
        		try {
        			
        			ResultSet usuarios = conexion.realizarConsultaRS("SELECT * FROM usuarios");
        			while(usuarios.next() && id == 0) {
        				if(usuarios.getString(2).equals(usuario)) {
        					id = usuarios.getInt(1);
        					categoria = usuarios.getString(5);
        				}
        			}
        			
        			int num = conexion.realizarUpdateStatement("INSERT INTO mensajes (idUsuario, nombreUsuario, categoriaUsuario, mensaje, fecha, hora) "
            				+ "VALUES(" + id + ", '" + usuario + "', '" + categoria + "', '" + respuesta + "', '" + fecha.format(date) + "', '" + hora.format(date) + "')");
            		if(num > 0) {
            			vistaConfigPrincipal.mostrarMensajeEmergente("Soporte Técnico", "Mensaje enviado a administradores");
            		}
            		else {
            			vistaConfigPrincipal.mostrarMensajeEmergente("ERROR Soporte Técnico", "El mensaje no se ha podido enviar");
            		}
        		}
        		catch(Exception e) {
        			vistaConfigPrincipal.mostrarMensajeEmergente("ERROR INESPERADO", "No se ha podido enviar mensaje");
        		}

        	}
    	}
    	catch(Exception e) {}
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
