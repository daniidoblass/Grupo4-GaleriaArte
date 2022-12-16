/**
 * @author Samuel Acosta Fernandez
 * @date 09/02/2022
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

import javax.swing.JFrame;

import org.apache.commons.net.ftp.FTPClient;

public class ControladorAdmin implements ActionListener {
    
    private Modelo modelo;
    private Vista vista;
    private VistaAdmin vistaAdmin;
    private Eventos eventos;
    private Conexion conexion;
    private FTPClient cliente;
    private ArrayList<String> nombreColumnas;
    private String nombreTabla;
    
    public ControladorAdmin(Modelo modelo, Vista vista, Eventos eventos, Conexion conexion, FTPClient cliente){
        this.modelo = modelo;
        this.vista = vista;
        this.eventos = eventos;
        vistaAdmin = new VistaAdmin(modelo, vista);
        this.conexion = conexion;
        this.cliente = cliente;

        // Configurar titulo de la pagina
        configurarTitulo();
        
        // Configurar paneles admin
        configurarPanelesAdmin();
        
        // Configurar tabla
        nombreTabla = "usuarios";
        configurarTabla(nombreTabla);
        
        // Configurar boton cambiar tabla
        configurarBotonCambiarTabla();
        
        // Actualizar ventana
        actualizarVentana();
    }

    private void configurarBotonCambiarTabla() {
    	vistaAdmin.configurarBotonCambiarTabla();
    	vistaAdmin.getBotonCambiarTabla().addActionListener(this);
	}
    
    @Override
	public void actionPerformed(ActionEvent e) {
    	if(nombreTabla.equals("usuarios")) {
    		nombreTabla = "movimientos";
    		actualizarTabla(nombreTabla);
    	}
    	else {
    		nombreTabla = "usuarios";
    		actualizarTabla(nombreTabla);
    	}
	}

	private void configurarTabla(String nombreTabla) {
    	// Rellenar Titulos
        rellenarTitulos(nombreTabla);

        // Configurar tabla con datos
        vistaAdmin.configuracionJTable1(nombreColumnas.toArray(new String[nombreColumnas.size()]));
        
        // Rellenar Datos
        rellenarDatos("SELECT * FROM " + nombreTabla);
	}
	
	private void actualizarTabla(String nombreTabla) {
    	// Rellenar Titulos
        rellenarTitulos(nombreTabla);

        // Actualizar tabla con datos
        vistaAdmin.modificarModeloTabla(nombreColumnas.toArray(new String[nombreColumnas.size()]));
        
        // Rellenar Datos
        rellenarDatos("SELECT * FROM " + nombreTabla);
	}
    
    private void rellenarTitulos(String nombreTablaSeleccionada) {
    	
        // Obtener columnas de la tabla seleccionada
        ResultSet rsColumnas = conexion.getColumns(nombreTablaSeleccionada);

        nombreColumnas = new ArrayList<>();

        try {
            while (rsColumnas.next()) {
                nombreColumnas.add(rsColumnas.getString("COLUMN_NAME"));
            } 
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void rellenarDatos(String select) {

        // Limpiamos los datos de la tabla
    	vistaAdmin.limpiarTabla();
        
        try {
            // Obtener datos de Tabla seleccionada
            ResultSet rs = conexion.realizarConsultaRS(select);
            
            
            // Bucle para cada resultado en la consulta
            while (rs.next()) {
                
               // Se crea un array que ser� una de las filas de la tabla
               Object[] fila = new Object[nombreColumnas.size()]; 

               for (int i=0;i<nombreColumnas.size();i++){
                   fila[i] = rs.getObject(i+1); // El primer indice en rs es el 1, no el cero, por eso se suma 1.
               }
                  

               // Se a�ade al modelo la fila completa.
               vistaAdmin.insertRow(fila);
            }
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }

	private void configurarPanelesAdmin() {
		for(int i=0; i<3; i++) {
			vistaAdmin.crearPanelesAdmin(i);
		}
		vistaAdmin.ordenarPaneles();
		vistaAdmin.configurarPanelAdminNorte();
		vistaAdmin.configurarPanelAdminEste();
		vistaAdmin.configurarPanelAdminCentral();
	}

	private void configurarTitulo() {
		vista.setIcono("src/opcionesprincipal/4.png");
		vista.setTitulo("Administracion MOKE");
	}

    private void actualizarVentana() {
        vista.repaint();
        vista.pack();
        vista.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
	
}
