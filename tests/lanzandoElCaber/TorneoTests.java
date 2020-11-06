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
        
    @Test
    public void caso_1_test(){
		this.torneo = new Torneo("casos-prueba\\caso_1.in");
		torneo.agregarLanzadoresACompetencia();
		torneo.generarPodios();
    }
    
    @Test
    public void caso_2_test(){
		this.torneo = new Torneo("casos-prueba\\caso_2.in");
		torneo.agregarLanzadoresACompetencia();
		torneo.generarPodios();
    }
    
    @Test
    public void caso_3_test(){
		this.torneo = new Torneo("casos-prueba\\caso_3.in");
		torneo.agregarLanzadoresACompetencia();
		torneo.generarPodios();
    }
    
    @Test
    public void caso_4_test(){
		this.torneo = new Torneo("casos-prueba\\\\caso_4.in");
		torneo.agregarLanzadoresACompetencia();
		torneo.generarPodios();
    }
    
    @Test
    public void caso_5_test(){
		this.torneo = new Torneo("casos-prueba\\caso_5.in");
		torneo.agregarLanzadoresACompetencia();
		torneo.generarPodios();
    }
    
    @Test
    public void caso_6_test(){
		this.torneo = new Torneo("casos-prueba\\caso_6.in");
		torneo.agregarLanzadoresACompetencia();
		torneo.generarPodios();
    }
    
	
}
