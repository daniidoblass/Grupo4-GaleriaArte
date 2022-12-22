package controlador;

import org.apache.commons.net.ftp.FTPClient;

import modelo.Modelo;

public class ControladorClienteFTP {

	private FTPClient cliente;
	private Modelo modelo;
	
	public ControladorClienteFTP() {
		
		this.modelo = modelo;
		
		try {
			
			// Crear cliente ftp
			cliente = new FTPClient();

			// conectarse a ese servidor
			cliente.connect(modelo.getServidorFTP());

			// iniciar sesión en el servidor
			boolean loginCorrecto = cliente.login(modelo.getUsuarioFTP(), modelo.getContrasenaFTP());
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
