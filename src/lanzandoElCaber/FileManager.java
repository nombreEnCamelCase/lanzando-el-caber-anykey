package lanzandoElCaber;

import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class FileManager {

    public final static FileManager singleton = new FileManager();

	public FileManager(){}
	
	Map<Integer, Lanzamiento> leerArchivo(String nombreArchivo){
		File archivo = new File(nombreArchivo); 
        Map<Integer, Lanzamiento> datos = new HashMap<>();
        
        try {
            Scanner lector = new Scanner(archivo);
            int lanzadores = lector.nextInt();
            lector.useLocale(Locale.US);
            
            int i = 0, lanzadorActual = 1;
            while(lector.hasNext()){
            	
            	Lanzamiento lanzamiento = new Lanzamiento(lector.nextDouble(), lector.nextDouble());
            	datos.put(lanzadorActual, lanzamiento);
            	
            	if(i == lanzadores){
            		lanzadorActual++;
            		i = 0;
            	}
            	i++;
            }

            lector.close();
        } catch(Exception e){
        	
        }
            
        return datos;
	}
	
}
