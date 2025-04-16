package org.example.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;

import org.example.model.dao.LocacaoDAO;
import org.example.model.dao.PagamentoDAO;
import org.example.model.dao.VeiculoDAO;
import org.example.model.entities.Locacao;
import org.example.model.entities.MetodoPagamento;
import org.example.model.entities.Pagamento;
import org.example.model.entities.StatusLocacao;
import org.example.model.entities.Veiculo;

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
		
		int idLocacao = Integer.parseInt(locacaoInfo.toString().split(" ")[1]);
		
		LocalDate devolucao = new LocacaoDAO().recuperar(idLocacao).getDevolucao();
		LocalDate pagamento = LocalDate.parse(dataPagamento.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		
		long dias = ChronoUnit.DAYS.between(devolucao, pagamento);
		
 		double custoJuros = new LocacaoDAO().recuperar(idLocacao).getVeiculo().calcularCustoLocacaoDiario() * 0.5;

        return custoJuros * dias;
	}
	
	public double calcularTotal(Object locacaoInfo, JTextField dataPagamento) throws IOException {
		double locacao = new LocacaoController().calcularCustoLocacao(locacaoInfo, dataPagamento);
		
		double multa = calcularMulta(locacaoInfo, dataPagamento);
		
		return locacao + multa;
	}
	
	public void cadastrarDados(Object locacaoInfo, Object metodo, JTextField devolucaoDataPagamento) throws Exception {
		
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		int idLocacao = Integer.parseInt(locacaoInfo.toString().split(" ")[1]);
		
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
		
		Veiculo veiculo = locacao.getVeiculo();
		veiculo.setStatus(StatusLocacao.DISPONIVEL);
			
		new VeiculoDAO().atualizar(veiculo);
		
		new PagamentoDAO().salvar(pagamento);
		
	}
	
	public double getFaturamentoMensal(Pagamento pagamento) throws IOException {
		List<Pagamento> pagamentos = new PagamentoDAO().recuperarTodos();
		
		double faturamento = 0.0; 
		
		Month mes = pagamento.getDataPagamento().getMonth();
		
		for (Pagamento pagamentoAtual : pagamentos) {
			if (mes.equals(pagamentoAtual.getDataPagamento().getMonth())) {
				faturamento += pagamentoAtual.getValorPago();
			}
		}
		
		return faturamento;
	}
	
	public String[] faturamentosPorMes() throws IOException {
		
		List<String> pagamentos = new ArrayList<>();
		
		if (new PagamentoDAO().isVazio()) {
			pagamentos.add("NENHUM PAGAMENTO");
			
			return pagamentos.toArray(new String[0]);
		}
		
		for (Pagamento pagamento : new PagamentoDAO().recuperarTodos()) {

            String texto = pagamento.getId() + " " +
                    pagamento.getIdLocacao() + " " +
                    String.format("%.2f", pagamento.getValorPago()) + " " +
                    pagamento.getMetodoPagamento() + " " +
                    pagamento.getDataPagamento() + " " +
                    String.format("%.2f", getFaturamentoMensal(pagamento));
				
			pagamentos.add(texto);
		}
		
		if (pagamentos.isEmpty()) {
			pagamentos.add("NENHUM PAGAMENTO");
		}
			
		return pagamentos.toArray(new String[0]);
	}

}
