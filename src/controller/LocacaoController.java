package controller;

import dao.LocacaoDAO;
import model.Locacao;

public class LocacaoController {
	
	private Locacao locacao;
	
	public LocacaoController() {

	}
	
	public Locacao recuperar(Integer id) throws Exception {
		return new LocacaoDAO().recuperar(id);
	}
	
	public void salvar() throws Exception {
		new LocacaoDAO().salvar(locacao);
	}
	
	public void remover() throws Exception {
		new LocacaoDAO().remover(locacao);
	}
	
	public void atualizar() throws Exception {
		new LocacaoDAO().atualizar(locacao);
	}

}
