package lanzandoElCaber;

import static org.junit.Assert.assertEquals;
import lanzandoElCaber.Lanzador;
import lanzandoElCaber.Torneo;
import lanzandoElCaber.Lanzamiento;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class TorneoTests {
	private Torneo torneo;
    
    @Before
    public void init() {
    	torneo = new Torneo();
    }
    
    @Test
    public void agregarLanzadoresACompetencia(){
    	Map<Integer, Lanzamiento> mockFileData = new HashMap<Integer, Lanzamiento>() {{
    		put(1, new Lanzamiento(0.1, 0.2));
    		put(1, new Lanzamiento(0.3, 1.2));
    		put(2, new Lanzamiento(0.4, 1.4));
    		put(2, new Lanzamiento(0.5, 1.5));
    	}};
    	
    	this.torneo.agregarLanzadoresACompetencia(mockFileData);
        assertEquals(2, this.torneo.obtenerCompetidores().size());
    }
	
}
