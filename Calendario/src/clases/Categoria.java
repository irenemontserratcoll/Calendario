package clases;
import java.awt.Color;

public class Categoria {
	
	//Atributos
	protected Usuario usuario; //Modificar creacion de categoria con usuario
	protected String categoria;
	protected Usuario autorizados;
	protected Color color;
	protected boolean activa;
	
	//Constructores
	public Categoria(String categoria, Usuario autorizados, Color color) {
		super();
		this.categoria = categoria;
		this.autorizados = autorizados;
		this.color = color;
		this.activa = true;
	}
	
	public Categoria(String categoria) {
		this.categoria = categoria;
		this.autorizados = null;
		this.color = Color.LIGHT_GRAY;
		this.activa=true;
	}
		
	//Get y set

	public boolean isActiva() {
		return activa;
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

	public Usuario getAutorizados() {
		return autorizados;
	}

	public void setAutorizados(Usuario autorizados) {
		this.autorizados = autorizados;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return categoria + "Autorizados: " + autorizados + "Color: " + color;
	}
	
	

}
