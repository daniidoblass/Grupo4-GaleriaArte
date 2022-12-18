package vista;

/**
 * @author Pablo P�rez Ferre
 * @date 15/12/2022
 * @version 01
 */
import javax.swing.*;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import modelo.Modelo;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class VistaRestablecerContra extends JFrame {

	private FTPClient cliente;

	public VistaRestablecerContra(FTPClient cliente) {

		this.cliente = cliente;

		try {
			cambiarContra();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void cambiarContra() {
		
	}
}