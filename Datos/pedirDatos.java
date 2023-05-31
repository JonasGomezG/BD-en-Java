package Datos;

import java.util.Scanner;

public class pedirDatos {
	
	public static Piloto pedirPiloto() {
		int dorsal;
		int edad;
		String nombre;
		String origen;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Nombre del nuevo Piloto: ");
		nombre = sc.nextLine();
		
		System.out.println("Dorsal del nuevo Piloto: ");
		dorsal = sc.nextInt();
		sc.nextLine();
		
		System.out.println("Edad del nuevo Piloto: ");
		edad = sc.nextInt();
		sc.nextLine();		
		
		System.out.println("Origen del nuevo Piloto: ");
		origen = sc.nextLine();
		
		return new Piloto(nombre, dorsal, edad, origen);
	}
	
	public static int numero() {
		Scanner sc = new Scanner(System.in);
		int numero;
		
		System.out.print("");
		numero = sc.nextInt();
		sc.nextLine();
		
		return numero;
	}
	
	public static Patrocinador pedirPatrocinador() {
		String nombre;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Nombre del nuevo Patrocinador");
		nombre = sc.nextLine();
		
		return new Patrocinador(nombre);
	}

}
