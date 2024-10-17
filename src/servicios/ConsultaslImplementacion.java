package servicios;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultaslImplementacion implements ConsultasInterfaz {
	
    ConexionBBDDInterfaz cbd = new ConexionBBDDPostgresqlImplementacion();

    // Método para eliminar un miembro
    @Override
    public void eliminarMiembroDeBBDD(String dniUsuario) throws IOException {
        Connection conexion = null;
        PreparedStatement sentencia = null;

        try {
            conexion = cbd.abrirConexion();
            String eliminarMiembroSQL = "DELETE FROM usuarios WHERE dniusuario = ?";
            sentencia = conexion.prepareStatement(eliminarMiembroSQL);
            sentencia.setString(1, dniUsuario);

            int filasAfectadas = sentencia.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("El miembro con DNI " + dniUsuario + " se ha eliminado con éxito de la base de datos.");
            } else {
                System.out.println("No se encontró ningún miembro con el DNI proporcionado en la base de datos.");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar el miembro de la base de datos: " + e.getMessage());
        } finally {
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
    
    // Otras consultas relacionadas a miembros o clubes pueden ir aquí
}
