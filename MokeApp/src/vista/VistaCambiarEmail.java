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

public class VistaCambiarEmail extends JFrame {

	private Conexion conexion;
	private String nombreUsuario = "paco";

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
		String contra = JOptionPane.showInputDialog(null, "Introduce tu contraseña");
		if (contra == null) {
		} else {
			ResultSet rs = conexion
					.realizarConsultaRS("Select password from usuarios where nombre='" + nombreUsuario + "'");
			while (rs.next()) {
				if (contra.equals(rs.getString(1))) {
					String nuevoEmail = JOptionPane.showInputDialog(null, "Introduce tu nuevo correo");
					// Patrón para validar el email
					Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
							+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
					// El email a validar
					Matcher mather = pattern.matcher(nuevoEmail);
					if (mather.find() == true) {
						conexion.realizarUpdateStatement(
								"Update usuarios set correo='" + nuevoEmail + "' WHERE nombre='" + nombreUsuario + "'");
						JOptionPane.showMessageDialog(null, "Correo cambiado correctamente a " + nuevoEmail);
					} else {
						JOptionPane.showMessageDialog(null, "El correo ingresado es inválido");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Contraseña Incorrecta");
				}
			}
		}
	}
}