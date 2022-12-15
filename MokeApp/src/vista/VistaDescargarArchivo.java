package vista;
/**
 * @author Daniel Jesús Doblas Florido
 * @date 14/12/2022
 * @version 01
 */
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import controlador.ControladorDescargarArchivo;
import modelo.Modelo;

public class VistaDescargarArchivo extends JFrame{

	private ControladorDescargarArchivo controladorDescargarArchivo;
	private Modelo modelo;
	private JFileChooser jFileChooser;
	
	public  VistaDescargarArchivo (Modelo modelo) {

		this.modelo = modelo;
		
	}
	
	public int mostrarJFileChooser() {
		
		jFileChooser = new JFileChooser();
        jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jFileChooser.setDialogTitle("Seleccione el Directorio donde DESCARGAR el fichero");
        int returnVal = jFileChooser.showDialog(null, "Descargar");
        
		return returnVal;
	}

	public JFileChooser getjFileChooser() {
		return jFileChooser;
	}

	public void setjFileChooser(JFileChooser jFileChooser) {
		this.jFileChooser = jFileChooser;
	}
	

	
	
}
