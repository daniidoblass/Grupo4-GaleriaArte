package vista;

import javax.swing.*;

import modelo.modelo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class vistaSubirArchivo extends JFrame {

	private modelo modelo;
	private JFileChooser jFileChooser = new JFileChooser();
	
    public vistaSubirArchivo(modelo modelo) {
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
