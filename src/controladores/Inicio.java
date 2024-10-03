package controladores;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import servicios.MenuImplementacion;
import servicios.MenuInterfaz;
import servicios.OperativaInterfaz;
import servicios.OperativaImplementacion;
import dtos.ClubesDto;
import dtos.MiembrosDto;

public class Inicio {
	
	public static List<ClubesDto> listaClubes = new ArrayList <ClubesDto>();
	public static List<MiembrosDto> listaMiembros = new ArrayList<MiembrosDto>();

	public static void main(String[] args) {
		
		MenuInterfaz mi = new MenuImplementacion();
		OperativaInterfaz oi = new OperativaImplementacion();
		Scanner sc = new Scanner(System.in);
		
		int opcion;
		boolean cerrarMenu=false;
		
		while(!cerrarMenu) {
			
			opcion= mi.mostrarMenuYSeleccion();
			switch(opcion) {
			case 0:
				cerrarMenu=true;
				break;
			case 1:
				oi.crearBiblioteca(listaBibliotecas);
				break;
			case 2:
				break;
			default: 
				System.out.println("Introduzca una opcion valida");
			}
		}
				
	}

}
