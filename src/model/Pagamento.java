package model;

import java.time.LocalDate;

public class Pagamento {
	
	private Integer id;
	private Integer idLocacao;
	private Double valorPago;
	private LocalDate dataPagamento;
	private MetodoPagamento metodoPagamento;
	
	public Pagamento() {
		
	}

	public Pagamento(Integer id, Integer idLocacao, Double valorPago, LocalDate dataPagamento, MetodoPagamento metodoPagamento) {
		this.id = id;
		this.idLocacao = idLocacao;
		this.valorPago = valorPago;
		this.dataPagamento = dataPagamento;
		this.metodoPagamento = metodoPagamento;
	}

	public Integer getId() {
		return id;
	}

	public Integer getIdLocacao() {
		return idLocacao;
	}

	public Double getValorPago() {
		return valorPago;
	}

	public LocalDate getDataPagamento() {
		return dataPagamento;
	}

	public MetodoPagamento getMetodoPagamento() {
		return metodoPagamento;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIdLocacao(Integer idLocacao) {
		this.idLocacao = idLocacao;
	}

	public void setValorPago(Double valorPago) {
		this.valorPago = valorPago;
	}

	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public void setMetodoPagamento(MetodoPagamento metodoPagamento) {
		this.metodoPagamento = metodoPagamento;
	}

}
