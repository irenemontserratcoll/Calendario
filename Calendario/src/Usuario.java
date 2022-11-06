
public class Usuario {

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
	
	

}
