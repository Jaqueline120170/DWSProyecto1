package conexionBBDD;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;

public class ConexionBBDD {

	

	private static final String URL = "jdbc:postgresql://localhost:5432/Club";

	private static final String USER = "postgres";

	private static final String PASSWORD = "4lt41r";

	public static void main(String[] args) {

		Connection conexion = null;

		try {

			

			Class.forName("org.postgresql.Driver");

	

			conexion = DriverManager.getConnection(URL, USER, PASSWORD);

			System.out.println("Conexión exitosa a la base de datos.");

		

			String consultaSQL = "SELECT * FROM public.\"usuarios\"";


			PreparedStatement preparedStatement = conexion.prepareStatement(consultaSQL);

	

			ResultSet resultado = preparedStatement.executeQuery();


			while (resultado.next()) {
			  
			    int idUsuario = resultado.getInt("idUsuario");
			    String nombreUsuario = resultado.getString("nombreUsuario");
			    String apellidoUsuario = resultado.getString("apellidoUsuario");
			    String dniUsuario = resultado.getString("dniUsuario");
			    String emailUsuario = resultado.getString("emailUsuario");

			  
			    System.out.println("ID: " + idUsuario + ", Nombre: " + nombreUsuario + ", Apellido: " + apellidoUsuario 
			                        + ", DNI: " + dniUsuario + ", Email: " + emailUsuario);
			}
			

			resultado.close();

			preparedStatement.close();

		} catch (ClassNotFoundException e) {

			System.out.println("Error al cargar el controlador de PostgreSQL.");

			e.printStackTrace();

		} catch (SQLException e) {

			System.out.println("Error al conectar o ejecutar la consulta.");

			e.printStackTrace();

		} finally {

			if (conexion != null) {

				try {

					conexion.close();

					System.out.println("Conexión cerrada.");

				} catch (SQLException e) {

					e.printStackTrace();

				}

			}

		}

	}

}
