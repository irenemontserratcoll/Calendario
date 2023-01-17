package clases;
import java.awt.Color;
import java.util.Objects;

public class Categoria {
	
	@Override
	public int hashCode() {
		return Objects.hash(categoria, color, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		return Objects.equals(categoria, other.categoria) && Objects.equals(color, other.color)
				&& Objects.equals(usuario, other.usuario);
	}

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
