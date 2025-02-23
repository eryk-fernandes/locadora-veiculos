package controller;

import model.Cliente;

public class ClienteController {
	
	private Cliente cliente;

	public ClienteController(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Integer getClienteId() {
		return cliente.getId();
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
	
	public void setClienteId(Integer id) {
		cliente.setId(id);
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

}
