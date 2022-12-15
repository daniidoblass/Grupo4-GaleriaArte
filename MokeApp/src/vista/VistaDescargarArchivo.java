package vista;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import modelo.Modelo;

public class VistaDescargarArchivo {

	public static String usuario = "b3_33188433";
	public static String contrasena = "2wknvh9m";
	public static String directorioTrabajo = "/";

	public VistaDescargarArchivo(Modelo modelo, FTPClient cliente) throws SocketException, IOException {

		// Decir a que servidor FTP voy a conectar.
		String servidor = "ftpupload.net";

		// Conectarse a ese servidor.
		cliente.connect(servidor);

		// Loguearse en el servidor.
		boolean loginCorrecto = cliente.login(usuario, contrasena);

		if (loginCorrecto) {
			System.out.println("Te has conectado al servidor...");

			cliente.changeWorkingDirectory(directorioTrabajo);
			cliente.setFileType(FTP.BINARY_FILE_TYPE);

			String archivoDelServidor = "Documento.docx";

			// ubicación donde se va a descargar
			BufferedOutputStream buffSalida = new BufferedOutputStream(
					new FileOutputStream("C:\\pruebas\\"+archivoDelServidor));

			// Descargar el archivo.
			boolean descargado = cliente.retrieveFile(archivoDelServidor, buffSalida); // Devuelve TRUE si se ha
																						// descargado.

			if (descargado) {

				System.out.println("Se ha podido descargar.");
				
			} else {

				System.out.println("No se ha podido descargar");
			}
		} else {

			System.out.print("ERROR: usuario o contraseña incorrectas");
			cliente.disconnect();
		}

		// Cerrar sesión en el servidor
		cliente.logout();

		// Desconectarse del servidor
		cliente.disconnect();
	}
}
