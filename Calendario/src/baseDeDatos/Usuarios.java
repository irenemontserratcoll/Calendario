package baseDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Usuarios {

	public static void main(String[] arg) {

		// Primero cargamos los drivers
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			System.out.println("No se ha podido cargar el driver de la base de datos");
		}
		// Segundo paso es crear una conexión

		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:Usuarios.db");
			Statement stmt = conn.createStatement();

			try (ResultSet rs = stmt.executeQuery("SELECT Usuario, Contraseña FROM usuarios")) {
				while (rs.next()) {

					String usuario = rs.getString("Usuario");
					String contraseña = rs.getString("Contraseña");

					System.out.println(usuario + " " + contraseña);
				}
			} catch (SQLException e) {
				System.out.println("No se ha podido ejecutar la sentencia SQL." + e.getMessage());
			}

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
