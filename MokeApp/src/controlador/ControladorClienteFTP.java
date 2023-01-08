/**
 * Clase ControladorClienteFTP
 * 
 * Permite realizar la conexión del Cliente FTP con el 
 * Servidor FTP mediante usuario y contraseña
 * 
 * @author Samuel Acosta Fernandez
 * @date 09/12/2022
 * @version 01
 */

package controlador;

import org.apache.commons.net.ftp.FTPClient;
import modelo.Modelo;

public class ControladorClienteFTP {

	/**
	 * cliente - tipo FTPClient - cliente FTP
	 */
	private FTPClient cliente;
	
	/**
	 * modelo - tipo Modelo - contiene textos del programa
	 */
	private Modelo modelo;
	
	/**
	 * Constructor por defecto para conexión con Server FTP
	 */
	public ControladorClienteFTP(Modelo modelo) {
		
		this.modelo = modelo;
		
		try {
			
			// Crear cliente ftp
			cliente = new FTPClient();

			// conectarse a ese servidor
			cliente.connect(modelo.getServidorFTP());

			// iniciar sesión en el servidor
			boolean loginCorrecto = cliente.login(modelo.getUsuarioFTP(), modelo.getContrasenaFTP());
			if(!loginCorrecto) {
				cliente.disconnect();
			}

		}
		catch(Exception e) {}
	}
	
	/**
	 * Obtener cliente FTP
	 * @return cliente - tipo FTPClient - cliente FTP
	 */
	public FTPClient getFTPClient() {
		return cliente;
	}
}
