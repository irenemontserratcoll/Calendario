package ventanas;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;

public class VentanaUsuario extends JFrame{
	
	public VentanaUsuario(){
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Ventana Usuario");
		setSize(700,700);
		
		JPanel principal = new JPanel();
		JLabel textoUsuario = new JLabel("Usuario");
		principal.add(textoUsuario);
		JTextField nombreUsuario = new JTextField(20);
		principal.add(nombreUsuario);
		
		JLabel textoContraseña = new JLabel("Contraseña");
		JTextField valorContraseña = new JTextField(20);
		
		JButton login = new JButton("login");
		
		login.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Boton A click!");
            }
        });
		
		
		principal.add(textoContraseña);
		principal.add(valorContraseña);
		
		principal.add(login);
		
		

		
		add(principal);
		setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new VentanaUsuario();
			}
		});
	}
}
