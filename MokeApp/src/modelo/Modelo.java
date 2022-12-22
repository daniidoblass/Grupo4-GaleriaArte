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
    
    // OPCIONES
    private String[] textoPanelesOpciones;
    private String textoOpciones;

    // CONEXION
    private String[] textoDatosConexion;
    private String archivo = "mokedb";
    private String ip = "localhost";
    private String user = "root";
    private String password = "";
    
    // TABLE
    private String nombreTable = "TABLE";
    private String nombreColumna = "COLUMN_NAME";
    
    // INFO
    private String[] textoOpcionesInfo;
    private String[] textoOpcionesInfoImages;
    private String[] textoDatosInfo;
    
    // CONFIGURACIÓN
    private String[] textoOpcionesConfig;
    private String[] textoOpcionesConfigImages;
    private String[] textoDatosConfig;
    
    // ADMIN
    private String[] textoOpcionesAdmin;
    private String[] textoOpcionesAdminImages;
    
    // CONSULTAS
    private String consultaParaObtenerTodosLosDatos = "SELECT * FROM ";
    
    // RUTAS
    private String [] rutasIconos;
    
    // FTP
    private String servidorFTP = "ftpupload.net";
	private String usuarioFTP = "b3_33188433";
	private String contrasenaFTP = "2wknvh9m"; 
	
	//TEXTOS PARA CREAR UNA CARPETA
	private String espacioEnBLanco = " ";
	private String nada = "";
	private String guionMedio = "-";
	private String guionBajo = "_";
	private String [] textosCrearCarpetas;

    public Modelo(){
        
        // VENTANA PRINCIPAL
        textoVentanaPrincipal = new String[]{"MOKE APP"};
        textoPanelesVentanaPrincipal = new String[]{"NORTE", "OESTE", "CENTRAL"};
        textoOpcionesMenu = new String[]{"Subir Archivo", "Descargar Archivo", "Eliminar Archivo", "Crear Carpeta", "Eliminar Carpeta", "Renombrar"};
        textoOpcionesMenuImages = new String[]{"subir_fichero", "descargar_fichero", "eliminar_fichero", "crear_carpeta", "eliminar_carpeta", "edit"};
        tipoOpciones = new String[]{"FTP_Moke", "Mail_Moke", "Configuracion_Moke", "Moke_Info"};
        textoLogos= new String[]{"MOKE APP", "FTP MOKE", "Mail MOKE", "Configuracion MOKE", "MOKE INFO","Administracion MOKE","ADMIN"};
        
        // LOGIN
        textoPanelesOpciones = new String[]{"arribaTexto", "medioUsuarios", "abajoFondo"};
        textoOpciones = "Seleccione una opcion";

        // CONEXION
        textoDatosConexion = new String[]{"jdbc:mysql://" + ip + "/" + archivo + "?serverTimezone=UTC", user, password, "com.mysql.cj.jdbc.Driver"};
        
        // INFO
        textoOpcionesInfo = new String[]{"Subir Archivo", "Descargar Archivo", "Eliminar Archivo", "Crear Carpeta", "Eliminar Carpeta", "Renombrar"};
        textoOpcionesInfoImages = new String[]{"subir_fichero", "descargar_fichero", "eliminar_fichero", "crear_carpeta", "eliminar_carpeta", "edit"};
        textoDatosInfo = new String[] {"El usuario podrá subir archivos desde su ordenador al servidor",
        		 "El usuario podrá descargar archivos de otros usuarios (no tiene permitido descargar carpetas)",
        		 "El usuario podrá borrar los archivos creados por él",
        		 "El usuario podrá crear carpetas donde subir los archivos",
        		 "El usuario podrá borrar las carpetas creadas por él",
        		 "El usuario podrá renombrar las carpetas y archivos subidos por él",
        		 "El usuario podrá acceder a un apartado de correo electrónico donde podrá enviar correos a otros usuarios"};
        
        // CONFIGURACIÓN
        textoOpcionesConfig = new String[]{"Reestablecer Contraseña", "Cambiar Correo Corporativo", "Soporte Técnico"};
        textoOpcionesConfigImages = new String[]{"password", "gmail", "soporte"};
        textoDatosConfig = new String[] {"Introduzca la nueva contraseña",
       		 "Introduzca el nuevo correo corporativo",
       		 "Introduzca un mensaje para el Soporte Técnico"};
        
        // ADMIN
        textoOpcionesAdmin = new String[]{"Movimientos", "Usuarios", "Mensajes"};
        textoOpcionesAdminImages = new String[]{"movements", "contacts", "message"};
        
        //RUTAS DE LOS ICONOS
        rutasIconos = new String [] {"src/opcionesprincipal/4.png","src/opcionesprincipal/2.png"};
        
        //TEXTOS CREAR UNA CARPETA
        textosCrearCarpetas = new String [] {"Crear Carpeta","Nombre Carpeta", " se ha creado correctamente"," no se ha podido crear","El campo nombre no puede estar vacío"};
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
    
    public String[] getTextoOpcionesAdmin(){
        return textoOpcionesAdmin;
    }
    
    public String[] getTextoOpcionesAdminImages(){
        return textoOpcionesAdminImages;
    }

	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombreTable() {
		return nombreTable;
	}

	public void setNombreTable(String nombreTable) {
		this.nombreTable = nombreTable;
	}

	public String getConsultaParaObtenerTodosLosDatos() {
		return consultaParaObtenerTodosLosDatos;
	}

	public void setConsultaParaObtenerTodosLosDatos(String consultaParaObtenerTodosLosDatos) {
		this.consultaParaObtenerTodosLosDatos = consultaParaObtenerTodosLosDatos;
	}

	public String getNombreColumna() {
		return nombreColumna;
	}

	public void setNombreColumna(String nombreColumna) {
		this.nombreColumna = nombreColumna;
	}

	public String[] getRutasIconos() {
		return rutasIconos;
	}

	public void setRutasIconos(String[] rutasIconos) {
		this.rutasIconos = rutasIconos;
	}

	public String getServidorFTP() {
		return servidorFTP;
	}

	public void setServidorFTP(String servidorFTP) {
		this.servidorFTP = servidorFTP;
	}

	public String getUsuarioFTP() {
		return usuarioFTP;
	}

	public void setUsuarioFTP(String usuarioFTP) {
		this.usuarioFTP = usuarioFTP;
	}

	public String getContrasenaFTP() {
		return contrasenaFTP;
	}

	public void setContrasenaFTP(String contrasenaFTP) {
		this.contrasenaFTP = contrasenaFTP;
	}

	public String getEspacioEnBLanco() {
		return espacioEnBLanco;
	}

	public void setEspacioEnBLanco(String espacioEnBLanco) {
		this.espacioEnBLanco = espacioEnBLanco;
	}

	public String getNada() {
		return nada;
	}

	public void setNada(String nada) {
		this.nada = nada;
	}

	public String getGuionMedio() {
		return guionMedio;
	}

	public void setGuionMedio(String guionMedio) {
		this.guionMedio = guionMedio;
	}

	public String getGuionBajo() {
		return guionBajo;
	}

	public void setGuionBajo(String guionBajo) {
		this.guionBajo = guionBajo;
	}

	public String[] getTextosCrearCarpetas() {
		return textosCrearCarpetas;
	}

	public void setTextosCrearCarpetas(String[] textosCrearCarpetas) {
		this.textosCrearCarpetas = textosCrearCarpetas;
	}
    
    
}
