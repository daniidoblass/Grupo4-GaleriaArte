/**
 * @author Samuel Acosta Fernandez
 * @date 27/04/2022
 * @version 01
 */
package conexion;
import java.sql.*;
import java.util.ArrayList;
import modelo.Modelo;

public class Conexion {
    
    private Modelo modelo;
    private Connection conexion;
    private DatabaseMetaData metadatos;
    
    public Conexion() {
        modelo = new Modelo();
	realizarConexion();
        realizarDatabaseMetaData();
    }
    
    /*
     * Realiza la conexion con la base de datos 
     */
    public void realizarConexion(){

        try {
            Class.forName(modelo.getTextoDatosConexion()[3]);	            

            conexion = DriverManager.getConnection(
                modelo.getTextoDatosConexion()[0],modelo.getTextoDatosConexion()[1], modelo.getTextoDatosConexion()[2]); 
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    /*
     * Obtener el Metadata de la conexion
    */
    public void realizarDatabaseMetaData(){
        try {
            metadatos = conexion.getMetaData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    /* 
     * Realiza la consulta indicada y devuelve su resultado en forma de ArrayList<String>
     */
    public ResultSet realizarConsultaRS(String consulta){

        try {
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery(consulta);
            return rs;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    /* 
     * Realiza la consulta indicada
     */
    public int realizarUpdateStatement(String consulta){

        try {
            Statement s1 = conexion.createStatement();
            return s1.executeUpdate(consulta);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    // Obtener Metadata
    public DatabaseMetaData getMetaData() {
        return metadatos;
    }

    // Obtener Tablas
    public ResultSet getTables() {
        try {
            return metadatos.getTables("mokedb", null, null, new String[]{"TABLE"});
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    
    // Obtener Columnas
    public ResultSet getColumns(String nombreTabla) {
        try {
            return metadatos.getColumns("mokedb", null, nombreTabla, null);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
