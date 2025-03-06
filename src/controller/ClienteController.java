package controller;

import dao.ClienteDAO;
import model.Cliente;

public class ClienteController {
	
	private Cliente cliente;

	public ClienteController() {

	}
	
	public void salvar() throws Exception {
		new ClienteDAO().salvar(cliente);
	}
	
	public void remover() throws Exception {
		new ClienteDAO().remover(cliente);
	}
	
	public void atualizar() throws Exception {
		new ClienteDAO().atualizar(cliente);
	}

}
