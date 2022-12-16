package vista;

import javax.swing.JFileChooser;

import modelo.Modelo;

public class VistaEliminarArchivo {


	private Modelo modelo;
	private JFileChooser jFileChooser = new JFileChooser();
	
    public VistaEliminarArchivo(Modelo modelo) {
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
