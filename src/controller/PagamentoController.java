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
	
	public double resgatarCustoLocacao(int idLocacao) throws IOException {
		return new LocacaoController().calcularCustoLocacao(new LocacaoDAO().recuperar(idLocacao));
	}
	
	public double resgatarCustoLocacao(Object locacaoInfo, JTextField pagamento) throws IOException {
		return new LocacaoController().calcularCustoLocacao(locacaoInfo, pagamento);
	}
	
	public double calcularMulta(Object locacaoInfo, JTextField dataPagamento) throws IOException {
		
		int idLocacao = Integer.valueOf(locacaoInfo.toString().split(" ")[1]);
		
		LocalDate devolucao = new LocacaoDAO().recuperar(idLocacao).getDevolucao();
		LocalDate pagamento = LocalDate.parse(dataPagamento.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		
		long dias = ChronoUnit.DAYS.between(devolucao, pagamento);
		
 		double custoJuros = new LocacaoDAO().recuperar(idLocacao).getVeiculo().calcularCustoLocacaoDiario() * 0.5;

		double multa = custoJuros * dias;
		
		return multa;
	}
	
	public double calcularTotal(Object locacaoInfo, JTextField dataPagamento) throws IOException {
		double locacao = new LocacaoController().calcularCustoLocacao(locacaoInfo, dataPagamento);
		
		double multa = calcularMulta(locacaoInfo, dataPagamento);
		
		return locacao + multa;
	}
	
	public void cadastrarDados(Object locacaoInfo, Object metodo, JTextField devolucaoDataPagamento) throws Exception {
		
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		int idLocacao = Integer.valueOf(locacaoInfo.toString().split(" ")[1]);
		
		Locacao locacao = new LocacaoDAO().recuperar(idLocacao);
		
		pagamento = new Pagamento();
		
		if (metodo.equals("DINHEIRO")) {
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
		
		LocalDate dataPagamento = LocalDate.parse(devolucaoDataPagamento.getText(), formatador);
		LocalDate dataRetirada = locacao.getRetirada();
		
		if (ChronoUnit.DAYS.between(dataRetirada, dataPagamento) <= 0) {
			throw new IllegalArgumentException("DATA DE PAGAMENTO DEVE SER MAIOR DO QUE A DATA DE RETIRADA");
		}

		pagamento.setId(locacao.getId());
		pagamento.setIdLocacao(locacao.getId());
		pagamento.setDataPagamento(dataPagamento);
		pagamento.setValorPago(calcularTotal(locacaoInfo, devolucaoDataPagamento));
		
		System.out.println(pagamento);
		
		Veiculo veiculo = locacao.getVeiculo();
		veiculo.setStatus(StatusLocacao.DISPONIVEL);
			
		new VeiculoDAO().atualizar(veiculo);
		
		new PagamentoDAO().salvar(pagamento);
		
	}

}
