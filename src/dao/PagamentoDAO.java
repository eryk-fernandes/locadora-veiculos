package dao;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import model.Pagamento;

public class PagamentoDAO implements Persistencia<Pagamento, Integer> {
	
	private static final String CAMINHO_JSON = "dados/pagamentos.json";

	@Override
	public Pagamento recuperar(Integer id) throws IOException {
		
		if (isVazio())
			return null;
		
		for (Pagamento pagamento : recuperarTodos()) {
			if (id.equals(pagamento.getId())) {
				return pagamento;
			}
		}
		
		return null;
	}

	@Override
	public List<Pagamento> recuperarTodos() throws IOException {
		
		if (isVazio()) {
			return null;
		}

		List<Pagamento> pagamentos;
		
		try (FileReader fr = new FileReader(CAMINHO_JSON)) {

			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.
				registerTypeAdapter(LocalDate.class, new SerializadorLocalDate());
				
			Gson gson = gsonBuilder.create();
			
			pagamentos = gson.fromJson(fr, new TypeToken<ArrayList<Pagamento>>(){}.getType());
			
		}
		
		return pagamentos;
	}
	
	@Override
	public void salvar(Pagamento pagamento) throws IOException {
		
		List<Pagamento> pagamentos;
		
		if (isVazio()) {
			pagamentos = new ArrayList<>();
		}
		else {
			pagamentos = new ArrayList<>(recuperarTodos());
		}
		
		for (Pagamento pagamentoAtual : pagamentos) {
			if (pagamento.getId().equals(pagamentoAtual.getId())) {
				return;
			}
		}
		
		pagamentos.add(pagamento);
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.
			registerTypeAdapter(LocalDate.class, new SerializadorLocalDate());
			
		Gson gson = gsonBuilder.create();
		
		String json = gson.toJson(pagamentos);
		
		try (FileWriter fw = new FileWriter(CAMINHO_JSON)) {
			fw.write(json);
		}
	}

	@Override
	public void remover(Integer id) throws IOException {
		List<Pagamento> pagamentos;
		
		List<Pagamento> pagamentosNovo = new ArrayList<>();
		
		if (isVazio()) {
			pagamentos = new ArrayList<>();
		}
		else {
			pagamentos = new ArrayList<>(recuperarTodos());
		}
		
		for (Pagamento clienteAtual : pagamentos) {
			if (!id.equals(clienteAtual.getId())) {
				pagamentosNovo.add(clienteAtual);
			}
		}

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.
			registerTypeAdapter(LocalDate.class, new SerializadorLocalDate());
			
		Gson gson = gsonBuilder.create();
		
		String json = gson.toJson(pagamentosNovo);
		
		try (FileWriter fw = new FileWriter(CAMINHO_JSON)) {
			fw.write(json);
		}
	}

	@Override
	public void atualizar(Pagamento pagamento) throws IOException {
		List<Pagamento> pagamentos;
		
		List<Pagamento> pagamentosNovo = new ArrayList<>();
		
		if (isVazio()) {
			pagamentos = new ArrayList<>();
		}
		else {
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

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.
			registerTypeAdapter(LocalDate.class, new SerializadorLocalDate());
			
		Gson gson = gsonBuilder.create();
		
		String json = gson.toJson(pagamentosNovo);
		
		try (FileWriter fw = new FileWriter(CAMINHO_JSON)) {
			fw.write(json);
		}
	}

	@Override
	public boolean isVazio() throws IOException {
		try (FileReader fr = new FileReader(CAMINHO_JSON)) {
			if (fr.read() == -1)
				return true;
		}
		return false;
	}

}
