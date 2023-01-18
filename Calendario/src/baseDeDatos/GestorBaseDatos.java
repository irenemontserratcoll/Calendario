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
import java.util.logging.Logger;
import java.util.*;

import clases.Categoria;
import clases.Evento;
//import clases.Usuario;
import clases.Usuario;

public class GestorBaseDatos {
	private static Logger logger = Logger.getLogger(Logger.class.getName());
	private static Connection conn;
	
	/**
	 * Metod para inciar la conexión de la BD
	 * @return True-> Iniciada correctamente ; False -> Iniciada Incorrectamente
	 */
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
	public static int buscarCategoria ( String nombreUsuario, String nombreCategoria) {
		try {
			Statement stmt = conn.createStatement();
			String consulta = "SELECT (Nombre) FROM categoria WHERE Usuario = '" + nombreUsuario + "';";
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
	public static void anyadirCategoria(String nombreUsuario, String nombreCategoria, Color color) {
		try {
			PreparedStatement intertaCategoria = conn
					.prepareStatement("INSERT INTO categoria (Usuario, Nombre, Color) VALUES (?, ?, ?);)");
			intertaCategoria.setString(1, nombreUsuario);
			intertaCategoria.setString(2, nombreCategoria);	
			String anadir = "#" +(Integer.toHexString(color.getRed()) + Integer.toHexString(color.getGreen())+ Integer.toHexString(color.getBlue())).toUpperCase();
			intertaCategoria.setString(3, anadir);			
			intertaCategoria.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**Este método devuelve todas las categorias de un usuario en concreto.
	 * 
	 * @param nombreUsuario
	 * @return
	 */
	public static List<Categoria> todasCategorias(String nombreUsuario){
		List<Categoria> devolver = new ArrayList<>();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String consulta = "SELECT * FROM categoria WHERE Usuario = '" + nombreUsuario + "';";
			ResultSet rs = stmt.executeQuery(consulta);
			while (rs.next()) {
				Categoria c = new Categoria(rs.getString("Nombre"),Color.decode(rs.getString("Color")));
				devolver.add(c);
			}
			stmt.close();
			return devolver;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}		
	}

	
	/**
	 * Metodo para añadir un evento a la base de datos
	 * 
	 * @param evento  que queremos añadir
	 * @param usuario al que pertenece
	 */
	public static void anyadirEvento(Evento evento, String usuario) {

		try {
			PreparedStatement insertarEvento = conn
					.prepareStatement("INSERT INTO eventos VALUES (?, ?,?, ?, ?, ?,?)");
			insertarEvento.setString(1, usuario);
			insertarEvento.setString(2, evento.getNombre());
			if (evento.getFechaInicio() == null) {
				insertarEvento.setLong(3, 0);
			}else {
				Long fechaInicio = evento.getFechaInicio().toInstant().toEpochMilli();
				insertarEvento.setLong(3, fechaInicio);
			}
			
			if (evento.getFechaFin() == null) {
				insertarEvento.setLong(4, 0);
			}else {
				Long fechaFin = evento.getFechaFin().toInstant().toEpochMilli();
				insertarEvento.setLong(4, fechaFin);
			}

			if (evento.getDuracionReal() == 0) {
				insertarEvento.setLong(5, 0);
			}else {
				insertarEvento.setInt(5, (int) evento.getDuracionReal());
			}

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
 * @return listaEventos devuelve la propia lista de eventos, habiendo añadido los eventos de ese nombre de Usuario en la lista
 */
	public static ArrayList<Evento> getListaEventosUsuario(String nombreUsuario) {
		ArrayList<Evento> listaEventos = new ArrayList<>();
		try {
			Statement obtenerEventos = conn.createStatement();
			String consulta = "SELECT * FROM eventos WHERE usuario = '"+ nombreUsuario +"';";
			ResultSet rs2 = obtenerEventos.executeQuery(consulta);
			
			
			while (rs2.next()) {
				String nombreEvento = rs2.getString("Nombre Evento");
				ZonedDateTime fechaInicio;
				ZonedDateTime fechaFin;
				long lFechaInicio = rs2.getLong("Fecha Inicio");
				long lFechaFin = rs2.getLong("Fecha Fin");
				float duracionReal = rs2.getFloat("Duracion Real");
				String sCategoria = rs2.getString("Categoria");
				String sUrgente = rs2.getString("Urgente");
				Boolean urgente=false;
				if (sUrgente.contains("Si")) {
					urgente=true;
				}
				if( lFechaInicio == 0 ) {
					fechaInicio = null;
				}else {
					LocalDateTime lInicio = LocalDateTime.ofInstant(Instant.ofEpochMilli(lFechaInicio), TimeZone.getDefault().toZoneId());
					fechaInicio = ZonedDateTime.of(lInicio, ZoneId.of("Europe/Madrid"));
				}
				if( lFechaFin == 0 ) {
					fechaFin = null;
				}else {
					LocalDateTime lFin = LocalDateTime.ofInstant(Instant.ofEpochMilli(lFechaFin), TimeZone.getDefault().toZoneId());
					fechaFin = ZonedDateTime.of(lFin, ZoneId.of("Europe/Madrid"));
				}
				
				Color ccolus = Color.black;
				for (Categoria catnombreBusca : todasCategorias(nombreUsuario)) {
					if (catnombreBusca.getCategoria().equals(sCategoria)) {
						ccolus = catnombreBusca.getColor();
					}
				}
				Categoria cat = new Categoria(sCategoria, ccolus);
				Evento e = new Evento(nombreEvento,fechaInicio, fechaFin, duracionReal, cat, urgente);

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
			String consulta ="SELECT * FROM categoria WHERE nombre = '"+nombreCategoria+"' AND usuario = '" + nombreUsuario + "'";
			ResultSet rs5 = obtenerCategoria.executeQuery(consulta);
			while (rs5.next()) {
				Color colorAnadir = Color.decode(rs5.getString("Color"));
				Categoria cat = new Categoria(nombreCategoria, colorAnadir);
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
	
	/**Mediante este metodo se seleccionan todos los eventos que no tienen fecha de inicio es decir que no son importantes.
	 * 
	 * @param nombreUsuario
	 * @return Lista de eventos que son tareas pendientes
	 */
	public static List<Evento> getTareasPendientes (String nombreUsuario){
		ArrayList<Evento> listaEventos = new ArrayList<>();
		try {
			Statement obtenerEventos = conn.createStatement();
			 
			String consulta = "SELECT * FROM eventos WHERE usuario = '"+ nombreUsuario +"' AND 'fecha inicio' IS null";
			ResultSet rs2 = obtenerEventos.executeQuery(consulta);
			while (rs2.next()) {
				String nombreEvento = rs2.getString("Nombre Evento");
				float duracionReal = rs2.getFloat("Duracion Real");
				String sCategoria = rs2.getString("Categoria");
				String sUrgente = rs2.getString("Urgente");
				Boolean urgente=false;
				if (sUrgente.contains("Si")) {
					urgente=true;
				}
				Categoria cat = getCategoriaDeNombre(nombreUsuario, sCategoria);
				Evento e = new Evento(nombreEvento, null , null, duracionReal, cat, urgente);
				listaEventos.add(e);
			}
			obtenerEventos.close();	
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return listaEventos;
		
	}
	
	/**Carga datos de prueba
	 */
	public static void cargarPrueba() {
		Usuario u1 = new Usuario("PRUEBA", "PRUEBA");
		
		List<Categoria> listaCategorias = todasCategorias("PRUEBA");
		Categoria deporte = new Categoria("Deporte", Color.decode("#FFB5E8"));
		Categoria estudiar = new Categoria("Estudiar", Color.decode("#B28DFF"));
		Categoria otros = new Categoria("Otros", Color.decode("#DCD3FF"));

		Boolean isDeporte = false;
		Boolean isEstudiar = false;
		Boolean isOtros = false;
		for (Categoria c : listaCategorias) {
			if (c.equals(deporte)) {
				isDeporte = true;
			}

			if (c.equals(estudiar)) {
				isEstudiar = true;
			}
			if (c.equals(otros)) {
				isOtros = true;
			}
		}

		if (isDeporte == false) {
			anyadirCategoria("PRUEBA","Deporte",  Color.decode("#FFB5E8"));
		}
		if (isEstudiar == false) {
			anyadirCategoria("PRUEBA","Estudiar", Color.decode("#B28DFF"));
		}
		if (isOtros == false) {
			anyadirCategoria("PRUEBA","Otros", Color.decode("#DCD3FF"));
		}
		
		
		ZonedDateTime hoy = ZonedDateTime.now();
		Evento e1 = new Evento("Bailar", hoy.minusDays(2).minusHours(3), hoy.minusDays(2).minusHours(2), 20.2F, deporte, true);
		Evento e2 = new Evento("Correr", hoy.plusDays(1).plusHours(1), hoy.plusDays(1).plusHours(2), 20.2F, deporte, true);
		Evento e3 = new Evento("Matematicas", hoy.minusMinutes(120), hoy, 5,estudiar, true);
		Evento e4 = new Evento ("Llamar mama", hoy.plusDays(3), hoy.plusDays(3).plusMinutes(30), 5, otros, true);
		Evento e5 = new Evento("Programacion", estudiar, true);
		List<Evento> listaEventosPrueba = getListaEventosUsuario("PRUEBA");
		
		Boolean isE1 =false;
		Boolean isE2 =false;
		Boolean isE3 =false;
		Boolean isE4 =false;
		Boolean isE5 =false;
		for (Evento e: listaEventosPrueba) {
			if (e.getNombre().equals(e1.getNombre())) {
				isE1 = true;
			}
			if (e.getNombre().equals(e2.getNombre())) {
				isE2 = true;
			}
			if (e.getNombre().equals(e3.getNombre())) {
				isE3 = true;
			}
			if (e.getNombre().equals(e4.getNombre())) {
				isE4 = true;
			}
			if (e.getNombre().equals(e5.getNombre())) {
				isE5 = true;
			}
		}

		if (isE1 == false) {
			anyadirEvento(e1, u1.getNombre());
		}
		if (isE2 == false) {
			anyadirEvento(e2, u1.getNombre());
		}
		if (isE3 == false) {
			anyadirEvento(e3, u1.getNombre());
		}
		if (isE4 == false) {
			anyadirEvento(e4, u1.getNombre());
		}
		if (isE5 == false) {
			anyadirEvento(e5, u1.getNombre());
		}

	}
	
	/**Borrar datos de prueba
	 */
	public static void borrarPrueba() {
		try {
			PreparedStatement insertaUsuario = conn.prepareStatement("DELETE FROM eventos WHERE Usuario = 'PRUEBA';");
			insertaUsuario.executeUpdate();
			PreparedStatement eliminarCategoria = conn.prepareStatement("DELETE FROM categoria WHERE Usuario = 'PRUEBA';");
			eliminarCategoria.executeUpdate();
			logger.info("Datos de PRUEBA borrados de la BD");
		} catch (SQLException e) {
			logger.severe("Borrado de datos de PRUEBA incorrecto");
			e.printStackTrace();
			
		}

	}
	
}
