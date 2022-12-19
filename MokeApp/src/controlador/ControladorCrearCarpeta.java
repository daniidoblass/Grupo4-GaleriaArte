package controlador;

/**
 * @author Daniel Jes�s Doblas Florido
 * @date 14/12/2022
 * @version 01
 */

import java.io.IOException;

import javax.swing.JOptionPane;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import conexion.Conexion;
import modelo.Modelo;
import vista.Vista;
import vista.VistaCrearCarpeta;
import vista.VistaSubirArchivo;

public class ControladorCrearCarpeta {

	  	private Modelo modelo;
	    private Vista vista;
	    private VistaCrearCarpeta vistaCrearCarpeta;
	    private FTPClient cliente;
	    private Eventos eventos;
	    private Conexion conexion;
	    private String nombreCarpeta;
	    
	    public ControladorCrearCarpeta(Modelo modelo, Vista vista, Eventos eventos, Conexion conexion, FTPClient cliente) throws IOException {
	    	
	    	this.cliente = cliente;
	    	this.modelo = modelo;
	        this.vista = vista;
	        this.eventos = eventos;
	        vistaCrearCarpeta = new VistaCrearCarpeta(modelo);
	        this.conexion = conexion;
	        crearCarpeta();
	        
	        

	    }
	    
	    private void crearCarpeta() throws IOException {
	    	
	    	String direcSelec = cliente.printWorkingDirectory();
	    	
	    	nombreCarpeta = vistaCrearCarpeta.crearNombreCarpeta();
			
			if(!(nombreCarpeta == null)) {
				
				String directorio = direcSelec; //Cambiar por direcSelec
				
				if(!direcSelec.equals("/")) { // directorio --> direcSelec.
					
					directorio = directorio + " que tal?";
				}
				
				directorio += nombreCarpeta.trim(); //quita los espacios en blanco a der e izq.

				try {
					
					if(cliente.makeDirectory(directorio)) {
						String m = nombreCarpeta.trim()+"Se ha creado correctamente...";
						JOptionPane.showMessageDialog(null, m);
						
						//Directorio de trabajo actual.
						cliente.changeWorkingDirectory(direcSelec);
						FTPFile [] ff2 = null;
						
						//Obtener ficheros del directorio actual.
						ff2 = cliente.listFiles();
						
						//llenar la lista.
						//llenarLista(ff2,direcSelec);
						
					}else {
						JOptionPane.showMessageDialog(null,nombreCarpeta.trim()+ " => No se ha podido crear ...");
					}
					
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
	    }
	    
	    
}
