package servicios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dtos.MiembroDto;

public class MenuImplementacion implements MenuInterfaz {
	Scanner sc = new Scanner (System.in);
	MiembroInterfaz mie = new MiembroImplementacion();
	ClubInterfaz ci = new ClubImplementacion();
	List<MiembroDto> listaMiembros = new ArrayList<MiembroDto>();
	
public int mostrarMenuYSeleccion() {
		
		System.out.println("##########################");
		System.out.println("0. Cerrar menu");
		System.out.println("1. Gestiones Usuarios");
		System.out.println("2. Gestiones Clubes");
		System.out.println("Seleccione una opcion");
		System.out.println("##########################");
		int seleccionUsuario= sc.nextInt();
		return seleccionUsuario;
	}
	
public void gestionMiembros()throws IOException {
		
		int opcionMiembro = menuGestionMiembros();
		switch(opcionMiembro) {
		case 0 :
			break;
		case 1:
			mie.altaMiembro();
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
		case 5:
			mie.modificarDatosMiembro();
			break;
		case 6:
			mie.eliminarMiembro();
			break;
		default:
			System.out.println("Introduzca una opcion valida");
			break;
		}
	}

private int menuGestionMiembros() {
	int seleccionGestionMiembro;
	System.out.println("##########################");
	System.out.println("0. Volver al inicio");
	System.out.println("1. Dar alta Miembro");
	System.out.println("2. Enviar solicitud a Club");
	System.out.println("3. Crear una ruta");
	System.out.println("4. Registrar una moto");
	System.out.println("5. Modificar Datos de un miembro");
	System.out.println("6. Dar de baja Miembro");
	System.out.println("Seleccione una opcion");
	System.out.println("##########################");
	seleccionGestionMiembro=sc.nextInt();
	return seleccionGestionMiembro;
}

public void gestionClubes()throws IOException {
	
	int opcionClub = menuGestionClubes();
	switch(opcionClub) {
	case 0 :
		break;
	case 1:
		ci.altaClub();
		break;
	case 2:
		//oi.registrarMiembro();
		break;
	case 3:
		ci.eliminarClub();
		break;
	case 4:
		//oi.registrarSede();
		break;
	case 5:
		ci.modificarDatosClub();
		break;
	case 6:
		//oi.crearEvento();
		break;
	case 7:
		//oi.crearRuta();
		break;
	default:
		System.out.println("Introduzca una opcion valida");
		break;
	}
}

private int menuGestionClubes() {
int seleccionGestionClub;
System.out.println("##########################");
System.out.println("0. Volver al inicio");
System.out.println("1. Registrar un club");
System.out.println("2. Registrar a un Miembro");
System.out.println("3. Dar de baja un club");
System.out.println("4. Registrar una sede");
System.out.println("5. Modificar datos Club");
System.out.println("6. Crear un evento");
System.out.println("6. Crear una ruta");
System.out.println("Seleccione una opcion");
System.out.println("##########################");
seleccionGestionClub=sc.nextInt();
return seleccionGestionClub;
}
}