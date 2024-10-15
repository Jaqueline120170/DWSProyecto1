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


}
