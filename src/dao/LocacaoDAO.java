package dao;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import model.Locacao;
import model.Veiculo;

public class LocacaoDAO implements Persistencia<Locacao, Integer> {
	
	private static final String CAMINHO_JSON = "dados/locacoes.json";

	@Override
	public Locacao recuperar(Integer id) throws IOException {
		try (FileReader fr = new FileReader(CAMINHO_JSON)) {
			if (fr.read() == -1)
				return null;
		}
		
		for (Locacao locacao : recuperarTodos()) {
			if (id.equals(locacao.getId())) {
				return locacao;
			}
		}
		
		return null;
	}

	@Override
	public List<Locacao> recuperarTodos() throws IOException {
		
		try (FileReader fr = new FileReader(CAMINHO_JSON)) {
			if (fr.read() == -1)
				return null;
		}

		List<Locacao> locacoes;
		
		try (FileReader fr = new FileReader(CAMINHO_JSON)) {
			
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.
				registerTypeAdapter(Veiculo.class, new SerializadorModel<Veiculo>()).
				registerTypeAdapter(LocalDate.class, new SerializadorLocalDate());
				
			Gson gson = gsonBuilder.create();

			locacoes = gson.fromJson(fr, new TypeToken<ArrayList<Locacao>>(){}.getType());
		}
		
		Collections.sort(locacoes);
		
		return locacoes;
	}

	@Override
	public void salvar(Locacao locacao) throws IOException {
		
		List<Locacao> locacoes;
		
		try (FileReader fr = new FileReader(CAMINHO_JSON)) {
			if (fr.read() == -1)
				locacoes = new ArrayList<>();
			else
				locacoes = new ArrayList<>(recuperarTodos());
		}
		
		for (Locacao locacaoAtual : locacoes) {
			if (locacao.getId().equals(locacaoAtual.getId()))
				return;
		}
		
		locacoes.add(locacao);
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.
			registerTypeAdapter(Veiculo.class, new SerializadorModel<Veiculo>()).
			registerTypeAdapter(LocalDate.class, new SerializadorLocalDate());
			
		Gson gson = gsonBuilder.create();
		
		String json = gson.toJson(locacoes);
		
		try (FileWriter fw = new FileWriter(CAMINHO_JSON)) {
			fw.write(json);
		}
	}

	@Override
	public void remover(Locacao locacao) throws IOException {
		
		List<Locacao> locacoes;
		
		List<Locacao> locacoesNovo = new ArrayList<>();
		
		try (FileReader fr = new FileReader(CAMINHO_JSON)) {
			if (fr.read() == -1)
				locacoes = new ArrayList<>();
			else
				locacoes = new ArrayList<>(recuperarTodos());
		}
		
		for (Locacao locacaoAtual : locacoes) {
			if (!locacao.getId().equals(locacaoAtual.getId())) {
				locacoesNovo.add(locacaoAtual);
			}
		}
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.
			registerTypeAdapter(Veiculo.class, new SerializadorModel<Veiculo>()).
			registerTypeAdapter(LocalDate.class, new SerializadorLocalDate());
			
		Gson gson = gsonBuilder.create();
		
		String json = gson.toJson(locacoesNovo);
		
		try (FileWriter fw = new FileWriter(CAMINHO_JSON)) {
			fw.write(json);
		}
	}
	
	@Override
	public void atualizar(Locacao locacao) throws IOException {
		List<Locacao> locacoes;
		
		List<Locacao> locacoesNovo = new ArrayList<>();
		
		try (FileReader fr = new FileReader(CAMINHO_JSON)) {
			if (fr.read() == -1) {
				locacoes = new ArrayList<>();
			}
			else {
				locacoes = new ArrayList<>(recuperarTodos());
			}
		}
		
		for (Locacao locacaoAtual : locacoes) {
			if (locacao.getId().equals(locacaoAtual.getId())) {
				locacoesNovo.add(locacao);
			}
			else {
				locacoesNovo.add(locacaoAtual);
			}

		}
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.
			registerTypeAdapter(Veiculo.class, new SerializadorModel<Veiculo>()).
			registerTypeAdapter(LocalDate.class, new SerializadorLocalDate());
			
		Gson gson = gsonBuilder.create();
		
		String json = gson.toJson(locacoesNovo);
		
		try (FileWriter fw = new FileWriter(CAMINHO_JSON)) {
			fw.write(json);
		}
	}
	
}
