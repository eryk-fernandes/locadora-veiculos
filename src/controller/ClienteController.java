package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;

import dao.ClienteDAO;
import model.Cliente;

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
	
	public String[] recuperarTodosComboBox() {
		
		List<String> clientes = new ArrayList<>();
		
		try {
			for (Cliente cliente : recuperarTodos()) {
				clientes.add(cliente.getCpf());
			}
		} catch (Exception e) {
			
		}
		
		if (clientes.size() == 0) {
			return new String[] {"NENHUM CLIENTE ADICIONADO"};
		}
			
		
		return clientes.toArray(new String[clientes.size()]);
	}
	
	public void cadastrarDados(JTextField nome, JTextField cpf, JTextField telefone, JTextField email) throws Exception {
		
		cliente = new Cliente();
		
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

}
