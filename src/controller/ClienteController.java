package controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;

import dao.ClienteDAO;
import dao.LocacaoDAO;
import dao.PagamentoDAO;
import exception.ProibidoRemoverException;
import model.Cliente;
import model.Locacao;

public class ClienteController {
	
	private Cliente cliente;

	public ClienteController() {
		
	}
	
	public List<Cliente> recuperarTodos() throws Exception {
		return new ClienteDAO().recuperarTodos();
	}
	
	public String[] criarListaCPFs() throws Exception {
		
		List<String> clientes = new ArrayList<>();
		
		if (new ClienteDAO().isVazio()) {
			clientes.add("NENHUM CLIENTE ADICIONADO");
			return clientes.toArray(new String[clientes.size()]);
		}

		for (Cliente cliente : recuperarTodos()) {
			clientes.add(cliente.getCpf());
		}
			
		if (clientes.size() == 0) {
			clientes.add("NENHUM CLIENTE ADICIONADO");
		}
		
		return clientes.toArray(new String[clientes.size()]);
	}
	
	public void cadastrarDados(JTextField clienteNome, JTextField clinteCpf, JTextField clienteTelefone, JTextField clienteEmail) throws Exception {
		
		String nome = clienteNome.getText();
		String cpf = clinteCpf.getText().replace("-", "").replace(".", "").replace(" ", "");
		String telefone = clienteTelefone.getText().replace(" ", "").replace("-", "");
		String email = clienteEmail.getText();
		
		if (nome.length() == 0 || cpf.length() == 0 || telefone.length() == 0 || email.length() == 0) {
			throw new IllegalArgumentException("DIGITE TODOS OS ATRIBUTOS");
		}
		
		if (!cpf.chars().allMatch(Character::isDigit)) {
			throw new IllegalArgumentException("CPF NÃO PODE CONTER LETRAS");
		}
		
		if (!telefone.chars().allMatch(Character::isDigit)) {
			throw new IllegalArgumentException("TELEFONE NÃO PODE CONTER LETRAS");
		}
		
		if (cpf.length() != 11) {
			throw new IllegalArgumentException("TAMANHO DE CPF INVÁLIDO");
		}
		
		if (new ClienteDAO().recuperar(cpf) != null) {
			throw new IllegalArgumentException("ESSE CPF JÁ ESTÁ PRESENTE NO SISTEMA");
		}
		
		cliente = new Cliente();
		
		cliente.setNome(nome);
		cliente.setCpf(cpf);
		cliente.setTelefone(telefone);
		cliente.setEmail(email);
		
		new ClienteDAO().salvar(cliente);
	}
	
	public void editarCliente(Object clienteInfo, Object tipoAtributo, JTextField atributo) throws Exception {
		Cliente cliente = new ClienteDAO().recuperar(clienteInfo.toString());
		
		if (clienteInfo.toString().equals("NENHUM CLIENTE ADICIONADO")) {
			throw new IllegalArgumentException("NENHUM CLIENTE NO SISTEMA");
		}
		
		if (atributo.getText().length() == 0) {
			throw new IllegalArgumentException("DIGITE UM ATRIBUTO");
		}
		
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
		
		if (clienteInfo.toString().equals("NENHUM CLIENTE ADICIONADO")) {
			throw new IllegalArgumentException("NENHUM CLIENTE NO SISTEMA");
		}
		
		if (new ClienteDAO().recuperar(cpf) == null) {
			throw new IllegalArgumentException("CLIENTE NÃO EXISTENTE");
		}
		
		if (new LocacaoDAO().recuperarTodos() == null) {
			new ClienteDAO().remover(cpf);
			return;
		}
		
		boolean clientePossuiLocacao = false;;
		
		for (Locacao locacao : new LocacaoDAO().recuperarTodos()) {
			
			if (cpf.equals(locacao.getCliente().getCpf())) {
				clientePossuiLocacao = true;
			}
			
			if (cpf.equals(locacao.getCliente().getCpf()) && new PagamentoDAO().recuperar(locacao.getId()) != null) {
				new ClienteDAO().remover(cpf);
				return;
			}
		}
		
		if (!clientePossuiLocacao) {
			new ClienteDAO().remover(cpf);
		}
		else {
			throw new ProibidoRemoverException("NÃO É PERMITIDO REMOVER CLIENTES COM LOCAÇÕES PENDENTES");
		}

	}

}
