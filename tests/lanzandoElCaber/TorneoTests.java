package lanzandoElCaber;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TorneoTests {
	private Torneo torneo;

	@Test
	public void agregarLanzadoresACompetencia() {
		this.torneo = new Torneo("casos-prueba\\caso_enunciado.in");
		this.torneo.agregarLanzadoresACompetencia();
		assertEquals(3, this.torneo.obtenerCompetidores().size(), 0);
	}

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
    public void caso_3_test(){
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
    public void caso_4_test(){
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
    public void caso_5_test(){
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
    public void caso_6_test(){
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
