package servicios;

import java.io.IOException;

public interface ConsultasInterfaz {
	
	public void eliminarMiembroDeBBDD(String dniUsuario) throws IOException;
	public void eliminarClubDeBBDD(String nombreClub) throws IOException;
}
