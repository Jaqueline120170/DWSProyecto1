package servicios;

import java.io.IOException;

public interface MenuInterfaz {
	
	public int mostrarMenuYSeleccion();
	public void gestionMiembros()throws IOException;
	public void gestionClubes()throws IOException;


}