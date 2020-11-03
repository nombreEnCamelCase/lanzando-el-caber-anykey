package lanzandoElCaber;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TorneoTests {
	private Torneo torneo;
  
    @Test
    public void agregarLanzadoresACompetencia(){
    	this.torneo = new Torneo("casos-prueba\\caso_enunciado.in");
    	this.torneo.agregarLanzadoresACompetencia();
        assertEquals(3, this.torneo.obtenerCompetidores().size());
    }
	
}
