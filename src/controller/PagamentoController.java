package controller;

import java.time.LocalDate;

import model.MetodoPagamento;
import model.Pagamento;

public class PagamentoController {
	
	private Pagamento pagamento;

	public PagamentoController(Pagamento pagamento) {
		this.pagamento = pagamento;
	}
	
	public Integer getPagamentoId() {
		return pagamento.getId();
	}
	
	public Integer getPagamentoIdLocacao() {
		return pagamento.getIdLocacao();
	}
	
	public Double getPagamentoValorPago() {
		return pagamento.getValorPago();
	}
	
	public LocalDate getPagamentoDataPagamento() {
		return pagamento.getDataPagamento();
	}
	
	public MetodoPagamento getPagamentoMetodoPagamento() {
		return pagamento.getMetodoPagamento();
	}
	
	public void setPagamentoId(Integer id) {
		pagamento.setId(id);
	}
	
	public void setPagamentoIdLocacao(Integer idLocacao) {
		pagamento.setIdLocacao(idLocacao);
	}
	
	public void setPagamentoValorPago(Double valorPago) {
		pagamento.setValorPago(valorPago);
	}
	
	public void setPagamentoDataPagamento(LocalDate dataPagamento) {
		pagamento.setDataPagamento(dataPagamento);
	}
	
	public void setPagamentoMetodoPagamento(MetodoPagamento metodoPagamento) {
		pagamento.setMetodoPagamento(metodoPagamento);
	}

}
