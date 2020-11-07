package lanzandoElCaber;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LanzamientoTest {
	private Lanzamiento lanzamiento;
	
	@Test
    public void constructorTest(){
		lanzamiento = new Lanzamiento(0.1, 0.2);
        assertEquals(0.2, lanzamiento.obtenerAngulo(), 0.0001);
        assertEquals(0.1, lanzamiento.obtenerDistanciaReal(), 0.0001);

    }
	
}
