/**
 * 
 * Clase ControladorFTPPrincipal
 * 
 * Crea explorador de archivos con el contenido del Servidor
 * FTP junto con la nevegación entre carpetas y la selección de
 * ficheros para la realización de operaciones de la barra
 * lateral izquierda configurada en la clase Controlador
 * 
 * @author Samuel Acosta Fernandez
 * @date 09/12/2022
 * @version 01
 */

package controlador;

import java.util.ArrayList;

import javax.swing.JFrame;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import conexion.Conexion;
import modelo.Modelo;
import vista.Vista;
import vista.VistaFTPPrincipal;

public class ControladorFTPPrincipal {
    
	/**
	 * modelo - tipo Modelo - contiene textos del programa
	 */
    private Modelo modelo;
    
    /**
     * vista - tipo Vista - vista principal del programa
     */
    private Vista vista;
    
    /**
     * vistaFTPPrincipal - tipo VistaFTPPrincipal - explorador Servidor FTP
     */
    private VistaFTPPrincipal vistaFTPPrincipal;
    
    /**
     * eventos - tipo Eventos - eventos principales 
     */
    private Eventos eventos;
    
    /**
     * conexion - tipo Conexion - conexion con base de datos
     */
    private Conexion conexion;
    
    /**
     * cliente - tipo FTPClient - cliente FTP
     */
    private FTPClient cliente;
    
    /**
     * nombreFicheros - tipo ArrayList<String> - ficheros del Servidor
     */
    private ArrayList<String> nombreFicheros;
    
    /**
     * infoFicheros - tipo ArrayList<String> - información de los ficheros
     */
    private ArrayList<String> infoFicheros;
    
    /**
     * eventosFTP - tipo EventosFTP - eventos para la navegación y selección
     */
    private EventosFTP eventosFTP;
    
    /**
     * infoFicheroPulsado - tipo String - información de fichero seleccionado
     */
    private String infoFicheroPulsado = "";

    /**
     * Constructor por defecto para crear explorador de servidor FTP
     * @param modelo - tipo Modelo - contiene textos del programa
	 * @param vista - tipo Vista - vista principal del programa
	 * @param eventos - tipo Eventos - eventos principales 
	 * @param conexion - tipo Conexion - conexion con base de datos
	 * @param cliente - tipo FTPClient - cliente FTP
     */
	public ControladorFTPPrincipal(Modelo modelo, Vista vista, Eventos eventos, Conexion conexion, FTPClient cliente){
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

	/**
	 * Obtener ficheros de directorio actual
	 */
    private void listarFicherosFTP() {
    	nombreFicheros = new ArrayList<>();
        infoFicheros = new ArrayList<>();
    	try {
    		// Ficheros en el directorio actual
    		FTPFile[] files = cliente.listFiles();
    		
    		//array para visualizar el tipo de fichero
    		String[] tipos = { modelo.getNombreFichero(), modelo.getCarpetaNombre(), modelo.getNombreEnlace() };

    		for (int i = 0; i < files.length; i++) {
    			if(!files[i].getName().equals(".") && !files[i].getName().equals("..")) {
    				nombreFicheros.add(files[i].getName());
    				infoFicheros.add(tipos[files[i].getType()] + "-" + files[i].getName());
    			}
    		}
    	}
    	catch(Exception e) {
    		vistaFTPPrincipal.mostrarMensajeEmergente(modelo.getTituloErrorServidor(), modelo.getMensajeErrorServidor());
    	}
    	
	}

    /**
     * Agrega imagen a fichero según su tipo
     */
	private void agregarCaratulasFicheros() {

    	for(int i=0; i<nombreFicheros.size(); i++) {
    		String formato = extraerFormato(infoFicheros.get(i));
    		vistaFTPPrincipal.crearCaratulasFicheros(nombreFicheros.get(i), formato, infoFicheros.get(i));
    		vistaFTPPrincipal.getCaratulasProductos().get(vistaFTPPrincipal.getCaratulasProductos().size() - 1).addMouseListener(eventosFTP);
    		vistaFTPPrincipal.getCaratulasProductos().get(vistaFTPPrincipal.getCaratulasProductos().size() - 1).addActionListener(eventosFTP);
    	}

    }

	/**
	 * Extrae el formato del fichero a procesar
	 * @param nombreFichero - tipo String - datos del fichero
	 * @return formato - tipo String - formato del fichero
	 */
	private String extraerFormato(String nombreFichero) {
		
		String formato = modelo.getFormatos()[0];
		
		if(nombreFichero.contains(modelo.getFicheroGuion())) {
			String[] contenido = nombreFichero.split("\\.");
			if(contenido.length > 0) {
				String extension = contenido[contenido.length - 1];
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
				}
			}
		}
		else {
			formato = modelo.getFormatos()[5];
		}
		
		
		return formato;
	}

	/**
	 * Configura título de barra superior
	 */
	private void configurarTitulo() {
		vista.setIcono("src/opcionesprincipal/0.png");
		try {
			vista.setTitulo("FTP MOKE " + cliente.printWorkingDirectory());
		} catch (Exception e) {
			vistaFTPPrincipal.mostrarMensajeEmergente(modelo.getTituloErrorServidor(), modelo.getMensajeErrorServidor());
		}
	}

	/**
	 * Actualiza el contenido de la ventana
	 */
    private void actualizarVentana() {
        vista.repaint();
        vista.pack();
        vista.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    /**
     * Cambia a directorio hijo pulsado
     * @param infoFicheroPulsado - tipo String - datos fichero pulsado
     */
	public void cambiarDirectorioHijo(String infoFicheroPulsado) {
    	try {
    		String nuevoDirectorio = infoFicheroPulsado.replace(modelo.getCarpetaGuion(), "");
    		if(cliente.changeWorkingDirectory(nuevoDirectorio)) {
    			actualizarContenido();
    		}
    	}
    	catch(Exception e) {
    		vistaFTPPrincipal.mostrarMensajeEmergente(modelo.getTituloErrorServidor(), modelo.getMensajeErrorServidor());
    	}
	}
	
	/**
     * Cambia a directorio padre de carpeta actual
     */
	public void cambiarDirectorioPadre() {
    	try {
    		if(cliente.changeToParentDirectory()) {
    			actualizarContenido();
    		}
    	}
    	catch(Exception e) {
    		vistaFTPPrincipal.mostrarMensajeEmergente(modelo.getTituloErrorServidor(), modelo.getMensajeErrorServidor());
    	}
	}
	
	/**
	 * Actualiza contenido del navegador
	 */
	public void actualizarContenido() {
		listarFicherosFTP();
		vistaFTPPrincipal.limpiarPanelCentral();
		configurarTitulo();
		agregarBotonVolver();
		agregarCaratulasFicheros();
	}
	
	/**
	 * Agrega botón para volver a carpeta padre
	 */
	private void agregarBotonVolver() {
		try {
        	if(!cliente.printWorkingDirectory().equals(eventos.getDirectorioLimite())) {
    			// agregar boton de volver
        		vistaFTPPrincipal.crearCaratulasFicheros(modelo.getTextosGenerales()[3], modelo.getTextosGenerales()[4],
						modelo.getTextosGenerales()[5]);
    			vistaFTPPrincipal.getCaratulasProductos().get(0).addMouseListener(eventosFTP);
        		vistaFTPPrincipal.getCaratulasProductos().get(0).addActionListener(eventosFTP);
    		}
        }
        catch(Exception e) {}
	}
	
	/**
	 * Obtener datos del fichero pulsado
	 * @return infoFicheroPulsado - tipo String - información de fichero pulsado
	 */
	public String getInfoFicheroPulsado() {
		return infoFicheroPulsado;
	}

	/**
	 * Inserta datos del fichero pulsado
	 * @param infoFicheroPulsado - tipo String - información de fichero pulsado
	 */
	public void setInfoFicheroPulsado(String infoFicheroPulsado) {
		this.infoFicheroPulsado = infoFicheroPulsado;
	}
}

