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
	private Integer[][] matrizResultado;
	// SOLO GUARDO LOS MEJORES 3 EN CADA ARRAY DE GANADORES.
	private HashMap<Integer, Double> podioConsistencia = new HashMap<Integer, Double>();
	private HashMap<Integer, Double> podioDistancia = new HashMap<Integer, Double>();

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

	public void agregarLanzadoresACompetencia(List<Lanzador> lanzadores) {
		this.competidores = lanzadores;
	}

	public void competirEnDistancia(Lanzador competidor, double distanciaTotal) {

		// Yo SIEMPRE me quedo con 3 ganadores MAXIMO.
		// Suma todas las distancias, si es invalido suma 0 pero cuenta el tiro igual.
		// Ganadores a distancia
		if (distanciaTotal > 0) {
			if (this.podioDistancia.size() > 3) {
				for (Map.Entry<Integer, Double> ganador : podioDistancia.entrySet()) {
					if (ganador.getValue() < distanciaTotal) {
						this.podioDistancia.remove(ganador);
						this.podioDistancia.put(competidor.getNumeroLanzador(), distanciaTotal);
						break;
					}
				}
			} else
				this.podioDistancia.put(competidor.getNumeroLanzador(), distanciaTotal);
		}
	}

	public void competirEnConsistencia(Lanzador competidor) {
		double x1 = competidor.obtenerLanzamientos().get(0).obtenerAngulo();
		double x2 = competidor.obtenerLanzamientos().get(1).obtenerAngulo();
		double x3 = competidor.obtenerLanzamientos().get(2).obtenerAngulo();
		double promedio, varianza;

		if (esLanzamientoValido(competidor.obtenerLanzamientos().get(0))
				&& esLanzamientoValido(competidor.obtenerLanzamientos().get(1))
				&& esLanzamientoValido(competidor.obtenerLanzamientos().get(2))) {
			promedio = (x1 + x2 + x3) / 3;
			varianza = ((x1 - promedio) + (x2 - promedio) + (x3 - promedio)) / 3;
			this.podioConsistencia.put(competidor.getNumeroLanzador(), varianza);
			if (this.podioConsistencia.size() > 3) {
				for (Map.Entry<Integer, Double> ganador : podioConsistencia.entrySet()) {
					if (ganador.getValue() > varianza) {
						this.podioConsistencia.remove(ganador);
						this.podioConsistencia.put(competidor.getNumeroLanzador(), varianza);
						break;
					}
				}
			} else
				this.podioConsistencia.put(competidor.getNumeroLanzador(), varianza);
		}

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

		this.matrizResultado = generarMatrizGandores();

		if (this.filePathCompetencia != "" && this.filePathCompetencia != null)
			FileManager.singleton.escribirArchivo(matrizResultado);
	}

	public boolean esLanzamientoValido(Lanzamiento lanzamiento) {
		double angulo = lanzamiento.obtenerAngulo();
		return angulo <= this.anguloMinimoPermitido && angulo >= this.anguloMaximoPermitido;
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

	private Integer[][] generarMatrizGandores() {
		Integer matrizResultado[][] = new Integer[2][3];

		int a = 0;

		for (int j = 0; j < 3; j++) {
			a = removerUnGanadorLimite(this.podioConsistencia, false);
			if (a != -1)
				matrizResultado[0][j] = a;
			else
				matrizResultado[0][j] = -1;
		}
		for (int j = 0; j < 3; j++) {
			a = removerUnGanadorLimite(this.podioDistancia, true);
			if (a != -1)
				matrizResultado[1][j] = a;
			else
				matrizResultado[1][j] = -1;

		}

		return matrizResultado;
	}

	private int removerUnGanadorLimite(HashMap<Integer, Double> ganadores, boolean isMax) {
		Map.Entry<Integer, Double> ganadorLimite = null;
		Integer ganadorResponse = null;
		for (Map.Entry<Integer, Double> ganador : ganadores.entrySet()) {
			if (ganadorLimite == null || (isMax ? (ganador.getValue().compareTo(ganadorLimite.getValue()) > 0)
					: (ganador.getValue().compareTo(ganadorLimite.getValue()) < 0))) {
				ganadorLimite = ganador;
			}
		}
		if (ganadores.size() > 0) {
			ganadorResponse = ganadorLimite.getKey();
			ganadores.remove(ganadorLimite.getKey());
			return ganadorResponse;
		}
		return -1;
	}

	public HashMap<Integer, Double> obtenerActualPodioConsistencia() {
		return this.podioConsistencia;
	}

	public HashMap<Integer, Double> obtenerActualPodioDistancia() {
		return this.podioDistancia;
	}

	public HashMap<Integer, Double> setearPodioDistancia(HashMap<Integer, Double> podio) {
		return this.podioDistancia = podio;
	}

	public HashMap<Integer, Double> setearPodioConsistencia(HashMap<Integer, Double> podio) {
		return this.podioConsistencia = podio;
	}

	public Integer[] obtenerPodioConsistencia() {
		if (this.matrizResultado != null && this.matrizResultado[0] != null)
			return this.matrizResultado[0];
		return null;
	}

	public Integer[] obtenerPodioDistancia() {
		return this.matrizResultado[1];
	}

	public List<Lanzador> obtenerCompetidores() {
		return this.competidores;
	}

}
