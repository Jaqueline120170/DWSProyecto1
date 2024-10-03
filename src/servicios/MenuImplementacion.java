package servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dtos.MiembrosDto;

public class MenuImplementacion implements MenuInterfaz {
	Scanner sc = new Scanner (System.in);
	OperativaInterfaz oi = new OperativaImplementacion();
	List<MiembrosDto> listaMiembros = new ArrayList<MiembrosDto>();
	
public int mostrarMenuYSeleccion() {
		
		System.out.println("0. Cerrar menu");
		System.out.println("1. Gestiones usuarios");
		System.out.println("2. Gestiones Clubes");
		System.out.println("Seleccione una opcion");
		int seleccionUsuario= sc.nextInt();
		return seleccionUsuario;
	}
	
public void gestionMiembros() {
		
		int opcionMiembro = menuGestionMiembros();
		switch(opcionMiembro) {
		case 0 :
			break;
		case 1:
			oi.altaMiembro( listaMiembros);
			break;
		case 2:
			//oi.solicitudClub();
			break;
		case 3:
			//oi.crearRuta();
			break;
		case 4:
			//oi.registrarMoto();
			break;
		default:
			System.out.println("Introduzca una opcion valida");
			break;
		}
	}

private int menuGestionMiembros() {
	int seleccionGestionMiembro;
	System.out.println("0. Volver al inicio");
	System.out.println("1. Dar alta Miembro");
	System.out.println("2. Enviar solicitud a Club");
	System.out.println("3. Crear una ruta");
	System.out.println("4. Registrar una moto");
	System.out.println("Seleccione una opcion");
	seleccionGestionMiembro=sc.nextInt();
	return seleccionGestionMiembro;
}

public void gestionClubes() {
	
	int opcionClub = menuGestionClubes();
	switch(opcionClub) {
	case 0 :
		break;
	case 1:
		//oi.registrarClub();
		break;
	case 2:
		//oi.registrarMiembro();
		break;
	case 3:
		//oi.eliminarMiembro();
		break;
	case 4:
		//oi.registrarSede();
		break;
	case 5:
		//oi.crearRuta();
		break;
	case 6:
		//oi.crearEvento();
		break;
	default:
		System.out.println("Introduzca una opcion valida");
		break;
	}
}

private int menuGestionClubes() {
int seleccionGestionClub;
System.out.println("0. Volver al inicio");
System.out.println("1. Registrar un club");
System.out.println("2. Registrar a un Miembro");
System.out.println("3. Eliminar a un Miembro");
System.out.println("4. Registrar una sede");
System.out.println("5. Crear una ruta");
System.out.println("6. Crear un evento");
System.out.println("Seleccione una opcion");
seleccionGestionClub=sc.nextInt();
return seleccionGestionClub;
}
}
