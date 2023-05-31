package Datos;
import Control.*;

public class Piloto {
	
	private String nombre;
	private int dorsal;
	private int edad;
	private String origen;
	
	public Piloto(String nombre, int dorsal, int edad, String origen) {
		super();
		this.nombre = nombre;
		this.dorsal = dorsal;
		this.edad = edad;
		this.origen = origen;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDorsal() {
		return dorsal;
	}

	public void setDorsal(int dorsal) {
		this.dorsal = dorsal;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	@Override
	public String toString() {
		return "Piloto [nombre=" + nombre + ", dorsal=" + dorsal + ", edad=" + edad + ", origen=" + origen + "]";
	}
	
	

}
