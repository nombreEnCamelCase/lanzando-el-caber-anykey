package lanzandoElCaber;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class LanzadorTest {
	private Lanzador lanzador;
	private Lanzamiento Lanzamiento1, lanzamiento2;

	@Before
	public void init() {
		lanzador = new Lanzador(1);
	}

	@Test
	public void constructorTest() {
		assertEquals(1, lanzador.getNumeroLanzador());
	}

	public void registarLanzamientoTest() {
		Lanzamiento1 = new Lanzamiento(0.1, 0.4);
		lanzamiento2 = new Lanzamiento(0.2, 1.4);
		lanzador.registrarLanzamiento(Lanzamiento1);
		lanzador.registrarLanzamiento(lanzamiento2);

		assertEquals(2, lanzador.obtenerLanzamientos().size());
	}

}
