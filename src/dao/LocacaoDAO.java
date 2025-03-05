package dao;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.Locacao;

public class LocacaoDAO implements Persistencia<Locacao, Integer> {
	
	private static final String CAMINHO_JSON = "src/json/locacoes.json";

	@Override
	public Locacao recuperar(Integer id) throws Exception {
		try (FileReader fr = new FileReader(CAMINHO_JSON)) {
			if (fr.read() == -1) {
				return null;
			}
		}
		
		for (Locacao locacao : recuperarTodos()) {
			if (id.equals(locacao.getId())) {
				return locacao;
			}
		}
		
		return null;
	}

	@Override
	public List<Locacao> recuperarTodos() throws Exception {
		
		try (FileReader fr = new FileReader(CAMINHO_JSON)) {
			if (fr.read() == -1) {
				return null;
			}
		}

		List<Locacao> locacoes;
		
		try (FileReader fr = new FileReader(CAMINHO_JSON)) {
			
			Type tipo = new TypeToken<ArrayList<Locacao>>(){}.getType();

			locacoes = new Gson().fromJson(fr, tipo);
			
		}
		
		return locacoes;
	}

	@Override
	public void salvar(Locacao locacao) throws Exception {
		
		List<Locacao> locacoes;
		
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
				return;
			}
		}
		
		locacoes.add(locacao);
		
		String json = new Gson().toJson(locacoes);
		
		try (FileWriter fw = new FileWriter(CAMINHO_JSON)) {
			fw.write(json);
		}
	}

	@Override
	public void remover(Locacao locacao) throws Exception {
		
		List<Locacao> locacoes;
		
		List<Locacao> locacaosNovo = new ArrayList<>();
		
		try (FileReader fr = new FileReader(CAMINHO_JSON)) {
			if (fr.read() == -1) {
				locacoes = new ArrayList<>();
			}
			else {
				locacoes = new ArrayList<>(recuperarTodos());
			}
		}
		
		for (Locacao locacaoAtual : locacoes) {
			if (!locacao.getId().equals(locacaoAtual.getId())) {
				locacaosNovo.add(locacaoAtual);
			}
		}

		String json = new Gson().toJson(locacaosNovo);
		
		try (FileWriter fw = new FileWriter(CAMINHO_JSON)) {
			fw.write(json);
		}
	}
	
	@Override
	public void atualizar(Locacao locacao) throws Exception {
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

		String json = new Gson().toJson(locacoesNovo);
		
		try (FileWriter fw = new FileWriter(CAMINHO_JSON)) {
			fw.write(json);
		}
	}
	
}
