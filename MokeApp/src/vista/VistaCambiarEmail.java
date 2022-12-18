package vista;

/**
 * @author Pablo P�rez Ferre
 * @date 15/12/2022
 * @version 01
 */
import javax.swing.*;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import com.mysql.cj.protocol.Resultset;

import conexion.Conexion;
import modelo.Modelo;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VistaCambiarEmail extends JFrame {

	private Conexion conexion;
	private String nombreUsuario="paco";
	public VistaCambiarEmail(Conexion conexion) {
		this.conexion = conexion;
		try {
			cambiarEmail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void cambiarEmail() throws HeadlessException, SQLException {
		
	}
}