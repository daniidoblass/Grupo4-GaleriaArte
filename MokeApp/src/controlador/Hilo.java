package controlador;

import javax.mail.MessagingException;

public class Hilo extends Thread {
	private ControladorMailPrincipal controlMail;
	private int contador;
	private int tiempoRefresco;

	public Hilo(ControladorMailPrincipal controlMail, int tiempoRefresco) {
		this.controlMail = controlMail;
		this.setName("hilo1");
		this.tiempoRefresco = tiempoRefresco;
		this.contador = 0;

	}

	@Override
	public void run() {
		while (true) {
			try {
				controlMail.controladorGmail();
				while (contador < tiempoRefresco) {
					sleep(1000);
					contador += 1000;
					System.out.println("Contador actual --> " + contador);
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
