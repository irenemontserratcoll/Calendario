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
	private Statement statement;
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
			conn.close();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public static void main(String[] args) {
		//iniciar();
	}
}