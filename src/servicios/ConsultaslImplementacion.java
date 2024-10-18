package servicios;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConsultaslImplementacion implements ConsultasInterfaz {

    ConexionBBDDInterfaz cbd = new ConexionBBDDPostgresqlImplementacion();

    // Método para eliminar un miembro
    @Override
    public void eliminarMiembroDeBBDD(String dniUsuario) throws IOException {
        // Uso de try-with-resources para asegurar el cierre de recursos
        try (Connection conexion = cbd.abrirConexion();
             PreparedStatement sentencia = conexion.prepareStatement("DELETE FROM usuarios WHERE dniusuario = ?")) {

            // Establecer el parámetro del DNI en la consulta
            sentencia.setString(1, dniUsuario);

            // Ejecutar la consulta de eliminación
            int filasAfectadas = sentencia.executeUpdate();

            // Verificar si se eliminó algún registro
            if (filasAfectadas > 0) {
                System.out.println("El miembro con DNI " + dniUsuario + " se ha eliminado con éxito de la base de datos.");
            } else {
                System.out.println("No se encontró ningún miembro con el DNI proporcionado en la base de datos.");
            }
        } catch (SQLException e) {
            // Loggear la excepción completa para mayor detalle
            System.err.println("Error al eliminar el miembro de la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para eliminar un club
    @Override
    public void eliminarClubDeBBDD(String nombreClub) throws IOException {
        // Uso de try-with-resources para asegurar el cierre de recursos
        try (Connection conexion = cbd.abrirConexion();
             PreparedStatement sentencia = conexion.prepareStatement("DELETE FROM clubes WHERE nombreclub = ?")) {

            // Establecer el parámetro del nombre en la consulta
            sentencia.setString(1, nombreClub);

            // Ejecutar la consulta de eliminación
            int filasAfectadas = sentencia.executeUpdate();

            // Verificar si se eliminó algún registro
            if (filasAfectadas > 0) {
                System.out.println("El club con el nombre " + nombreClub + " se ha eliminado con éxito de la base de datos.");
            } else {
                System.out.println("No se encontró ningún club con el nombre proporcionado en la base de datos.");
            }
        } catch (SQLException e) {
            // Loggear la excepción completa para mayor detalle
            System.err.println("Error al eliminar el club de la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
