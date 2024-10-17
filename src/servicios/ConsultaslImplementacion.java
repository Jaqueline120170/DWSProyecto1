package servicios;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controladores.Inicio;
import dtos.ClubDto;
import dtos.MiembroDto;

public class ConsultaslImplementacion implements ConsultasInterfaz {

	ConexionBBDDInterfaz cbd = new ConexionBBDDPostgresqlImplementacion();
	@Override
	public void cargaBBDD()throws IOException {
		
		Connection conexion = null;
		Statement sentencia=null;
		try {
			
			conexion = cbd.abrirConexion();
			sentencia = conexion.createStatement();
			ResultSet resultado = sentencia.executeQuery(" SELECT * FROM usuarios ");
			
			while(resultado.next()) {
				MiembroDto miembro = new MiembroDto();
				
				miembro.setIdUsuario(resultado.getLong(1));
				miembro.setNombreUsuario(resultado.getString(2));
				miembro.setApellidoUsuario(resultado.getString(3));
				miembro.setDniUsuario(resultado.getString(4));
				miembro.setEmailUsuario(resultado.getString(5));
				
				Inicio.listaMiembros.add(miembro);
			}
			
		}catch(SQLException e) {
			System.out.println("Error al hacer la carga inicial de usuarios "+e.getMessage());
		}finally {
			
			cbd.cerrarConexion();
		}
		
	}
	
	@Override
	public void eliminarMiembroDeBBDD(String dniUsuario) throws IOException {
	    Connection conexion = null;
	    PreparedStatement sentencia = null;

	    try {
	        // Abrir conexión a la base de datos
	        conexion = cbd.abrirConexion();

	        // Consulta SQL para eliminar el miembro por su DNI
	        String eliminarMiembroSQL = "DELETE FROM usuarios WHERE dniusuario = ?";
	        sentencia = conexion.prepareStatement(eliminarMiembroSQL);
	        sentencia.setString(1, dniUsuario);

	        // Ejecutar la consulta y verificar cuántas filas fueron afectadas
	        int filasAfectadas = sentencia.executeUpdate();

	        if (filasAfectadas > 0) {
	            System.out.println("El miembro con DNI " + dniUsuario + " se ha eliminado con éxito de la base de datos.");
	        } else {
	            System.out.println("No se encontró ningún miembro con el DNI proporcionado en la base de datos.");
	        }

	    } catch (SQLException e) {
	        System.out.println("Error al eliminar el miembro de la base de datos: " + e.getMessage());
	    } finally {
	        // Cerrar la conexión y el statement si están abiertos
	        try {
	            if (sentencia != null) {
	                sentencia.close();
	            }
	            if (conexion != null) {
	                cbd.cerrarConexion();
	            }
	        } catch (SQLException e) {
	            System.out.println("Error al cerrar la conexión: " + e.getMessage());
	        }
	    }
	}

}
