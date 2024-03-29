package ventanas;


import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import baseDeDatos.GestorBaseDatos;
import clases.GestorEventos;
import recursividad.GeneradorContraseñas;


public class VentanaUsuario extends JFrame{

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(Logger.class.getName());
	static GestorBaseDatos baseDatosUsuarios;
	
	JPanel principal;
	JLabel textoUsuario;
	static JTextField nombreUsuario;
	JLabel textoContraseña;
	static JTextField valorContraseña;
	JButton bLogin;
	JButton bNuevoUsuario;
	JDialog crearUsuario;
	
	/**
	 * Creación de ventana usuario
	 */
	public VentanaUsuario(){
		baseDatosUsuarios=new GestorBaseDatos();
		GestorBaseDatos.iniciar();
		GestorBaseDatos.cargarPrueba();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Ventana Usuario");
		setSize(700,400);
		
		principal = new JPanel(new GridLayout(2,1));
		JPanel abajo = new JPanel(new GridLayout(4,1));
		
		JPanel usuario = new JPanel();
		usuario.setBackground(Color.WHITE);
		JPanel contraseña = new JPanel();
		contraseña.setBackground(Color.WHITE);
		JPanel login = new JPanel();
		login.setBackground(Color.WHITE);
		JPanel nuevoUsuario = new JPanel();
		nuevoUsuario.setBackground(Color.WHITE);

		
		ImageIcon icono = new ImageIcon(VentanaUsuario.class.getResource("/Logobien.jpg"));
		JLabel imagen = new JLabel(icono, JLabel.CENTER);

		textoUsuario = new JLabel("Usuario");
		usuario.add(textoUsuario);
		nombreUsuario = new JTextField(20);
		usuario.add(nombreUsuario);
		
		textoContraseña = new JLabel("Contraseña");
		contraseña.add(textoContraseña);
		valorContraseña = new JTextField(20);
		contraseña.add(valorContraseña);
		JButton botonGenerarContrasena = new JButton("Generar Contraseña");
		contraseña.add(botonGenerarContrasena);
        botonGenerarContrasena.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GeneradorContraseñas g = new GeneradorContraseñas();
				String valor = g.generate();
				valorContraseña.setText(valor);
				
			}
		});

		bLogin = new JButton("Login");
		bLogin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (comprobarBaseDatos()=="Login correcto") {
                	JOptionPane.showMessageDialog(null, "LOGIN CORRECTO","login correcto",JOptionPane.INFORMATION_MESSAGE);
                	//Creo un gestor de eventos a partir del usuario de login y de la base de datos. (Con los eventos del usuario)
                	GestorEventos gestorEventos = new GestorEventos(nombreUsuario.getText().toString(), baseDatosUsuarios);  
                	SwingUtilities.invokeLater(new Runnable() {
            			public void run() {
            				new VentanaPrincipal(gestorEventos, nombreUsuario.getText());
            			}
            		});
                } else {
                    mensajeError(comprobarBaseDatos());
                }
            }
        });
		
		bNuevoUsuario = new JButton("Nuevo Usuario");
		bNuevoUsuario.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Click boton nuevo Usuario");
                anyadirUsuario();
                
            }
        });
		
		addWindowListener( new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				//GestorBaseDatos.borrarPrueba();
				GestorBaseDatos.close();
			}
		});
		
		login.add(bLogin);
		nuevoUsuario.add(bNuevoUsuario);
		
		abajo.add(usuario);
		abajo.add(contraseña);
		abajo.add(login);
		abajo.add(nuevoUsuario);
		
		
		principal.add(imagen);
		principal.add(abajo);
				

		add(principal);
		setVisible(true);
	
	}

	/**
	 * Método para mostrar en un JOptionPane un mensaje de error
	 * @param s -> Error que quieres mostrar
	 */
	public static void mensajeError(String s) {
		JOptionPane.showMessageDialog(null, s,"Error",JOptionPane.ERROR_MESSAGE);
    }

	/**
	 * Metodo para anyadir un usuario a la base de datos. 
	 * JDialog de interaccion con el usuario
	 * Comprobación nuevo usuario correcto
	 */
	public void anyadirUsuario() {
		String bd = comprobarBaseDatos();
		if (bd.equals("Usuario no registrado")) {
			int reply = JOptionPane.showConfirmDialog(null, "¿Desea crear un nuevo usuario?", "Confirmación",
					JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				int contraAut = JOptionPane.showConfirmDialog(null, "¿Desea generar contraseña automaticamente?", "Confirmación",
						JOptionPane.YES_NO_OPTION);
				if (contraAut == JOptionPane.YES_OPTION) {
					GeneradorContraseñas c = new GeneradorContraseñas();
					String contrasenyaAut = c.generate();
					valorContraseña.setText(contrasenyaAut);		
				}
				String usuario = nombreUsuario.getText().toString();
				String contrasenya = valorContraseña.getText().toString();

				switch (baseDatosUsuarios.anyadirUsuarioBD(usuario, contrasenya)) {
				case 0:
					JOptionPane.showMessageDialog(null, "Usuario creado. Ya puedes acceder a tu nuevo calendario!",
							"USUARIO CREADO", JOptionPane.INFORMATION_MESSAGE);
					logger.info("Usuario creado correctamente");
					break;
				case 1:
					mensajeError("Campos vacíos");
					break;
				case 2:
					mensajeError("El usuario ya existe");
					break;
				case 3:
					mensajeError("Error");
					break;
				}
				;
			}

		} else if (bd.equals("Login correcto") || bd.equals("Contraseña incorrecta")) {
			JOptionPane.showMessageDialog(null, "Este usuario ya existe", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}
	
	/**
	 * Método para comprobar que el login es correcto.
	 * @return String con el resultado producido
	 */
	public static String comprobarBaseDatos() {
        String usuario = nombreUsuario.getText().toString();
        String contrasenya = valorContraseña.getText().toString();
        
        switch (baseDatosUsuarios.loginCorrecto(usuario, contrasenya)) {
        case 0:
        	return "Login correcto";
        case 1:
        	return "Contraseña incorrecta";
        case 2:
        	return "Usuario no registrado";
        case 3:
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
