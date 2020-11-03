package lanzandoElCaber;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.Map.Entry;

public class Torneo {
	private List<Lanzador> competidores = new ArrayList<>();  
	private int anguloMinimoPermitido = -90;
	private int anguloMaximoPermitido = 90;
	private int anguloMinimoValorTotal = -45;
	private int anguloMayorValorTotal = 45;

	
	public Torneo(){
		
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
    
    void competirEnDistancia(){
    	/** TODO: completar */
    	for(Lanzador competidor : this.competidores){
    		boolean enJuego = true;
    		for(Lanzamiento lanzamiento : competidor.obtenerLanzamientos()){
    			if(esLanzamientoValido(lanzamiento)){
    				lanzamiento.obtenerDistanciaCalculada();
    			}else{
    				enJuego = false;
    			}
    		}
    	}
    }
    
    boolean esLanzamientoValido(Lanzamiento lanzamiento){
    	double angulo = lanzamiento.obtenerAngulo();
    	return anguloMaximoPermitido > angulo  && angulo > anguloMinimoPermitido;
    }
    
    public List<Lanzador> obtenerCompetidores(){
    	return this.competidores;
    }
    
}
