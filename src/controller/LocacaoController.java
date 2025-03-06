package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
				locacoes.add(locacao.getId() + " " + locacao.getCliente().getCpf() + " " + locacao.getVeiculo().getPlaca());
			}
		} catch (Exception e) {
			
		}
		
		if (locacoes.size() == 0) {
			return new String[] {"NENHUMA LOCAÇÃO ADICIONADO"};
		}
			
		
		return locacoes.toArray(new String[locacoes.size()]);
	}
	
	public void cadastrarDados(Object clienteCpf, Object veiculoPlaca) throws Exception {
		
		locacao = new Locacao();
		
		locacao.setId(gerarId());
		
		System.out.println(new ClienteDAO().recuperar(clienteCpf.toString()));
		
		locacao.setCliente(new ClienteDAO().recuperar(clienteCpf.toString()));
		locacao.setVeiculo(new VeiculoDAO().recuperar(veiculoPlaca.toString()));
		
		try {
			salvar();
		} catch (Exception e) {
			throw new IOException("ERRO AO ADICIONAR O USUÁRIO");
		}
	}
	
	public int gerarId() throws Exception {
		
		if (recuperarTodos().size() == 0) {
			return 100;
		}
		
		int id = 100;
		
		for (Locacao locacao : recuperarTodos()) {
			if (locacao.getId() == id) {
				id += 1;
			}
		}
		
		return id;
	}

}
