package model;

import java.time.LocalDate;

public class Locacao {
	
	private Cliente cliente;
	private Veiculo veiculo;
	private LocalDate retirada;
	private LocalDate devolucao;

	public Locacao(Cliente cliente, Veiculo veiculo, LocalDate retirada, LocalDate devolucao) {
		this.cliente = cliente;
		this.veiculo = veiculo;
		this.retirada = retirada;
		this.devolucao = devolucao;
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

}
