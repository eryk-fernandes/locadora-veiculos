package controller;

import model.Locacao;
import model.Pagamento;

public class PagamentoController {
	
	private Pagamento pagamento;

	public PagamentoController(Pagamento pagamento) {
		this.pagamento = pagamento;
	}
	
	public Locacao getPagamentoLocacao() {
		return pagamento.getLocacao();
	}
	
	public void setPagamentoLocacao(Locacao locacao) {
		pagamento.setLocacao(locacao);
	}

}
