package lanzandoElCaber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private HashMap<Lanzador, Double> ganadoresDistancia = new HashMap<Lanzador, Double>();

	public Torneo(String competencia) {
		// Torneo con reglas default.
		this.filePathCompetencia = competencia;
		this.anguloMinimoPermitido = 90;
		this.anguloMaximoPermitido = -90;
		this.anguloMayorDistanciaTotal = -30;
		this.anguloMinimoDistanciaTotal = 30;
		this.porcentajeMinimoValido = 0.8;
	}

	public Torneo(String competencia, double anguloMaximo, double anguloMinimo, double anguloMaxTotal,
			double anguloMinTotal, double porcentajeMinimoValido) {
		// Torneo con reglas reglas personalizadas.
		this.filePathCompetencia = competencia;
		this.anguloMaximoPermitido = anguloMaximo;
		this.anguloMinimoPermitido = anguloMinimo;
		this.anguloMayorDistanciaTotal = anguloMaxTotal;
		this.anguloMinimoDistanciaTotal = anguloMinTotal;
		this.porcentajeMinimoValido = porcentajeMinimoValido / 100;
	}

	public void agregarLanzadoresACompetencia() {
		try {
			this.competidores = FileManager.singleton.leerArchivo(this.filePathCompetencia);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Ocurrio un error al agregar lanzadores al torneo.");
		}
	}

	public void competirEnDistancia(Lanzador competidor, double distanciaTotal) {

		// Yo SIEMPRE me quedo con 3 ganadores MAXIMO.
		// Suma todas las distancias, si es invalido suma 0 pero cuenta el tiro igual.
		// Ganadores a distancia
		if (this.ganadoresDistancia.size() > 3) {
			for (Map.Entry<Lanzador, Double> ganador : ganadoresDistancia.entrySet()) {
				if (ganador.getValue() < distanciaTotal) {
					this.ganadoresDistancia.remove(ganador);
					this.ganadoresDistancia.put(competidor, distanciaTotal);
					break;
				}
			}
		} else
			this.ganadoresDistancia.put(competidor, distanciaTotal);
	}

	public void competirEnConsistencia(Lanzador competidor) {
		// modulo entre t1 y t2
		// modulo entre t2 y t3
		// modulo entre t1 y t3
		// nos quedamos con el modulo de mayor valor.
		// y lo comparamos con el de otro jugador.
		// Ver en clases, cuando pregunta sobre angulos consistentes.
	}

	public void generarPodios() {
		// Aca se llaman a los dos metodos de competencia.
		// Se genera la matriz con los arraylist de ganadores
		// Se llama al FileManager y se le envia el array al metodo escribirArchivo.

		// Yo SIEMPRE me quedo con 3 ganadores MAXIMO.
		// Suma todas las distancias, si es invalido suma 0 pero cuenta el tiro igual.

		double distanciaTotal;
		double distanciaLanzamiento;
		for (Lanzador competidor : this.competidores) {
			boolean enJuego = true;
			distanciaTotal = 0;

			for (Lanzamiento lanzamiento : competidor.obtenerLanzamientos()) {

				if ((distanciaLanzamiento = obtenerDistanciaCalculada(lanzamiento)) < 0)
					enJuego = false;

				distanciaTotal += (distanciaLanzamiento < 0) ? 0 : distanciaLanzamiento;
			}

			competirEnDistancia(competidor, distanciaTotal);

			if (enJuego) {
				// Lo considero para consistencia
				competirEnConsistencia(competidor);
			}
		}

		FileManager.singleton.escribirArchivo(generarMatrizGandores());
	}

	private boolean esLanzamientoValido(Lanzamiento lanzamiento) {
		double angulo = lanzamiento.obtenerAngulo();
		return angulo <= this.anguloMinimoPermitido && angulo >= this.anguloMaximoPermitido;
	}

	public List<Lanzador> obtenerCompetidores() {
		return this.competidores;
	}

	public double obtenerDistanciaCalculada(Lanzamiento lanzamientoRealizado) {
		double anguloLanza = lanzamientoRealizado.obtenerAngulo(),
				distanciaLanza = lanzamientoRealizado.obtenerDistanciaReal();

		if (esLanzamientoValido(lanzamientoRealizado)) {
			if (anguloLanza <= this.anguloMinimoDistanciaTotal && anguloLanza >= this.anguloMayorDistanciaTotal)
				return distanciaLanza;
			else
				return distanciaLanza * porcentajeMinimoValido;
		} else
			return -1;
	}

	private int[][] generarMatrizGandores() {
		int matrizResultado[][] = new int[2][3];
		int [][] matriz = {{-1,-1,-1},{-1,-1,-1}};
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3; j++) {
				matrizResultado[j][i] = removerMayorGanadorDistancia();
			}
		}

		return matrizResultado;
	}

	private int removerMayorGanadorDistancia() {
		Map.Entry<Lanzador, Double> ganadorMax = null;
		Lanzador ganadorResponse = null;
		for (Map.Entry<Lanzador, Double> ganador : this.ganadoresDistancia.entrySet()) {
			if (ganadorMax == null || ganador.getValue().compareTo(ganadorMax.getValue()) > 0) {
				ganadorMax = ganador;
			}
		}
		ganadorResponse = ganadorMax.getKey();
		this.ganadoresDistancia.remove(ganadorMax);
		return ganadorResponse.getNumeroLanzador();
	}

}
