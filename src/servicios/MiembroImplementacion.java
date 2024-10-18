package servicios;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

        // Validar los datos del miembro (se puede mejorar con expresiones regulares)
        if (!nuevoMiembro.getEmailUsuario().contains("@")) {
            System.out.println("El correo electrónico no es válido.");
            return;
        }

        // Uso de try-with-resources para manejar la conexión
        try (Connection conexion = cbd.abrirConexion();
             PreparedStatement sentencia = conexion.prepareStatement(
                 "INSERT INTO usuarios (idusuario, nombreusuario, apellidousuario, dniusuario, emailusuario) VALUES (?, ?, ?, ?, ?)")) {

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
        }
    }
    
    //Método para eliminar un miembro de la base de datos sin usar listas lcoales
    public void eliminarMiembro() throws IOException {
	    
	    String dni = utilidades.Util.pedirDni();

	    ConsultasInterfaz consulta = new ConsultaslImplementacion();
	    consulta.eliminarMiembroDeBBDD(dni);
	}
    
  //Método para modificar campos de un miembro de la base de datos sin usar listas lcoales
    public void modificarDatosMiembro() throws IOException {
        String dni = utilidades.Util.pedirDni();
        boolean miembroEncontrado = false;

        // Verificar si el miembro existe en la base de datos
        try (Connection conexion = cbd.abrirConexion();
             PreparedStatement verificarSentencia = conexion.prepareStatement("SELECT * FROM usuarios WHERE dniusuario = ?")) {

            verificarSentencia.setString(1, dni);
            ResultSet resultado = verificarSentencia.executeQuery();

            if (resultado.next()) {
                miembroEncontrado = true;

                // Si el miembro existe, mostrar opciones de modificación
                int campoIntroducido = mostrarCamposYseleccion();

                if (campoIntroducido >= 1 && campoIntroducido <= 4) {
                    modificarCampoMiembroEnBBDD(dni, campoIntroducido);
                } else {
                    System.out.println("[ERROR] - Opción no válida. Intente nuevamente.");
                }
            } else {
                System.out.println("El miembro no existe.");
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar el miembro en la base de datos: " + e.getMessage());
        }
    }

    // Método para modificar el campo del miembro en la base de datos
    private void modificarCampoMiembroEnBBDD(String dni, int campoIntroducido) throws IOException {
        String nuevoValor = "";

        // Obtener el nuevo valor para el campo seleccionado
        switch (campoIntroducido) {
            case 1:
                System.out.println("[INFO] - Escriba el nuevo nombre:");
                nuevoValor = Inicio.sc.nextLine();
                actualizarCampoEnBBDD("nombreusuario", nuevoValor, dni);
                break;
            case 2:
                System.out.println("[INFO] - Escriba el nuevo apellido:");
                nuevoValor = Inicio.sc.nextLine();
                actualizarCampoEnBBDD("apellidousuario", nuevoValor, dni);
                break;
            case 3:
                System.out.println("[INFO] - Escriba el nuevo DNI:");
                nuevoValor = Inicio.sc.nextLine();
                actualizarCampoEnBBDD("dniusuario", nuevoValor, dni);
                break;
            case 4:
                System.out.println("[INFO] - Escriba el nuevo email:");
                nuevoValor = Inicio.sc.nextLine();
                actualizarCampoEnBBDD("emailusuario", nuevoValor, dni);
                break;
            default:
                System.out.println("[ERROR] - Opción no válida.");
                break;
        }
    }

    // Método para realizar la actualización en la base de datos
    private void actualizarCampoEnBBDD(String campo, String nuevoValor, String dni)throws IOException {
        String consultaSQL = "UPDATE usuarios SET " + campo + " = ? WHERE dniusuario = ?";

        try (Connection conexion = cbd.abrirConexion();
             PreparedStatement sentencia = conexion.prepareStatement(consultaSQL)) {

            // Establecer los valores para la actualización
            sentencia.setString(1, nuevoValor);
            sentencia.setString(2, dni);

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

    // Método para mostrar los campos disponibles para modificar
    private int mostrarCamposYseleccion() {
        int campoIntroducido;

        System.out.println("1. Nombre");
        System.out.println("2. Apellido");
        System.out.println("3. DNI");
        System.out.println("4. Email");

        String entrada = Inicio.sc.nextLine();
        try {
            campoIntroducido = Integer.parseInt(entrada);
        } catch (NumberFormatException e) {
            campoIntroducido = -1;
        }

        return campoIntroducido;
    }

    /*
    public void modificarDatosMiembro()throws IOException{
    	
    	String dni = utilidades.Util.pedirDni();
    	boolean aModificar = false;
    	for (MiembroDto miembro : Inicio.listaMiembros) {
            if (miembro.getDniUsuario().equals(dni)) {
                
                int campoIntroducido = mostrarCamposYseleccion(); 
                boolean control = true;
                
                while (control) {
                    
                    control = false;
                
                aModificar = true;
                break; 
            }
        }
        
        if (!aModificar) {
           
            System.out.println("El miembro no existe");
        }
    }
    }
    
    private int mostrarCamposYseleccion() {
        int campoIntroducido;

        System.out.println("1. Nombre");
        System.out.println("2. Apellido");
        System.out.println("3. DNI");
        System.out.println("4. Email");

        String entrada = Inicio.sc.nextLine();
        try {
            campoIntroducido = Integer.parseInt(entrada);
        } catch (NumberFormatException e) {
           
            campoIntroducido = -1;
        }

        return campoIntroducido;
    }
    
    private boolean modificarCampoMiembro(MiembroDto miembroAModificar, int campoIntroducido) {
        boolean abrirMenu = true;

        switch (campoIntroducido) {
            case 1:
                System.out.println("[INFO] - Escriba nuevo nombre:");
                String nuevoNombre = Inicio.sc.nextLine();
                miembroAModificar.setNombreUsuario(nuevoNombre);
                break;
            case 2:
                System.out.println("[INFO] - Escriba nuevo apellido:");
                String nuevoApellido = Inicio.sc.nextLine();
                miembroAModificar.setApellidoUsuario(nuevoApellido);
                break;
            case 3:
                System.out.println("[INFO] - Escriba nuevo DNI:");
                String nuevoTelefono = Inicio.sc.nextLine();
                miembroAModificar.setDniUsuario(nuevoTelefono);
                break;
            case 4:
                System.out.println("[INFO] - Escriba nuevo email:");
                String nuevoEmail = Inicio.sc.nextLine();
                miembroAModificar.setEmailUsuario(nuevoEmail);
                break;
            default:
                System.out.println("[ERROR] - Opción no válida. Intente nuevamente.");
                abrirMenu = false; 
                break;
        }

        return abrirMenu;
    }
    */

}

