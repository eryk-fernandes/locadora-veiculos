package org.example.model.entities;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Pagamento implements Comparable<Pagamento> {

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

	@Override
	public int compareTo(Pagamento outro) {
		if (ChronoUnit.DAYS.between(dataPagamento, outro.getDataPagamento()) < 0) {
			return -1;
		}
		if (ChronoUnit.DAYS.between(dataPagamento, outro.dataPagamento) > 0) {
			return 1;
		}
		
		return 0;
	}

}
