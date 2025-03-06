package excecoes;

public class UsuarioJaAdicionadoException extends Exception {

	private static final long serialVersionUID = 1L;

	public UsuarioJaAdicionadoException(String msg) {
		super(msg);
	}
}
