package lanzandoElCaber;
import java.util.ArrayList;
import java.util.List;

public class Lanzador {
	private int numeroLanzador;
	private List<Lanzamiento> lanzamientos = new ArrayList<>();
	
	public Lanzador(int numeroLanzador){
		this.numeroLanzador = numeroLanzador;
	}
	
	public void registrarLanzamiento(Lanzamiento lanzamiento){
		this.lanzamientos.add(lanzamiento);
	}
}
