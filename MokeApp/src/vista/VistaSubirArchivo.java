package vista;

/**
 * @author Pablo Navarro Vázquez
 * @date 14/12/2022
 * @version 01
 */

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

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
        jFileChooser.setDialogTitle("Selecciona un archivo");
    	jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    }
    
    public int mostrarJFileChooser() {
    	return jFileChooser.showDialog(jFileChooser, "Cargar");
    }
    
    public JFileChooser getJFileChooser() {
    	return jFileChooser;
    }

}
