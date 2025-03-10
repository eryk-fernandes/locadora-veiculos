package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;

import dao.ClienteDAO;
import dao.LocacaoDAO;
import dao.PagamentoDAO;
import dao.VeiculoDAO;
import excecoes.VeiculoLocadoException;
import model.Locacao;
import model.StatusLocacao;
import model.Veiculo;

public class LocacaoController {
	
	private Locacao locacao;
	
	public LocacaoController() {

	}
	
	public double calcularCustoLocacao(Object locacaoInfo, JTextField pagamento) throws IOException {
		
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		int idLocacao = Integer.valueOf(locacaoInfo.toString().split(" ")[1]);
		
		Locacao locacao = new LocacaoDAO().recuperar(idLocacao);
		
		LocalDate dataRetirada = locacao.getRetirada();
		LocalDate dataPagamento = LocalDate.parse(pagamento.getText(), formatador);
 		
 		long dias = ChronoUnit.DAYS.between(dataRetirada, dataPagamento);
 		double custoLocacaoDiario = locacao.getVeiculo().calcularCustoLocacaoDiario();
		
		return custoLocacaoDiario * dias;
	}
	
	public double calcularCustoLocacao(Object veiculoInfo, JTextField retirada, JTextField pagamento) throws IOException {

		Veiculo veiculo = null;
		
		LocalDate dataRetirada = LocalDate.parse(retirada.getText().split(" ")[0], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		
		LocalDate dataDevolucao = LocalDate.parse(pagamento.getText().split(" ")[0], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		
		String placa = veiculoInfo.toString().split(" ")[0];
		
		veiculo = new VeiculoDAO().recuperar(placa);
		
 		long dias = ChronoUnit.DAYS.between(dataRetirada, dataDevolucao);
 		double custoLocacaoDiario = veiculo.calcularCustoLocacaoDiario();
 		
		return custoLocacaoDiario * dias;
	}
	
	public double calcularCustoLocacao(Locacao locacao) throws IOException {
		
 		long dias = ChronoUnit.DAYS.between(locacao.getRetirada(), locacao.getDevolucao());
 		double custoLocacaoDiario = locacao.getVeiculo().calcularCustoLocacaoDiario();
 		
		return custoLocacaoDiario * dias;
	}
	
	public String[] recuperarStringLocacoes() throws Exception {
		
		List<String> locacoes = new ArrayList<>();
		
		if (new LocacaoDAO().isVazio()) {
			locacoes.add("NENHUM CLIENTE ADICIONADO");
			
			return locacoes.toArray(new String[locacoes.size()]);
		}
		
		for (Locacao locacao : new LocacaoDAO().recuperarTodos()) {
			StringBuilder texto = new StringBuilder();
				
			texto.append("ID: " + locacao.getId() + " ");
			texto.append("CPF: " + locacao.getCliente().getCpf() + " ");
			texto.append("PLACA: " + locacao.getVeiculo().getPlaca() + " ");
			texto.append("DATA: " + locacao.getRetirada().format(DateTimeFormatter.ofPattern("MM/yyyy")));
				
			locacoes.add(texto.toString());
		}
		
		if (locacoes.size() == 0) {
			locacoes.add("NENHUMA LOCAÇÃO ADICIONADA");
		}

		return locacoes.toArray(new String[locacoes.size()]);
	}
	
	public String[] recuperarStringLocacoesPendentes() throws Exception {
		
		List<String> locacoes = new ArrayList<>();
		
		if (new LocacaoDAO().isVazio()) {
			locacoes.add("NENHUM CLIENTE ADICIONADO");
			
			return locacoes.toArray(new String[locacoes.size()]);
		}
		
		for (Locacao locacao : new LocacaoDAO().recuperarTodos()) {		
			
			if (new PagamentoDAO().recuperar(locacao.getId()) == null) {
				StringBuilder texto = new StringBuilder();
				
				texto.append("ID: " + locacao.getId() + " ");
				texto.append("CPF: " + locacao.getCliente().getCpf() + " ");
				texto.append("PLACA: " + locacao.getVeiculo().getPlaca() + " ");
				texto.append("DATA: " + locacao.getRetirada().format(DateTimeFormatter.ofPattern("MM/yyyy")));
					
				locacoes.add(texto.toString());
			}
		}
		
		if (locacoes.size() == 0) {
			locacoes.add("NENHUMA LOCAÇÃO ADICIONADA");
		}

		return locacoes.toArray(new String[locacoes.size()]);
	}
	
	public void cadastrarDados(Object clienteCpf, Object veiculoInfo, JTextField retirada, JTextField devolucao) throws Exception {
		
		String cpf = clienteCpf.toString();
		String status = veiculoInfo.toString().split(" ")[2];
		String placa = veiculoInfo.toString().split(" ")[0];
		
		if (status.equals("LOCADO")) {
			throw new VeiculoLocadoException("NÃO É POSSÍVEL ADICIONAR ESSE VEÍCULO POIS ELE JÁ ESTÁ LOCADO");
		}
		
		LocalDate dataRetirada = LocalDate.parse(retirada.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		LocalDate dataDevolucao = LocalDate.parse(devolucao.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		
		if (ChronoUnit.DAYS.between(dataRetirada, dataDevolucao) <= 0) {
			throw new IllegalArgumentException("DATA DE DEVOLUÇÃO DEVE SER MAIOR DO QUE A DATA DE RETIRADA");
		}
		
		locacao = new Locacao();
		
		locacao.setId(gerarId());
		locacao.setCliente(new ClienteDAO().recuperar(cpf));
		locacao.setVeiculo(new VeiculoDAO().recuperar(placa));
		
		locacao.setRetirada(dataRetirada);
		locacao.setDevolucao(dataDevolucao);

		Veiculo veiculo = new VeiculoDAO().recuperar(placa);
			
		veiculo.setStatus(StatusLocacao.LOCADO);
		
		new VeiculoDAO().atualizar(veiculo);
			
		new LocacaoDAO().salvar(locacao);
	}
	
	public int gerarId() throws Exception {
		
		int id = 100;
		
		LocacaoDAO locacaoDAO = new LocacaoDAO();
		
		if (locacaoDAO.recuperarTodos() == null) {
			return id;
		}
		
		for (Locacao locacao : locacaoDAO.recuperarTodos()) {
			if (locacao.getId() == id) {
				id += 1;
			}
		}
		
		return id;
	}

}
