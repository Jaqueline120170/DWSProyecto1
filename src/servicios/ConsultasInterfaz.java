package servicios;

import java.sql.Connection;
import java.util.List;

import dtos.MiembrosDto;

public interface ConsultasInterfaz {
	
	public List<MiembrosDto> consultaUsuario(Connection conexion);
}
