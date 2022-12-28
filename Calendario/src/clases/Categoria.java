package clases;
import java.awt.Color;

public class Categoria {
	
	//Atributos
	protected Usuario usuario; //Modificar creacion de categoria con usuario
	protected String categoria;
	protected Color color;
	protected boolean activa;
	
	//Constructores
	public Categoria(String categoria, Color color) {
		super();
		this.categoria = categoria;
		this.color = color;
		this.activa = true;
	}
	
	public Categoria(String categoria) {
		this.categoria = categoria;
		this.color = Color.LIGHT_GRAY;
		this.activa=true;
	}
		
	//Get y set
	
	

	public boolean isActiva() {
		return activa;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setActiva(boolean activa) {
		this.activa = activa;
	}
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return categoria;
	}
}
