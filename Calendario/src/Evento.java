import java.util.Date;

public class Evento {
	//ATRIBUTOS
	protected String nombre;
	protected Date fechaInicio; //Ver clase Calendar en vez de Date
	protected Date fechaFin;
	protected float duracionReal;
	protected Categoria categoria;
	protected boolean urgente;
	
	
	//CONSTRUCTOR
	public Evento(String nombre, Date fechaInicio, Date fechaFin, float duracionReal, Categoria categoria, boolean urgente) {
		super();
		this.nombre = nombre;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.duracionReal = duracionReal;
		this.categoria = categoria;
		this.urgente = urgente;
	}
	
	public Evento(String nombre) {
		super();
		this.nombre = nombre;
		this.fechaInicio = null;
		this.fechaFin = null;
		this.duracionReal = 0.F;
		this.categoria = null;
		this.urgente = false;
	}

	//METODOS
	
	
	//GETTERS Y SETTERS
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public float getDuracionReal() {
		return duracionReal;
	}

	public void setDuracionReal(float duracionReal) {
		this.duracionReal = duracionReal;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public boolean isUrgente() {
		return urgente;
	}

	public void setUrgente(boolean urgente) {
		this.urgente = urgente;
	}
	
}
