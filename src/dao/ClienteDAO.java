package dao;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.Cliente;

public class ClienteDAO extends DAO<Cliente, String> {
	
	public ClienteDAO() {
		this.caminhoJson = "src\\json\\clientes.json";
	}

	@Override
	public Cliente recuperar(String cpf) throws Exception {
		
		if (isVazio()) {
			return null;
		}
		
		for (Cliente cliente : recuperarTodos()) {
			if (cpf.equals(cliente.getCpf())) {
				return cliente;
			}
		}
		
		return null;
			
	}

	@Override
	public List<Cliente> recuperarTodos() throws Exception {
		
		if (isVazio()) {
			return null;
		}

		List<Cliente> clientes;
		
		try (FileReader fr = new FileReader(caminhoJson)) {
			
			Type tipo = new TypeToken<ArrayList<Cliente>>(){}.getType();

			clientes = new Gson().fromJson(fr, tipo);
			
		}
		
		return clientes;
	}

	@Override
	public void salvar(Cliente cliente) throws Exception {
		
		List<Cliente> clientes;
		
		if (isVazio()) 
			clientes = new ArrayList<>();
		else
			clientes = new ArrayList<>(recuperarTodos());
		
		for (Cliente clienteAtual : clientes) {
			if (cliente.getCpf().equals(clienteAtual.getCpf())) {
				return;
			}
		}
		
		clientes.add(cliente);
		
		String json = new Gson().toJson(clientes);
		
		try (FileWriter fw = new FileWriter(caminhoJson)) {
			fw.write(json);
		}

	}

	@Override
	public void remover(Cliente cliente) throws Exception {
		List<Cliente> clientes;
		
		List<Cliente> clientesNovo = new ArrayList<>();
		
		if (isVazio()) 
			clientes = new ArrayList<>();
		else
			clientes = new ArrayList<>(recuperarTodos());
		
		for (Cliente clienteAtual : clientes) {
			if (!cliente.getCpf().equals(clienteAtual.getCpf())) {
				clientesNovo.add(clienteAtual);
			}
		}

		String json = new Gson().toJson(clientesNovo);
		
		try (FileWriter fw = new FileWriter(caminhoJson)) {
			fw.write(json);
		}
	}
	
	@Override
	public void atualizar(Cliente cliente) throws Exception {
		List<Cliente> clientes;
		
		List<Cliente> clientesNovo = new ArrayList<>();
		
		if (isVazio()) 
			clientes = new ArrayList<>();
		else
			clientes = new ArrayList<>(recuperarTodos());
		
		for (Cliente clienteAtual : clientes) {
			if (cliente.getCpf().equals(clienteAtual.getCpf())) {
				clientesNovo.add(cliente);
			}
			else {
				clientesNovo.add(clienteAtual);
			}

		}

		String json = new Gson().toJson(clientesNovo);
		
		try (FileWriter fw = new FileWriter(caminhoJson)) {
			fw.write(json);
		}
	}

}
