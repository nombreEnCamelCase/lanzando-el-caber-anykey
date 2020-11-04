package lanzandoElCaber;

public class Lanzamiento {
	private double distancia;
	private double angulo;

	public Lanzamiento(double distancia,double angulo) {
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
