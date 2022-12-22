/**
 * @author Samuel Acosta Fernandez
 * @date 09/02/2022
 * @version 01
 */

package controlador;

import modelo.Modelo;
import vista.Vista;
import vista.VistaFTPPrincipal;
import conexion.Conexion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class ControladorFTPPrincipal {

	private Modelo modelo;
	private Vista vista;
	private VistaFTPPrincipal vistaFTPPrincipal;
	private Eventos eventos;
	private Conexion conexion;
	private FTPClient cliente;
	private ArrayList<String> nombreFicheros;
	private ArrayList<String> infoFicheros;
	private EventosFTP eventosFTP;
	private String infoFicheroPulsado = "";

	public ControladorFTPPrincipal(Modelo modelo, Vista vista, Eventos eventos, Conexion conexion, FTPClient cliente) {
		this.modelo = modelo;
		this.vista = vista;
		this.eventos = eventos;
		vistaFTPPrincipal = new VistaFTPPrincipal(modelo, vista);
		this.conexion = conexion;
		this.cliente = cliente;
		eventosFTP = new EventosFTP(modelo, vista, conexion, cliente, this);
		eventos.setControladorFTPPrincipal(this);

		// Configurar titulo de la pagina
		configurarTitulo();

		// Agregar boton de volver
		agregarBotonVolver();

		// ficheros de prueba
		listarFicherosFTP();

		// Crear lista de ficheros
		agregarCaratulasFicheros();

		// Actualizar ventana
		actualizarVentana();
	}

	private void listarFicherosFTP() {
		nombreFicheros = new ArrayList<>();
		infoFicheros = new ArrayList<>();
		try {
			// Ficheros en el directorio actual
			FTPFile[] files = cliente.listFiles();

			// array para visualizar el tipo de fichero
			String[] tipos = { modelo.getNombreFichero(), modelo.getCarpetaNombre(), modelo.getNombreEnlace() };

			for (int i = 0; i < files.length; i++) {
				if (!files[i].getName().equals(modelo.getPunto())
						&& !files[i].getName().equals(modelo.getPuntoDoble())) {
					nombreFicheros.add(files[i].getName());
					infoFicheros.add(tipos[files[i].getType()] + modelo.getGuionMedio() + files[i].getName());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void agregarCaratulasFicheros() {

		for (int i = 0; i < nombreFicheros.size(); i++) {
			String formato = extraerFormato(nombreFicheros.get(i));
			vistaFTPPrincipal.crearCaratulasFicheros(nombreFicheros.get(i), formato, infoFicheros.get(i));
			vistaFTPPrincipal.getCaratulasProductos().get(vistaFTPPrincipal.getCaratulasProductos().size() - 1)
					.addMouseListener(eventosFTP);
			vistaFTPPrincipal.getCaratulasProductos().get(vistaFTPPrincipal.getCaratulasProductos().size() - 1)
					.addActionListener(eventosFTP);
		}

	}

	private String extraerFormato(String nombreFichero) {

		String formato = modelo.getFormatos()[0];

		if (nombreFichero.contains(modelo.getExtensiones()[0]) || nombreFichero.contains(modelo.getExtensiones()[1])) {
			formato = modelo.getFormatos()[1];
		} else if (nombreFichero.contains(modelo.getExtensiones()[2])
				|| nombreFichero.contains(modelo.getExtensiones()[3])) {
			formato = modelo.getFormatos()[2];
		} else if (nombreFichero.contains(modelo.getExtensiones()[4])
				|| nombreFichero.contains(modelo.getExtensiones()[5])
				|| nombreFichero.contains(modelo.getExtensiones()[6])) {
			formato = modelo.getFormatos()[3];
		} else if (nombreFichero.contains(modelo.getExtensiones()[7])
				|| nombreFichero.contains(modelo.getExtensiones()[8])
				|| nombreFichero.contains(modelo.getExtensiones()[9])) {
			formato = modelo.getFormatos()[4];
		} else if (!nombreFichero.contains(modelo.getPunto())) {
			formato = modelo.getFormatos()[5];
		}

		return formato;
	}

	private void configurarTitulo() {
		vista.setIcono(modelo.getRutasIconos()[2]);
		try {
			vista.setTitulo(modelo.getTipoOpciones()[0] + cliente.printWorkingDirectory());
		} catch (Exception e) {
			vistaFTPPrincipal.mostrarMensajeEmergente(modelo.getTextosGenerales()[0], modelo.getTextosGenerales()[1]);
		}
	}

	private void actualizarVentana() {
		vista.repaint();
		vista.pack();
		vista.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	public void cambiarDirectorioHijo(String infoFicheroPulsado) {
		try {
			String nuevoDirectorio = infoFicheroPulsado.replace(modelo.getCarpetaGuion(), modelo.getNada());
			if (cliente.changeWorkingDirectory(nuevoDirectorio)) {
				actualizarContenido();
			} else {
				System.out.println("ERROR: no se ha podido acceder al directorio seleccionado");
			}
		} catch (Exception e) {
			vistaFTPPrincipal.mostrarMensajeEmergente(modelo.getTextosGenerales()[0], modelo.getTextosGenerales()[1]);
		}
	}

	public void cambiarDirectorioPadre() {
		try {
			if (cliente.changeToParentDirectory()) {
				actualizarContenido();
			} else {
				System.out.println("ERROR: no se ha podido acceder al directorio padre");
			}
		} catch (Exception e) {
			vistaFTPPrincipal.mostrarMensajeEmergente(modelo.getTextosGenerales()[0], modelo.getTextosGenerales()[1]);
		}
	}

	public void actualizarContenido() {
		listarFicherosFTP();
		vistaFTPPrincipal.limpiarPanelCentral();
		configurarTitulo();
		agregarBotonVolver();
		agregarCaratulasFicheros();
	}

	private void agregarBotonVolver() {
		try {
			if (!cliente.printWorkingDirectory().equals(eventos.getDirectorioLimite())) {
				// agregar boton de volver
				vistaFTPPrincipal.crearCaratulasFicheros(modelo.getTextosGenerales()[3], modelo.getTextosGenerales()[4],
						modelo.getTextosGenerales()[5]);
				vistaFTPPrincipal.getCaratulasProductos().get(0).addMouseListener(eventosFTP);
				vistaFTPPrincipal.getCaratulasProductos().get(0).addActionListener(eventosFTP);
			}
		} catch (Exception e) {
			vistaFTPPrincipal.mostrarMensajeEmergente(modelo.getTextosGenerales()[0], modelo.getTextosGenerales()[2]);
		}
	}

	public String getInfoFicheroPulsado() {
		return infoFicheroPulsado;
	}

	public void setInfoFicheroPulsado(String infoFicheroPulsado) {
		this.infoFicheroPulsado = infoFicheroPulsado;
	}
}
