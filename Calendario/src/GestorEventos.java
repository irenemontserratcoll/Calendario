import java.util.ArrayList;

public class GestorEventos {
	// ATRIBUTOS
	private final String usuario; // En la ventana usuario. If usuario y contraseña bien. Abrir gestor eventos [usuario]
	private ArrayList<Evento> listaEventos;
	
	//CONSTRUCTOR
	/** Construye el Gestor de Eventos o Calendario particular del usuario.
	 * Es necesario que tenga un nombre de usuario único para poder diferenciarlo de los otros.
	 * Contiene una lista (ArrayList) con todos los eventos del usuario.
	 * @param usuario
	 * @param listaEventos
	 */
	public GestorEventos(String usuario, ArrayList<Evento> listaEventos) {
		this.usuario = usuario;
		this.listaEventos = new ArrayList<> ();
	}

	
	//METODOS
	
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
