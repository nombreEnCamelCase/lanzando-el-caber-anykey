package lanzandoElCaber;

import java.util.Map;

public class App {
    public static void main(String[] args) {
        Torneo torneo = new Torneo();
        torneo.agregarLanzadoresACompetencia(FileManager.singleton.leerArchivo("entrada.txt"));
        
    }
}
