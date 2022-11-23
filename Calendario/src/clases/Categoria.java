package clases;
import java.awt.Color;

public class Categoria {
	
	//Atributos
	protected String categoria;
	protected Usuario autorizados;
	protected Color color;
	
	//Constructores
	public Categoria(String categoria, Usuario autorizados, Color color) {
		super();
		this.categoria = categoria;
		this.autorizados = autorizados;
		this.color = color;
	}
	
	public Categoria(String categoria) {
		this.categoria = categoria;
		this.autorizados = null;
		this.color = Color.LIGHT_GRAY;
	}
		
	//Get y set
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
		return "En esta categoria (" + categoria + ") estan autorizados " + autorizados + "y se señaliza con el color" + color;
	}
	
	

}
