package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import javax.swing.JTextField;

import dao.LocacaoDAO;
import dao.PagamentoDAO;
import dao.VeiculoDAO;
import model.Locacao;
import model.MetodoPagamento;
import model.Pagamento;
import model.StatusLocacao;
import model.Veiculo;

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
	
	public double resgatarCustoLocacao(int idLocacao) throws IOException {
		return new LocacaoController().calcularCustoLocacao(new LocacaoDAO().recuperar(idLocacao));
	}
	
	public double resgatarCustoLocacao(Object locacaoInfo) throws IOException {
		int idLocacao = Integer.valueOf(locacaoInfo.toString().split(" ")[1]);
		
		return new LocacaoController().calcularCustoLocacao(new LocacaoDAO().recuperar(idLocacao));
	}
	
	public double calcularMulta(Object locacaoInfo, JTextField dataPagamento) throws IOException {
		
		int idLocacao = Integer.valueOf(locacaoInfo.toString().split(" ")[1]);
		
		LocalDate devolucao = new LocacaoDAO().recuperar(idLocacao).getDevolucao();
		LocalDate pagamento = LocalDate.parse(dataPagamento.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		
		long dias = ChronoUnit.DAYS.between(devolucao, pagamento);
		
 		double custoJuros = new LocacaoDAO().recuperar(idLocacao).getVeiculo().calcularCustoLocacaoDiario() * 0.1;
 		
		double multa = custoJuros * dias;
		
		return multa;
	}
	
	public double calcularMulta(int idLocacao, JTextField dataPagamento) throws IOException {

		LocalDate devolucao = new LocacaoDAO().recuperar(idLocacao).getDevolucao();
		LocalDate pagamento = LocalDate.parse(dataPagamento.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		
		long dias = ChronoUnit.DAYS.between(devolucao, pagamento);
		
 		double custoJuros = new LocacaoDAO().recuperar(idLocacao).getVeiculo().calcularCustoLocacaoDiario() * 0.1;
 		
		double multa = custoJuros * dias;
		
		return multa;
	}
	
	public double calcularTotal(Object locacaoInfo, JTextField dataPagamento) throws IOException {
		int idLocacao = Integer.valueOf(locacaoInfo.toString().split(" ")[1]);
		
		double locacao = new LocacaoController().calcularCustoLocacao(new LocacaoDAO().recuperar(idLocacao));
		
		double multa = calcularMulta(idLocacao, dataPagamento);
		
		return locacao + multa;
	}
	
	public void cadastrarDados(Object locacaoInfo, Object metodo, JTextField dataPagamento) throws Exception {
		
		Locacao locacao = new LocacaoDAO().recuperar(Integer.valueOf(locacaoInfo.toString().split(" ")[1]));

		Pagamento pagamento = new Pagamento();
		
		if (metodo.toString().equals("DINHEIRO")) {
			pagamento.setMetodoPagamento(MetodoPagamento.DINHEIRO);
		}
		else if (metodo.toString().equals("BOLETO")) {
			pagamento.setMetodoPagamento(MetodoPagamento.BOLETO);
		}
		else if (metodo.toString().equals("DÉBITO")) {
			pagamento.setMetodoPagamento(MetodoPagamento.DEBITO);
		}
		else if (metodo.toString().equals("CRÉDITO")) {
			pagamento.setMetodoPagamento(MetodoPagamento.CREDITO);
		}
		else if (metodo.toString().equals("PIX")) {
			pagamento.setMetodoPagamento(MetodoPagamento.PIX);
		}
		else {
			throw new IllegalArgumentException("ESCOLHA UM MÉTODO DE PAGAMENTO");
		}

		pagamento.setId(locacao.getId());
		pagamento.setDataPagamento(LocalDate.parse(dataPagamento.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		pagamento.setIdLocacao(locacao.getId());
		
		double valorInicial = new LocacaoController().calcularCustoLocacao(locacao);
		
		pagamento.setValorPago(
				valorInicial +
				calcularMulta(locacao.getId(), dataPagamento)
				);
		
		try {
			Veiculo veiculo = new VeiculoDAO().recuperar(locacaoInfo.toString().split(" ")[5]);
			veiculo.setStatus(StatusLocacao.DISPONIVEL);
			
			new VeiculoDAO().atualizar(veiculo);
			
			salvar();
		} catch (Exception e) {
			throw new IOException("ERRO AO ADICIONAR O USUÁRIO");
		}
		
	}

}
