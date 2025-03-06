package dao;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.Pagamento;

public class PagamentoDAO implements Persistencia<Pagamento, Integer> {
	
	private static final String CAMINHO_JSON = "src/json/pagamentos.json";

	@Override
	public Pagamento recuperar(Integer id) throws IOException {
		
		try (FileReader fr = new FileReader(CAMINHO_JSON)) {
			if (fr.read() == -1)
				return null;
		}
		
		for (Pagamento pagamento : recuperarTodos()) {
			if (id.equals(pagamento.getId())) {
				return pagamento;
			}
		}
		
		return null;
	}

	@Override
	public List<Pagamento> recuperarTodos() throws IOException {
		
		try (FileReader fr = new FileReader(CAMINHO_JSON)) {
			if (fr.read() == -1)
				return null;
		}

		List<Pagamento> pagamentos;
		
		try (FileReader fr = new FileReader(CAMINHO_JSON)) {
			
			Type tipo = new TypeToken<ArrayList<Pagamento>>(){}.getType();

			pagamentos = new Gson().fromJson(fr, tipo);
			
		}
		
		return pagamentos;
	}

	@Override
	public void salvar(Pagamento pagamento) throws IOException {
		
		List<Pagamento> pagamentos;
		
		try (FileReader fr = new FileReader(CAMINHO_JSON)) {
			if (fr.read() == -1)
				pagamentos = new ArrayList<>();
			else
				pagamentos = new ArrayList<>(recuperarTodos());
		}
		
		for (Pagamento pagamentoAtual : pagamentos) {
			if (pagamento.getId().equals(pagamentoAtual.getId())) {
				return;
			}
		}
		
		pagamentos.add(pagamento);
		
		String json = new Gson().toJson(pagamentos);
		
		try (FileWriter fw = new FileWriter(CAMINHO_JSON)) {
			fw.write(json);
		}
	}

	@Override
	public void remover(Pagamento pagamento) throws IOException {
		List<Pagamento> pagamentos;
		
		List<Pagamento> pagamentosNovo = new ArrayList<>();
		
		try (FileReader fr = new FileReader(CAMINHO_JSON)) {
			if (fr.read() == -1)
				pagamentos = new ArrayList<>();
			else
				pagamentos = new ArrayList<>(recuperarTodos());
		}
		
		for (Pagamento clienteAtual : pagamentos) {
			if (!pagamento.getId().equals(clienteAtual.getId())) {
				pagamentosNovo.add(clienteAtual);
			}
		}

		String json = new Gson().toJson(pagamentosNovo);
		
		try (FileWriter fw = new FileWriter(CAMINHO_JSON)) {
			fw.write(json);
		}
	}

	@Override
	public void atualizar(Pagamento pagamento) throws IOException {
		List<Pagamento> pagamentos;
		
		List<Pagamento> pagamentosNovo = new ArrayList<>();
		
		try (FileReader fr = new FileReader(CAMINHO_JSON)) {
			if (fr.read() == -1)
				pagamentos = new ArrayList<>();
			else
				pagamentos = new ArrayList<>(recuperarTodos());
		}
		
		for (Pagamento clienteAtual : pagamentos) {
			if (pagamento.getId().equals(clienteAtual.getId())) {
				pagamentosNovo.add(pagamento);
			}
			else {
				pagamentosNovo.add(clienteAtual);
			}

		}

		String json = new Gson().toJson(pagamentosNovo);
		
		try (FileWriter fw = new FileWriter(CAMINHO_JSON)) {
			fw.write(json);
		}
	}

}
