/**
 * 
 * Clase Hilo
 * 
 * Permite realizar la ejecución de una operación en 
 * segundo plano para la actualización automática del
 * contenido de la bandeja de entrada de Mail
 * 
 * @author Javier Jimenez Torres
 * @date 09/12/2022
 * @version 01
 */

package controlador;

public class Hilo extends Thread {
	
	/**
	 * controlMail - tipo ControladorMailPrincipal - controlador de mail
	 */
	private ControladorMailPrincipal controlMail;
	
	/**
	 * contador - tipo int - contador de tiempo
	 */
	private int contador;
	
	/**
	 * tiempoRefresco - tipo int - tiempo de refresco
	 */
	private int tiempoRefresco;

	/**
	 * Constructor por defecto del hilo de mail
	 * @param controlMail - tipo ControladorMailPrincipal - controlador de mail
	 * @param tiempoRefresco - tipo int - tiempo de refresco
	 */
	public Hilo(ControladorMailPrincipal controlMail, int tiempoRefresco) {
		this.controlMail = controlMail;
		this.setName("hilo1");
		this.tiempoRefresco = tiempoRefresco;
		this.contador = 0;

	}

	/**
	 * Cuenta tiempo y actualiza valor
	 */
	@Override
	public void run() {
		while (true) {
			try {
				controlMail.controladorGmail();
				while (contador < tiempoRefresco) {
					sleep(1000);
					contador += 1000;
				}
				contador = 0;

			} catch (Exception e) {} 
		}
	}
	
	/**
	 * Insertar contador de tiempo
	 * @param contador - tipo int - contador de tiempo
	 */
	public void setContador(int contador) {
		this.contador = contador;
	}
	
	/**
	 * Obtener contador de tiempo
	 * @return contador - tipo int - contador de tiempo
	 */
	public int getContador() {
		return contador;
	}
}
