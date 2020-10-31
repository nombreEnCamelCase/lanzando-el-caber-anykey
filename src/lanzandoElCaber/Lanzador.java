package lanzandoElCaber;
import java.util.List;

public class Lanzador {
	private int numeroLanzador;
	private List<Lanzamiento> lanzamientos;
	
	public Lanzador(int numeroLanzador, List<Lanzamiento> lanzamientos){
		this.numeroLanzador = numeroLanzador;
		this.lanzamientos = lanzamientos;
	}
}
