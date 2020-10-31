package lanzandoElCaber;

public class Lanzamiento {
	private double distancia;
	private double angulo;
	
	public Lanzamiento(double angulo, double distancia){
		this.distancia = distancia;
		this.angulo = angulo;
	}
	
	public double obtenerDistanciaCalculada(){
		return this.distancia;
	}
	
	public double obtenerAngulo(){
		return this.angulo;
	}
}
