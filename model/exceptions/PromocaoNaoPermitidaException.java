package model.exceptions;

public class PromocaoNaoPermitidaException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public PromocaoNaoPermitidaException(String msg) {
		super(msg);
	}

}
