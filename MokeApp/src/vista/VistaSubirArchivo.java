package vista;

import javax.swing.*;

import modelo.Modelo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class VistaSubirArchivo extends JFrame {

	private Modelo modelo;
	private JFileChooser jFileChooser = new JFileChooser();
	
    public VistaSubirArchivo(Modelo modelo) {
    	this.modelo = modelo;
    	configurarJFileChooser();
    }
    
    public void configurarJFileChooser() {
    	jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jFileChooser.setDialogTitle("Selecciona un archivo");
    }
    
    public int mostrarJFileChooser() {
    	return jFileChooser.showDialog(jFileChooser, "Cargar");
    }
    
    public JFileChooser getJFileChooser() {
    	return jFileChooser;
    }
}
