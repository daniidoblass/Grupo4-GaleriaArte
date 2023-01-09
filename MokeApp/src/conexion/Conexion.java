/**
 * Clase Conexion
 * 
 * Métodos para la conexión con la base de datos MySQL
 * 
 * @author Samuel Acosta Fernandez
 * @date 09/12/2022
 * @version 01
 */

package conexion;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import modelo.Modelo;

public class Conexion {
    
	/**
	 * modelo - tipo Modelo - contiene textos del programa
	 */
    private Modelo modelo;
    
    /**
     * conexion - tipo Connection - conexión con MySQL
     */
    private Connection conexion;
    
    /**
     * metadatos - tipo DatabaseMetaData - metadatos MySQL
     */
    private DatabaseMetaData metadatos;
    
    /**
     * Constructor de Conexion para base de datos MySQL
     * @param modelo - tipo Modelo - contiene textos del programa
     */
    public Conexion(Modelo modelo) {
        this.modelo = modelo;
        realizarConexion();
        realizarDatabaseMetaData();
    }
    
    /**
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
    
    /**
     * Obtener el Metadata de la conexion
     */
    public void realizarDatabaseMetaData(){
        try {
            metadatos = conexion.getMetaData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    /**
     * Realiza la consulta indicada y devuelve su resultado en forma de ResultSet
     * @param consulta - tipo String - consulta sql a realizar
     * @return rs - tipo ResultSet - resultados de consulta sql
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
    
    /**
     * Actualiza base de datos mediante consulta indicada
     * @param consulta - tipo String - consulta sql a realizar
     * @return s1.executeUpdate - tipo int - número registros afectados
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
    
    /**
     * Obtener Metadata
     * @return metadatos - tipo DatabaseMetaData - metadatos MySQL
     */
    public DatabaseMetaData getMetaData() {
        return metadatos;
    }

    /**
     * Obtener Tablas
     * @return metadatos.getTables - tipo ResultSet - nombres de tablas
     */
    public ResultSet getTables() {
        try {
            return metadatos.getTables(modelo.getArchivo(), null, null, new String[]{modelo.getNombreTable()});
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    
    /**
     * Obtener Columnas de tabla pasada por parámetro
     * @param nombreTabla - tipo String - tabla a obtener columnas
     * @return metadatos.getColumns - tipo ResultSet - nombres de columnas
     */
    public ResultSet getColumns(String nombreTabla) {
        try {
            return metadatos.getColumns(modelo.getArchivo(), null, nombreTabla, null);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Registrar movimiento de usuario en base de datos
     * @param operacion - tipo String - operacion realizada
     * @param exito - tipo String - si ha podido realizarlo
     * @param resultado - tipo String - resultado de la operación
     * @return realizarUpdateStatement(sql) - tipo int - número modificados
     */
	public int registrarMovimiento(String operacion, String exito, String resultado) {
		SimpleDateFormat fecha = new SimpleDateFormat(modelo.getFormatoFecha()); 
		SimpleDateFormat hora = new SimpleDateFormat(modelo.getFormatoHora()); 
		Date date = new Date(); 
		String sql = modelo.getSqlMovimiento()
				+ modelo.getId() + ", '" + modelo.getUsuario() + "', '" + modelo.getCategoria() + "', '" + operacion 
				+ "', '" + exito + "', '" + resultado + "', '" + fecha.format(date) + "', '" + hora.format(date) + "')";
		return realizarUpdateStatement(sql);
	}
}
