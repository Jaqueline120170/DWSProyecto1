package controladores;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import servicios.MenuImplementacion;
import servicios.MenuInterfaz;
import dtos.ClubDto;
import dtos.MiembroDto;

public class Inicio {
	
	//Listas estaticas para poder llamarlas desde cualquier aprte del codigocuando se necesiten
	//como referencia para otras funcionalidades.
	public static List<ClubDto> listaClubes = new ArrayList <ClubDto>();
	public static List<MiembroDto> listaMiembros = new ArrayList<MiembroDto>();
	public static Scanner sc= new Scanner(System.in);
	static LocalDateTime fechaActual = LocalDateTime.now();
	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	public static String fechaYhora = fechaActual.format(formatter);

	//Método principal de acceso a la aplicacion
	public static void main(String[] args) {
		
		//Contructores de clases e interfaces
		MenuInterfaz mi = new MenuImplementacion();
		
		try {
		int opcion;
		boolean cerrarMenu=false;
		
		while(!cerrarMenu) {
			
			opcion= mi.mostrarMenuYSeleccion();
			switch(opcion) {
			case 0:
				utilidades.GestorFicheros.sobreEscribir(fechaYhora +  "0  Acceso a menu principal");
				cerrarMenu=true;
				break;
			case 1:
				utilidades.GestorFicheros.sobreEscribir(fechaYhora +  "1  Acceso a menu miembros");
				mi.gestionMiembros();
				break;
			case 2:
				utilidades.GestorFicheros.sobreEscribir(fechaYhora +  "2  Acceso a menu clubes");
				mi.gestionClubes();
				break;
			default: 
				System.out.println("Introduzca una opcion valida");
			}
		}
				
	}catch(Exception e) {
		String mensajeError = "Error general en la aplicación: " + e.getMessage();
		utilidades.GestorFicheros.sobreEscribir(fechaYhora + " " + mensajeError);
		 System.out.println("Error al escribir en el archivo: " + e.getMessage());
  	 }

}
}