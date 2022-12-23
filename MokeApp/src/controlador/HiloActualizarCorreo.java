package controlador;

import javax.mail.MessagingException;

public class HiloActualizarCorreo implements Runnable {
	private ControladorMailPrincipal controlMail;
	private int contador;
	private int tiempoRefresco;

	/**
	 * Constructor parametrizado de la clase HiloActualizarCorreo, que recibe el controlador del mail para poder
	 * actualizarlo y el tiempo de refresco que indicará cada cuantos milisegundos se actualiza 
	 * @param controlMail
	 * @param tiempoRefresco
	 */
	public HiloActualizarCorreo(ControladorMailPrincipal controlMail, int tiempoRefresco) {
		this.controlMail = controlMail;
		this.tiempoRefresco = tiempoRefresco;
		this.contador = 0;

	}

	/**
	 * Método que se ejecutará al iniciar el hilo y que actualizará el email cada 60 segundos
	 */
	@Override
	public void run() {
		while (true) {
			try {
				controlMail.controladorGmail();
				while (contador < tiempoRefresco) {
					Thread.sleep(1000);
					System.out.println("Contador --> " + contador);
					contador += 1000;
				}
				contador = 0;

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void setContador(int contador) {
		this.contador = contador;
	}
	
	public int getContador() {
		return contador;
	}
}
