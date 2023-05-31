package Datos;

public class Patrocinador {
	
	private String nombre;
	
	public Patrocinador(String nombre) {
		super();
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Nombre=" + nombre + "]";
	}
	
	

}
