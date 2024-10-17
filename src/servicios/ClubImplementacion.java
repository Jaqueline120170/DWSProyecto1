package servicios;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import controladores.Inicio;
import dtos.ClubDto;
import dtos.MiembroDto;

public class ClubImplementacion implements ClubInterfaz {
	
	ConexionBBDDInterfaz cbd = new ConexionBBDDPostgresqlImplementacion();
	
	//Método privado que se encarga de pedir datos necesarios para registrar un club nuevo
	private ClubDto pedirDatosClub() throws IOException{
		
		ClubDto club = new ClubDto();
		
		System.out.println("Introduzca el nombre del club: ");
		club.setNombreClub(Inicio.sc.next());
		System.out.println("Introduzca la sede del club:");
		club.setSedeClub(Inicio.sc.next());
		club.setIdClub(utilidades.Util.calcularIdClub());
		
		return club;
	}
	
	// Método para dar de alta un club sin usar listas locales
	public void altaClub() throws IOException {
	    ClubDto nuevoClub = pedirDatosClub(); // Obtener los datos del nuevo club

	    try {
	        // Abrir la conexión a la base de datos
	        cbd.abrirConexion();

	        // Consulta SQL para insertar el nuevo club
	        String insertarClub = "INSERT INTO clubes (idclub, nombreclub, sedeclub) VALUES (?, ?, ?)";
	        PreparedStatement sentencia = cbd.abrirConexion().prepareStatement(insertarClub);

	        // Establecer los parámetros en la consulta
	        sentencia.setLong(1, nuevoClub.getIdClub());
	        sentencia.setString(2, nuevoClub.getNombreClub());
	        sentencia.setString(3, nuevoClub.getSedeClub());

	        // Ejecutar la inserción en la base de datos
	        int filasAfectadas = sentencia.executeUpdate();

	        // Verificar si la inserción fue exitosa
	        if (filasAfectadas > 0) {
	            System.out.println("Alta exitosa. El club ha sido registrado en la base de datos.");
	        } else {
	            System.out.println("No se pudo registrar el club. Inténtelo de nuevo.");
	        }
	    } catch (SQLException ex) {
	        System.out.println("Error al insertar datos: " + ex.getMessage());
	    } finally {
	        // Cerrar la conexión a la base de datos
	        cbd.cerrarConexion();
	    }
	}
	
	@Override
	public void eliminarMiembro() throws IOException {
	    System.out.println("Introduzca el DNI del miembro que desea eliminar:");
	    String dni = Inicio.sc.nextLine();

	    ConsultasInterfaz consulta = new ConsultaslImplementacion();
	    consulta.eliminarMiembroDeBBDD(dni);
	}

	/*
	// Método para eliminar un miembro del club
	public void eliminarMiembro() throws IOException {
	    boolean existe = false;
	    MiembroDto miembroAux = null;
	    
	    // Mostrar todos los miembros actuales
	    for (MiembroDto miembro : Inicio.listaMiembros) {
	        System.out.println(miembro.toString());
	    }

	    // Pedir el DNI del miembro que se desea eliminar
	    System.out.println("Introduzca el DNI del miembro que desea eliminar:");
	    String dni = Inicio.sc.nextLine();

	    // Buscar al miembro en la lista local
	    for (MiembroDto miembro : Inicio.listaMiembros) {
	        if (dni.equals(miembro.getDniUsuario())) {
	            miembroAux = miembro;
	            existe = true;
	            break;
	        }
	        
	    }

	    // Si el miembro existe, proceder a eliminarlo de la base de datos
	    if (existe && miembroAux != null) {
	        try {
	            cbd.abrirConexion();

	            // Consulta SQL para eliminar al miembro de la base de datos
	            String eliminarMiembroSQL = "DELETE FROM usuarios WHERE dniusuario = ?";
	            PreparedStatement sentencia = cbd.abrirConexion().prepareStatement(eliminarMiembroSQL);
	            sentencia.setString(1, miembroAux.getDniUsuario());

	            int filasAfectadas = sentencia.executeUpdate();

	            if (filasAfectadas > 0) {
	                // Si la eliminación en la base de datos fue exitosa, eliminar también de la lista local
	                Inicio.listaMiembros.remove(miembroAux);
	                System.out.println("El miembro se ha eliminado con éxito.");
	            } else {
	                System.out.println("No se encontró al miembro en la base de datos.");
	            }
	        } catch (SQLException ex) {
	            System.out.println("Error al eliminar el miembro: " + ex.getMessage());
	        } finally {
	            cbd.cerrarConexion();
	        }
	    } else {
	        System.out.println("El miembro que desea eliminar no existe. Verifique y vuelva a intentar.");
	    }
	}
	*/
}
