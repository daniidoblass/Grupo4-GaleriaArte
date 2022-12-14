package controlador;

import org.apache.commons.net.ftp.FTPClient;

public class ControladorClienteFTP {

	private FTPClient cliente;
	private String servidorFTP = "ftpupload.net";
	private String usuario = "b3_33188433";
	private String contrasena = "2wknvh9m";
	
	public ControladorClienteFTP() {
		
		try {
			
			// Crear cliente ftp
			cliente = new FTPClient();

			// conectarse a ese servidor
			cliente.connect(servidorFTP);

			// iniciar sesión en el servidor
			boolean loginCorrecto = cliente.login(usuario, contrasena);
			if(!loginCorrecto) {
				System.out.print("ERROR: usuario o contraseña incorrectas");
				cliente.disconnect();
			}

		}
		catch(Exception e) {
			System.out.println("ERROR: no se ha podido establecer conexion con el Cliente FTP");
		}
	}
	
	public FTPClient getFTPClient() {
		return cliente;
	}
}
