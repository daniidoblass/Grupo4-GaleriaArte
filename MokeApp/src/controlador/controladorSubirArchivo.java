package controlador;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import conexion.conexion;
import modelo.modelo;
import vista.vista;
import vista.vistaSubirArchivo;

public class controladorSubirArchivo {

  
    private modelo modelo;
    private vista vista;
    private vistaSubirArchivo vistaSubirArchivo;
    private eventos eventos;
    private conexion conexion;
    
    
    public controladorSubirArchivo(modelo modelo, vista vista, eventos eventos, conexion conexion){
        this.modelo = modelo;
        this.vista = vista;
        this.eventos = eventos;
        vistaSubirArchivo = new vistaSubirArchivo(modelo);
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
}
