package model.exceptions;

@SuppressWarnings("serial")
public class FuncionarioInexistenteException extends Exception {
	
	public FuncionarioInexistenteException(String msg) {
		super(msg);
	}

}
