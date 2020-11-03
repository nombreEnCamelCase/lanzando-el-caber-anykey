package lanzandoElCaber;

import java.util.Map;

public class App {
	public static void main(String[] args) {
		Torneo torneo = new Torneo("casos-prueba\\\\caso_enunciado.in");
		torneo.agregarLanzadoresACompetencia();
		torneo.generarPodios();
	}
}
