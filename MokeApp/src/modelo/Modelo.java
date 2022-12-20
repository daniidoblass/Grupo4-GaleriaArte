/**
 * @author Samuel Acosta Fernandez
 * @date 09/02/2022
 * @version 01
 */
package modelo;


public class Modelo {
    
    // VENTANA PRINCIPAL
    private String[] textoVentanaPrincipal;
    private String[] textoPanelesVentanaPrincipal;
    private String[] textoOpcionesMenu;
    private String[] textoOpcionesMenuImages;
    private String[] tipoOpciones;
    private String[] textoLogos;

    private String archivoClicado;
    private String[] textoConfiguracion;
    private String[] textoSoporteTecnico;
    
    // OPCIONES
    private String[] textoPanelesOpciones;
    private String textoOpciones;

    // CONEXION
    private String[] textoDatosConexion;
    private String archivo = "mokedb";
    private String ip = "localhost";
    private String user = "root";
    private String password = "";
    
    // INFO
    private String[] textoOpcionesInfo;
    private String[] textoOpcionesInfoImages;
    private String[] textoDatosInfo;
    
    // CONFIGURACIÓN
    private String[] textoOpcionesConfig;
    private String[] textoOpcionesConfigImages;
    private String[] textoDatosConfig;

    public Modelo(){
        
        // VENTANA PRINCIPAL
        textoVentanaPrincipal = new String[]{"MOKE APP"};
        textoPanelesVentanaPrincipal = new String[]{"NORTE", "OESTE", "CENTRAL"};
        textoOpcionesMenu = new String[]{"Subir Archivo", "Descargar Archivo", "Eliminar Archivo", "Crear Carpeta", "Eliminar Carpeta","Renombrar"};
        textoOpcionesMenuImages = new String[]{"subir_fichero", "descargar_fichero", "eliminar_fichero", "crear_carpeta", "eliminar_carpeta","edit"};
        tipoOpciones = new String[]{"FTP_Moke", "Mail_Moke", "Configuracion_Moke", "Moke_Info"};
        textoLogos= new String[]{"MOKE APP", "FTP MOKE", "Mail MOKE", "Configuracion MOKE", "MOKE INFO"};
        textoConfiguracion= new String[]{"Reestablecer Contraseña", "Cambiar Email Corporativo", "Soporte Tecnico"};
        textoSoporteTecnico= new String[]{"Si desea informarnos sobre algún problema relacionado con el programa\nContáctanos por:","Télefono: 695138058","Correo Electrónico: adminMokeApp@gmail.com"};        
        // LOGIN
        textoPanelesOpciones = new String[]{"arribaTexto", "medioUsuarios", "abajoFondo"};
        textoOpciones = "Seleccione una opcion";

        // CONEXION
        textoDatosConexion = new String[]{"jdbc:mysql://" + ip + "/" + archivo + "?serverTimezone=UTC", user, password, "com.mysql.cj.jdbc.Driver"};
        
        // INFO
        textoOpcionesInfo = new String[]{"Subir Archivo", "Descargar Archivo", "Eliminar Archivo", "Crear Carpeta", "Eliminar Carpeta"};
        textoOpcionesInfoImages = new String[]{"subir_fichero", "descargar_fichero", "eliminar_fichero", "crear_carpeta", "eliminar_carpeta"};
        textoDatosInfo = new String[] {"El usuario podrá subir archivos desde su ordenador al servidor",
        		 "El usuario podrá descargar archivos de otros usuarios (no tiene permitido descargar carpetas)",
        		 "El usuario podrá borrar los archivos creados por él",
        		 "El usuario podrá crear carpetas donde subir los archivos",
        		 "El usuario podrá borrar las carpetas creadas por él",
        		 "El usuario podrá renombrar las carpetas y archivos subidos por él",
        		 "El usuario podrá acceder a un apartado de correo electrónico donde podrá enviar correos a otros usuarios"};
        
     // CONFIGURACI�N
        textoOpcionesConfig = new String[]{"Reestablecer Contrase�a", "Cambiar Correo Corporativo", "Soporte T�cnico"};
        textoOpcionesConfigImages = new String[]{"password", "gmail", "soporte"};
        textoDatosConfig = new String[] {"Introduzca la nueva contrase�a",
       		 "Introduzca el nuevo correo corporativo",
       		 "Introduzca un mensaje para el Soporte T�cnico"};
    }
    
    public String[] getTextoConfiguracion() {
		return textoConfiguracion;
	}

	public void setTextoConfiguracion(String[] textoConfiguracion) {
		this.textoConfiguracion = textoConfiguracion;
	}

	public String[] getTextoVentanaPrincipal(){
        return textoVentanaPrincipal;
    }
    
    public String[] getTextoPanelesVentanaPrincipal(){
        return textoPanelesVentanaPrincipal;
    }
    
    public String[] getTextoOpcionesMenu(){
        return textoOpcionesMenu;
    }
    
    public String[] getTextoOpcionesMenuImages(){
        return textoOpcionesMenuImages;
    }
    
    public String[] getTipoOpciones(){
        return tipoOpciones;
    }
    
    public String[] getTextoPanelesOpciones(){
        return textoPanelesOpciones;
    }
    
    public String[] getTextoLogos(){
        return textoLogos;
    }
    
    public String getTextoOpciones(){
        return textoOpciones;
    }

    public String[] getTextoDatosConexion(){
        return textoDatosConexion;
    }

	public String getArchivoClicado() {
		return archivoClicado;
	}

	public void setArchivoClicado(String archivoClicado) {
		this.archivoClicado = archivoClicado;
	}
	public String[] getTextoSoporteTecnico() {
		return textoSoporteTecnico;
	}

    public String[] getTextoOpcionesInfo(){
        return textoOpcionesInfo;
    }
    
    public String[] getTextoOpcionesInfoImages(){
        return textoOpcionesInfoImages;
    }
    
    public String[] getTextoDatosInfo() {
		return textoDatosInfo;
	}
    
    public String[] getTextoOpcionesConfig(){
        return textoOpcionesConfig;
    }
    
    public String[] getTextoOpcionesConfigImages(){
        return textoOpcionesConfigImages;
    }
    
    public String[] getTextoDatosConfig() {
		return textoDatosConfig;
	}
    
}
