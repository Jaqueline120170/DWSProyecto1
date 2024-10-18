package servicios;

import java.io.IOException;

public interface MiembroInterfaz {
	
	public void altaMiembro()throws IOException;
	public void eliminarMiembro() throws IOException;
	public void modificarDatosMiembro()throws IOException;
}