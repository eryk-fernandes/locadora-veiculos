package excecoes;

public class SenhaIncorretaException extends Exception {

	private static final long serialVersionUID = 1L;

	public SenhaIncorretaException(String msg) {
		super(msg);
	}
}
