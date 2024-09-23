package model.exceptions;

public class CargoInexistenteException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public CargoInexistenteException(String msg) {
		super(msg);
	}

}
