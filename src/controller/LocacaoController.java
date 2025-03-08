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
import dao.VeiculoDAO;
import excecoes.VeiculoLocadoException;
import model.Locacao;
import model.StatusLocacao;
import model.Veiculo;

public class LocacaoController {
	
	private Locacao locacao;
	
	public LocacaoController() {

	}
	
	public double calcularCustoLocacao() {
		
		LocalDate retirada = locacao.getRetirada();
 		
 		long dias = ChronoUnit.DAYS.between(retirada, LocalDate.now());
 		double custoLocacaoDiario = locacao.getVeiculo().calcularCustoLocacaoDiario();
		
		return custoLocacaoDiario * dias;
	}
	
	public double calcularCustoLocacao(Object veiculoInfo, JTextField retirada, JTextField devolucao) throws IOException {

		Veiculo veiculo = null;
		
		LocalDate dataRetirada = LocalDate.parse(retirada.getText().split(" ")[0], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		
		LocalDate dataDevolucao = LocalDate.parse(devolucao.getText().split(" ")[0], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		
		String placa = veiculoInfo.toString().split(" ")[0];
		
		try {
			veiculo = new VeiculoDAO().recuperar(placa);
		} catch (IOException e) {
			throw new IOException("NÃO FOI POSSÍVEL CALCULAR VALOR DA LOCAÇÃO");
		}
		
 		long dias = ChronoUnit.DAYS.between(dataRetirada, dataDevolucao);
 		double custoLocacaoDiario = veiculo.calcularCustoLocacaoDiario();
 		
		return custoLocacaoDiario * dias;
	}
	
	public double calcularCustoLocacao(Locacao locacao) throws IOException {
		
 		long dias = ChronoUnit.DAYS.between(locacao.getRetirada(), locacao.getDevolucao());
 		double custoLocacaoDiario = locacao.getVeiculo().calcularCustoLocacaoDiario();
 		
		return custoLocacaoDiario * dias;
	}
	
	public Locacao recuperar(Integer id) throws Exception {
		return new LocacaoDAO().recuperar(id);
	}
	
	public List<Locacao> recuperarTodos() throws Exception {
		return new LocacaoDAO().recuperarTodos();
	}
	
	public void salvar() throws Exception {
		new LocacaoDAO().salvar(locacao);
	}
	
	public void remover() throws Exception {
		new LocacaoDAO().remover(locacao);
	}
	
	public void atualizar() throws Exception {
		new LocacaoDAO().atualizar(locacao);
	}
	
	public String[] recuperarTodosComboBox() {
		
		List<String> locacoes = new ArrayList<>();
		
		try {
			for (Locacao locacao : recuperarTodos()) {
				StringBuilder texto = new StringBuilder();
				
				texto.append("ID: " + locacao.getId() + " ");
				texto.append("CPF: " + locacao.getCliente().getCpf() + " ");
				texto.append("PLACA: " + locacao.getVeiculo().getPlaca());
				
				locacoes.add(texto.toString());
			}
		} catch (Exception e) {
			
		}
		
		if (locacoes.size() == 0) {
			return new String[] {"NENHUMA LOCAÇÃO ADICIONADA"};
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
		
		locacao = new Locacao();
		
		locacao.setId(gerarId());
		locacao.setCliente(new ClienteDAO().recuperar(cpf));
		locacao.setVeiculo(new VeiculoDAO().recuperar(placa));
		
		try {
			locacao.setRetirada(LocalDate.parse(retirada.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			locacao.setDevolucao(LocalDate.parse(devolucao.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		}
		catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("DATA INVÁLIDA");
		}

		try {
			Veiculo veiculo = new VeiculoDAO().recuperar(placa);
			
			veiculo.setStatus(StatusLocacao.LOCADO);
			
			new VeiculoDAO().atualizar(veiculo);
			
			salvar();
		} catch (Exception e) {
			throw new IOException("ERRO AO ADICIONAR O USUÁRIO");
		}
	}
	
	public int gerarId() throws Exception {
		
		int id = 100;
		
		if (recuperarTodos() == null) {
			return id;
		}
		
		for (Locacao locacao : recuperarTodos()) {
			if (locacao.getId() == id) {
				id += 1;
			}
		}
		
		return id;
	}

}
