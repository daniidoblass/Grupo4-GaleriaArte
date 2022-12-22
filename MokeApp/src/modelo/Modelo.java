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
	private String comillaSimple = "'";

	// Consulta para obtener el correo y password de quien se ha registrado el app
	private String consultaCorreoPassword = "SELECT correo,contraseniaGmail FROM `usuarios` WHERE nombre like '";

	// Consulta para obtener el responsable asignado.
	private String consultaResponsableAsignado = "SELECT ResponsableAsignado FROM `usuarios` WHERE nombre LIKE '";

	// Consulta para obtener la password encriptado.
	private String consultaPasswordEncriptado01 = "SELECT categoria FROM `usuarios` WHERE password like PASSWORD('";
	private String consultaPasswordEncriptado02 = "') AND nombre like '";

	// RUTAS
	private String[] rutasIconos;

	// FTP
	private String servidorFTP = "ftpupload.net";
	private String usuarioFTP = "b3_33188433";
	private String contrasenaFTP = "2wknvh9m";
	private String nombreFichero = "fichero";
	private String nombreEnlace = "enlace";

	// TEXTOS PARA CREAR UNA CARPETA
	private String espacioEnBLanco = " ";
	private String nada = "";
	private String guionMedio = "-";
	private String guionBajo = "_";
	private String[] textosCrearCarpetas;

	// TEXTOS DESCARGAR ARCHIVOS
	private String[] textosDescargarArchivos;
	private String carpetaGuion = "carpeta-";
	private String ficheroGuion = "fichero-";
	private String signoPregunta = "?";
	private String dobleBarraInvertida = "\\";

	// TEXTOS ELIMINAR ARCHIVOS
	private String[] textosEliminarArchivos;

	// TEXTOS ELIMINAR CARPETAS
	private String carpetaNombre = "carpeta";
	private String barra = "/";
	private String[] textosEliminarCarpetas;
	private String punto = ".";
	private String puntoDoble = "..";

	// SERVIDOR SMTP DE GOOGLE
	private String[] textosSmtp;

	// EXTENSIONES
	private String[] formatos;
	private String[] extensiones;

	// MENSAJES GENERALES
	private String[] textosGenerales;
	private String signoMenor = "<";
	private String signoMayor = ">";

	// CATEGORIAS
	private String[] categorias;
	private String[] rutasUsers;

	// NOMBRE COLUMNAS MAIL
	private String[] nombresColumns;

	// PROPIEDADES MAIL
	private String[] propiedadesMail;
	private String[] infoMail;

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

	public String[] getTextoVentanaPrincipal() {
		return textoVentanaPrincipal;
	}

	public String[] getTextoPanelesVentanaPrincipal() {
		return textoPanelesVentanaPrincipal;
	}

	public String[] getTextoOpcionesMenu() {
		return textoOpcionesMenu;
	}

	public String[] getTextoOpcionesMenuImages() {
		return textoOpcionesMenuImages;
	}

	public String[] getTipoOpciones() {
		return tipoOpciones;
	}

	public String[] getTextoPanelesOpciones() {
		return textoPanelesOpciones;
	}

	public String[] getTextoLogos() {
		return textoLogos;
	}

	public String getTextoOpciones() {
		return textoOpciones;
	}

	public String[] getTextoDatosConexion() {
		return textoDatosConexion;
	}

	public String[] getTextoOpcionesInfo() {
		return textoOpcionesInfo;
	}

	public String[] getTextoOpcionesInfoImages() {
		return textoOpcionesInfoImages;
	}

	public String[] getTextoDatosInfo() {
		return textoDatosInfo;
	}

	public String[] getTextoOpcionesConfig() {
		return textoOpcionesConfig;
	}

	public String[] getTextoOpcionesConfigImages() {
		return textoOpcionesConfigImages;
	}

	public String[] getTextoDatosConfig() {
		return textoDatosConfig;
	}

	public String[] getTextoOpcionesAdmin() {
		return textoOpcionesAdmin;
	}

	public String[] getTextoOpcionesAdminImages() {
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

	public String[] getTextosDescargarArchivos() {
		return textosDescargarArchivos;
	}

	public void setTextosDescargarArchivos(String[] textosDescargarArchivos) {
		this.textosDescargarArchivos = textosDescargarArchivos;
	}

	public String getCarpetaGuion() {
		return carpetaGuion;
	}

	public void setCarpetaGuion(String carpetaGuion) {
		this.carpetaGuion = carpetaGuion;
	}

	public String getFicheroGuion() {
		return ficheroGuion;
	}

	public void setFicheroGuion(String ficheroGuion) {
		this.ficheroGuion = ficheroGuion;
	}

	public String getSignoPregunta() {
		return signoPregunta;
	}

	public void setSignoPregunta(String signoPregunta) {
		this.signoPregunta = signoPregunta;
	}

	public String getDobleBarraInvertida() {
		return dobleBarraInvertida;
	}

	public void setDobleBarraInvertida(String dobleBarraInvertida) {
		this.dobleBarraInvertida = dobleBarraInvertida;
	}

	public String[] getTextosEliminarArchivos() {
		return textosEliminarArchivos;
	}

	public void setTextosEliminarArchivos(String[] textosEliminarArchivos) {
		this.textosEliminarArchivos = textosEliminarArchivos;
	}

	public String[] getTextosEliminarCarpetas() {
		return textosEliminarCarpetas;
	}

	public void setTextosEliminarCarpetas(String[] textosEliminarCarpetas) {
		this.textosEliminarCarpetas = textosEliminarCarpetas;
	}

	public String getCarpetaNombre() {
		return carpetaNombre;
	}

	public void setCarpetaNombre(String carpetaNombre) {
		this.carpetaNombre = carpetaNombre;
	}

	public String getBarra() {
		return barra;
	}

	public void setBarra(String barra) {
		this.barra = barra;
	}

	public String getPunto() {
		return punto;
	}

	public void setPunto(String punto) {
		this.punto = punto;
	}

	public String getComillaSimple() {
		return comillaSimple;
	}

	public void setComillaSimple(String comillaSimple) {
		this.comillaSimple = comillaSimple;
	}

	public String getConsultaCorreoPassword() {
		return consultaCorreoPassword;
	}

	public void setConsultaCorreoPassword(String consultaCorreoPassword) {
		this.consultaCorreoPassword = consultaCorreoPassword;
	}

	public String[] getTextosSmtp() {
		return textosSmtp;
	}

	public void setTextosSmtp(String[] textosSmtp) {
		this.textosSmtp = textosSmtp;
	}

	public String getNombreFichero() {
		return nombreFichero;
	}

	public void setNombreFichero(String nombreFichero) {
		this.nombreFichero = nombreFichero;
	}

	public String getNombreEnlace() {
		return nombreEnlace;
	}

	public void setNombreEnlace(String nombreEnlace) {
		this.nombreEnlace = nombreEnlace;
	}

	public String getPuntoDoble() {
		return puntoDoble;
	}

	public void setPuntoDoble(String puntoDoble) {
		this.puntoDoble = puntoDoble;
	}

	public String[] getExtensiones() {
		return extensiones;
	}

	public void setExtensiones(String[] extensiones) {
		this.extensiones = extensiones;
	}

	public String[] getFormatos() {
		return formatos;
	}

	public void setFormatos(String[] formatos) {
		this.formatos = formatos;
	}

	public String[] getTextosGenerales() {
		return textosGenerales;
	}

	public void setTextosGenerales(String[] textosGenerales) {
		this.textosGenerales = textosGenerales;
	}

	public String[] getCategorias() {
		return categorias;
	}

	public void setCategorias(String[] categorias) {
		this.categorias = categorias;
	}

	public String[] getRutasUsers() {
		return rutasUsers;
	}

	public void setRutasUsers(String[] rutasUsers) {
		this.rutasUsers = rutasUsers;
	}

	public String getConsultaResponsableAsignado() {
		return consultaResponsableAsignado;
	}

	public void setConsultaResponsableAsignado(String consultaResponsableAsignado) {
		this.consultaResponsableAsignado = consultaResponsableAsignado;
	}

	public String getConsultaPasswordEncriptado01() {
		return consultaPasswordEncriptado01;
	}

	public void setConsultaPasswordEncriptado01(String consultaPasswordEncriptado01) {
		this.consultaPasswordEncriptado01 = consultaPasswordEncriptado01;
	}

	public String getConsultaPasswordEncriptado02() {
		return consultaPasswordEncriptado02;
	}

	public void setConsultaPasswordEncriptado02(String consultaPasswordEncriptado02) {
		this.consultaPasswordEncriptado02 = consultaPasswordEncriptado02;
	}

	public String[] getNombresColumns() {
		return nombresColumns;
	}

	public void setNombresColumns(String[] nombresColumns) {
		this.nombresColumns = nombresColumns;
	}

	public String getSignoMenor() {
		return signoMenor;
	}

	public void setSignoMenor(String signoMenor) {
		this.signoMenor = signoMenor;
	}

	public String getSignoMayor() {
		return signoMayor;
	}

	public void setSignoMayor(String signoMayor) {
		this.signoMayor = signoMayor;
	}

	public String[] getPropiedadesMail() {
		return propiedadesMail;
	}

	public void setPropiedadesMail(String[] propiedadesMail) {
		this.propiedadesMail = propiedadesMail;
	}

	public String[] getInfoMail() {
		return infoMail;
	}

	public void setInfoMail(String[] infoMail) {
		this.infoMail = infoMail;
	}

}
