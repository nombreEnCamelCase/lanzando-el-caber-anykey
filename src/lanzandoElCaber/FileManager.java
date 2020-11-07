package lanzandoElCaber;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;


public class FileManager {

	public final static FileManager singleton = new FileManager();

	private String pathOutput;

	public FileManager() {

	}

	public List<Lanzador> leerArchivo(String pathCompletoArchivo) {
		this.setearNombreRealArchivo(pathCompletoArchivo);
		
		File archivo = new File(pathCompletoArchivo);
		List<Lanzador> datosEntrada = new LinkedList<Lanzador>();
		Scanner lector = null;
		try {
			lector = new Scanner(archivo);
			int lanzadores = lector.nextInt();
			lector.useLocale(Locale.US);

			int lanzadorActual = 1;
			while (lanzadorActual <= lanzadores) {
				
				Lanzador competidor = new Lanzador(lanzadorActual);
				
				for(int x=0;x<3;x++) 
	        		competidor.registrarLanzamiento(new Lanzamiento(lector.nextDouble(), lector.nextDouble()));

				datosEntrada.add(competidor);
				lanzadorActual++;
			}
			
			if(lanzadorActual-1 > lanzadores)
				throw new InconsistenciaInputDataException("Existe una inconsistencia en los datos de entrada y la cantidad de lanzadores.");

		}catch (InconsistenciaInputDataException excepcion) {
			excepcion.getMessage();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			lector.close();
		}

		return datosEntrada;
	}

	public void setearNombreRealArchivo(String pathCompleto) {
		// Realizar el proceso de parseo. Splitear desde derecha a izq, desde el punto
		// .in hacia encontrar una / o nada y guardar eso como nombre de archivo.
		this.pathOutput = pathCompleto.substring(0, pathCompleto.lastIndexOf('.'))+".out";
	}

	public void escribirArchivo(Integer mat[][]) {
		FileWriter archivo = null;
		PrintWriter pw = null;
		String matString[][] = new String[2][3];
		 
		for(int i=0;i<2;i++) {
			for(int j=0;j<3;j++) {
				if(mat[i][j]==-1)
					matString[i][j] = " ";
				else
					matString[i][j] = String.valueOf(mat[i][j]);
			}
		}
		
		try {
			archivo = new FileWriter(this.pathOutput);
			pw = new PrintWriter(archivo);
			
			pw.println(matString[0][0]+" "+matString[0][1]+" "+matString[0][2]);
			pw.println(matString[1][0]+" "+matString[1][1]+" "+matString[1][2]);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (archivo != null) {
				try {
					archivo.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

}
