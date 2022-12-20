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

<<<<<<< HEAD
=======
import javax.swing.JButton;
>>>>>>> ramaLogin
import javax.swing.JFrame;

import org.apache.commons.net.ftp.FTPClient;

<<<<<<< HEAD
public class ControladorAdmin implements ActionListener {
=======
public class ControladorAdmin {
>>>>>>> ramaLogin
    
    private Modelo modelo;
    private Vista vista;
    private VistaAdmin vistaAdmin;
    private Eventos eventos;
    private Conexion conexion;
    private FTPClient cliente;
    private ArrayList<String> nombreColumnas;
<<<<<<< HEAD
    private String nombreTabla;
=======
    private EventosAdmin eventosAdmin;
>>>>>>> ramaLogin
    
    public ControladorAdmin(Modelo modelo, Vista vista, Eventos eventos, Conexion conexion, FTPClient cliente){
        this.modelo = modelo;
        this.vista = vista;
        this.eventos = eventos;
        vistaAdmin = new VistaAdmin(modelo, vista);
        this.conexion = conexion;
        this.cliente = cliente;
<<<<<<< HEAD
=======
        eventosAdmin = new EventosAdmin(modelo, this);
>>>>>>> ramaLogin

        // Configurar titulo de la pagina
        configurarTitulo();
        
        // Configurar paneles admin
        configurarPanelesAdmin();
        
        // Configurar tabla
<<<<<<< HEAD
        nombreTabla = "usuarios";
        configurarTabla(nombreTabla);
=======
        configurarTabla("movimientos");
>>>>>>> ramaLogin
        
        // Configurar boton cambiar tabla
        configurarBotonCambiarTabla();
        
        // Actualizar ventana
        actualizarVentana();
    }

    private void configurarBotonCambiarTabla() {
<<<<<<< HEAD
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

=======
    	for(int i=0; i<modelo.getTextoOpcionesAdmin().length; i++) {
    		vistaAdmin.configurarBotonCambiarTabla(modelo.getTextoOpcionesAdmin()[i], modelo.getTextoOpcionesAdminImages()[i]);
    		vistaAdmin.getBotonesCambiarTabla().get(i).addMouseListener(eventosAdmin);
        	vistaAdmin.getBotonesCambiarTabla().get(i).addActionListener(eventosAdmin);
    	}
	}
    
    
>>>>>>> ramaLogin
	private void configurarTabla(String nombreTabla) {
    	// Rellenar Titulos
        rellenarTitulos(nombreTabla);

        // Configurar tabla con datos
        vistaAdmin.configuracionJTable1(nombreColumnas.toArray(new String[nombreColumnas.size()]));
        
        // Rellenar Datos
        rellenarDatos("SELECT * FROM " + nombreTabla);
	}
	
<<<<<<< HEAD
	private void actualizarTabla(String nombreTabla) {
=======
	public void actualizarTabla(String nombreTabla) {
>>>>>>> ramaLogin
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
                
<<<<<<< HEAD
               // Se crea un array que será una de las filas de la tabla
=======
               // Se crea un array que sera una de las filas de la tabla
>>>>>>> ramaLogin
               Object[] fila = new Object[nombreColumnas.size()]; 

               for (int i=0;i<nombreColumnas.size();i++){
                   fila[i] = rs.getObject(i+1); // El primer indice en rs es el 1, no el cero, por eso se suma 1.
               }
                  

<<<<<<< HEAD
               // Se añade al modelo la fila completa.
=======
               // Se aï¿½ade al modelo la fila completa.
>>>>>>> ramaLogin
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
<<<<<<< HEAD
=======
		eventos.setVentanaActual("ADMIN");
>>>>>>> ramaLogin
	}

    private void actualizarVentana() {
        vista.repaint();
        vista.pack();
        vista.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
	
}

