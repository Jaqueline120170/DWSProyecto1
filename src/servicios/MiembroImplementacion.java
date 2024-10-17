package servicios;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import controladores.Inicio;
import dtos.MiembroDto;

public class MiembroImplementacion implements MiembroInterfaz {
	
	ConexionBBDDInterfaz cbd = new ConexionBBDDPostgresqlImplementacion();
	
	private MiembroDto pedirDatosMiembro() throws IOException{
		MiembroDto miembro = new MiembroDto();
		
		System.out.println("Introduzca su nombre: ");
		miembro.setNombreUsuario(Inicio.sc.next());
		System.out.println("Introduzca sus apellidos");
		miembro.setApellidoUsuario(Inicio.sc.next());
		System.out.println("Introduzca su dni deben ser 8 digitos: ");
		miembro.setDniUsuario(Inicio.sc.next());
		System.out.println("Introduzca su email: ");
		miembro.setEmailUsuario(Inicio.sc.next());
		miembro.setIdUsuario(utilidades.Util.calcularIdUsuario());
		
		return miembro;
	}
	@Override
	public void altaMiembro()throws IOException{
		ConsultasInterfaz consulta = new ConsultaslImplementacion();
		consulta.cargaBBDD();
		MiembroDto nuevoMiembro= pedirDatosMiembro();
		
		try {
			
			cbd.abrirConexion();
			
			String insertarMiembro="INSERT INTO usuarios (idusuario, nombreusuario,apellidousuario,dniusuario,emailusuario) VALUES (?,?,?,?,?)";	
			PreparedStatement sentencia = cbd.abrirConexion().prepareStatement(insertarMiembro);
			
			sentencia.setLong(1, nuevoMiembro.getIdUsuario());
			sentencia.setString(2,nuevoMiembro.getNombreUsuario());
			sentencia.setString(3, nuevoMiembro.getApellidoUsuario());
			sentencia.setString(4,nuevoMiembro.getDniUsuario());
			sentencia.setString(5, nuevoMiembro.getEmailUsuario());
			
			sentencia.executeUpdate();
			
		}catch(SQLException ex) {
			System.out.println("Error al insertar datos"+ex.getMessage());
		}finally {
			cbd.cerrarConexion();
		}
		
		Inicio.listaMiembros.add(nuevoMiembro);
		System.out.println("Alta exitosa");
		
	}
}
