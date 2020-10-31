package lanzandoElCaber;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.Map.Entry;

public class Torneo {
	private List<Lanzador> competidores = new ArrayList<>();  
	
	public Torneo(){}
	
    public static void main(String[] args) {
        Map<Integer, Lanzamiento> lanzamientos = FileManager.singleton.leerArchivo("entrada.txt");
    }
    
    void agregarLanzadoresACompetencia(Map<Integer, Lanzamiento> lanzamientos){
        int lanzadorActual = 1;
    	Lanzador competidor = new Lanzador(lanzadorActual);
		this.competidores.add(competidor);

        for (Entry<Integer, Lanzamiento> lanzamiento : lanzamientos.entrySet()) {
        	int keyLanzador = lanzamiento.getKey();
        	
        	if(lanzadorActual != keyLanzador){
        		lanzadorActual = keyLanzador;

        		competidor = new Lanzador(lanzadorActual);
        		competidor.registrarLanzamiento(lanzamiento.getValue());
        		this.competidores.add(competidor);
        		
        	}else{
        		competidor.registrarLanzamiento(lanzamiento.getValue());
        	}
        }
    }
    
    public List<Lanzador> getCompetidores(){
    	return this.competidores;
    }
    
}
