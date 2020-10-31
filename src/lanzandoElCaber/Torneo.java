package lanzandoElCaber;
import java.util.Map;
import java.util.List;
import java.util.Map.Entry;

public class Torneo {
	List<Lanzador> competidores;  
	
	public Torneo(){}
	
    public static void main(String[] args) {
        Map<Integer, Lanzamiento> lanzamientos = FileManager.singleton.leerArchivo("entrada.txt");
        
        
    }
    
    void agregarLanzadoresACompetencia(Map<Integer, Lanzamiento> lanzamientos){
    	List<Lanzamiento> lanzamientosPorLanzador = null;
        int lanzadorActual = 1;
        for (Entry<Integer, Lanzamiento> lanzamiento : lanzamientos.entrySet()) {
        	int keyLanzador = lanzamiento.getKey();
        	if(lanzadorActual == keyLanzador){
            	lanzamientosPorLanzador.add(lanzamiento.getValue());
        	} else {
        		Lanzador competidor = new Lanzador(lanzadorActual, lanzamientosPorLanzador);
        		this.competidores.add(competidor);
        		lanzadorActual = keyLanzador;
        	}
        }
    }
    
    
}
