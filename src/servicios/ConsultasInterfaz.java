package servicios;

import java.io.IOException;

public interface ConsultasInterfaz {
	public void cargaBBDD()throws IOException ;
	public void eliminarMiembroDeBBDD(String dniUsuario) throws IOException;
}
