package clases;

import java.util.Objects;

public class Usuario {

	@Override
	public int hashCode() {
		return Objects.hash(contraseña, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(contraseña, other.contraseña) && Objects.equals(nombre, other.nombre);
	}

	//Atributos
	protected String nombre;
	protected String contraseña;

	//Constructores

	public Usuario(String nombre, String contraseña) {
		super();
		this.nombre = nombre;
		this.contraseña = contraseña;
	}	
	
	//Get y set
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getContraseña() {
		return contraseña;
	}
	
	
	
	

}
