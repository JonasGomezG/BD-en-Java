package Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import Control.Control;

public class usuario {
	
	public static void menu(){
		
		Scanner sc = new Scanner(System.in);
		int opcionPrincipal;

		do {
			System.out.println("Elije una opcion que quieres hacer:");
			System.out.println("1. Insertar un Piloto."); // hecho
			System.out.println("2. Insertar un Patrocinador."); // hecho
			System.out.println("3. Borrar un Patrocinador."); // hecho
			System.out.println("4. Borrar un Piloto."); // hecho
			System.out.println("5. Actualizar un Piloto."); // hecho
			System.out.println("6. Actualizar un Patrocinador."); // hecho
			System.out.println("7. Asociar Patrocinador a nuevo Piloto.");  // hecho
			System.out.println("8. Mostrar Pilotos con un cierto Patrocinador.");  // hecho
			System.out.println("9. Mostrar Patrocinadores de un cierto Piloto."); // hecho
			System.out.println("10. Mostrar cantidad de Pilotos."); // hecho
			System.out.println("11. Mostrar Patrocinador mas usado."); // hecho
			System.out.println("12. Mostrar Patrocinadores sin Pilotos"); //hecho
			System.out.println("13. Mostrar Pilotos sin Patrociandores"); //hecho
			System.out.println("14. Mostrar todos los Pilotos."); // hecho
			System.out.println("15. Mostrar todos los Patrocinadores."); // hecho
			System.out.println("16. Finalizar programa.");
			opcionPrincipal = sc.nextInt();
			sc.nextLine();

			switch (opcionPrincipal) {
			case 1: {
				insertarPiloto();
				break;
			}
			case 2: {
				insertarPatrocinador();
				break;
			}
			case 3: {
				seleccionar("patrocinador");
				eliminarPatrocinador();
				break;
			}
			case 4: {
				eliminarPiloto();
				break;
			}
			case 5: {
				seleccionar("piloto");
				actualizarPiloto();
				break;
			}
			case 6: {
				seleccionar("patrocinador");
				actualizarPatrocinador();
				break;
			}
			case 7: {
				insertarPatrocinadorAPiloto();
				break;
			}
			case 8: {
				seleccionarPatroPiloto("patrocinador");
				break;
			}
			case 9: {
				seleccionarPatroPiloto("piloto");
				break;
			}
			case 10: {
				seleccionarTotal("piloto");
				break;
			}
			case 11: {
				seleccionarMayorPatro();
				break;
			}
			case 12: {
				seleccionarPatrosSinPilo("patrocinador");
				break;
			}
			case 13: {
				seleccionarPatrosSinPilo("piloto");
				break;
			}
			case 14: {
				seleccionar("piloto");
				break;
			}
			case 15: {
				seleccionar("patrocinador");
				break;
			}
			case 16: {
				System.out.println("Programa finalizado.");
				break;
			}
			default:
				System.out.println("Opcion no valida");
				break;
			}

		} while (opcionPrincipal != 16);
		
	}
	
	public static void insertarPiloto(){

		Connection conexion = null;
		Statement sentenciaSQL = null;
		ResultSet rs;
		int resultado = 0;
		String sql = "";

		try {

			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/programacion_BD", "root", "");

			sentenciaSQL = conexion.createStatement();

			Piloto p1 = pedirDatos.pedirPiloto();

			sql = Control.insertarPiloto(p1);

			resultado = sentenciaSQL.executeUpdate(sql);

			if (resultado >= 1) {
				System.out.println(" ");
				System.out.println("Se ha insertado bien.");
				System.out.println(" ");
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				sentenciaSQL.close();
				conexion.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}
		
	}
	
	public static void insertarPatrocinador(){
		Scanner sc = new Scanner(System.in);
		Connection conexion = null;
		Statement sentenciaSQL = null;
		ResultSet rs;
		int resultado = 0;
		int pilotoAUnir;
		String sql = "";

		try {

			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/programacion_BD", "root", "");

			sentenciaSQL = conexion.createStatement();

			Patrocinador p1 = pedirDatos.pedirPatrocinador();

			sql = Control.insertarPatrocinador(p1);

			resultado = sentenciaSQL.executeUpdate(sql);

			System.out.println("A que piloto quieres unir el patrocinador");
			seleccionar("piloto");
			System.out.print("");
			pilotoAUnir = sc.nextInt();
			sc.nextLine();
			
			sql = Control.unirPatroAPiloto(p1, pilotoAUnir);
			
			resultado = sentenciaSQL.executeUpdate(sql);

			if (resultado >= 1) {
				System.out.println(" ");
				System.out.println("Se ha insertado bien.");
				System.out.println(" ");
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				sentenciaSQL.close();
				conexion.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}

	}
	
	public static void seleccionar(String tipo){
		Connection conexion = null;
		Statement sentenciaSQL = null;
		ResultSet rs;
		String sql = "";

		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/programacion_BD", "root", "");

			sentenciaSQL = conexion.createStatement();

			sql = Control.seleccionarPP(tipo.toLowerCase());

			rs = sentenciaSQL.executeQuery(sql);

			System.out.println(" ");

			if (tipo.equals("piloto")) {
				while (rs.next()) {
					System.out.println("id Piloto: " + rs.getInt("id") + " - Nombre: " + rs.getString("nombre")
							+ " - Dorsal: " + rs.getInt("dorsal") + " - Edad: " + rs.getInt("edad") + " - Origen: "
							+ rs.getString("origen"));
				}
			}

			if (tipo.equals("patrocinador")) {
				while (rs.next()) {
					System.out.println("id Patrocinador: " + rs.getInt("id") + " - Nombre: " + rs.getString("nombre"));
				}
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				sentenciaSQL.close();
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		System.out.println(" ");
	}
	
	public static void eliminarPatrocinador() {
		Connection conexion = null;
		Statement sentenciaSQL = null;
		ResultSet rs;
		int resultado = 0;
		int numero;
		String sql = "";

		try {

			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/programacion_BD", "root", "");

			sentenciaSQL = conexion.createStatement();

			System.out.println("Que patrocinador deseas eliminar, selecciona su id");
			numero = pedirDatos.numero();

			sql = Control.eliminarPatrocinador(numero);

			resultado = sentenciaSQL.executeUpdate(sql);

			if (resultado >= 1) {
				System.out.println(" ");
				System.out.println("Eliminado correctamente");
				System.out.println(" ");
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				sentenciaSQL.close();
				conexion.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}

	}
	
	public static void seleccionarTotal(String tipo){
		Connection conexion = null;
		Statement sentenciaSQL = null;
		ResultSet rs;
		String sql = "";

		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/programacion_BD", "root", "");

			sentenciaSQL = conexion.createStatement();

			sql = Control.conteoPilotos(tipo);

			rs = sentenciaSQL.executeQuery(sql);

			System.out.println(" ");

			while (rs.next()) {
				System.out.println("Total pilotos: " + rs.getInt("count(*)"));
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				sentenciaSQL.close();
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		System.out.println(" ");
	}
	
	public static void actualizarPiloto()  {
		Connection conexion = null;
		Statement sentenciaSQL = null;
		ResultSet rs;
		int resultado = 0;
		int numero;
		String sql = "";

		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/programacion_BD", "root", "");

			sentenciaSQL = conexion.createStatement();

			System.out.println("id del Piloto que quieras editar: ");
			numero = pedirDatos.numero();
			Piloto p2 = pedirDatos.pedirPiloto();

			sql = Control.actualizarPilto(p2, numero);

			resultado = sentenciaSQL.executeUpdate(sql);

			if (resultado >= 1) {
				System.out.println(" ");
				System.out.println("Piloto actualizado correctamente");
				System.out.println(" ");
			} else {
				System.out.println(" ");
				System.out.println("Error al actualizar el piloto");
				System.out.println(" ");
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				sentenciaSQL.close();
				conexion.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}
	}
	
	public static void actualizarPatrocinador() {
		Connection conexion = null;
		Statement sentenciaSQL = null;
		ResultSet rs;
		int resultado = 0;
		int numero;
		String sql = "";

		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/programacion_BD", "root", "");

			sentenciaSQL = conexion.createStatement();

			System.out.println("id del Patrocinador que quieras editar: ");
			numero = pedirDatos.numero();
			Patrocinador p1 = pedirDatos.pedirPatrocinador();

			sql = Control.actualizarPatrocinador(p1, numero);

			resultado = sentenciaSQL.executeUpdate(sql);

			if (resultado >= 1) {
				System.out.println(" ");
				System.out.println("Piloto actualizado correctamente");
				System.out.println(" ");
			} else {
				System.out.println(" ");
				System.out.println("Error al actualizar el piloto");
				System.out.println(" ");
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				sentenciaSQL.close();
				conexion.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}
	}
	
	public static void eliminarPiloto() {
		Connection conexion = null;
		Statement sentenciaSQL = null;
		ResultSet rs;
		int resultado = 0;
		int numero;
		String sql = "";

		try {

			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/programacion_BD", "root", "");

			sentenciaSQL = conexion.createStatement();

			System.out.println("Que Piloto deseas eliminar, selecciona su id");
			seleccionar("piloto");
			numero = pedirDatos.numero();

			sql = Control.eliminarPiloto(numero);

			resultado = sentenciaSQL.executeUpdate(sql);
			
			sql = Control.eliminarPiloto2Tabla(numero);
			
			resultado = sentenciaSQL.executeUpdate(sql);

			if (resultado >= 1) {
				System.out.println(" ");
				System.out.println("Eliminado correctamente");
				System.out.println(" ");
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				sentenciaSQL.close();
				conexion.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}

	}
	
	public static void insertarPatrocinadorAPiloto() {
		Scanner sc = new Scanner(System.in);
		Connection conexion = null;
		Statement sentenciaSQL = null;
		ResultSet rs;
		int resultado = 0;
		int piloto;
		int patrocinador;
		String sql = "";

		try {

			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/programacion_BD", "root", "");

			sentenciaSQL = conexion.createStatement();
			
			System.out.println("Que patrocinador quieres unir");
			seleccionar("patrocinador");
			patrocinador = pedirDatos.numero();
			System.out.println(" ");
			System.out.println("A que piloto quieres unir ese patrocinador");
			seleccionar("piloto");
			piloto = pedirDatos.numero();

			sql = Control.unirPilotoAPatro(patrocinador, piloto);

			resultado = sentenciaSQL.executeUpdate(sql);

			if (resultado >= 1) {
				System.out.println(" ");
				System.out.println("Patrocinador unido al piloto correctamente");
				System.out.println(" ");
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				sentenciaSQL.close();
				conexion.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}

	}
	
	public static void seleccionarPatroPiloto(String tipo)  {
		Connection conexion = null;
		Statement sentenciaSQL = null;
		ResultSet rs;
		String sql = "";
		int patrociandor;

		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/programacion_BD", "root", "");

			sentenciaSQL = conexion.createStatement();
			
		if(tipo.equals("patrocinador")) {
			System.out.println("De que patrocinador quieres ver los pilotos");
			seleccionar("patrocinador");
			patrociandor = pedirDatos.numero();

			sql = Control.seleccionarPilotoCPatro(patrociandor);

			rs = sentenciaSQL.executeQuery(sql);

			System.out.println(" ");

			while (rs.next()) {
				System.out.println("id Piloto: " + rs.getInt("id") + " - Nombre: " + rs.getString("nombre") + " - Dorsal: " + rs.getInt("dorsal") + " - Edad: " + rs.getInt("edad") + " - Origen: " + rs.getString("origen"));
			}
		}
		
		if(tipo.equals("piloto")) {
			System.out.println("De que Piloto quieres ver los patrociandores");
			seleccionar("piloto");
			patrociandor = pedirDatos.numero();

			sql = Control.seleccionarPatroCPiloto(patrociandor);

			rs = sentenciaSQL.executeQuery(sql);

			System.out.println(" ");

			while (rs.next()) {
				System.out.println("id Patrocinador: " + rs.getInt("id") + " - Nombre: " + rs.getString("nombre"));
			}
		}
			

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				sentenciaSQL.close();
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		System.out.println(" ");
	}
	
	public static void seleccionarPatrosSinPilo(String tipo){
		Connection conexion = null;
		Statement sentenciaSQL = null;
		ResultSet rs;
		String sql = "";

		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/programacion_BD", "root", "");

			sentenciaSQL = conexion.createStatement();;

			System.out.println(" ");
			
			if(tipo.equals("patrocinador")) {
				sql = Control.seleccionarPatroSin("patrocinador");

				rs = sentenciaSQL.executeQuery(sql);
				
				System.out.println("Los patrocinadores sin piloto son: ");

				while (rs.next()) {
					System.out.println("id Patrocinador: " + rs.getInt("id") + " - Nombre: " + rs.getString("nombre"));
				}
			}
			
			if(tipo.equals("piloto")) {
				
				sql = Control.seleccionarPatroSin("piloto");

				rs = sentenciaSQL.executeQuery(sql);
				
				System.out.println("Los Pilotos sin patrocinador son: ");

				while (rs.next()) {
					System.out.println("id Piloto: " + rs.getInt("id") + " - Nombre: " + rs.getString("nombre") + " - Dorsal: " + rs.getInt("dorsal") + " - Edad: " + rs.getInt("edad") + " - Origen: " + rs.getString("origen"));
				}
			}
			
			

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				sentenciaSQL.close();
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		System.out.println(" ");
	}
	
	public static void seleccionarMayorPatro(){
		Connection conexion = null;
		Statement sentenciaSQL = null;
		ResultSet rs;
		String sql = "";

		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/programacion_BD", "root", "");

			sentenciaSQL = conexion.createStatement();;

			System.out.println(" ");
			
			sql = Control.PatroMasUsado();

			rs = sentenciaSQL.executeQuery(sql);
				
			System.out.println("El patrocinador mas usado es: ");

			while (rs.next()) {
				System.out.println("id Patrocinador: " + rs.getInt("id") + " - Nombre: " + rs.getString("nombre"));
			}
			
			

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				sentenciaSQL.close();
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		System.out.println(" ");
	}

}
