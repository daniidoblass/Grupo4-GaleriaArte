package vista;

/**
 * @author Daniel Jesús Doblas Florido
 * @date 14/12/2022
 * @version 01
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import conexion.Conexion;
import controlador.Eventos;

import javax.swing.*;

import modelo.Modelo;

public class VistaCrearCarpeta extends JFrame{
	

	public VistaCrearCarpeta(Modelo modelo, FTPClient cliente) throws IOException {
		
		String nombreCarpeta = JOptionPane.showInputDialog(null,"Introducir nombre carpeta", "carpeta");
		
		String direcSelec = cliente.printWorkingDirectory();
		
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