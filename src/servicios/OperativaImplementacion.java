package servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dtos.MiembrosDto;

public class OperativaImplementacion implements OperativaInterfaz {
	
	Scanner sc = new Scanner (System.in);
	List<MiembrosDto> listaMiembros = new ArrayList<MiembrosDto>();
	
	private void pedirDatos() {
		
		System.out.println("DAR ALTA MIEMBRO");
		
		long idCalculado = calculoId(listaMiembros);
		System.out.println("Introduzca su nombre");
		String nombre =sc.next();
		System.out.println("Introduzca sus apellidos");
		String apellidos =sc.next();
		System.out.println("Introduzca su DNI");
		String dni= sc.next();
		System.out.println("Introduzca su email");
		String email =sc.next();
		
		MiembrosDto miembro = new MiembrosDto (idCalculado,nombre,apellidos,dni,email);
		listaMiembros.add(miembro);
	}
	
	public void altaMiembro(List<MiembrosDto> listaMiembros) {
		
		MiembrosDto miembroNuevo =new MiembrosDto ();
		String usuarioNuevo= "INSERT INTO tabla (col4, col3) VALUES (val4, val3)";
		
		
	}
	
	private long calculoId (List<MiembrosDto>listaMiembros) {
		
		  long idCalculado;
	        int tamanioLista = listaMiembros.size();
	        if (tamanioLista == 0)
	        {
	            idCalculado = 1;
	        }
	        else
	        {
	            idCalculado = listaMiembros.get(tamanioLista-1).getIdUsuario();
	        }

	        return idCalculado;
	}

}
