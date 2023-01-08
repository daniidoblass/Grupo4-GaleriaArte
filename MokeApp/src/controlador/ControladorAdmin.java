/**
 * 
 * Clase ControladorAdmin
 * 
 * Crea y configura la ventana administrador junto con tablas 
 * para el visionado de la información almacenada en MySQL
 * 
 * @author Samuel Acosta Fernandez
 * @date 09/12/2022
 * @version 01
 */

package controlador;

import modelo.Modelo;
import vista.Vista;
import vista.VistaAdmin;
import conexion.Conexion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

import org.apache.commons.net.ftp.FTPClient;

public class ControladorAdmin {
    
	/**
	 * modelo - tipo Modelo - contiene textos del programa
	 */
    private Modelo modelo;
    
    /**
     * vista - tipo Vista - vista principal del programa
     */
    private Vista vista;
    
    /**
     * vistaAdmin - tipo VistaAdmin - vista del administrador
     */
    private VistaAdmin vistaAdmin;
    
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
     * nombreColumnas - tipo ArrayList<String> - columnas de tabla
     */
    private ArrayList<String> nombreColumnas;
    
    /**
     * eventosAdmin - tipo EventosAdmin - eventos del administrador
     */
    private EventosAdmin eventosAdmin;
    
    /**
     * Permite establecer panel de administración y sus eventos
     * @param modelo - tipo Modelo - contiene textos del programa
     * @param vista - tipo Vista - vista principal del programa
     * @param eventos - tipo Eventos - eventos principales 
     * @param conexion - tipo Conexion - conexion con base de datos
     * @param cliente - tipo FTPClient - cliente FTP
     */
    public ControladorAdmin(Modelo modelo, Vista vista, Eventos eventos, Conexion conexion, FTPClient cliente){
        this.modelo = modelo;
        this.vista = vista;
        this.eventos = eventos;
        vistaAdmin = new VistaAdmin(modelo, vista);
        this.conexion = conexion;
        this.cliente = cliente;
        eventosAdmin = new EventosAdmin(modelo, this);

        // Configurar titulo de la pagina
        configurarTitulo();
        
        // Configurar paneles admin
        configurarPanelesAdmin();
        
        // Configurar tabla
        configurarTabla(modelo.getMovimientos());
        
        // Configurar boton cambiar tabla
        configurarBotonCambiarTabla();
        
        // Actualizar ventana
        actualizarVentana();
    }

    /**
     * Configuración de botones laterales para cambiar la tabla
     */
    private void configurarBotonCambiarTabla() {
    	for(int i=0; i<modelo.getTextoOpcionesAdmin().length; i++) {
    		vistaAdmin.configurarBotonCambiarTabla(modelo.getTextoOpcionesAdmin()[i], modelo.getTextoOpcionesAdminImages()[i]);
    		vistaAdmin.getBotonesCambiarTabla().get(i).addMouseListener(eventosAdmin);
        	vistaAdmin.getBotonesCambiarTabla().get(i).addActionListener(eventosAdmin);
    	}
	}
    
    /**
     * Permite cambiar contenido de la tabla
     * @param nombreTabla - tipo String - tabla a cambiar
     */
	private void configurarTabla(String nombreTabla) {
    	// Rellenar Titulos
        rellenarTitulos(nombreTabla);

        // Configurar tabla con datos
        vistaAdmin.configuracionJTable1(nombreColumnas.toArray(new String[nombreColumnas.size()]));
        
        // Rellenar Datos
        rellenarDatos(modelo.getConsultaParaObtenerTodosLosDatos() + nombreTabla);
	}
	
	/**
	 * Actualiza el contenido de la tabla
	 * @param nombreTabla - tipo String - tabla a cambiar
	 */
	public void actualizarTabla(String nombreTabla) {
    	// Rellenar Titulos
        rellenarTitulos(nombreTabla);

        // Actualizar tabla con datos
        vistaAdmin.modificarModeloTabla(nombreColumnas.toArray(new String[nombreColumnas.size()]));
        
        // Rellenar Datos
        rellenarDatos(modelo.getConsultaParaObtenerTodosLosDatos() + nombreTabla);
	}
    
	/**
	 * Rellena cabecera de la tabla
	 * @param nombreTablaSeleccionada - tipo String - tabla a cambiar
	 */
    private void rellenarTitulos(String nombreTablaSeleccionada) {
    	
        // Obtener columnas de la tabla seleccionada
        ResultSet rsColumnas = conexion.getColumns(nombreTablaSeleccionada);

        nombreColumnas = new ArrayList<>();

        try {
            while (rsColumnas.next()) {
                nombreColumnas.add(rsColumnas.getString(modelo.getNombreColumna()));
            } 
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Agrega los datos a la tabla
     * @param select - tipo String - consulta sql
     */
    private void rellenarDatos(String select) {

        // Limpiamos los datos de la tabla
    	vistaAdmin.limpiarTabla();
        
        try {
            // Obtener datos de Tabla seleccionada
            ResultSet rs = conexion.realizarConsultaRS(select);
            
            
            // Bucle para cada resultado en la consulta
            while (rs.next()) {
                
               // Se crea un array que sera una de las filas de la tabla
               Object[] fila = new Object[nombreColumnas.size()]; 

               for (int i=0;i<nombreColumnas.size();i++){
                   fila[i] = rs.getObject(i+1); // El primer indice en rs es el 1, no el cero, por eso se suma 1.
               }
                  

               // Se anade al modelo la fila completa.
               vistaAdmin.insertRow(fila);
            }
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }

    /**
     * Crea y configura vista de administrador 
     */
	private void configurarPanelesAdmin() {
		for(int i=0; i<3; i++) {
			vistaAdmin.crearPanelesAdmin(i);
		}
		vistaAdmin.ordenarPaneles();
		vistaAdmin.configurarPanelAdminNorte();
		vistaAdmin.configurarPanelAdminEste();
		vistaAdmin.configurarPanelAdminCentral();
	}

	/**
	 * Configura título de barra superior
	 */
	private void configurarTitulo() {
		vista.setIcono(modelo.getRutasIconos()[0]);
		vista.setTitulo(modelo.getTextoLogos()[5]);
		eventos.setVentanaActual(modelo.getTextoLogos()[6]);
	}

	/**
	 * Actualiza el contenido de la ventana
	 */
    private void actualizarVentana() {
        vista.repaint();
        vista.pack();
        vista.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
	
}

