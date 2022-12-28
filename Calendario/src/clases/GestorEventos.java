package clases;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import baseDeDatos.GestorBaseDatos;


public class GestorEventos {
	// ATRIBUTOS
	private final String usuario; // En la ventana usuario. If usuario y contraseña bien. Abrir gestor eventos
									// [usuario]
	
	private static List<Evento> listaEventos;
	private List<Evento> listaUrgente;

	// CONSTRUCTOR
//	/**
//	 * Construye el Gestor de Eventos o Calendario particular del usuario. Es
//	 * necesario que tenga un nombre de usuario único para poder diferenciarlo de
//	 * los otros. Contiene una lista (ArrayList) con todos los eventos del usuario.
//	 * 
//	 * @param usuario
//	 * @param listaEventos
//	 * @param listaUrgente
//	 */
//	public GestorEventos(String usuario, List<Evento> listaEventos) {
//		this.usuario = usuario;
//		GestorEventos.listaEventos = listaEventos;
//	}
	
	/**Creo el gestor eventos a partir de la base de datos.
	 * Obteniendo la lista de eventos de un usuario concreto.
	 * @param usuario
	 * @param baseDatos
	 */
	public GestorEventos(String usuario, GestorBaseDatos baseDatos) {
		this.usuario = usuario;
		listaEventos = new ArrayList<>();
		listaEventos = baseDatos.getListaEventosUsuario(usuario, listaEventos);
		listaUrgente = new ArrayList<>();
		for (Evento evento : listaEventos) {
			if (evento.isUrgente()) {
				listaUrgente.add(evento);
			}
		}
	}

	// METODOS
	public List<Evento> getListaUrgente() {
		for (Evento evento : listaEventos) {
			if (evento.isUrgente()) {
				listaUrgente.add(evento);
			}
		}
		return listaUrgente;
	}

	public String getUsuario() {
		return usuario;
	}

	public List<Evento> getListaEventos() {
		return listaEventos;
	}

	public void addEvento(Evento evento) {
		listaEventos.add(evento);
		if(evento.isUrgente()) {
			listaUrgente.add(evento);
		}
	}

	public void removeEvento(Evento evento) {
		listaEventos.remove(evento);
	}

	public void setListaUrgente(List<Evento> listaUrgente) {

		this.listaUrgente = listaUrgente;

	}
	
	
	public void crearListaTareas(List<Evento> listaEventos) {
		
	}
	

	public void cargarEnFicheroCSV() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("evento.csv"))){
			
			for (Evento e : listaEventos) {
			
			String evento = String.format(
			"%s,%d,%d,%d,%s,%b",
			e.getNombre(),
			e.getFechaInicio(),
			e.getFechaFin(),
			e.getDuracionReal(),
			e.getCategoria(),
			e.isUrgente()
			);
			
			// escribe el string al fichero
			
			writer.write(evento);
		}
		} catch (FileNotFoundException e) {
			// Si no se encuentra el fichero al intentar abrirlo

			System.out.println("No se pudo encontrar el fichero");
			} catch (IOException e) {
			// Si hay problemas al escribir en el fichero.

			System.out.println("Hay problemas al escribir");
			}
	}
	
	public static  List<Evento> leerDeFicheroCSV(String fichero) {
		try (BufferedReader reader = new BufferedReader(new FileReader(fichero))) {
			String linea = null;
			while ((linea = reader.readLine()) != null) {

				String[] campos = linea.split(",");

				String usuario = campos[0];
				ZonedDateTime fechaInicio = ZonedDateTime.parse(campos[1]);
				ZonedDateTime fechafin = ZonedDateTime.parse(campos[2]);
				Float duracionReal = Float.parseFloat(campos[3]);
				Categoria categoria = new Categoria(campos[4]);
				Boolean urgente = Boolean.parseBoolean(campos[5]);
				Evento e = new Evento(usuario, fechaInicio, fechafin, duracionReal, categoria, urgente);
				listaEventos.add(e);
			}
		} catch (IOException e) {
			System.out.println("No se ha podido leer el fichero CSV.");
		}
		return listaEventos;
	}
	
	
	public static List<Evento> crearListaTareasPnedientes(ArrayList<Evento> eventos){
		List<Evento> tareaPendiente = new ArrayList<>();
		for (Evento ev : eventos) {
			ev.getCategoria();
			if (!(Objects.nonNull(ev.getFechaFin()))&&!(Objects.nonNull(ev.getFechaInicio()) && !(Objects.nonNull(ev.getDuracionReal())))){
				tareaPendiente.add(ev);
			}
		}
		return tareaPendiente;	
	}
}



