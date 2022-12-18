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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VistaSoporteTecnico extends JFrame {

	
	public VistaSoporteTecnico(Modelo modelo) {
			cambiarEmail(modelo);
	}

	private void cambiarEmail(Modelo modelo){
		JOptionPane.showMessageDialog(null,modelo.getTextoSoporteTecnico()[0]+"\n\n"+modelo.getTextoSoporteTecnico()[1]+"\n"+modelo.getTextoSoporteTecnico()[2], "Soporte Técnico",JOptionPane.INFORMATION_MESSAGE);
	}
}