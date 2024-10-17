package servicios;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import controladores.Inicio;
import dtos.MiembroDto;

public class MiembroImplementacion implements MiembroInterfaz {
	
    ConexionBBDDInterfaz cbd = new ConexionBBDDPostgresqlImplementacion();
	
    // Método para pedir datos de un miembro
    private MiembroDto pedirDatosMiembro() throws IOException {
        MiembroDto miembro = new MiembroDto();
		
        System.out.println("Introduzca su nombre: ");
        miembro.setNombreUsuario(Inicio.sc.next());
        System.out.println("Introduzca sus apellidos");
        miembro.setApellidoUsuario(Inicio.sc.next());
        System.out.println("Introduzca su DNI (deben ser 8 dígitos): ");
        miembro.setDniUsuario(Inicio.sc.next());
        System.out.println("Introduzca su email: ");
        miembro.setEmailUsuario(Inicio.sc.next());
        miembro.setIdUsuario(utilidades.Util.calcularIdUsuario());
		
        return miembro;
    }
    
    // Método para dar de alta un miembro sin usar listas locales
    @Override
    public void altaMiembro() throws IOException {
        MiembroDto nuevoMiembro = pedirDatosMiembro(); // Obtener los datos del nuevo miembro
		
        try {
            // Abrir la conexión a la base de datos
            cbd.abrirConexion();
			
            // Consulta SQL para insertar el nuevo miembro
            String insertarMiembro = "INSERT INTO usuarios (idusuario, nombreusuario, apellidousuario, dniusuario, emailusuario) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement sentencia = cbd.abrirConexion().prepareStatement(insertarMiembro);
			
            // Establecer los parámetros en la consulta
            sentencia.setLong(1, nuevoMiembro.getIdUsuario());
            sentencia.setString(2, nuevoMiembro.getNombreUsuario());
            sentencia.setString(3, nuevoMiembro.getApellidoUsuario());
            sentencia.setString(4, nuevoMiembro.getDniUsuario());
            sentencia.setString(5, nuevoMiembro.getEmailUsuario());
			
            // Ejecutar la inserción en la base de datos
            int filasAfectadas = sentencia.executeUpdate();
			
            // Verificar si la inserción fue exitosa
            if (filasAfectadas > 0) {
                System.out.println("Alta exitosa. El miembro ha sido registrado en la base de datos.");
            } else {
                System.out.println("No se pudo registrar el miembro. Inténtelo de nuevo.");
            }
        } catch (SQLException ex) {
            System.out.println("Error al insertar datos: " + ex.getMessage());
        } finally {
            // Cerrar la conexión a la base de datos
            cbd.cerrarConexion();
        }
    }
}
