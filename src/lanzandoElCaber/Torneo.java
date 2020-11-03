package lanzandoElCaber;
import java.util.ArrayList;
import java.util.List;

public class Torneo {
	private List<Lanzador> competidores = new ArrayList<>();  
	private double anguloMinimoPermitido;
	private double anguloMaximoPermitido;
	private double anguloMayorDistanciaTotal;
	private double anguloMinimoDistanciaTotal;
	private double porcentajeMinimoValido;
	private String filePathCompetencia;
	
	// SOLO GUARDO LOS MEJORES 3 EN CADA ARRAY DE GANADORES.
	private ArrayList<Lanzador> ganadoresConsistencia = new ArrayList<Lanzador>();
	private ArrayList<Lanzador> ganadoresDistancia = new ArrayList<Lanzador>();
	
	public Torneo(String competencia){
		// Torneo con reglas default.
		this.filePathCompetencia = competencia;
		this.anguloMinimoPermitido = -90;
		this.anguloMaximoPermitido = 90;
		this.anguloMayorDistanciaTotal = 30;
		this.anguloMinimoDistanciaTotal = -30;
		this.porcentajeMinimoValido = 0.8;
	}
	
	public Torneo(String competencia, double anguloMaximo, double anguloMinimo, double anguloMaxTotal, double anguloMinTotal, double porcentajeMinimoValido) {
		// Torneo con reglas reglas personalizadas.
		this.filePathCompetencia = competencia;
		this.anguloMaximoPermitido = anguloMaximo;
		this.anguloMinimoPermitido = anguloMinimo;
		this.anguloMayorDistanciaTotal = anguloMaxTotal;
		this.anguloMinimoDistanciaTotal = anguloMinTotal;
		this.porcentajeMinimoValido = porcentajeMinimoValido/100;
	}
	
	
    
	public void agregarLanzadoresACompetencia(){
		try {
			this.competidores = FileManager.singleton.leerArchivo(this.filePathCompetencia);
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("Ocurrio un error al agregar lanzadores al torneo.");
		}
    }
    

    public void competirEnDistancia(){

    	// Yo SIEMPRE me quedo con 3 ganadores MAXIMO.
    	// Suma todas las distancias, si es invalido suma 0 pero cuenta el tiro igual.
    	
    	double valor;
    	for(Lanzador competidor : this.competidores){
    		boolean enJuego = true;
    		for(Lanzamiento lanzamiento : competidor.obtenerLanzamientos()){
    			if(esLanzamientoValido(lanzamiento)){
    				valor = obtenerDistanciaCalculada(lanzamiento);
    				
    			}else{
    				enJuego = false;
    			}
    		}
    	}
    }
   
    public void competirEnConsistencia() {
		//    	modulo entre t1 y t2
		//    	modulo entre t2 y t3
		//    	modulo entre t1 y t3
		//    	nos quedamos con el modulo de mayor valor.
		//    	y lo comparamos con el de otro jugador.
    	// Ver en clases, cuando pregunta sobre angulos consistentes.
    }
    
    public void generarPodios() {
    	// Aca se llaman a los dos metodos de competencia.
    	// Se genera la matriz con los arraylist de ganadores
    	// Se llama al FileManager y se le envia el array al metodo escribirArchivo.
    }
    
    private boolean esLanzamientoValido(Lanzamiento lanzamiento){
    	double angulo = lanzamiento.obtenerAngulo();
    	return angulo>=this.anguloMinimoPermitido && angulo<=this.anguloMaximoPermitido;
    }
    
    public List<Lanzador> obtenerCompetidores(){
    	return this.competidores;
    }
    
	public double obtenerDistanciaCalculada(Lanzamiento lanzamientoRealizado){
		double anguloLanza = lanzamientoRealizado.obtenerAngulo(),
		distanciaLanza = lanzamientoRealizado.obtenerDistanciaReal();
		
		if(esLanzamientoValido(lanzamientoRealizado)) {
			if(anguloLanza<=this.anguloMinimoDistanciaTotal && anguloLanza >= this.anguloMayorDistanciaTotal)
				return distanciaLanza;
			else
				return distanciaLanza*porcentajeMinimoValido;
		}
		else
			return -1;
	}
    
    
}
