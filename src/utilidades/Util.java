package utilidades;

import controladores.Inicio;

public class Util {
	
public static long calcularIdUsuario() {
		
		long nuevoId = 0;
		int tamanio = Inicio.listaMiembros.size();

		if (tamanio > 0)
		{

		    nuevoId = Inicio.listaMiembros.get(tamanio - 1).getIdUsuario()+1;

		}
		else
		{

		    nuevoId = 1;

		}

		return nuevoId;

		
	}

public static long calcularIdClub() {
	
	long nuevoIdClub = 0;
	int tamanio = Inicio.listaClubes.size();

	if (tamanio > 0)
	{

	    nuevoIdClub = Inicio.listaClubes.get(tamanio - 1).getIdClub()+1;

	}
	else
	{

	    nuevoIdClub = 1;

	}

	return nuevoIdClub;

	
}
}
