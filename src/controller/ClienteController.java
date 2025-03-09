package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;

import dao.ClienteDAO;
import dao.LocacaoDAO;
import dao.PagamentoDAO;
import excecoes.ProibidoRemoverException;
import excecoes.TamanhoInvalidoException;
import model.Cliente;
import model.Locacao;

public class ClienteController {
	
	private Cliente cliente;

	public ClienteController() {
		
	}
	
	public List<Cliente> recuperarTodos() throws Exception {
		return new ClienteDAO().recuperarTodos();
	}
	
	public void salvar() throws IOException {
		new ClienteDAO().salvar(cliente);
	}
	
	public void remover() throws IOException {
		new ClienteDAO().remover(cliente);
	}
	
	public void atualizar() throws IOException {
		new ClienteDAO().atualizar(cliente);
	}
	
	public String[] criarListaCPFs() throws Exception {
		
		List<String> clientes = new ArrayList<>();

		for (Cliente cliente : recuperarTodos()) {
			clientes.add(cliente.getCpf());
		}
			
		if (clientes.size() == 0) {
			clientes.add("NENHUM VEÍCULO ADICIONADO");
		}
		
		return clientes.toArray(new String[clientes.size()]);
	}
	
	public void cadastrarDados(JTextField nome, JTextField cpf, JTextField telefone, JTextField email) throws Exception {
		
		cliente = new Cliente();
		
		if (cpf.getText().replace("-", "").replace(".", "").replace(" ", "").length() != 11) {
			throw new TamanhoInvalidoException("TAMANHO DE CPF INVÁLIDO");
		}
		
		cliente.setNome(nome.getText());
		cliente.setCpf(cpf.getText().replace("-", "").replace(".", "").replace(" ", ""));
		cliente.setTelefone(telefone.getText().replace("-", "").replace(".", "").replace(" ", ""));
		cliente.setEmail(email.getText());
		
		try {
			salvar();
		} catch (IOException e) {
			throw new IOException("ERRO AO ADICIONAR CLIENTE");
		}
	}
	
	public void editarCliente(Object clienteInfo, Object tipoAtributo, JTextField atributo) throws Exception {
		Cliente cliente = new ClienteDAO().recuperar(clienteInfo.toString());
		
		if (tipoAtributo.toString() == "NOME") {
			cliente.setNome(atributo.getText());
		}
		else if (tipoAtributo.toString() == "TELEFONE") {
			cliente.setTelefone(atributo.getText().replace(" ", "").replace("-", ""));
		}
		else if (tipoAtributo.toString() == "EMAIL") {
			cliente.setEmail(atributo.getText());
		}
		else {
			throw new IllegalArgumentException("SELECIONE UM TIPO DE ATRIBUTO");
		}
		
		if (atributo.getText() == "") {
			throw new IllegalArgumentException("DIGITE UM ATRIBUTO");
		}
		
		new ClienteDAO().atualizar(cliente);
		
		List<Locacao> locacoes = new LocacaoDAO().recuperarTodos();
		
		if (locacoes != null) {
			for (Locacao locacao : locacoes) {
				if (cliente.getCpf().equals(locacao.getCliente().getCpf())) {
					locacao.setCliente(cliente);
					
					new LocacaoDAO().atualizar(locacao);
				}
			}
		}
	}
	
	public void removerCliente(Object clienteInfo) throws Exception {
		
		String cpf = clienteInfo.toString();
		
		Cliente cliente = new ClienteDAO().recuperar(cpf);
		
		if (new LocacaoDAO().recuperarTodos() == null) {
			new ClienteDAO().remover(cliente);
			return;
		}
		
		boolean clientePossuiLocacao = false;;
		
		for (Locacao locacao : new LocacaoDAO().recuperarTodos()) {
			
			if (cpf.equals(locacao.getCliente().getCpf())) {
				clientePossuiLocacao = true;
			}
			
			if (cpf.equals(locacao.getCliente().getCpf()) && new PagamentoDAO().recuperar(locacao.getId()) != null) {
				new ClienteDAO().remover(cliente);
				return;
			}
		}
		
		if (!clientePossuiLocacao) {
			new ClienteDAO().remover(cliente);
		}
		else {
			throw new ProibidoRemoverException("NÃO É PERMITIDO REMOVER CLIENTES COM LOCAÇÕES PENDENTES");
		}

	}

}
