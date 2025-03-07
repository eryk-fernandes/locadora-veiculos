package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFormattedTextField;

import dao.ClienteDAO;
import dao.LocacaoDAO;
import dao.VeiculoDAO;
import model.Locacao;

public class LocacaoController {
	
	private Locacao locacao;
	
	public LocacaoController() {

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
				
				texto.append("ID: " + locacao.getId() + ". ");
				texto.append("CPF: " + locacao.getCliente().getCpf() + ". ");
				texto.append("PLACA: " + locacao.getVeiculo().getPlaca());
				
				locacoes.add(texto.toString());
			}
		} catch (Exception e) {
			
		}
		
		if (locacoes.size() == 0) {
			return new String[] {"NENHUMA LOCAÇÃO ADICIONADA"};
		}
		
		locacoes.add("");
		
		return locacoes.toArray(new String[locacoes.size()]);
	}
	
	public void cadastrarDados(Object clienteCpf, Object veiculoPlaca, JFormattedTextField devolucao) throws Exception {
		
		locacao = new Locacao();
		
		locacao.setId(gerarId());
		locacao.setCliente(new ClienteDAO().recuperar(clienteCpf.toString()));
		locacao.setVeiculo(new VeiculoDAO().recuperar(veiculoPlaca.toString()));
		locacao.setRetirada(LocalDate.now());
		
		try {
			locacao.setDevolucao(LocalDate.parse(devolucao.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		}
		catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("DATA INVÁLIDA");
		}

		try {
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
