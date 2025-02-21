package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Pagamento {
	
	private Locacao locacao;

	public Pagamento(Locacao locacao) {
		this.locacao = locacao;
	}
	
	public Locacao getLocacao() {
		return locacao;
	}
	
	public double calcularMulta() {
		
		LocalDate devolucao = locacao.getDevolucao();
		long diasAtraso = ChronoUnit.DAYS.between(devolucao, LocalDate.now());
		
		if (diasAtraso > 0) {
			return diasAtraso * locacao.getVeiculo().calcularCustoLocacaoDiario() * 0.1;
		}
		
		return 0.0;
	}
	
	public double calcularValorTotal() {
		LocalDate retirada = locacao.getRetirada();
		
		long dias = ChronoUnit.DAYS.between(retirada, LocalDate.now());
		double custoLocacaoDiario = locacao.getVeiculo().calcularCustoLocacaoDiario();
		
		return custoLocacaoDiario * dias + calcularMulta();
	}
}
