package vista;

/**
 * @author Pablo Perez Ferre
 * @date 15/12/2022
 * @version 01
 */
import javax.swing.*;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import controlador.Eventos;
import modelo.Modelo;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class VistaEliminarCarpeta extends JFrame {

	private FTPClient cliente;
	
	private Eventos eventos;

	public VistaEliminarCarpeta(FTPClient cliente, Modelo modelo, Eventos eventos) {
		
		this.cliente = cliente;
		this.eventos = eventos;
		
	}


}