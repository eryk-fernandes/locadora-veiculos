package controller;

import dao.PagamentoDAO;
import model.Pagamento;

public class PagamentoController {
	
	private Pagamento pagamento;

	public PagamentoController() {
		
	}
	
	public void salvar() throws Exception {
		new PagamentoDAO().salvar(pagamento);
	}
	
	public void remover() throws Exception {
		new PagamentoDAO().remover(pagamento);
	}
	
	public void atualizar() throws Exception {
		new PagamentoDAO().atualizar(pagamento);
	}

}
