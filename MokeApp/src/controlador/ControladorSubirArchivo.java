package controlador;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import conexion.Conexion;
import modelo.Modelo;
import vista.Vista;
import vista.VistaSubirArchivo;

public class ControladorSubirArchivo {

  
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
}
