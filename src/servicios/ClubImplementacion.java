package servicios;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	public void eliminarClub() throws IOException {
	    
	    String club =utilidades.Util.pedirNombreClub();

	    ConsultasInterfaz consultaClub = new ConsultaslImplementacion();
	    consultaClub.eliminarClubDeBBDD(club);
	}
	
	public void modificarDatosClub() throws IOException {
	    String nombreClub = utilidades.Util.pedirNombreClub();
	    boolean clubEncontrado = false;

	    // Verificar si el club existe en la base de datos
	    try (Connection conexion = cbd.abrirConexion();
	         PreparedStatement verificarSentencia = conexion.prepareStatement("SELECT * FROM clubes WHERE nombreclub = ?")) {

	        verificarSentencia.setString(1, nombreClub);
	        ResultSet resultado = verificarSentencia.executeQuery();

	        if (resultado.next()) {
	            clubEncontrado = true;

	            // Si el club existe, mostrar opciones de modificación
	            int campoIntroducido = mostrarCamposYseleccionParaClub();

	            if (campoIntroducido >= 1 && campoIntroducido <= 2) {
	                modificarCampoClubEnBBDD(nombreClub, campoIntroducido);
	            } else {
	                System.out.println("[ERROR] - Opción no válida. Intente nuevamente.");
	            }
	        } else {
	            System.out.println("El club no existe.");
	        }
	    } catch (SQLException e) {
	        System.err.println("Error al buscar el club en la base de datos: " + e.getMessage());
	    }
	}

	// Método para modificar el campo del club en la base de datos
	private void modificarCampoClubEnBBDD(String nombreClub, int campoIntroducido) throws IOException {
	    String nuevoValor = "";

	    // Obtener el nuevo valor para el campo seleccionado
	    switch (campoIntroducido) {
	        case 1:
	            System.out.println("[INFO] - Escriba el nuevo nombre del club:");
	            nuevoValor = Inicio.sc.nextLine();
	            actualizarCampoEnBBDDClub("nombreclub", nuevoValor, nombreClub);
	            break;
	        case 2:
	            System.out.println("[INFO] - Escriba la nueva sede del club:");
	            nuevoValor = Inicio.sc.nextLine();
	            actualizarCampoEnBBDDClub("sedeclub", nuevoValor, nombreClub);
	            break;
	        default:
	            System.out.println("[ERROR] - Opción no válida.");
	            break;
	    }
	}

	// Método para realizar la actualización en la base de datos
	private void actualizarCampoEnBBDDClub(String campo, String nuevoValor, String nombreClub)throws IOException {
	    String consultaSQL = "UPDATE clubes SET " + campo + " = ? WHERE nombreclub = ?";

	    try (Connection conexion = cbd.abrirConexion();
	         PreparedStatement sentencia = conexion.prepareStatement(consultaSQL)) {

	        // Establecer los valores para la actualización
	        sentencia.setString(1, nuevoValor);
	        sentencia.setString(2, nombreClub);

	        // Ejecutar la actualización
	        int filasAfectadas = sentencia.executeUpdate();

	        if (filasAfectadas > 0) {
	            System.out.println("[INFO] - El campo " + campo + " ha sido actualizado correctamente.");
	        } else {
	            System.out.println("[ERROR] - No se pudo actualizar el campo.");
	        }
	    } catch (SQLException e) {
	        System.err.println("Error al actualizar el campo en la base de datos: " + e.getMessage());
	    }
	}

	// Método para mostrar los campos disponibles para modificar en el club
	private int mostrarCamposYseleccionParaClub() {
	    int campoIntroducido;

	    System.out.println("1. Nombre del club");
	    System.out.println("2. Sede del club");

	    String entrada = Inicio.sc.nextLine();
	    try {
	        campoIntroducido = Integer.parseInt(entrada);
	    } catch (NumberFormatException e) {
	        campoIntroducido = -1;
	    }

	    return campoIntroducido;
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
