package Control;
import Datos.*;

public class Control {
	
	public static String insertarPiloto(Piloto item) {
		String sentencia;
		sentencia = "INSERT INTO piloto (id, nombre, dorsal, edad, origen) VALUES (NULL, '" + item.getNombre() + "', " + item.getDorsal() + ", " + item.getEdad() + ", '" + item.getOrigen() + "' )";
		return sentencia;
	}
	
	public static String insertarPatrocinador(Patrocinador item) {
		String sentencia;
		
		sentencia = "INSERT INTO patrocinador (id, nombre) VALUES (NULL, '" + item.getNombre() + "')";
		
		return sentencia;
	}
	
	public static String seleccionarPP(String tipo) {
		String sentencia;
		
		sentencia = "SELECT * from " + tipo;
		
		return sentencia;		
	}
	
	public static String eliminarPatrocinador(int numero) {
		String sentencia;
		
		sentencia = "DELETE from patrocinador where id = " + numero;
		
		return sentencia;
	}
	
	public static String eliminarPiloto(int numero) {
		String sentencia;
		
		sentencia = "DELETE from piloto where id = " + numero;
		
		return sentencia;
	}
	
	public static String eliminarPiloto2Tabla(int numero) {
		String sentencia;
		
		sentencia = "DELETE from pilototienepatrocinador where Piloto_id = " + numero;
		
		return sentencia;
	}
	
	public static String conteoPilotos(String tipo) {
		String sentencia;
		
		sentencia = "SELECT COUNT(*) FROM " + tipo;
		
		return sentencia;		
	}
	
	public static String actualizarPilto(Piloto piloto, int numero) {
		String sentencia;
		
		sentencia = "UPDATE piloto set nombre = '" + piloto.getNombre() + "', dorsal = " + piloto.getDorsal() + ", edad = " + piloto.getEdad() + ", origen = '" + piloto.getOrigen() + "' WHERE id = " + numero;
		
		return sentencia;
	}
	
	public static String actualizarPatrocinador(Patrocinador patrocinador, int numero) {
		String sentencia;
		
		sentencia = "UPDATE patrocinador set nombre = '" + patrocinador.getNombre() + "' WHERE id = " + numero;
		
		return sentencia;
	}
	
	public static String unirPatroAPiloto(Patrocinador patrocinador, int numero) {
		String sentencia;
		
		sentencia = "INSERT INTO pilototienepatrocinador (Piloto_id, Patrocinador_id) VALUES (" + numero + ",(SELECT id from patrocinador where nombre like '" + patrocinador.getNombre() + "'))";
		
		return sentencia;
	}
	
	public static String unirPilotoAPatro(int patrocinador, int piloto) {
		String sentencia;
		
		sentencia = "INSERT INTO pilototienepatrocinador (Piloto_id, Patrocinador_id) VALUES (" + piloto + ", " + patrocinador + ")";
		
		return sentencia;
	}
	
	public static String seleccionarPilotoCPatro(int patrocinador) {
		String sentencia;
		
		sentencia = "SELECT * FROM piloto where id in (SELECT distinct Piloto_id from pilototienepatrocinador where Patrocinador_id = " + patrocinador + " )";
		
		return sentencia;
	}
	
	public static String seleccionarPatroCPiloto(int piloto) {
		String sentencia;
		
		sentencia = "SELECT * FROM patrocinador where id in (SELECT distinct Patrocinador_id from pilototienepatrocinador where Piloto_id = " + piloto + " )";
		
		return sentencia;
	}
	
	public static String seleccionarPatroSin(String tipo) {
		String sentencia = "";
		
		if(tipo.equals("patrocinador")) {
			sentencia = "SELECT * FROM patrocinador where id not in (SELECT distinct Patrocinador_id from pilototienepatrocinador)";
		}
		
		if(tipo.equals("piloto")) {
			sentencia = "SELECT * FROM piloto where id not in (SELECT distinct Piloto_id from pilototienepatrocinador)";
		}
		
		return sentencia;
	}
	
	public static String PatroMasUsado() {
		String sentencia;
		
		sentencia = "select * from patrocinador where id = (select Patrocinador_id from pilototienepatrocinador GROUP BY Patrocinador_id ORDER BY COUNT(*) DESC limit 1)";
		
		return sentencia;
	}

}
