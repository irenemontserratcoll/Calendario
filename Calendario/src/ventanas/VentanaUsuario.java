package ventanas;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.ImageIcon;
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
	ImageIcon imagen2 = new ImageIcon("/res/gato.jpg");
	JPanel panel1 = new JPanel();
	private JLabelAjustado lImagen = new JLabelAjustado( null );
	
	

	public VentanaUsuario(){
		baseDatosUsuarios=new Usuarios();
		baseDatosUsuarios.iniciar();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Ventana Usuario");
		setSize(700,400);
		
		principal = new JPanel(new GridLayout(2,1));
		
		JPanel usuario = new JPanel();
		JPanel contraseña = new JPanel();
		JPanel login = new JPanel();
		JPanel nuevoUsuario = new JPanel();
		//JLabel imagen = new JLabel();
		
		//imagen.setIcon(new ImageIcon("/res/favicon.ico"));
		lImagen.setImagen(imagen2);
		lImagen.setPreferredSize( new Dimension( 200, 200 ) );
		lImagen.paintComponent(lImagen.getGraphics());
		
		
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
		
		login.add(bLogin);
		nuevoUsuario.add(bNuevoUsuario);
		panel1.setLayout(new GridLayout(4,1));
		panel1.add(usuario);
		panel1.add(contraseña); 
		panel1.add(login);
		panel1.add(nuevoUsuario);
		principal.add(lImagen);
		principal.add(panel1);

				

		add(principal);
		setVisible(true);
	}
	
	public static void mensajeError(String s) {
		JOptionPane.showMessageDialog(null, s,"Error",JOptionPane.ERROR_MESSAGE);
    }
	
	private static class JLabelAjustado extends JLabel {

		private static final long serialVersionUID = 1L;
		private ImageIcon imagen; 
		private int tamX;
		private int tamY;
		/** Crea un jlabel que ajusta una imagen cualquiera con fondo blanco a su tamaño (a la que ajuste más de las dos escalas, horizontal o vertical)
		 * @param imagen	Imagen a visualizar en el label
		 */
		public JLabelAjustado( ImageIcon imagen ) {
			setImagen( imagen );
		}
		/** Modifica la imagen
		 * @param imagen	Nueva imagen a visualizar en el label
		 */
		public void setImagen( ImageIcon imagen ) {
			this.imagen = imagen;
			if (imagen==null) {
				tamX = 0;
				tamY = 0;
				System.out.println("Imagen nula");
			} else {
				this.tamX = imagen.getIconWidth();
				this.tamY = imagen.getIconHeight();
			}
		}
		protected void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;  // El Graphics realmente es Graphics2D
			g2.setColor( Color.WHITE );
			g2.fillRect( 0, 0, getWidth(), getHeight() );
			if (imagen!=null && tamX>0 && tamY>0) {
				g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
				g2.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);	
				double escalaX = 1.0 * getWidth() / tamX;
				double escalaY = 1.0 * getHeight() / tamY;
				double escala = escalaX;
				int x = 0;
				int y = 0;
				if (escalaY < escala) {
					escala = escalaY;
					x = (int) ((getWidth() - (tamX * escala)) / 2);
				} else {
					y = (int) ((getHeight() - (tamY * escala)) / 2);
				}
		        g2.drawImage( imagen.getImage(), x, y, (int) (tamX*escala), (int) (tamY*escala), null );
			}
		}
		
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new VentanaUsuario();
			}
		});
	}
	
}
