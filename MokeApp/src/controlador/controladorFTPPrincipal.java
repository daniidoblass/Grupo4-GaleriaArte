/**
 * @author Samuel Acosta Fernandez
 * @date 09/02/2022
 * @version 01
 */

package controlador;

import modelo.modelo;
import vista.vista;
import vista.vistaFTPPrincipal;
import conexion.conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;

import org.apache.commons.net.ftp.FTPClient;

public class controladorFTPPrincipal {
    
    private modelo modelo;
    private vista vista;
    private vistaFTPPrincipal vistaFTPPrincipal;
    private eventos eventos;
    private conexion conexion;
    private FTPClient cliente;
    private ArrayList<String> nombreFicheros;
    
    
    public controladorFTPPrincipal(modelo modelo, vista vista, eventos eventos, conexion conexion, FTPClient cliente){
        this.modelo = modelo;
        this.vista = vista;
        this.eventos = eventos;
        vistaFTPPrincipal = new vistaFTPPrincipal(modelo, vista);
        this.conexion = conexion;
        this.cliente = cliente;
        nombreFicheros = new ArrayList<>();

        // Configurar t�tulo de la p�gina
        configurarTitulo();
        
        // ficheros de prueba
        nombreFicheros.add("Descargas");
        
        nombreFicheros.add("Peliculas");
        nombreFicheros.add("Piratas del Caribe.mp4");
        nombreFicheros.add("Bohemian Rapsody.mp3");
        nombreFicheros.add("texto1.txt");
        nombreFicheros.add("Piratas del Caribe.mp4");
        nombreFicheros.add("texto1.txt");
        nombreFicheros.add("foto.png");
        nombreFicheros.add("Bohemian Rapsody.mp3");
        nombreFicheros.add("texto1.txt");
        nombreFicheros.add("Piratas del Caribe.mp4");

        
        
        // Crear lista de ficheros
        agregarCaratulasFicheros();
        
        // Actualizar ventana
        actualizarVentana();
    }

    private void agregarCaratulasFicheros() {

    	for(int i=0; i<nombreFicheros.size(); i++) {
    		String formato = extraerFormato(nombreFicheros.get(i));
    		vistaFTPPrincipal.crearCaratulasFicheros(i, nombreFicheros.get(i), formato);
    		vistaFTPPrincipal.getCaratulasProductos().get(i).addMouseListener(eventos);
    		vistaFTPPrincipal.getCaratulasProductos().get(i).addActionListener(eventos);
    	}

    }

	private String extraerFormato(String nombreFichero) {
		
		String formato = "file";
		
		if(nombreFichero.contains(".mp4")) {
			formato = "movie";
		}
		else if(nombreFichero.contains(".mp3")) {
			formato = "music";
		}
		else if(nombreFichero.contains(".txt")) {
			formato = "document";
		}
		else if(nombreFichero.contains(".png")) {
			formato = "image";
		}
		else if(!nombreFichero.contains(".")) {
			formato = "folder";
		}
		
		return formato;
	}

	private void configurarTitulo() {
		vista.setIcono("src/opcionesprincipal/0.png");
		vista.setTitulo("FTP MOKE");
	}

    private void actualizarVentana() {
        vista.repaint();
        vista.pack();
        vista.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
	
}

