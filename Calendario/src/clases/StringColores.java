package clases;

public enum StringColores {
	ROSA("#FFB5E8"),
	MORADO("#B28DFF"),
	GRIS("#DCD3FF"),
	VERDE("#AFF8DB"),
	AMARILLO("#F4FAB4"),
	AZUL("#A0D2F3"),;
	
	private String name;
	
	StringColores(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
