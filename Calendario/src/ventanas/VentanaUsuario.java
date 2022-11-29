package ventanas;



import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
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
	static JTextField nombreUsuario;
	JLabel textoContraseña;
	static JTextField valorContraseña;
	JButton bLogin;
	JButton bNuevoUsuario;
	static Usuarios baseDatosUsuarios;
	JDialog crearUsuario;
	

	public VentanaUsuario(){
		baseDatosUsuarios=new Usuarios();
		baseDatosUsuarios.iniciar();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Ventana Usuario");
		setSize(700,400);
		
		principal = new JPanel(new GridLayout(5,1));
		
		JPanel usuario = new JPanel();
		JPanel contraseña = new JPanel();
		JPanel login = new JPanel();
		JPanel nuevoUsuario = new JPanel();
		
		ImageIcon icono = new ImageIcon(VentanaUsuario.class.getResource("/Logo.jpg"));
		JLabel imagen = new JLabel(icono,JLabel.CENTER);
		
		textoUsuario = new JLabel("Usuario");
		usuario.add(textoUsuario);
		nombreUsuario = new JTextField(20);
		usuario.add(nombreUsuario);
		
		textoContraseña = new JLabel("Contraseña");
		contraseña.add(textoContraseña);
		valorContraseña = new JTextField(20);
		contraseña.add(valorContraseña);
        

		bLogin = new JButton("Login");
		bLogin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Boton login click!");
                if (comprobarBaseDatos()=="Login correcto") {
                	JOptionPane.showMessageDialog(null, "LOGIN CORRECTO","login correcto",JOptionPane.INFORMATION_MESSAGE);
                } else {
                    mensajeError(comprobarBaseDatos());
                }
            }
        });
		
		bNuevoUsuario = new JButton("Nuevo Usuario");
		bNuevoUsuario.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Boton A click!");
                if (comprobarBaseDatos()!="Login correcto") {
                    JOptionPane.showConfirmDialog(null, "¿Desea crear un nuevo usuario?", "Confirmación", JOptionPane.YES_NO_OPTION);   
                }else {
            		JOptionPane.showMessageDialog(null, "Este usuario ya existe","Error",JOptionPane.ERROR_MESSAGE);
                }
                
            }
        });
		
		addWindowListener( new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				baseDatosUsuarios.close();
			}
		});
		
		login.add(bLogin);
		nuevoUsuario.add(bNuevoUsuario);
		
		principal.add(imagen);
		principal.add(usuario);
		principal.add(contraseña); 
		principal.add(login);
		principal.add(nuevoUsuario);
				

		add(principal);
		setVisible(true);
	}
	
	public static void mensajeError(String s) {
		JOptionPane.showMessageDialog(null, s,"Error",JOptionPane.ERROR_MESSAGE);
    }
	
	public static String comprobarBaseDatos() {
        String usuario = nombreUsuario.getText().toString();
        System.out.println("Usuario: "+usuario);
        String contrasenya = valorContraseña.getText().toString();
        System.out.println("Contraseña: "+contrasenya);
        
        System.out.println("Error: "+ baseDatosUsuarios.loginCorrecto(usuario, contrasenya));
        switch (baseDatosUsuarios.loginCorrecto(usuario, contrasenya)) {
        case 0:
        	//System.out.println("Login correcto");
        	return "Login correcto";
        case 1:
        	//mensajeError("Contraseña incorrecta");
        	return "Contraseña incorrecta";
        case 2:
        	//mensajeError("Usuario no registrado");
        	return "Usuario no registrado";
        case 3:
        	//mensajeError("Problema con la base de datos");
        	return"Problema con la base de datos"; 
        }
		return null; 
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new VentanaUsuario();
			}
		});
	}
}
