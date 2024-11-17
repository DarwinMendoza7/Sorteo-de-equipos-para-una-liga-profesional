package clases;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
public class ArchivoSorteo {

	private String nombreArchivo;
	
	public ArchivoSorteo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	
	public void guardarSorteo(String contenido) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo,true))){
			writer.write(contenido);
			writer.newLine();  //Agrega una nueva linea despues de cada entrada
		}catch(IOException e) {
			System.out.println("Error al guardar los resultados: " + e.getMessage());
		}
	}
}
