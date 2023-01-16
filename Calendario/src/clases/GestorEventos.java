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
	private final String usuario; //  Abrir gestor eventos de cada usuario
	private static List<Evento> listaEventos; //Lista con todos los eventos de la base de datos
	private List<Evento> listaUrgente;
	private GestorBaseDatos baseDatos; //Tiene que estar conectado con la base de datos

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
	@SuppressWarnings("static-access")
	public GestorEventos(String usuario, GestorBaseDatos baseDatos) {
		this.usuario = usuario;
		this.baseDatos=baseDatos;
		listaEventos = new ArrayList<>();
		listaEventos = baseDatos.getListaEventosUsuario(usuario);
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

	/** Función que devuelve una lista con los eventos de la semana
	 * dada la fecha del día central de la semana (atributo fecha de la Ventana Principal)
	 * @param fecha
	 * @return listaSemana
	 */
	public List<Evento> getListaEventosSemanal(ZonedDateTime fecha){
		List<Evento> listaSemana = new ArrayList<Evento>();
		for(Evento e: listaEventos) {
			ZonedDateTime inicio = e.getFechaInicio();
			ZonedDateTime fin = e.getFechaFin();
			if (inicio != null) {
				if ( (fecha.isAfter(inicio.minusDays(3)) && fecha.isBefore(fin.plusDays(3)) ) ||  //El evento comienza durante la semana pero sigue
						(inicio.isBefore(fecha.plusDays(3))  && (inicio.isAfter(fecha.minusDays(3)) ) )   ){ //El evento comienza durante la semana
					listaSemana.add(e);
				}
			}
		}
		return listaSemana;
		
	}
	
	public void addEvento(Evento evento) {
		baseDatos.anyadirEvento(evento, usuario);
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
	
	
	public static List<Evento> crearListaTareasPendientes(ArrayList<Evento> eventos){
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



