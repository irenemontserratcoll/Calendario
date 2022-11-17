package baseDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import clases.Usuario;

import java.util.ArrayList;
import java.util.logging.Logger;



public class Usuarios {
	private static Logger logger = Logger.getLogger(Logger.class.getName());
	private Connection conn;
	//private Statement statement;
	private ArrayList<Usuario> listaUsuarios;
	
	public boolean iniciar(){
		// Primero cargamos los drivers
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			logger.severe("No se ha podido cargar el driver de la base de datos: "+ e.getMessage());
		}
		// Segundo paso es crear una conexión
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:Usuarios.db");
			Statement stmt = conn.createStatement();

			try (ResultSet rs = stmt.executeQuery("SELECT Usuario, Contraseña FROM usuarios")) {
				while (rs.next()) {

					String nombre = rs.getString("Usuarios");
					String contraseña = rs.getString("Contraseña");
					listaUsuarios.add(new Usuario(nombre, contraseña));

				}
			} catch (SQLException e) {
				logger.severe("No se ha podido ejecutar la sentencia SQL" + e.getMessage());
			}
			logger.info("Conexion con base de datos Usuarios.db establecida");
			//conn.close();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			logger.severe("No se ha podido establecer conexión con el sistema");
			return false;
		}
	}
	
	public void close() {
		try {
			if (conn!=null) conn.close();
			logger.info( "Cierre de base de datos");
		} catch (SQLException e) {		
			logger.severe( "Error en cierre de base de datos" );
			e.printStackTrace();
		}
	}
	
	
	/** Metodo para saber el resultado del login
	 * @param nombreUsuario
	 * @param valorContraseña
	 * @return 0 si el login es CORRECTO
	 * 		   1,2,3 si el login es INCORRECTO
	 * 	1- Contraseña incorrecta
	 *  2- Usuario no registrado
	 *  3- Error en conexión con base de datos
	 */
	public int loginCorrecto(String nombreUsuario, String valorContraseña) {
		
		Statement stmt1;
		
		try {
			stmt1 = conn.createStatement();
			ResultSet rs1 = stmt1.executeQuery("SELECT COUNT(Usuario) FROM usuarios WHERE Usuario='"+nombreUsuario+"'");
			//while (rs1.next()) {
				int numUsuarios = rs1.getInt("COUNT(Usuario)");
				System.out.println("Numeros usuarios: "+numUsuarios);
				if (numUsuarios==0) {return 2;}
			//}		
			stmt1.close();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		try {			
			Statement stmt = conn.createStatement();
			String consulta = "SELECT (Contraseña) FROM usuarios WHERE Usuario = '" + nombreUsuario + "';";
			ResultSet rs = stmt.executeQuery(consulta);
			
				while (rs.next()) {
					String contrasenaBD = rs.getString("Contraseña");
					if (contrasenaBD.equals(valorContraseña)) {
						return 0; //Correcto
					} else {
						return 1; //Contraseña incorrecta
					}
				}
				stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return 3; //Error en conexión con base de datos
		}
		return 3;
		

	}
	
	public static void main(String[] args) {
		//iniciar();
		
	}
}