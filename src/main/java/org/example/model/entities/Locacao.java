package org.example.model.entities;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Locacao implements Comparable<Locacao> {
	
	protected Integer id;
	private Cliente cliente;
	private Veiculo veiculo;
	private LocalDate retirada;
	private LocalDate devolucao;
	
	public Locacao() {
		
	}

	public Locacao(Integer id, Cliente cliente, Veiculo veiculo, LocalDate retirada, LocalDate devolucao) {
		this.id = id;
		this.cliente = cliente;
		this.veiculo = veiculo;
		this.retirada = retirada;
		this.devolucao = devolucao;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}
	
	public Veiculo getVeiculo() {
		return veiculo;
	}
	
	public LocalDate getRetirada() {
		return retirada;
	}
	
	public LocalDate getDevolucao() {
		return devolucao;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public void setRetirada(LocalDate retirada) {
		this.retirada = retirada;
	}

	public void setDevolucao(LocalDate devolucao) {
		this.devolucao = devolucao;
	}
	
	@Override
	public int compareTo(Locacao outro) {
		if (ChronoUnit.DAYS.between(retirada, outro.getRetirada()) < 0) {
			return -1;
		}
		if (ChronoUnit.DAYS.between(retirada, outro.getRetirada()) > 0) {
			return 1;
		}
		
		return 0;
	}
	
}
