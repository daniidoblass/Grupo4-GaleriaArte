/**
 * @author Samuel Acosta Fernandez
 * @date 09/02/2022
 * @version 01
 */
package modelo;
/**
 * Clase Modelo que almacena todos los textos usados en el programa
 * @author Pablo Pérez Ferre
 *
 */
public class Modelo {

	// VENTANA PRINCIPAL
	/**
	 * textoVentanaPrincipal - tipo String[] - Almacena todos los textos de la ventana Principal 
	 */
	private String[] textoVentanaPrincipal;
	/**
	 * textoPanelesVentanaPrincipal - tipo String[] - Almacena todos los textos de los paneles de la ventana Principal 
	 */
	private String[] textoPanelesVentanaPrincipal;
	/**
	 * textoOpcionesMenu - tipo String[] - Almacena todos los textos de las opciones del menu 
	 */
	private String[] textoOpcionesMenu;
	/**
	 * textoOpcionesMenuImages - tipo String[] - Almacena todos los textos de las opciones de las imagenes del manual 
	 */
	private String[] textoOpcionesMenuImages;
	/**
	 * tipoOpciones - tipo String[] - Almacena todos los tipos de Opciones
	 */
	private String[] tipoOpciones;
	/**
	 * textoLogos - tipo String[] - Almacena todos los textos de los logos
	 */
	private String[] textoLogos;

	// OPCIONES
	/**
	 * textoPanelesOpciones - tipo String[] - Almacena todos los textos de los paneles de las opciones
	 */
	private String[] textoPanelesOpciones;
	/**
	 * textoOpciones - tipo String - Almacena todos los textos de las opciones
	 */
	private String textoOpciones;

	// CONEXION
	/**
	 * textoDatosConexion - tipo String[] - Almacena todos los textos de los datos necesarios para la conexion
	 */
	private String[] textoDatosConexion;
	/**
	 * archivo - tipo String - Almacena todos los textos de los datos necesarios para la conexion
	 */
	private String archivo = "mokedb";
	/**
	 * ip - tipo String - Almacena la ip de la conexion
	 */
	private String ip = "localhost";
	/**
	 * user - tipo String - Almacena el usuario de la conexion
	 */
	private String user = "root";
	/**
	 * password - tipo String - Almacena la contraseña para la conexion
	 */
	private String password = "";

	// TABLE
	/**
	 * nombreTable - tipo String - Almacena el nombre de la tabla
	 */
	private String nombreTable = "TABLE";
	/**
	 * nombreColumna - tipo String - Almacena el nombre de las columnas
	 */
	private String nombreColumna = "COLUMN_NAME";

	// INFO
	/**
	 * textoOpcionesInfo - tipo String[] - Almacena todos los textos de las opciones de la Info
	 */
	private String[] textoOpcionesInfo;
	/**
	 * textoOpcionesInfoImages - tipo String[] - Almacena todos los textos de las imagenes de la Info
	 */
	private String[] textoOpcionesInfoImages;
	/**
	 * textoDatosInfo - tipo String[] - Almacena todos los textos de cada apartado de la info
	 */
	private String[] textoDatosInfo;

	// CONFIGURACIÓN
	/**
	 * textoOpcionesConfig - tipo String[] - Almacena todos los textos de las opciones de la Configuración
	 */
	private String[] textoOpcionesConfig;
	/**
	 * textoOpcionesConfigImages - tipo String[] - Almacena todos los textos de las imagenes de la Configuración
	 */
	private String[] textoOpcionesConfigImages;
	/**
	 * textoDatosConfig - tipo String[] - Almacena todos los textos de cada apartado de la Configuración
	 */
	private String[] textoDatosConfig;

	// ADMIN
	/**
	 * textoOpcionesAdmin - tipo String[] - Almacena todos los textos de las opciones del Admin
	 */
	private String[] textoOpcionesAdmin;
	/**
	 * textoOpcionesAdminImages - tipo String[] - Almacena todos los textos de las imagenes del apartado del Admin
	 */
	private String[] textoOpcionesAdminImages;

	// CONSULTAS
	/**
	 * consultaParaObtenerTodosLosDatos - tipo String - Almacena la consulta para obtener todos los datos de unta tabla
	 */
	private String consultaParaObtenerTodosLosDatos = "SELECT * FROM ";
	/**
	 * comillaSimple - tipo String - Almacena una comilla simple
	 */
	private String comillaSimple = "'";

	// Consulta para obtener el correo y password de quien se ha registrado el app
	/**
	 * consultaCorreoPassword - tipo String - Almacena la consulta para obtener el correo y la contraseña de un usuario
	 */
	private String consultaCorreoPassword = "SELECT correo,contraseniaGmail FROM `usuarios` WHERE nombre like '";

	// Consulta para obtener el responsable asignado.
	/**
	 * consultaResponsableAsignado - tipo String - Almacena la consulta para obtener el responsable de dicho usuario
	 */
	private String consultaResponsableAsignado = "SELECT ResponsableAsignado FROM `usuarios` WHERE nombre LIKE '";

	// Consulta para obtener la password encriptado.
	/**
	 * consultaPasswordEncriptado01 - tipo String - Almacena una parte de la consulta para sacar la contraseña encriptada
	 */
	private String consultaPasswordEncriptado01 = "SELECT categoria FROM `usuarios` WHERE password like PASSWORD('";
	/**
	 * consultaPasswordEncriptado02 - tipo String - Almacena una parte de la consulta para sacar la contraseña encriptada
	 */
	private String consultaPasswordEncriptado02 = "') AND nombre like '";

	// RUTAS
	/**
	 * rutasIconos - tipo String[] - Almacena todas las rutas de los iconos
	 */
	private String[] rutasIconos;

	// FTP
	/**
	 * servidorFTP - tipo String - Almacena la direccion del FTP
	 */
	private String servidorFTP = "ftpupload.net";
	/**
	 * usuarioFTP - tipo String - Almacena el usuario del FTP
	 */
	private String usuarioFTP = "b3_33188433";
	/**
	 * contrasenaFTP - tipo String - Almacena la contraseña del FTP
	 */
	private String contrasenaFTP = "2wknvh9m";
	/**
	 * nombreFichero - tipo String - Almacena el nombre del fichero del FTP
	 */
	private String nombreFichero = "fichero";
	/**
	 * nombreEnlace - tipo String - Almacena el nombre del enlace del FTP
	 */
	private String nombreEnlace = "enlace";

	// TEXTOS PARA CREAR UNA CARPETA
	/**
	 * espacioEnBLanco - tipo String - Almacena el un espacio en blanco
	 */
	private String espacioEnBLanco = " ";
	/**
	 * nada - tipo String - Almacena nada
	 */
	private String nada = "";
	/**
	 * guionMedio - tipo String - Almacena un guion medio
	 */
	private String guionMedio = "-";
	/**
	 * guionBajo - tipo String - Almacena un guion bajo
	 */
	private String guionBajo = "_";
	/**
	 * textosCrearCarpetas - tipo String[] - Almacena textos de Crear Carpetas
	 */
	private String[] textosCrearCarpetas;

	// TEXTOS DESCARGAR ARCHIVOS
	/**
	 * textosDescargarArchivos - tipo String[] - Almacena textos para descargar Archivos
	 */
	private String[] textosDescargarArchivos;
	/**
	 * carpetaGuion - tipo String - Almacena "carpeta-"
	 */
	private String carpetaGuion = "carpeta-";
	/**
	 * ficheroGuion - tipo String - Almacena "fichero-"
	 */
	private String ficheroGuion = "fichero-";
	/**
	 * signoPregunta - tipo String - Almacena un signo de Interrogación
	 */
	private String signoPregunta = "?";
	/**
	 * dobleBarraInvertida - tipo String - Almacena doble barra
	 */
	private String dobleBarraInvertida = "\\";

	// TEXTOS ELIMINAR ARCHIVOS
	/**
	 * textosEliminarArchivos - tipo String[] - Almacena los textos de Eliminar Archivos
	 */
	private String[] textosEliminarArchivos;

	// TEXTOS ELIMINAR CARPETAS
	/**
	 * carpetaNombre - tipo String - Almacena carpeta
	 */
	private String carpetaNombre = "carpeta";
	/**
	 * barra - tipo String - Almacena una barra
	 */
	private String barra = "/";
	/**
	 * textosEliminarCarpetas - tipo String[] - Almacena los textos de Eliminar Carpetas
	 */
	private String[] textosEliminarCarpetas;
	/**
	 * punto - tipo String - Almacena una punto
	 */
	private String punto = ".";
	/**
	 * puntoDoble - tipo String - Almacena dos punto
	 */
	private String puntoDoble = "..";

	// SERVIDOR SMTP DE GOOGLE
	/**
	 * textosSmtp - tipo String[] - Almacena los textos del server SMTP
	 */
	private String[] textosSmtp;

	// EXTENSIONES
	/**
	 * formatos - tipo String[] - Almacena formatos
	 */
	private String[] formatos;
	/**
	 * extensiones - tipo String[] - Almacena extensiones de los archivos
	 */
	private String[] extensiones;

	// MENSAJES GENERALES
	/**
	 * textosGenerales - tipo String[] - Almacena todos los mensajes generales del programa
	 */
	private String[] textosGenerales;
	/**
	 * signoMenor - tipo String - Almacena el signo menor 
	 */
	private String signoMenor = "<";
	/**
	 * signoMayor - tipo String - Almacena el signo mayor 
	 */
	private String signoMayor = ">";

	// CATEGORIAS
	/**
	 * categorias - tipo String[] - Almacena las categorias
	 */
	private String[] categorias;
	/**
	 * rutasUsers - tipo String[] - Almacena las rutas de los usuarios
	 */
	private String[] rutasUsers;

	// NOMBRE COLUMNAS MAIL
	/**
	 * nombresColumns - tipo String[] - Almacena los nombres de las columnas del mail
	 */
	private String[] nombresColumns;

	// PROPIEDADES MAIL
	/**
	 * propiedadesMail - tipo String[] - Almacena las propiedades del mail
	 */
	private String[] propiedadesMail;
	/**
	 * infoMail - tipo String[] - Almacena la info del mail
	 */
	private String[] infoMail;
/**
 * Metodo Modelo que inserta todos los string en las variables
 */
	public Modelo() {

		// VENTANA PRINCIPAL
		textoVentanaPrincipal = new String[] { "MOKE APP" };

		textoPanelesVentanaPrincipal = new String[] { "NORTE", "OESTE", "CENTRAL" };

		textoOpcionesMenu = new String[] { "Subir Archivo", "Descargar Archivo", "Eliminar Archivo", "Crear Carpeta",
				"Eliminar Carpeta", "Renombrar" };

		textoOpcionesMenuImages = new String[] { "subir_fichero", "descargar_fichero", "eliminar_fichero",
				"crear_carpeta", "eliminar_carpeta", "edit" };

		tipoOpciones = new String[] { "FTP_Moke", "Mail_Moke", "Configuracion_Moke", "Moke_Info" };

		textoLogos = new String[] { "MOKE APP", "FTP MOKE", "Mail MOKE", "Configuracion MOKE", "MOKE INFO",
				"Administracion MOKE", "ADMIN", "MOKE LOGIN" };

		// LOGIN
		textoPanelesOpciones = new String[] { "arribaTexto", "medioUsuarios", "abajoFondo" };
		textoOpciones = "Seleccione una opcion";

		// CONEXION
		textoDatosConexion = new String[] { "jdbc:mysql://" + ip + "/" + archivo + "?serverTimezone=UTC", user,
				password, "com.mysql.cj.jdbc.Driver" };

		// INFO
		textoOpcionesInfo = new String[] { "Subir Archivo", "Descargar Archivo", "Eliminar Archivo", "Crear Carpeta",
				"Eliminar Carpeta", "Renombrar" };
		textoOpcionesInfoImages = new String[] { "subir_fichero", "descargar_fichero", "eliminar_fichero",
				"crear_carpeta", "eliminar_carpeta", "edit" };
		textoDatosInfo = new String[] { "El usuario podrá subir archivos desde su ordenador al servidor",
				"El usuario podrá descargar archivos de otros usuarios (no tiene permitido descargar carpetas)",
				"El usuario podrá borrar los archivos creados por él",
				"El usuario podrá crear carpetas donde subir los archivos",
				"El usuario podrá borrar las carpetas creadas por él",
				"El usuario podrá renombrar las carpetas y archivos subidos por él",
				"El usuario podrá acceder a un apartado de correo electrónico donde podrá enviar correos a otros usuarios" };

		// CONFIGURACIÓN
		textoOpcionesConfig = new String[] { "Reestablecer Contraseña", "Cambiar Correo Corporativo",
				"Soporte Técnico" };
		textoOpcionesConfigImages = new String[] { "password", "gmail", "soporte" };
		textoDatosConfig = new String[] { "Introduzca la nueva contraseña", "Introduzca el nuevo correo corporativo",
				"Introduzca un mensaje para el Soporte Técnico" };

		// ADMIN
		textoOpcionesAdmin = new String[] { "Movimientos", "Usuarios", "Mensajes" };
		textoOpcionesAdminImages = new String[] { "movements", "contacts", "message" };

		// RUTAS DE LOS ICONOS
		rutasIconos = new String[] { "src/opcionesprincipal/4.png", "src/opcionesprincipal/2.png",
				"src/opcionesprincipal/0.png", "src/opcionesprincipal/3.png", "src/subiconos/usuario.png",
				"src/opcionesprincipal/1.png" };

		// TEXTOS CREAR UNA CARPETA
		textosCrearCarpetas = new String[] { "Crear Carpeta", "Nombre Carpeta", " se ha creado correctamente",
				" no se ha podido crear", "El campo nombre no puede estar vacío" };

		// TEXTOS DESCARGAR ARCHIVOS
		textosDescargarArchivos = new String[] { "Descargar Archivo", "No hay seleccionado ningún archivo",
				"¿Desea descargar ", "Se ha descargado correctamente", "No se ha podido descargar el archivo" };

		// TEXTOS ELIMINAR ARCHIVOS
		textosEliminarArchivos = new String[] { "Eliminar Archivo", "No hay seleccionado ningún archivo",
				"¿Desea eliminar ", "Archivo eliminado correctamente", "No se ha podido eliminar el archivo" };

		// TEXTOS ELIMINAR CARPETAS
		textosEliminarCarpetas = new String[] { "ERROR: la carpeta seleccionada contiene espacios",
				"¿Deseas eliminar la carpeta ", "Eliminar Carpeta", " se ha eliminado correctamente",
				"seleccionada la opcion si" };

		// TEXTOS SMTP
		textosSmtp = new String[] { "mail.smtp.host", "smtp.gmail.com", "mail.smtp.user", "mail.smtp.clave",
				"mail.smtp.auth", "true", "mail.smtp.starttls.enable", "mail.smtp.port", "587", "smtp",
				"Correo Enviado", "El correo no existe", "No puedes dejar campos vacios" };

		// FORMATOS
		formatos = new String[] { "file", "movie", "music", "document", "image", "folder" };

		// TEXTOS EXTENSIONES
		extensiones = new String[] { ".mp4", ".avi", ".mp3", ".wav", ".txt", ".docx", ".pdf", ".png", ".jpg", ".jpeg" };

		// TEXTOS GENERALES
		textosGenerales = new String[] { "Servidor FTP",
				"Servidor FTP desconectado. Por favor, reinicie \nel programa para conectarse",
				"No se ha podido acceder a la carpeta", "Volver", "return", "carpeta-Volver", "LOGIN",
				"ERROR AL INICIAR SESION", "Usuario o contraseña incorrectos, vuelva a intentarlo", "usuarios",
				"Ver mensaje", "Actualizar", "Correo" };

		// CATEGORIAS
		categorias = new String[] { "admin", "null", "Responsable" };
		rutasUsers = new String[] { "/GaleriaDeArte/Responsables/", "/Marchantes/" };

		// NOMBRE COLUMNAS MAIL
		nombresColumns = new String[] { "Destinatario", "Asunto", "Fecha" };

		// PROPIEDADES DEL MAIL
		propiedadesMail = new String[] { "mail.pop3.starttls.enable", "false", "mail.pop3.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory", "mail.pop3.socketFactory.fallback", "mail.pop3.port", "995",
				"mail.pop3.socketFactory.port", "pop3", };

		// INFO MAIL
		infoMail = new String[] { "INFO PERSONA\n", "Remitente: ", "Asunto: ", "Fecha: ", "Mensaje: " };
	}
	/**
	 * @return Array de TextoVentanaPrincipal
	 */
	public String[] getTextoVentanaPrincipal() {
		return textoVentanaPrincipal;
	}
	/**
	 * @return Array de TextoPanelesVentanaPrincipal
	 */
	public String[] getTextoPanelesVentanaPrincipal() {
		return textoPanelesVentanaPrincipal;
	}
	/**
	 * @return Array de TextoOpcionesMenu
	 */
	public String[] getTextoOpcionesMenu() {
		return textoOpcionesMenu;
	}
	/**
	 * @return Array de TextoOpcionesMenuImages
	 */
	public String[] getTextoOpcionesMenuImages() {
		return textoOpcionesMenuImages;
	}
	/**
	 * @return Array de TipoOpciones
	 */
	public String[] getTipoOpciones() {
		return tipoOpciones;
	}
	/**
	 * @return Array de TextoPanelesOpciones
	 */
	public String[] getTextoPanelesOpciones() {
		return textoPanelesOpciones;
	}
	/**
	 * @return Array de TextoLogos
	 */
	public String[] getTextoLogos() {
		return textoLogos;
	}
	/**
	 * @return String de TextoOpciones
	 */
	public String getTextoOpciones() {
		return textoOpciones;
	}
	/**
	 * @return Array de TextoDatosConexion
	 */
	public String[] getTextoDatosConexion() {
		return textoDatosConexion;
	}
	/**
	 * @return Array de TextoOpcionesInfo
	 */
	public String[] getTextoOpcionesInfo() {
		return textoOpcionesInfo;
	}
	/**
	 * @return Array de TextoOpcionesInfoImages
	 */
	public String[] getTextoOpcionesInfoImages() {
		return textoOpcionesInfoImages;
	}
	/**
	 * @return Array de TextoDatosInfo
	 */
	public String[] getTextoDatosInfo() {
		return textoDatosInfo;
	}
	/**
	 * @return Array de TextoOpcionesConfig
	 */
	public String[] getTextoOpcionesConfig() {
		return textoOpcionesConfig;
	}
	/**
	 * @return Array de TextoOpcionesConfigImages
	 */
	public String[] getTextoOpcionesConfigImages() {
		return textoOpcionesConfigImages;
	}
	/**
	 * @return Array de TextoDatosConfig
	 */
	public String[] getTextoDatosConfig() {
		return textoDatosConfig;
	}
	/**
	 * @return Array de TextoOpcionesAdmin
	 */
	public String[] getTextoOpcionesAdmin() {
		return textoOpcionesAdmin;
	}
	/**
	 * @return Array de TextoOpcionesAdminImages
	 */
	public String[] getTextoOpcionesAdminImages() {
		return textoOpcionesAdminImages;
	}
	/**
	 * @return String de Archivo
	 */
	public String getArchivo() {
		return archivo;
	}
	/**
	 * Metodo que guarda el nombre del archivo
	 * @param archivo - Tipo String - nombre del archivo
	 */
	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}
	/**
	 * @return String de Ip
	 */
	public String getIp() {
		return ip;
	}
	/**
	 * Metodo que guarda la direccion ip
	 * @param ip - Tipo String - Direccion ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
	 * @return String de Password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * Metodo que guarda la contraseña
	 * @param password - Tipo String - Contraseña del usuario
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return String de NombreTable
	 */
	public String getNombreTable() {
		return nombreTable;
	}
	/**
	 * Metodo que guarda el nombre de la tabla
	 * @param nombreTable - Tipo String - Nombre de la tabla
	 */
	public void setNombreTable(String nombreTable) {
		this.nombreTable = nombreTable;
	}
	/**
	 * @return String de ConsultaParaObtenerTodosLosDatos
	 */
	public String getConsultaParaObtenerTodosLosDatos() {
		return consultaParaObtenerTodosLosDatos;
	}
	/**
	 * Metodo que guarda la consulta para obtener todos los datos
	 * @param consultaParaObtenerTodosLosDatos - Tipo String - consulta para obtener todos los datos
	 */
	public void setConsultaParaObtenerTodosLosDatos(String consultaParaObtenerTodosLosDatos) {
		this.consultaParaObtenerTodosLosDatos = consultaParaObtenerTodosLosDatos;
	}
	/**
	 * @return String de NombreColumna
	 */
	public String getNombreColumna() {
		return nombreColumna;
	}
	/**
	 * Metodo que guarda el nombre de la columna
	 * @param nombreColumna - Tipo String - Nombre de la columna
	 */
	public void setNombreColumna(String nombreColumna) {
		this.nombreColumna = nombreColumna;
	}
	/**
	 * @return Array de RutasIconos
	 */
	public String[] getRutasIconos() {
		return rutasIconos;
	}
	/**
	 * Metodo que guarda el la ruta de los iconos
	 * @param rutasIconos - Tipo String[] - Rutas de los Iconos
	 */
	public void setRutasIconos(String[] rutasIconos) {
		this.rutasIconos = rutasIconos;
	}
	/**
	 * @return String de ServidorFTP
	 */
	public String getServidorFTP() {
		return servidorFTP;
	}
	/**
	 * Metodo que guarda el Servidor FTP
	 * @param servidorFTP - Tipo String - Servidor FTP
	 */
	public void setServidorFTP(String servidorFTP) {
		this.servidorFTP = servidorFTP;
	}
	/**
	 * @return String de UsuarioFTP
	 */
	public String getUsuarioFTP() {
		return usuarioFTP;
	}
	/**
	 * Metodo que guarda el Usuario FTP
	 * @param usuarioFTP - Tipo String - Usuario FTP
	 */
	public void setUsuarioFTP(String usuarioFTP) {
		this.usuarioFTP = usuarioFTP;
	}
	/**
	 * @return String de ContrasenaFTP
	 */
	public String getContrasenaFTP() {
		return contrasenaFTP;
	}
	/**
	 * Metodo que guarda la Contrasena FTP
	 * @param contrasenaFTP - Tipo String - Contrasena FTP
	 */
	public void setContrasenaFTP(String contrasenaFTP) {
		this.contrasenaFTP = contrasenaFTP;
	}
	/**
	 * @return String de EspacioEnBLanco
	 */
	public String getEspacioEnBLanco() {
		return espacioEnBLanco;
	}
	/**
	 * Metodo que guarda un espacio en bLanco
	 * @param espacioEnBLanco - Tipo String - Espacio En BLanco
	 */
	public void setEspacioEnBLanco(String espacioEnBLanco) {
		this.espacioEnBLanco = espacioEnBLanco;
	}
	/**
	 * @return String de Nada
	 */
	public String getNada() {
		return nada;
	}
	/**
	 * Metodo que guarda ¿nada?
	 * @param nada - Tipo String - Nada
	 */
	public void setNada(String nada) {
		this.nada = nada;
	}
	/**
	 * @return String de GuionMedio
	 */
	public String getGuionMedio() {
		return guionMedio;
	}
	/**
	 * Metodo que guarda un guion medio
	 * @param guionMedio - Tipo String - Guion Medio
	 */
	public void setGuionMedio(String guionMedio) {
		this.guionMedio = guionMedio;
	}
	/**
	 * @return String de GuionBajo
	 */
	public String getGuionBajo() {
		return guionBajo;
	}
	/**
	 * Metodo que guarda un guion bajo
	 * @param guionBajo - Tipo String - Guion Bajo
	 */
	public void setGuionBajo(String guionBajo) {
		this.guionBajo = guionBajo;
	}
	/**
	 * @return Array de TextosCrearCarpetas
	 */
	public String[] getTextosCrearCarpetas() {
		return textosCrearCarpetas;
	}
	/**
	 * Metodo que guarda textos para crear carpetas
	 * @param textosCrearCarpetas - Tipo String[] - Textos para Crear Carpetas
	 */
	public void setTextosCrearCarpetas(String[] textosCrearCarpetas) {
		this.textosCrearCarpetas = textosCrearCarpetas;
	}
	/**
	 * @return Array de TextosDescargarArchivos
	 */
	public String[] getTextosDescargarArchivos() {
		return textosDescargarArchivos;
	}
	/**
	 * Metodo que guarda textos para descargar archivos
	 * @param textosDescargarArchivos - Tipo String[] - Textos para Descargar Archivos
	 */
	public void setTextosDescargarArchivos(String[] textosDescargarArchivos) {
		this.textosDescargarArchivos = textosDescargarArchivos;
	}
	/**
	 * @return String de CarpetaGuion
	 */
	public String getCarpetaGuion() {
		return carpetaGuion;
	}
	/**
	 * Metodo que guarda Carpeta + Guion
	 * @param carpetaGuion - Tipo String - Carpeta Guion
	 */
	public void setCarpetaGuion(String carpetaGuion) {
		this.carpetaGuion = carpetaGuion;
	}
	/**
	 * @return String de FicheroGuion
	 */
	public String getFicheroGuion() {
		return ficheroGuion;
	}
	/**
	 * Metodo que guarda Fichero + Guion
	 * @param ficheroGuion - Tipo String - Fichero Guion
	 */
	public void setFicheroGuion(String ficheroGuion) {
		this.ficheroGuion = ficheroGuion;
	}
	/**
	 * @return String de SignoPregunta
	 */
	public String getSignoPregunta() {
		return signoPregunta;
	}
	/**
	 * Metodo que guarda un signo de pregunta
	 * @param signoPregunta - Tipo String - Signo de interrogación
	 */
	public void setSignoPregunta(String signoPregunta) {
		this.signoPregunta = signoPregunta;
	}
	/**
	 * @return String de DobleBarraInvertida
	 */
	public String getDobleBarraInvertida() {
		return dobleBarraInvertida;
	}
	/**
	 * Metodo que guarda una doble barra invertida
	 * @param dobleBarraInvertida - Tipo String - Doble Barra Invertida
	 */
	public void setDobleBarraInvertida(String dobleBarraInvertida) {
		this.dobleBarraInvertida = dobleBarraInvertida;
	}
	/**
	 * @return Array de textosEliminarArchivos
	 */
	public String[] getTextosEliminarArchivos() {
		return textosEliminarArchivos;
	}
	/**
	 * Metodo que guarda textos para eliminar Archivos
	 * @param textosEliminarArchivos - Tipo String[] - Textos para Eliminar Archivos
	 */
	public void setTextosEliminarArchivos(String[] textosEliminarArchivos) {
		this.textosEliminarArchivos = textosEliminarArchivos;
	}
	/**
	 * @return Array de textosEliminarCarpetas
	 */
	public String[] getTextosEliminarCarpetas() {
		return textosEliminarCarpetas;
	}
	/**
	 * Metodo que guarda textos para eliminar Carpetas
	 * @param textosEliminarCarpetas - Tipo String[] - Textos para Eliminar Carpetos
	 */
	public void setTextosEliminarCarpetas(String[] textosEliminarCarpetas) {
		this.textosEliminarCarpetas = textosEliminarCarpetas;
	}
	/**
	 * @return String de carpetaNombre
	 */
	public String getCarpetaNombre() {
		return carpetaNombre;
	}
	/**
	 * Metodo que guarda el Nombre de Carpeta
	 * @param textosEliminarCarpetas - Tipo String - Textos para Eliminar Carpetos
	 */
	public void setCarpetaNombre(String carpetaNombre) {
		this.carpetaNombre = carpetaNombre;
	}
	/**
	 * @return String de barra
	 */
	public String getBarra() {
		return barra;
	}
	/**
	 * Metodo que guarda una barra
	 * @param barra - Tipo String - Barra
	 */
	public void setBarra(String barra) {
		this.barra = barra;
	}
	/**
	 * @return String de punto
	 */
	public String getPunto() {
		return punto;
	}
	/**
	 * Metodo que guarda un punto
	 * @param punto - Tipo String - Punto
	 */
	public void setPunto(String punto) {
		this.punto = punto;
	}
	/**
	 * @return String de comillaSimple
	 */
	public String getComillaSimple() {
		return comillaSimple;
	}
	/**
	 * Metodo que guarda una comilla Simple
	 * @param comillaSimple - Tipo String - Comilla Simple
	 */
	public void setComillaSimple(String comillaSimple) {
		this.comillaSimple = comillaSimple;
	}
	/**
	 * @return String de consultaCorreoPassword
	 */
	public String getConsultaCorreoPassword() {
		return consultaCorreoPassword;
	}
	/**
	 * Metodo que guarda una consulta para sacar la contraseña del correo
	 * @param consultaCorreoPassword - Tipo String - Consulta Correo Password
	 */
	public void setConsultaCorreoPassword(String consultaCorreoPassword) {
		this.consultaCorreoPassword = consultaCorreoPassword;
	}
	/**
	 * @return String de textosSmtp
	 */
	public String[] getTextosSmtp() {
		return textosSmtp;
	}
	/**
	 * Metodo que guarda Textos para el Smtp
	 * @param textosSmtp - Tipo String[] - Textos de Smtp
	 */
	public void setTextosSmtp(String[] textosSmtp) {
		this.textosSmtp = textosSmtp;
	}
	/**
	 * @return String de nombreFichero
	 */
	public String getNombreFichero() {
		return nombreFichero;
	}
	/**
	 * Metodo que guarda el Nombre del Fichero
	 * @param nombreFichero - Tipo String - Nombre Fichero
	 */
	public void setNombreFichero(String nombreFichero) {
		this.nombreFichero = nombreFichero;
	}
	/**
	 * @return String de nombreEnlace
	 */
	public String getNombreEnlace() {
		return nombreEnlace;
	}
	/**
	 * Metodo que guarda el Nombre del Enlace
	 * @param nombreEnlace - Tipo String - nombre Enlace
	 */
	public void setNombreEnlace(String nombreEnlace) {
		this.nombreEnlace = nombreEnlace;
	}
	/**
	 * @return String de puntoDoble
	 */
	public String getPuntoDoble() {
		return puntoDoble;
	}
	/**
	 * Metodo que guarda un punto doble
	 * @param puntoDoble - Tipo String - Punto Doble
	 */
	public void setPuntoDoble(String puntoDoble) {
		this.puntoDoble = puntoDoble;
	}
	/**
	 * @return String de Extensiones
	 */
	public String[] getExtensiones() {
		return extensiones;
	}
	/**
	 * Metodo que guarda las extensiones de los archivos
	 * @param extensiones - Tipo String[] - extensiones
	 */
	public void setExtensiones(String[] extensiones) {
		this.extensiones = extensiones;
	}
	/**
	 * @return Array de Formatos
	 */
	public String[] getFormatos() {
		return formatos;
	}
	/**
	 * Metodo que guarda los formatos
	 * @param formatos - Tipo String[] - formatos
	 */
	public void setFormatos(String[] formatos) {
		this.formatos = formatos;
	}
	/**
	 * @return Array de Textos Generales
	 */
	public String[] getTextosGenerales() {
		return textosGenerales;
	}
	/**
	 * Metodo que guarda textos Generales
	 * @param textosGenerales - Tipo String[] - Textos Generales
	 */
	public void setTextosGenerales(String[] textosGenerales) {
		this.textosGenerales = textosGenerales;
	}
	/**
	 * @return Array de Categorias
	 */
	public String[] getCategorias() {
		return categorias;
	}
	/**
	 * Metodo que guarda categorias
	 * @param categorias - Tipo String[] - Categorias
	 */
	public void setCategorias(String[] categorias) {
		this.categorias = categorias;
	}
	/**
	 * @return Array de rutasUsers
	 */
	public String[] getRutasUsers() {
		return rutasUsers;
	}
	/**
	 * Metodo que guarda las rutas de los usuarios
	 * @param rutasUsers - Tipo String[] - Rutas de Usuarios
	 */
	public void setRutasUsers(String[] rutasUsers) {
		this.rutasUsers = rutasUsers;
	}
	/**
	 * @return String de consultaResponsableAsignado
	 */
	public String getConsultaResponsableAsignado() {
		return consultaResponsableAsignado;
	}
	/**
	 * Metodo que guarda una consulta para saber el responsable asignado
	 * @param consultaResponsableAsignado - Tipo String - Consulta de Responsable Asignado
	 */
	public void setConsultaResponsableAsignado(String consultaResponsableAsignado) {
		this.consultaResponsableAsignado = consultaResponsableAsignado;
	}
	/**
	 * @return String de consultaPasswordEncriptado01
	 */
	public String getConsultaPasswordEncriptado01() {
		return consultaPasswordEncriptado01;
	}
	/**
	 * Metodo que guarda una parte de la consulta para saber la contraseña encriptada
	 * @param consultaPasswordEncriptado01 - Tipo String - Consulta Password Encriptado Parte 1
	 */
	public void setConsultaPasswordEncriptado01(String consultaPasswordEncriptado01) {
		this.consultaPasswordEncriptado01 = consultaPasswordEncriptado01;
	}
	/**
	 * @return String de consultaPasswordEncriptado02
	 */
	public String getConsultaPasswordEncriptado02() {
		return consultaPasswordEncriptado02;
	}
	/**
	 * Metodo que guarda una parte de la consulta para saber la contraseña encriptada
	 * @param consultaPasswordEncriptado02 - Tipo String - Consulta Password Encriptado Parte 2
	 */
	public void setConsultaPasswordEncriptado02(String consultaPasswordEncriptado02) {
		this.consultaPasswordEncriptado02 = consultaPasswordEncriptado02;
	}
	/**
	 * @return Array de nombresColumns
	 */
	public String[] getNombresColumns() {
		return nombresColumns;
	}
	/**
	 * Metodo que guarda el Nombres de las Columnas
	 * @param nombresColumns - Tipo String[] - Nombres de las Columnas
	 */
	public void setNombresColumns(String[] nombresColumns) {
		this.nombresColumns = nombresColumns;
	}
	/**
	 * @return String de signoMenor
	 */
	public String getSignoMenor() {
		return signoMenor;
	}
	/**
	 * Metodo que guarda un signo Menor
	 * @param signoMenor - Tipo String - Signo Menor
	 */
	public void setSignoMenor(String signoMenor) {
		this.signoMenor = signoMenor;
	}
	/**
	 * @return String de signoMayor
	 */
	public String getSignoMayor() {
		return signoMayor;
	}
	/**
	 * Metodo que guarda un signo Menor
	 * @param signoMayor - Tipo String - Signo Mayor
	 */
	public void setSignoMayor(String signoMayor) {
		this.signoMayor = signoMayor;
	}
	/**
	 * @return Array de propiedadesMail
	 */
	public String[] getPropiedadesMail() {
		return propiedadesMail;
	}
	/**
	 * Metodo que guarda un signo Menor
	 * @param propiedadesMail - Tipo String[] - Propiedades del Mail
	 */
	public void setPropiedadesMail(String[] propiedadesMail) {
		this.propiedadesMail = propiedadesMail;
	}
	/**
	 * @return Array de infoMail
	 */
	public String[] getInfoMail() {
		return infoMail;
	}
	/**
	 * Metodo que guarda la info del Mail
	 * @param infoMail - Tipo String[] - Info del Mail
	 */
	public void setInfoMail(String[] infoMail) {
		this.infoMail = infoMail;
	}

}
