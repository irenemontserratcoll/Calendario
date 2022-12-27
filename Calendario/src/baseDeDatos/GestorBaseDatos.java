package baseDeDatos;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class GestorBaseDatos {
	private static Logger logger = Logger.getLogger(Logger.class.getName());
	private static Connection conn;
	
	public static boolean iniciar(){
		//INICIO LA BASE DE DATOS
		
		// Primero cargamos los drivers
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			logger.severe("No se ha podido cargar el driver de la base de datos: "+ e.getMessage());
		}
		// Segundo paso es crear una conexión
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:CalendarioBD.db");
//			Statement stmt = conn.createStatement();

//			try (ResultSet rs = stmt.executeQuery("SELECT Usuario, Contraseña FROM usuarios")) {
//				while (rs.next()) {
//
//					String nombre = rs.getString("Usuarios");
//					String contraseña = rs.getString("Contraseña");
//					listaUsuarios.add(new Usuario(nombre, contraseña));
//
//				}
//			} catch (SQLException e) {
//				logger.severe("No se ha podido ejecutar la sentencia SQL" + e.getMessage());
//			}
			logger.info("Conexion con base de datos Calendario.BD establecida");
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			logger.severe("No se ha podido establecer conexión con el sistema");
			return false;
		}
	}
	
	public static void close() {
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
				//System.out.println("Numeros usuarios: "+numUsuarios);
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
	
	/**Metodo para añadir un nuevo usuario en la base de datos
	 * @param nombreUsuario
	 * @param valorContraseña
	 * 
	 * Devuelve:
	 * 0- Insercción correcta
	 * 1- Campos vacios
	 * 2- Usuario ya existe en la base de datos
	 * 3- Error
	 */
	public int anyadirUsuarioBD(String nombreUsuario, String valorContraseña) {
		// CONTROL DE ERRORES
		if (nombreUsuario.isEmpty() || valorContraseña.isEmpty()) {
			return 1; 
		} else if (loginCorrecto(nombreUsuario, " ") == 1) {
			return 2;
		} else {
		// INSERTAR
			try {
				PreparedStatement insertaUsuario = conn
						.prepareStatement("INSERT INTO Usuarios (Usuario, Contraseña) VALUES (?, ?);)");
				insertaUsuario.setString(1, nombreUsuario);
				insertaUsuario.setString(2, valorContraseña);
				insertaUsuario.executeUpdate();
				return 0;
			} catch (SQLException e) {
				e.printStackTrace();
				return 3;
			}
		}
	}
	
	
	/**Metodo para comprobar si una categoria existe.
	 * @param nombreUsuario
	 * @param nombreCategoria
	 * @param color
	 * @return
	 * Devuelve un 0 si encuentra la categoria, 
	 * devuelve un 1 si no existe,
	 * devuelve un 2 si es un error.
	 */
	public int buscarCategoria ( String nombreUsuario, String nombreCategoria, Color color) {
		try {
			Statement stmt = conn.createStatement();
			String consulta = "SELECT (Nombre) FROM categorias WHERE Usuario = '" + nombreUsuario + "';";
			ResultSet rs = stmt.executeQuery(consulta);
			while (rs.next()) {
				String categoria = rs.getString("Nombre");
				if (categoria.equals(nombreCategoria) ) {
					return 0;
				}else {
					return 1;
				}
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return 2;
		}
		return 3;		
	}
	
	/**Añade en la base de datos una categoria nueva.
	 * 
	 * @param nombreUsuario
	 * @param nombreCategoria
	 * @param color
	 * @return
	 */
	public void anyadirCategoria(String nombreUsuario, String nombreCategoria, Color color) {
		try {
			PreparedStatement intertaCategoria = conn
					.prepareStatement("INSERT INTO categorias (Usuario, Nombre, Color) VALUES (?, ?, ?);)");
			intertaCategoria.setString(1, nombreUsuario);
			intertaCategoria.setString(2, nombreCategoria);
			
			//NO SE COMO HACER PARA AÑADIR EN LA BASE DE DATOS EL COLOR COMO STRING 
			
			intertaCategoria.setString(3, "Blanco");
			intertaCategoria.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	public static void main(String[] args) {
//		iniciar();
//		close();
	}
}
