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

public class VistaRestablecerContra extends JFrame {

	private Conexion conexion;
	private String nombreUsuario="paco";
	public VistaRestablecerContra(Conexion conexion) {
		this.conexion = conexion;
		try {
			cambiarContra();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void cambiarContra() throws HeadlessException, SQLException {
		String contraActual = JOptionPane.showInputDialog(null, "Introduce tu contraseña actual");
		ResultSet rs= conexion.realizarConsultaRS("Select password from usuarios where nombre='"+nombreUsuario+"'");
		while(rs.next()) {
			if(contraActual.equals(rs.getString(1))) {
				String contraNueva = JOptionPane.showInputDialog(null, "Introduce tu nueva contraseña");
				conexion.realizarUpdateStatement("Update usuarios set password='"+contraNueva+"' WHERE nombre='"+nombreUsuario+"'");
				JOptionPane.showMessageDialog(null, "Contraseña cambiada correctamente");
			}else {
				JOptionPane.showMessageDialog(null, "Contraseña Incorrecta");
			}
		}
	}
}