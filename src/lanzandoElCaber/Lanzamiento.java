package lanzandoElCaber;

public class Lanzamiento {
	private double distancia;
	private double angulo;

	public Lanzamiento(double angulo, double distancia) {
		this.distancia = distancia;
		this.angulo = angulo;
	}

	public double obtenerAngulo() {
		return this.angulo;
	}

	public double obtenerDistanciaReal() {
		return this.distancia;
	}
}
