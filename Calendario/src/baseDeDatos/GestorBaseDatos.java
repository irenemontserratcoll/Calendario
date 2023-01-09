package baseDeDatos;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.logging.Logger;
import java.util.*;

import clases.Categoria;
import clases.Evento;
import clases.Usuario;

public class GestorBaseDatos {
	private static Logger logger = Logger.getLogger(Logger.class.getName());
	private static Connection conn;
	
	public static boolean iniciar(){
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			logger.severe("No se ha podido cargar el driver de la base de datos: "+ e.getMessage());
		}
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:CalendarioBD.db");
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
	
	/** Añade en la base de datos una categoria nueva.
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
			
			//TODO NO SE COMO HACER PARA AÑADIR EN LA BASE DE DATOS EL COLOR COMO STRING 
			
			intertaCategoria.setString(3, "Blanco");
			intertaCategoria.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo para añadir un evento a la base de datos
	 * 
	 * @param evento  que queremos añadir
	 * @param usuario al que pertenece
	 */
	public void anyadirEvento(Evento evento, Usuario usuario) {
		// Ejemplo statement
		// INSERT INTO eventos VALUES ('Nahia', 'Estudiar', 76737, 78998, 30,
		// 'bailar','si');

		try {
			PreparedStatement insertarEvento = conn
					.prepareStatement("INSERT INTO eventos VALUES ('?', '?', ?, ?, ?, '?','?'");
			insertarEvento.setString(1, usuario.getNombre());
			insertarEvento.setString(1, evento.getNombre());
			Long fechaInicio = evento.getFechaInicio().toInstant().toEpochMilli();
			insertarEvento.setLong(3, fechaInicio);
			Long fechaFin = evento.getFechaFin().toInstant().toEpochMilli();
			insertarEvento.setLong(4, fechaFin);
			insertarEvento.setInt(5, (int) evento.getDuracionReal());
			insertarEvento.setString(6, evento.getCategoria().getCategoria());
			String urgente = "";
			if (evento.isUrgente()) {
				urgente = "Si";
			} else {
				urgente = "No";
			}
			insertarEvento.setString(7, urgente);

			insertarEvento.executeUpdate();
			logger.info("Evento añadido a la base de datos");
		} catch (SQLException e) {
			logger.severe("No se ha añadido el evento a la Base de datos");
			e.printStackTrace();
		}
	}

/**Obtiene una lista con los eventos de un determinado usuario almacenados en la BD
 * @param nombreUsuario
 * @param listaEventos
 * @return listaEventos devuelve la propia lista de eventos, habiendo añadido los eventos de ese nombre de Usuario en la BD
 */
	
	public List<Evento> getListaEventosUsuario(String nombreUsuario, List<Evento> listaEventos) {
		try {
			Statement obtenerEventos = conn.createStatement();
			String consulta = "SELECT * FROM eventos WHERE usuario = '"+ nombreUsuario +"';";
			ResultSet rs2 = obtenerEventos.executeQuery(consulta);
			
			while (rs2.next()) {
				long lFechaInicio = rs2.getLong("Fecha Inicio");
				long lFechaFin = rs2.getLong("Fecha Fin");
				float duracionReal = rs2.getFloat("Duracion Real");
				String sCategoria = rs2.getString("Categoria");
				String sUrgente = rs2.getString("Urgente");
				Boolean urgente=false;
				if (sUrgente.contains("Si")) {
					urgente=true;
				}
				LocalDateTime lInicio = LocalDateTime.ofInstant(Instant.ofEpochMilli(lFechaInicio), TimeZone.getDefault().toZoneId());
				ZonedDateTime fechaInicio = ZonedDateTime.of(lInicio, ZoneId.of("Europe/Madrid"));
				LocalDateTime lFin = LocalDateTime.ofInstant(Instant.ofEpochMilli(lFechaFin), TimeZone.getDefault().toZoneId());
				ZonedDateTime fechaFin = ZonedDateTime.of(lFin, ZoneId.of("Europe/Madrid"));
				Categoria cat = getCategoriaDeNombre(nombreUsuario, sCategoria);
				
				Evento e = new Evento(nombreUsuario,fechaInicio, fechaFin, duracionReal, cat, urgente);
				listaEventos.add(e);
			}
			obtenerEventos.close();		
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return listaEventos;
	}
	
	/**Obtiene la categoria almacenada en la BD dado el nombre del usuario y de la categorica
	 * @param nombreUsuario
	 * @param nombreCategoria
	 * @return Categoria
	 */
	public static Categoria getCategoriaDeNombre(String nombreUsuario, String nombreCategoria) {
		try {
			Statement obtenerCategoria = conn.createStatement();
			String consulta ="SELECT * FROM categorias WHERE nombre = '"+nombreCategoria+"' AND usuario = '" + nombreUsuario + "'";
			ResultSet rs5 = obtenerCategoria.executeQuery(consulta);
			while (rs5.next()) {
				int intColor = rs5.getInt("Color");
				Color color = new Color(intColor); //En RGB
				Categoria cat = new Categoria(nombreCategoria, color);
				return cat;
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
			logger.severe("No se ha podido cargar la categoria de la base de datos: "+ e.getMessage());
		}
		return null;
		
	}
	
	/**
	 * 
	 * @param nombreUsuario del que se quieren buscar los eventos
	 * @param diasMostrados en la pantalla en ese momento
	 * @return Lista de los eventos de esa semana.
	 */
	public static List<Evento> getEventosSemana(String nombreUsuario, List<String> diasMostrados  ){
		try {
			List<Evento> listaEventos = new ArrayList<Evento>();
			Statement obtenerEventos = conn.createStatement();
			String consulta = "SELECT * FROM eventos WHERE usuario = '"+ nombreUsuario +"' ";
			ResultSet rs2 = obtenerEventos.executeQuery(consulta);

			while (rs2.next()) {
				System.out.println("he llegado aqui");

				long lFechaInicio = rs2.getLong("Fecha Inicio");
				long lFechaFin = rs2.getLong("Fecha Fin");
				float duracionReal = rs2.getFloat("Duracion Real");
				String sCategoria = rs2.getString("Categoria");
				String sUrgente = rs2.getString("Urgente");
				Boolean urgente=false;
				if (sUrgente.contains("Si")) {
					urgente=true;
				}
				LocalDateTime lInicio = LocalDateTime.ofInstant(Instant.ofEpochMilli(lFechaInicio), TimeZone.getDefault().toZoneId());
				ZonedDateTime fechaInicio = ZonedDateTime.of(lInicio, ZoneId.of("Europe/Madrid"));
				LocalDateTime lFin = LocalDateTime.ofInstant(Instant.ofEpochMilli(lFechaFin), TimeZone.getDefault().toZoneId());
				ZonedDateTime fechaFin = ZonedDateTime.of(lFin, ZoneId.of("Europe/Madrid"));
				Categoria cat = getCategoriaDeNombre(nombreUsuario, sCategoria);

				
				for (String dia: diasMostrados){
					if ((fechaInicio.getDayOfWeek() + " " +fechaInicio.getDayOfMonth( )).equals(dia)) {
						Evento e = new Evento(nombreUsuario,fechaInicio, fechaFin, duracionReal, cat, urgente);
						listaEventos.add(e);
					}
				}

			}
			obtenerEventos.close();		
			return listaEventos;

		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	

}
