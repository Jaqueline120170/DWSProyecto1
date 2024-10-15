package servicios;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import controladores.Inicio;
import dtos.MiembrosDto;


public class ConsultaslImplementacion implements ConsultasInterfaz {

    @Override
    public List<MiembrosDto> consultaUsuario(Connection conexion) {
        
        java.sql.Statement declaracionSQL = null;
        ResultSet resultadoConsulta = null;
        MiembrosDto miembro = new 	MiembrosDto();
        
        try {
            // Se abre una declaración
            declaracionSQL = conexion.createStatement();
            // Se define la consulta de la declaración y se ejecuta
            resultadoConsulta = declaracionSQL.executeQuery("SELECT * FROM public.\"usuarios\"");
            
            // Llamada a la conversión a UsuarioDto
            Inicio.listaMiembros =miembro.resultset(resultadoConsulta);  // Se asigna a la lista estática
            
            int i = Inicio.listaMiembros.size();
            System.out.println("[INFORMACIÓN-ConsultaPostgresqlImplementacion-consultaUsuario] Número de usuarios: " + i);
            
            // Cierre de conexión, declaración y resultado
            resultadoConsulta.close();
            declaracionSQL.close();
            conexion.close();
            
        } catch (SQLException e) {
            System.err.println("[ERROR-ConsultaPostgresqlImplementacion-consultaUsuario] Error generando o ejecutando la declaración SQL: " + e.getMessage());
        }
        
        return Inicio.listaMiembros;
    }
}
