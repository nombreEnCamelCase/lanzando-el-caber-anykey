package lanzandoElCaber;

public class InconsistenciaInputDataException extends Exception {

	private static final long serialVersionUID = -7328308612537488222L;

	public InconsistenciaInputDataException(String msje) {
		System.out.println("Ocurrio una InconsistenciaInputDataException:\n" + msje);
	}
}
