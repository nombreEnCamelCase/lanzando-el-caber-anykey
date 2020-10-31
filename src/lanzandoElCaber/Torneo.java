package lanzandoElCaber;
import java.util.Map;

public class Torneo {
    public static void main(String[] args) {
        Map<Integer, Lanzamiento> lanzamientos = FileManager.singleton.leerArchivo("entrada.txt");

        for (Integer value : lanzamientos.keySet()) {
        	System.out.println(value);
        }
        
    }
}
