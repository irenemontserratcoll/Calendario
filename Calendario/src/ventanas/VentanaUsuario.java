package ventanas;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import baseDeDatos.Usuarios;

public class VentanaUsuario extends JFrame{

	private static final long serialVersionUID = 1L;
	JPanel principal;
	JLabel textoUsuario;
	JTextField nombreUsuario;
	JLabel textoContraseña;
	JTextField valorContraseña;
	JButton bLogin;
	JButton bNuevoUsuario;
	Usuarios baseDatosUsuarios;
	

	public VentanaUsuario(){
		baseDatosUsuarios=new Usuarios();
		baseDatosUsuarios.iniciar();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Ventana Usuario");
		setSize(700,700);
		
		principal = new JPanel();
		textoUsuario = new JLabel("Usuario");
		principal.add(textoUsuario);
		nombreUsuario = new JTextField(20);
		principal.add(nombreUsuario);
		
		textoContraseña = new JLabel("Contraseña");
		valorContraseña = new JTextField(20);
		
		bLogin = new JButton("Login");
		bLogin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Boton login click!");
                String usuario = nombreUsuario.getText().toString();
                System.out.println("Usuario: "+usuario);
                String contrasenya = valorContraseña.getText().toString();
                System.out.println("Contraseña: "+contrasenya);
                
                System.out.println("Error: "+ baseDatosUsuarios.loginCorrecto(usuario, contrasenya));
                switch (baseDatosUsuarios.loginCorrecto(usuario, contrasenya)) {
                case 0:
                	System.out.println("Login correcto");
                	break;
                case 1:
                	mensajeError("Contraseña incorrecta");
                	break;
                case 2:
                	mensajeError("Usuario no registrado");
                	break;
                case 3:
                	mensajeError("Problema con la base de datos");
                	break;
                }    
            }
        });
		
		bNuevoUsuario = new JButton("Nuevo Usuario");
		bNuevoUsuario.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Boton A click!");
                
            }
        });
		
		addWindowListener( new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				baseDatosUsuarios.close();
			}
		});
		
		principal.add(textoContraseña);
		principal.add(valorContraseña);
		
		principal.add(bLogin);
		principal.add(bNuevoUsuario);
		
		

		
		add(principal);
		setVisible(true);
	}
	
	public static void mensajeError(String s) {
		JOptionPane.showMessageDialog(null, s,"Error",JOptionPane.ERROR_MESSAGE);
    }

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new VentanaUsuario();
			}
		});
	}
}
