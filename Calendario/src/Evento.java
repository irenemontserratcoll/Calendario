import java.time.*;
import java.time.format.*;

public class Evento {
	//ATRIBUTOS
	protected String nombre;
	
	//TODO Ver clase Calendar, Gregorian Calendar y Time (SQL)
	protected ZonedDateTime fechaInicio; 
	protected ZonedDateTime fechaFin;
	protected float duracionReal;
	protected Categoria categoria;
	protected boolean urgente;
	//HashCode?
	
	
	/** Construye un evento a partir de todos los datos
	 * @param nombre
	 * @param fechaInicio
	 * @param fechaFin
	 * @param duracionReal
	 * @param categoria
	 * @param urgente
	 */
	public Evento(String nombre, ZonedDateTime fechaInicio, ZonedDateTime fechaFin, float duracionReal, Categoria categoria, boolean urgente) {
		super();
		this.nombre = nombre;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.duracionReal = duracionReal;
		this.categoria = categoria;
		this.urgente = urgente;
	}
	
	/** Construye un evento a partir del nombre
	 * @param nombre
	 */
	public Evento(String nombre) {
		super();
		this.fechaInicio = null;
		this.fechaFin = null;
		this.duracionReal = 0.F;
		this.categoria = null;
		this.urgente = false;
	}
	
	public Evento(String nombre, int anyo, int mes, int dia, int hora, int minuto) {
		super();
		LocalDateTime dt = LocalDateTime.of(anyo, mes, dia, hora, minuto);
		ZonedDateTime fechaInicio = ZonedDateTime.of(dt,ZoneId.of("Europe/Madrid"));
		this.fechaInicio= fechaInicio;
	}
	
	public Evento(String nombre, int anyo, int mes, int dia, int hora, int minuto,int duracionMinutos) {
		super();
		this.fechaFin = fechaInicio.plusMinutes(duracionMinutos);
	}

	//METODOS
	
	
	//GETTERS Y SETTERS
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ZonedDateTime getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(ZonedDateTime fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	public void setFechaInicio(int anyo, int mes, int dia, int hora, int minuto) {
		LocalDateTime dt = LocalDateTime.of(anyo, mes, dia, hora, minuto);
		ZonedDateTime fechaInicio = ZonedDateTime.of(dt,ZoneId.of("Europe/Madrid"));
		this.fechaInicio= fechaInicio;
	}

	public ZonedDateTime getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(ZonedDateTime fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	public void setFechaFin(int duracionMinutos) {
		this.fechaFin = fechaInicio.plusMinutes(duracionMinutos);
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
