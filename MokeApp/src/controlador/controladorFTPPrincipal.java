/**
 * @author Samuel Acosta Fernandez
 * @date 09/02/2022
 * @version 01
 */

package controlador;

import modelo.Modelo;
import vista.Vista;
import vista.VistaFTPPrincipal;
import conexion.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;

import org.apache.commons.net.ftp.FTPClient;

public class ControladorFTPPrincipal {
    
    private Modelo modelo;
    private Vista vista;
    private VistaFTPPrincipal vistaFTPPrincipal;
    private Eventos eventos;
    private Conexion conexion;
    private FTPClient cliente;
    private ArrayList<String> nombreFicheros;
    
    
    public ControladorFTPPrincipal(Modelo modelo, Vista vista, Eventos eventos, Conexion conexion, FTPClient cliente){
        this.modelo = modelo;
        this.vista = vista;
        this.eventos = eventos;
        vistaFTPPrincipal = new VistaFTPPrincipal(modelo, vista);
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

<<<<<<< Updated upstream:MokeApp/src/controlador/controladorFTPPrincipal.java
    private void agregarCaratulasFicheros() {
=======
    private void listarFicherosFTP() {
    	try {
    		// Ficheros en el directorio actual
    		FTPFile[] files = cliente.listFiles();
    		
    		//array para visualizar el tipo de fichero
    		String[] tipos = {"Fichero", "Directorio","Enlace simb."};

    		for (int i = 0; i < files.length; i++) {
    			nombreFicheros.add(files[i].getName());
    		}
    	}
    	catch(Exception e) {
    		System.out.println("ERROR: no se han podido listar los archivos");
    	}
    	
	}

	private void agregarCaratulasFicheros() {
>>>>>>> Stashed changes:MokeApp/src/controlador/ControladorFTPPrincipal.java

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

