package controlador;

<<<<<<< HEAD
import java.io.File;
=======
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
>>>>>>> ramaLogin

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

<<<<<<< HEAD
=======
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

>>>>>>> ramaLogin
import conexion.Conexion;
import modelo.Modelo;
import vista.Vista;
import vista.VistaSubirArchivo;

public class ControladorSubirArchivo {

<<<<<<< HEAD
  
    private Modelo modelo;
    private Vista vista;
    private VistaSubirArchivo vistaSubirArchivo;
    private Eventos eventos;
    private Conexion conexion;
    
    
    public ControladorSubirArchivo(Modelo modelo, Vista vista, Eventos eventos, Conexion conexion){
        this.modelo = modelo;
        this.vista = vista;
        this.eventos = eventos;
        vistaSubirArchivo = new VistaSubirArchivo(modelo);
        this.conexion = conexion;

        obtenerFicheroSeleccionado();
    }
    
    public void obtenerFicheroSeleccionado() {
    	if (vistaSubirArchivo.mostrarJFileChooser() == JFileChooser.APPROVE_OPTION) {
            File f = vistaSubirArchivo.getJFileChooser().getSelectedFile();
            String archivo = f.getAbsolutePath();
            String nombreArchivo = f.getName();

            System.out.println("Ruta archivo seleccionada --> " + archivo);
            System.out.println("Archivo seleccionado --> " + nombreArchivo);

            try {
                //SubirFichero(archivo, nombreArchivo);
                JOptionPane.showMessageDialog(vistaSubirArchivo.getJFileChooser(), "Se ha subido correctamente el archivo " + nombreArchivo);
            } catch (Exception el) {
                System.out.println("ERROR: no se ha podido mostrar la ventana emergente");
            }
        }
    }
=======
	private Modelo modelo;
	private Eventos eventos;
	private VistaSubirArchivo vistaSubirArchivo;
	private FTPClient cliente;

	public ControladorSubirArchivo(Modelo modelo, Vista vista, Eventos eventos, Conexion conexion, FTPClient cliente) {
		this.modelo = modelo;
		this.eventos = eventos;
		vistaSubirArchivo = new VistaSubirArchivo(modelo);
		this.cliente = cliente;

		obtenerFicheroSeleccionado();
	}

	/*
	 * Obtiene fichero seleccionado y lo sube a servidor FTP
	 */
	public void obtenerFicheroSeleccionado() {
		if (vistaSubirArchivo.mostrarJFileChooser() == JFileChooser.APPROVE_OPTION) {
			File f = vistaSubirArchivo.getJFileChooser().getSelectedFile();
			String archivo = f.getAbsolutePath();
			String nombreArchivo = f.getName();

			try {
				if (subirFichero(archivo, nombreArchivo)) {
					JOptionPane.showMessageDialog(vistaSubirArchivo.getJFileChooser(),
							"Se ha subido correctamente el archivo " + nombreArchivo);
					eventos.getControladorFTPPrincipal().actualizarContenido();
				} else {
					JOptionPane.showMessageDialog(vistaSubirArchivo.getJFileChooser(),
							"No se ha podido subir el archivo " + nombreArchivo);
				}
			} catch (Exception el) {
				System.err.println("ERROR: no se ha podido mostrar la ventana emergente \n" + el.getMessage());
			}
		}
	}

	/*
	 * Sube fichero pasado por parámetro al servidor FTP
	 */
	private boolean subirFichero(String archivo, String nombreArchivo) throws IOException {
		cliente.setFileType(FTP.BINARY_FILE_TYPE);
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(archivo));
		boolean operacion = cliente.storeFile(nombreArchivo, in);
		in.close();
		return operacion;
	}
>>>>>>> ramaLogin
}
