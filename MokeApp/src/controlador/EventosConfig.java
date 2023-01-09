/**
 * 
 * Clase EventosConfig
 * 
 * Permite realizar los eventos de los distintos componentes
 * de la ventana de administración. Principalmente realiza las
 * operaciones de restablecer contraseña, cambiar correo electrónico
 * y soporte técnico
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
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;

import conexion.Conexion;
import modelo.Modelo;
import vista.VistaConfigPrincipal;

public class EventosConfig implements ActionListener, MouseListener {

	/**
	 * modelo - tipo Modelo - contiene textos del programa
	 */
    private Modelo modelo;
    
    /**
     * eventos - tipo Eventos - eventos principales 
     */
    private Eventos eventos;
    
    /**
     * conexion - tipo Conexion - conexion con base de datos
     */
    private Conexion conexion;
    
    /**
     * controladorFTPPrincipal - tipo ControladorFTPPrincipal - controlador FTP
     */
    private ControladorConfigPrincipal controladorConfigPrincipal;
    
    /**
     * vistaConfigPrincipal - tipo VistaConfigPrincipal - vista de configuración
     */
    private VistaConfigPrincipal vistaConfigPrincipal;

    /**
     * Constructor por defectos de eventos de configuración
     * @param modelo - tipo Modelo - contiene textos del programa
     * @param eventos - tipo Eventos - eventos principales 
     * @param conexion - tipo Conexion - conexion con base de datos
     * @param controladorConfigPrincipal - tipo ControladorFTPPrincipal - controlador FTP
     * @param vistaConfigPrincipal - tipo VistaConfigPrincipal - vista de configuración
     */
    public EventosConfig(Modelo modelo, Eventos eventos, Conexion conexion, ControladorConfigPrincipal controladorConfigPrincipal, VistaConfigPrincipal vistaConfigPrincipal){
        this.modelo = modelo;
        this.eventos = eventos;
        this.conexion = conexion;
        this.controladorConfigPrincipal = controladorConfigPrincipal;
        this.vistaConfigPrincipal = vistaConfigPrincipal; 
    }
    
    /**
     * Eventos principales de la configuración
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        if(source instanceof JButton) { 
            
            JButton btn = (JButton)source;
            
        	if(btn.getText().contains(modelo.getTextoOpcionesConfig()[0])){ 		// Restablecer Contraseña
        		if(comprobarPassword()) {
        			String respuesta = vistaConfigPrincipal.mostrarMensajePassword(modelo.getTextoOpcionesConfig()[0], modelo.getTextoDatosConfig()[0]);
            		restablecerPassword(respuesta);
        		}
        		else {
        			vistaConfigPrincipal.mostrarMensajeEmergente(modelo.getTextosPasswordIncorrecta()[0], modelo.getTextosPasswordIncorrecta()[1]);
        		}
            }
        	else if(btn.getText().contains(modelo.getTextoOpcionesConfig()[1])){ 	// Cambio Correo Corporativo
        		if(comprobarPassword()) {
        			String respuesta = vistaConfigPrincipal.mostrarComboBoxEmergente(modelo.getTextoOpcionesConfig()[1], modelo.getTextoDatosConfig()[1]);
            		cambiarCorreo(respuesta);
        		}
        		else {
        			vistaConfigPrincipal.mostrarMensajeEmergente(modelo.getTextosPasswordIncorrecta()[0], modelo.getTextosPasswordIncorrecta()[1]);
        		}
            }
        	else if(btn.getText().contains(modelo.getTextoOpcionesConfig()[2])){ 	// Soporte Técnico
        		String respuesta = vistaConfigPrincipal.mostrarInputEmergente(modelo.getTextoOpcionesConfig()[2], modelo.getTextoDatosConfig()[2]);
        		enviarMensajeSoporte(respuesta);
            }
        }
    }
    
    /**
     * Comprueba password introducida con registrada
     * @return comprobacion - tipo boolean - devuelve true si password correcta
     */
    private boolean comprobarPassword() {
    	boolean comprobacion = false;
    	try {
    		String passwordActual = vistaConfigPrincipal.mostrarMensajePassword(modelo.getTextosComprobarPassword()[0], 
    				modelo.getTextosComprobarPassword()[1]);
        	if(!passwordActual.equals("") && !passwordActual.isEmpty()) {
        		String usuario = modelo.getUsuario();
        		ResultSet rs = conexion.realizarConsultaRS(modelo.getTextosComprobarPassword()[2] + usuario + modelo.getComillaSimple());
        		try {
    				while(rs.next()) {
    					if (passwordActual.equals(rs.getString(1))) {
    						comprobacion = true;
    					}
    				}
    			} catch (Exception e) {}
        	}
    	}
    	catch(Exception e1) {}
		return comprobacion;
	}

    /**
     * Permite restablecer password del usuario
     * @param respuesta - tipo String - password introducida
     */
	private void restablecerPassword(String respuesta) {
    	try {
    		if(respuesta.equals("") || respuesta.isEmpty()) {
        		vistaConfigPrincipal.mostrarMensajeEmergente(modelo.getTextosRestablecerPassword()[0], modelo.getTextosRestablecerPassword()[1]);
        		conexion.registrarMovimiento(modelo.getMovimientoRestablecerPassword()[0], modelo.getMovimientoExito()[1], modelo.getMovimientoRestablecerPassword()[1]);
        	}
        	else if(respuesta.contains(" ")) {
        		vistaConfigPrincipal.mostrarMensajeEmergente(modelo.getTextosRestablecerPassword()[2], modelo.getTextosRestablecerPassword()[3]);
        		conexion.registrarMovimiento(modelo.getMovimientoRestablecerPassword()[0], modelo.getMovimientoExito()[1], modelo.getMovimientoRestablecerPassword()[2]);
        	}
        	else if(respuesta.length() < 6) {
        		vistaConfigPrincipal.mostrarMensajeEmergente(modelo.getTextosRestablecerPassword()[4], modelo.getTextosRestablecerPassword()[5]);
        		conexion.registrarMovimiento(modelo.getMovimientoRestablecerPassword()[0], modelo.getMovimientoExito()[1], modelo.getMovimientoRestablecerPassword()[3]);
        	}
        	else if(respuesta.length() > 30) {
        		vistaConfigPrincipal.mostrarMensajeEmergente(modelo.getTextosRestablecerPassword()[6], modelo.getTextosRestablecerPassword()[7]);
        		conexion.registrarMovimiento(modelo.getMovimientoRestablecerPassword()[0], modelo.getMovimientoExito()[1], modelo.getMovimientoRestablecerPassword()[3]);
        	}
        	else {
        		String usuario = modelo.getUsuario();
        		int num = conexion.realizarUpdateStatement(modelo.getTextosRestablecerPassword()[8] + respuesta + 
        				modelo.getTextosRestablecerPassword()[9] + usuario + modelo.getComillaSimple());
        		if(num > 0) {
        			modelo.setPasswordUsuario(respuesta);
        			vistaConfigPrincipal.mostrarMensajeEmergente(modelo.getTextosRestablecerPassword()[10], modelo.getTextosRestablecerPassword()[11]);
        			conexion.registrarMovimiento(modelo.getMovimientoRestablecerPassword()[0], modelo.getMovimientoExito()[0], 
        					modelo.getMovimientoRestablecerPassword()[4] + respuesta);
        		}
        		else {
        			vistaConfigPrincipal.mostrarMensajeEmergente(modelo.getTextosRestablecerPassword()[12], modelo.getTextosRestablecerPassword()[13]);
        			conexion.registrarMovimiento(modelo.getMovimientoRestablecerPassword()[0], modelo.getMovimientoExito()[1], modelo.getMovimientoRestablecerPassword()[5]);
        		}
        	}
    	}
    	catch(Exception e) {}
    }
    
	/**
	 * Cambiar correo por uno configurado
	 * @param respuesta - tipo String - correo introducido
	 */
    private void cambiarCorreo(String respuesta) {
    	try {
    		// Patrón para validar el email
			Pattern pattern = Pattern.compile(modelo.getTextosCambiarCorreo()[0]);
					
			// El email a validar
			Matcher mather = pattern.matcher(respuesta);
			
    		if(respuesta.equals("") || respuesta.isEmpty()) {
        		vistaConfigPrincipal.mostrarMensajeEmergente(modelo.getTextosCambiarCorreo()[1], modelo.getTextosCambiarCorreo()[2]);
        		conexion.registrarMovimiento(modelo.getMovimientoCambiarCorreo()[0], modelo.getMovimientoExito()[1], modelo.getMovimientoCambiarCorreo()[1]);
        	}
        	else if(respuesta.contains(" ")) {
        		vistaConfigPrincipal.mostrarMensajeEmergente(modelo.getTextosCambiarCorreo()[3], modelo.getTextosCambiarCorreo()[4]);
        		conexion.registrarMovimiento(modelo.getMovimientoCambiarCorreo()[0], modelo.getMovimientoExito()[1], modelo.getMovimientoCambiarCorreo()[2]);
        	}
        	else if(!mather.find()) {
        		vistaConfigPrincipal.mostrarMensajeEmergente(modelo.getTextosCambiarCorreo()[5], modelo.getTextosCambiarCorreo()[6]);
        		conexion.registrarMovimiento(modelo.getMovimientoCambiarCorreo()[0], modelo.getMovimientoExito()[1], modelo.getMovimientoCambiarCorreo()[3]);
        	}
        	else {
        		String usuario = modelo.getUsuario();
        		int num = conexion.realizarUpdateStatement(modelo.getTextosCambiarCorreo()[7] + respuesta + modelo.getTextosCambiarCorreo()[8] 
        				+ usuario + modelo.getComillaSimple());
        		if(num > 0) {
        			// Guardar datos del correo en el modelo
        			modelo.setCorreo(respuesta);
        			ResultSet correo = conexion.realizarConsultaRS(modelo.getTextosCambiarCorreo()[9] + respuesta + modelo.getComillaSimple());
    				while(correo.next()) {
    					modelo.setPasswordCorreo(correo.getString(3));		// passwordCorreo
    				}
        			vistaConfigPrincipal.mostrarMensajeEmergente(modelo.getTextosCambiarCorreo()[10], modelo.getTextosCambiarCorreo()[11]);
        			conexion.registrarMovimiento(modelo.getMovimientoCambiarCorreo()[0], modelo.getMovimientoExito()[0], modelo.getMovimientoCambiarCorreo()[4] 
        					+ respuesta);
        		}
        		else {
        			vistaConfigPrincipal.mostrarMensajeEmergente(modelo.getTextosCambiarCorreo()[12], modelo.getTextosCambiarCorreo()[13]);
        			conexion.registrarMovimiento(modelo.getMovimientoCambiarCorreo()[0], modelo.getMovimientoExito()[1], modelo.getMovimientoCambiarCorreo()[5]);
        		}
        	}
    	}
    	catch(Exception e) {
    		vistaConfigPrincipal.mostrarMensajeEmergente(modelo.getTextosCambiarCorreo()[14], modelo.getTextosCambiarCorreo()[15]);
    		conexion.registrarMovimiento(modelo.getMovimientoCambiarCorreo()[0], modelo.getMovimientoExito()[1], modelo.getMovimientoCambiarCorreo()[5]);
    	}
    }
    
    /**
     * Registra mensaje en base de datos del soporte
     * @param respuesta - tipo String - mensaje para el soporte
     */
    private void enviarMensajeSoporte(String respuesta) {
    	try {
    		if(respuesta.equals("") || respuesta.isEmpty()) {
        		vistaConfigPrincipal.mostrarMensajeEmergente(modelo.getTextosSoporte()[0], modelo.getTextosSoporte()[1]);
        		conexion.registrarMovimiento(modelo.getMovimientoSoporte()[0], modelo.getMovimientoExito()[1], modelo.getMovimientoSoporte()[1]);
        	}
        	else {
        		int id = 0;
        		String usuario = modelo.getUsuario();
        		String categoria = "";
        		SimpleDateFormat fecha = new SimpleDateFormat(modelo.getFormatoFecha()); 
        		SimpleDateFormat hora = new SimpleDateFormat(modelo.getFormatoHora()); 
        		Date date = new Date(); 
        		try {
        			
        			ResultSet usuarios = conexion.realizarConsultaRS(modelo.getTextosSoporte()[2]);
        			while(usuarios.next() && id == 0) {
        				if(usuarios.getString(2).equals(usuario)) {
        					id = usuarios.getInt(1);
        					categoria = usuarios.getString(5);
        				}
        			}
        			
        			int num = conexion.realizarUpdateStatement(modelo.getTextosSoporte()[3] + id + ", '" + usuario + "', '" + categoria + "', '" + respuesta + "', '" + fecha.format(date) + "', '" + hora.format(date) + "')");
            		if(num > 0) {
            			vistaConfigPrincipal.mostrarMensajeEmergente(modelo.getTextosSoporte()[4], modelo.getTextosSoporte()[5]);
            			conexion.registrarMovimiento(modelo.getMovimientoSoporte()[0], modelo.getMovimientoExito()[0], modelo.getMovimientoSoporte()[2] + respuesta);
            		}
            		else {
            			vistaConfigPrincipal.mostrarMensajeEmergente(modelo.getTextosSoporte()[6], modelo.getTextosSoporte()[7]);
            			conexion.registrarMovimiento(modelo.getMovimientoSoporte()[0], modelo.getMovimientoExito()[1], modelo.getMovimientoSoporte()[3]);
            		}
        		}
        		catch(Exception e) {
        			vistaConfigPrincipal.mostrarMensajeEmergente(modelo.getTextosSoporte()[8], modelo.getTextosSoporte()[9]);
        			conexion.registrarMovimiento(modelo.getMovimientoSoporte()[0], modelo.getMovimientoExito()[1], modelo.getMovimientoSoporte()[3]);
        		}

        	}
    	}
    	catch(Exception e) {
    		conexion.registrarMovimiento(modelo.getMovimientoSoporte()[0], modelo.getMovimientoExito()[1], modelo.getMovimientoSoporte()[3]);
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
