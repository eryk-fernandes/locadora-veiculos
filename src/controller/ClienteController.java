package controller;

import dao.ClienteDAO;
import model.Cliente;

public class ClienteController {
	
	private Cliente cliente;

	public ClienteController(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public String getClienteNome() {
		return cliente.getNome();
	}

	public String getClienteCpf() {
		return cliente.getCpf();
	}

	public String getClienteTelefone() {
		return cliente.getTelefone();
	}

	public String getClienteEmail() {
		return cliente.getEmail();
	}
	
	public void setClienteNome(String nome) {
		cliente.setNome(nome);
	}
	
	public void setClienteCpf(String cpf) {
		cliente.setCpf(cpf);
	}

	public void setClienteTelefone(String telefone) {
		cliente.setTelefone(telefone);
	}

	public void setClienteEmail(String email) {
		cliente.setEmail(email);
	}
	
	public void cadastrarCliente() throws Exception {
		new ClienteDAO().salvar(cliente);
	}
	
	public void removerCliente() throws Exception {
		new ClienteDAO().remover(cliente);
	}
	
	public void atualizarCliente() throws Exception {
		new ClienteDAO().atualizar(cliente);
	}

}
