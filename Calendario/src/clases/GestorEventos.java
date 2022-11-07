package clases;
import java.util.ArrayList;


public class GestorEventos {
	// ATRIBUTOS
	private final String usuario; // En la ventana usuario. If usuario y contraseña bien. Abrir gestor eventos [usuario]
	private ArrayList<Evento> listaEventos;
	private ArrayList<Evento> listaUrgente;
	//CONSTRUCTOR
	/** Construye el Gestor de Eventos o Calendario particular del usuario.
	 * Es necesario que tenga un nombre de usuario único para poder diferenciarlo de los otros.
	 * Contiene una lista (ArrayList) con todos los eventos del usuario.
	 * @param usuario
	 * @param listaEventos
	 */
	public GestorEventos(String usuario, ArrayList<Evento> listaEventos) {
		this.usuario = usuario;
		this.listaEventos = listaEventos;
	}

	
	//METODOS
	public ArrayList<Evento> getListaUrgentes() {
		ArrayList<Evento> listaUrgente = new ArrayList<Evento>();
		for (Evento evento : getListaEventos()) {
				if (evento.isUrgente()){
					listaUrgente.add(evento);
				}
			}
		return listaUrgente;
	}
	public String getUsuario() {
		return usuario;
	}

	public ArrayList<Evento> getListaEventos() {
		return listaEventos;
	}
	
	public void addEvento (Evento evento) {
		
		listaEventos.add(evento);
		
	}
	
	public void removeEvento (Evento evento) {
		listaEventos.remove(evento);
	}



	
	

	
	
	
	
	
	
}