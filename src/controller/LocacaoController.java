package controller;

import java.time.LocalDate;

import model.Cliente;
import model.Locacao;
import model.Veiculo;

public class LocacaoController {
	
	private Locacao locacao;
	
	public LocacaoController(Locacao locacao) {
		this.locacao = locacao;
	}
	
	public Cliente getLocacaoCliente() {
		return locacao.getCliente();
	}
	
	public Veiculo getLocacaoVeiculo() {
		return locacao.getVeiculo();
	}
	
	public LocalDate getLocacaoRetirada() {
		return locacao.getRetirada();
	}
	
	public LocalDate getLocacaoDevolucao() {
		return locacao.getDevolucao();
	}
	
	public void setLocacaoCliente(Cliente cliente) {
		locacao.setCliente(cliente);
	}
	
	public void setLocacaoVeiculo(Veiculo veiculo) {
		locacao.setVeiculo(veiculo);
	}
	
	public void setLocacaoRetirada(LocalDate retirada) {
		locacao.setRetirada(retirada);
	}

	public void setLocacaoDevolucao(LocalDate devolucao) {
		locacao.setDevolucao(devolucao);
	}

}
