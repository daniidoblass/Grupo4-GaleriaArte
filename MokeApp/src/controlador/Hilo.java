package controlador;

import javax.mail.MessagingException;

public class Hilo extends Thread{
	private ControladorMailPrincipal controlMail;
	
	public Hilo(ControladorMailPrincipal controlMail) {
		this.controlMail = controlMail;
		this.setName("hilo1");

	}

	@Override
	public void run() {
		while(true) {
			try {
				controlMail.controladorGmail();
				sleep(6000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
