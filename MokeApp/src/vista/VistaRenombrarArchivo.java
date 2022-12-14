package vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaRenombrarArchivo extends JFrame implements ActionListener {

	public VistaRenombrarArchivo() {
		setTitle("Ventana principal");
		setResizable(false);
		setSize(300, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JButton btnPrueba = new JButton("Prueba");
		btnPrueba.addActionListener(this);
		add(btnPrueba);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String nombreArchivo = JOptionPane.showInputDialog(null, "Introduce nombre del archivo");
//        cliente.changeWorkingDirectory(dirSel);
		if (nombreArchivo != null) {
			if (true /* cliente.rename(archivoSel, nombreArchivo) */) {
//                    JOptionPane.showMessageDialog(null, "Se ha renombrado el archivo " + archivoSel.getName() + " a " + nombreArchivo);
				JOptionPane.showMessageDialog(null, "Se ha renombrado el archivo a " + nombreArchivo);
			} else {
//                    JOptionPane.showMessageDialog(null, "No se ha podido renombrar el archivo " + archivoSel.getName() + " a " + nombreArchivo);
				JOptionPane.showMessageDialog(null, "No se ha podido renombrar el archivo a " + nombreArchivo);
			}
		}
	}
}