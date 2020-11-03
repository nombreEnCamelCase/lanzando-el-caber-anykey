package lanzandoElCaber;

import java.util.ArrayList;
import java.util.List;

public class Lanzador {
	private int numeroLanzador;
	private List<Lanzamiento> lanzamientos = new ArrayList<Lanzamiento>();

	public Lanzador(int numeroLanzador) {
		this.numeroLanzador = numeroLanzador;
	}

	public void registrarLanzamiento(Lanzamiento lanzamiento) {
		this.lanzamientos.add(lanzamiento);
	}

	public List<Lanzamiento> obtenerLanzamientos() {
		return this.lanzamientos;
	}
	
	public int getNumeroLanzador() {
		return this.numeroLanzador;
	}
}
