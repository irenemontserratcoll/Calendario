package baseDeDatos;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EventosUsuario {
	public static void main(String[] args) {
		// Cargar drivers
	
		try {
			Class.forName("org.sqlite.JDBC");
			
		} catch (ClassNotFoundException e) {
			System.out.println("No se ha podido cargar el driver de la base de datos");
		}

		// Crear conexion

			

			 try {
				 Connection conn = DriverManager.getConnection("jdbc:sqlite:EventosUsuario.db");
				 Statement stmt = conn.createStatement();

				 try (ResultSet rs = stmt.executeQuery("SELECT Usuario FROM eventos")) {
				 while (rs.next()) {
				 // Mientras tenga filas
				 // Cogemos cada columna contenida en la fila

				 String name = rs.getString("Usuario");

				 // y hacemos algo con esos datos: imprimir, crear un objeto, etc
				 System.out.println(name);
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
