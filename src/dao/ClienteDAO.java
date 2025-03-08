package dao;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.Cliente;

public class ClienteDAO implements Persistencia<Cliente, String> {
	
	private static final String CAMINHO_JSON = "dados/clientes.json";

	@Override
	public Cliente recuperar(String cpf) throws IOException {
		
		try (FileReader fr = new FileReader(CAMINHO_JSON)) {
			if (fr.read() == -1)
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
	public List<Cliente> recuperarTodos() throws IOException {
		
		try (FileReader fr = new FileReader(CAMINHO_JSON)) {
			if (fr.read() == -1)
				return null;
		}

		List<Cliente> clientes;
		
		try (FileReader fr = new FileReader(CAMINHO_JSON)) {
			
			Type tipo = new TypeToken<ArrayList<Cliente>>(){}.getType();

			clientes = new Gson().fromJson(fr, tipo);
			
		}
		
		Collections.sort(clientes);
		
		return clientes;
	}

	@Override
	public void salvar(Cliente cliente) throws IOException {
		
		List<Cliente> clientes;
		
		try (FileReader fr = new FileReader(CAMINHO_JSON)) {
			if (fr.read() == -1)
				clientes = new ArrayList<>();
			else
				clientes = new ArrayList<>(recuperarTodos());
		}
		
		for (Cliente clienteAtual : clientes) {
			if (cliente.getCpf().equals(clienteAtual.getCpf())) {
				return;
			}
		}
		
		clientes.add(cliente);
		
		String json = new Gson().toJson(clientes);
		
		try (FileWriter fw = new FileWriter(CAMINHO_JSON)) {
			fw.write(json);
		}

	}

	@Override
	public void remover(Cliente cliente) throws IOException {
		List<Cliente> clientes;
		
		List<Cliente> clientesNovo = new ArrayList<>();
		
		try (FileReader fr = new FileReader(CAMINHO_JSON)) {
			if (fr.read() == -1)
				clientes = new ArrayList<>();
			else
				clientes = new ArrayList<>(recuperarTodos());
		}
		
		for (Cliente clienteAtual : clientes) {
			if (!cliente.getCpf().equals(clienteAtual.getCpf())) {
				clientesNovo.add(clienteAtual);
			}
		}

		String json = new Gson().toJson(clientesNovo);
		
		try (FileWriter fw = new FileWriter(CAMINHO_JSON)) {
			fw.write(json);
		}
	}
	
	@Override
	public void atualizar(Cliente cliente) throws IOException {
		List<Cliente> clientes;
		
		List<Cliente> clientesNovo = new ArrayList<>();
		
		try (FileReader fr = new FileReader(CAMINHO_JSON)) {
			if (fr.read() == -1)
				clientes = new ArrayList<>();
			else
				clientes = new ArrayList<>(recuperarTodos());
		}
		
		for (Cliente clienteAtual : clientes) {
			if (cliente.getCpf().equals(clienteAtual.getCpf())) {
				clientesNovo.add(cliente);
			}
			else {
				clientesNovo.add(clienteAtual);
			}

		}

		String json = new Gson().toJson(clientesNovo);
		
		try (FileWriter fw = new FileWriter(CAMINHO_JSON)) {
			fw.write(json);
		}
	}

}
