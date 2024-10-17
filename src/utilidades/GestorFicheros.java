package utilidades;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GestorFicheros {
	public static String nombreFichero() 
	 {
	        LocalDate fechaActual = LocalDate.now();
	        String diasFecha = DateTimeFormatter.ofPattern("dd").format(fechaActual);
	        String mesFecha = DateTimeFormatter.ofPattern("MM").format(fechaActual);
	        String anioFecha = DateTimeFormatter.ofPattern("yy").format(fechaActual);
	        String fechaCompleta = diasFecha + mesFecha + anioFecha;
	        String rutaArchivo = "C:\\Users\\PC\\eclipse-workspace\\edu.DAWproyecto1\\ficherolog" + fechaCompleta + ".txt";
	        return rutaArchivo;
	    }

	    public static void sobreEscribir(String accion)
	    {
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreFichero(), true)))
	        {
	            writer.write(accion + "\n");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    public static void creaFichero()
	    {
	        try {
	            FileWriter fw = new FileWriter(nombreFichero());
	            fw.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

}
