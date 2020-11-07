package lanzandoElCaber;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class TorneoTests {
	private Torneo torneo;

	@Test
	public void agregarLanzadoresACompetencia() {
		// Arrange
		this.torneo = new Torneo("casos-prueba\\caso_enunciado.in");

		// Act
		this.torneo.agregarLanzadoresACompetencia();

		// Assert
		assertEquals(3, this.torneo.obtenerCompetidores().size(), 0);
	}

	@Test
	public void agregarLanzadoresACompetencia_2() {
		// Arrange
		this.torneo = new Torneo("");
		Lanzador competidorMock = new Lanzador(-2);
		competidorMock.registrarLanzamiento(new Lanzamiento(1.0, 20.1));
		competidorMock.registrarLanzamiento(new Lanzamiento(1.0, 20.2));
		competidorMock.registrarLanzamiento(new Lanzamiento(1.0, 20.3));
		List<Lanzador> competidores = new LinkedList<Lanzador>();
		competidores.add(competidorMock);

		// Act
		this.torneo.agregarLanzadoresACompetencia(competidores);

		// Assert
		assertEquals(1, this.torneo.obtenerCompetidores().size(), 0);
	}

	@Test
	public void competirEnDistancia_AgregaLanzadorAPodio() {
		// Arrange
		Lanzador competidorMock = new Lanzador(-2);
		this.torneo = new Torneo("casos-prueba\\caso_enunciado.in");
		this.torneo.agregarLanzadoresACompetencia();

		// Act
		this.torneo.competirEnDistancia(competidorMock, 200);

		// Assert
		assertEquals(true, this.torneo.obtenerActualPodioDistancia().containsKey(competidorMock.getNumeroLanzador()));
	}

	@Test
	public void competirEnDistancia_NOAgregaLanzadorAPodio() {
		// Arrange
		Lanzador competidorMock = new Lanzador(-2);
		this.torneo = new Torneo("casos-prueba\\caso_enunciado.in");
		this.torneo.agregarLanzadoresACompetencia();
		this.torneo.generarPodios();

		// Act
		this.torneo.competirEnDistancia(competidorMock, 0);

		// Assert
		assertEquals(false, this.torneo.obtenerActualPodioDistancia().containsKey(competidorMock.getNumeroLanzador()));
	}

	@Test
	public void competirEnConsistencia_AgregaLanzadorAPodio() {
		// Arrange
		Lanzador competidorMock = new Lanzador(-2);
		competidorMock.registrarLanzamiento(new Lanzamiento(1.0, 20.1));
		competidorMock.registrarLanzamiento(new Lanzamiento(1.0, 20.2));
		competidorMock.registrarLanzamiento(new Lanzamiento(1.0, 20.3));

		this.torneo = new Torneo("casos-prueba\\caso_enunciado.in");
		this.torneo.agregarLanzadoresACompetencia();
		this.torneo.generarPodios();

		// Act
		this.torneo.competirEnConsistencia(competidorMock);

		// Assert
		assertEquals(true,
				this.torneo.obtenerActualPodioConsistencia().containsKey(competidorMock.getNumeroLanzador()));
	}

	@Test
	public void competirEnConsistencia_NOAgregaLanzadorAPodio() {
		// Arrange
		Lanzador competidorMock = new Lanzador(-2);
		competidorMock.registrarLanzamiento(new Lanzamiento(1.0, 100));
		competidorMock.registrarLanzamiento(new Lanzamiento(1.0, -100));
		competidorMock.registrarLanzamiento(new Lanzamiento(1.0, 120));

		this.torneo = new Torneo("casos-prueba\\caso_enunciado.in");
		this.torneo.agregarLanzadoresACompetencia();
		this.torneo.generarPodios();

		// Act
		this.torneo.competirEnConsistencia(competidorMock);

		// Assert
		assertEquals(false,
				this.torneo.obtenerActualPodioConsistencia().containsKey(competidorMock.getNumeroLanzador()));
	}

	@Test
	public void lanzamiento_esValido() {
		// Arrange

		Lanzamiento lanzamiento = new Lanzamiento(1.0, 30);
		this.torneo = new Torneo("");

		// Act
		boolean result = this.torneo.esLanzamientoValido(lanzamiento);

		// Assert
		assertEquals(true, result);
	}

	@Test
	public void lanzamiento_NOesValido() {
		// Arrange

		Lanzamiento lanzamiento = new Lanzamiento(1.0, -95);
		this.torneo = new Torneo("");

		// Act
		boolean result = this.torneo.esLanzamientoValido(lanzamiento);

		// Assert
		assertEquals(false, result);
	}

	@Test
	public void lanzamientoFullDistancia_ObtenerDistanciaCalculada() {
		// Arrange

		Lanzamiento lanzamiento = new Lanzamiento(1.0, -80);
		this.torneo = new Torneo("");

		// Act
		double result = this.torneo.obtenerDistanciaCalculada(lanzamiento);

		// Assert
		assertEquals(0.8, result, 1);
	}

	@Test
	public void lanzamiento80Distancia_ObtenerDistanciaCalculada() {
		// Arrange

		Lanzamiento lanzamiento = new Lanzamiento(1.0, 10);
		this.torneo = new Torneo("");

		// Act
		double result = this.torneo.obtenerDistanciaCalculada(lanzamiento);

		// Assert
		assertEquals(1.0, result, 0);
	}

	/* UT referentes a los casos de prueba */
	@Test
	public void caso_enunciado_test() {
		// Arrange
		Integer[] ganadoresConsistencia, ganadoresDistancia;

		this.torneo = new Torneo("casos-prueba\\caso_enunciado.in");

		// Act
		torneo.agregarLanzadoresACompetencia();
		torneo.generarPodios();
		ganadoresConsistencia = torneo.obtenerPodioConsistencia();
		ganadoresDistancia = torneo.obtenerPodioDistancia();

		// Assert
		assertEquals(1, ganadoresConsistencia[0], 0);
		assertEquals(2, ganadoresConsistencia[1], 0);
		assertEquals(-1, ganadoresConsistencia[2], 0);

		assertEquals(2, ganadoresDistancia[0], 0);
		assertEquals(1, ganadoresDistancia[1], 0);
		assertEquals(3, ganadoresDistancia[2], 0);

	}

	@Test
	public void caso_1_test() {
		// Arrange
		Integer[] ganadoresConsistencia, ganadoresDistancia;

		this.torneo = new Torneo("casos-prueba\\caso_1.in");

		// Act
		torneo.agregarLanzadoresACompetencia();
		torneo.generarPodios();
		ganadoresConsistencia = torneo.obtenerPodioConsistencia();
		ganadoresDistancia = torneo.obtenerPodioDistancia();

		// Assert
		assertEquals(-1, ganadoresConsistencia[0], 0);
		assertEquals(-1, ganadoresConsistencia[1], 0);
		assertEquals(-1, ganadoresConsistencia[2], 0);

		assertEquals(2, ganadoresDistancia[0], 0);
		assertEquals(3, ganadoresDistancia[1], 0);
		assertEquals(1, ganadoresDistancia[2], 0);

	}

	@Test
	public void caso_2_test() {
		// Arrange
		Integer[] ganadoresConsistencia, ganadoresDistancia;

		this.torneo = new Torneo("casos-prueba\\caso_2.in");

		// Act
		torneo.agregarLanzadoresACompetencia();
		torneo.generarPodios();
		ganadoresConsistencia = torneo.obtenerPodioConsistencia();
		ganadoresDistancia = torneo.obtenerPodioDistancia();

		// Assert
		assertEquals(4, ganadoresConsistencia[0], 0);
		assertEquals(3, ganadoresConsistencia[1], 0);
		assertEquals(1, ganadoresConsistencia[2], 0);

		assertEquals(3, ganadoresDistancia[0], 0);
		assertEquals(4, ganadoresDistancia[1], 0);
		assertEquals(1, ganadoresDistancia[2], 0);
	}

	@Test
	public void caso_3_test() {
		// Arrange
		Integer[] ganadoresConsistencia, ganadoresDistancia;

		this.torneo = new Torneo("casos-prueba\\caso_3.in");

		// Act
		torneo.agregarLanzadoresACompetencia();
		torneo.generarPodios();
		ganadoresConsistencia = torneo.obtenerPodioConsistencia();
		ganadoresDistancia = torneo.obtenerPodioDistancia();

		// Assert
		assertEquals(1, ganadoresConsistencia[0], 0);
		assertEquals(4, ganadoresConsistencia[1], 0);
		assertEquals(2, ganadoresConsistencia[2], 0);

		assertEquals(4, ganadoresDistancia[0], 0);
		assertEquals(1, ganadoresDistancia[1], 0);
		assertEquals(2, ganadoresDistancia[2], 0);
	}

	@Test
	public void caso_4_test() {
		// Arrange
		Integer[] ganadoresConsistencia, ganadoresDistancia;

		this.torneo = new Torneo("casos-prueba\\caso_4.in");

		// Act
		torneo.agregarLanzadoresACompetencia();
		torneo.generarPodios();
		ganadoresConsistencia = torneo.obtenerPodioConsistencia();
		ganadoresDistancia = torneo.obtenerPodioDistancia();

		// Assert
		assertEquals(2, ganadoresConsistencia[0], 0);
		assertEquals(1, ganadoresConsistencia[1], 0);
		assertEquals(3, ganadoresConsistencia[2], 0);

		assertEquals(1, ganadoresDistancia[0], 0);
		assertEquals(2, ganadoresDistancia[1], 0);
		assertEquals(3, ganadoresDistancia[2], 0);
	}

	@Test
	public void caso_5_test() {
		// Arrange
		Integer[] ganadoresConsistencia, ganadoresDistancia;

		this.torneo = new Torneo("casos-prueba\\caso_5.in");

		// Act
		torneo.agregarLanzadoresACompetencia();
		torneo.generarPodios();
		ganadoresConsistencia = torneo.obtenerPodioConsistencia();
		ganadoresDistancia = torneo.obtenerPodioDistancia();

		// Assert
		assertEquals(1, ganadoresConsistencia[0], 0);
		assertEquals(2, ganadoresConsistencia[1], 0);
		assertEquals(-1, ganadoresConsistencia[2], 0);

		assertEquals(1, ganadoresDistancia[0], 0);
		assertEquals(2, ganadoresDistancia[1], 0);
		assertEquals(-1, ganadoresDistancia[2], 0);
	}

	@Test
	public void caso_6_test() {
		// Arrange
		Integer[] ganadoresConsistencia, ganadoresDistancia;

		this.torneo = new Torneo("casos-prueba\\caso_6.in");

		// Act
		torneo.agregarLanzadoresACompetencia();
		torneo.generarPodios();
		ganadoresConsistencia = torneo.obtenerPodioConsistencia();
		ganadoresDistancia = torneo.obtenerPodioDistancia();

		// Assert
		assertEquals(-1, ganadoresConsistencia[0], 0);
		assertEquals(-1, ganadoresConsistencia[1], 0);
		assertEquals(-1, ganadoresConsistencia[2], 0);

		assertEquals(-1, ganadoresDistancia[0], 0);
		assertEquals(-1, ganadoresDistancia[1], 0);
		assertEquals(-1, ganadoresDistancia[2], 0);
	}

}
