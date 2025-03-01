package app;

import java.time.LocalDate;

import controller.LocacaoController;
import model.Carro;
import model.Cliente;
import model.Locacao;
import model.StatusLocacao;

public class Main {

	public static void main(String[] args) throws Exception {
		LocacaoController crtl = new LocacaoController(new Locacao());
		
		crtl.setLocacaoCliente(new Cliente("Eryk", "123-456-789-10", "9999-9999", "eryk@ifpb.com"));
		crtl.setLocacaoVeiculo(new Carro("AOP-2032", "ASTRA", 2020, StatusLocacao.DISPONIVEL));
		crtl.setLocacaoId(1001);
		crtl.setLocacaoRetirada(LocalDate.of(2025, 2, 28));
		crtl.setLocacaoDevolucao(LocalDate.now());
		
		crtl.cadastrarLocacao();
		
	}
}
