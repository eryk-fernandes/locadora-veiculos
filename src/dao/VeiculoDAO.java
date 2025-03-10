package dao;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import model.Veiculo;

public class VeiculoDAO implements Persistencia<Veiculo, String> {
	
	private static final String CAMINHO_JSON = "dados/json/veiculos.json";
	
	@Override
	public Veiculo recuperar(String placa) throws IOException {
		
		try (FileReader fr = new FileReader(CAMINHO_JSON)) {
			if (fr.read() == -1)
				return null;
		}
		
		for (Veiculo veiculo : recuperarTodos()) {
			if (placa.equals(veiculo.getPlaca())) {
				return veiculo;
			}
		}
		
		return null;
	}

	@Override
	public List<Veiculo> recuperarTodos() throws IOException {
		
		try (FileReader fr = new FileReader(CAMINHO_JSON)) {
			if (fr.read() == -1)
				return null;
		}

		ArrayList<Veiculo> veiculos = new ArrayList<>();;
		
		try (FileReader fr = new FileReader(CAMINHO_JSON)) {
			
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(Veiculo.class, new SerializadorModel<Veiculo>());
			
			Gson gson = gsonBuilder.create();
			
			veiculos = gson.fromJson(fr, new TypeToken<ArrayList<Veiculo>>(){}.getType());
		}
		
		Collections.sort(veiculos);
		
		return veiculos;
	}

	@Override
	public void salvar(Veiculo veiculo) throws IOException {
		List<Veiculo> veiculos;
		
		try (FileReader fr = new FileReader(CAMINHO_JSON)) {
			if (fr.read() == -1)
				veiculos = new ArrayList<>();
			else
				veiculos = new ArrayList<>(recuperarTodos());
		}
		
		for (Veiculo veiculoAtual : veiculos) {
			if (veiculo.getPlaca().equals(veiculoAtual.getPlaca())) {
				return;
			}
		}
		
		veiculos.add(veiculo);
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Veiculo.class, new SerializadorModel<Veiculo>());
		
		Gson gson = gsonBuilder.create();
		
		String json = gson.toJson(veiculos, new TypeToken<ArrayList<Veiculo>>(){}.getType());
		
		try (FileWriter fw = new FileWriter(CAMINHO_JSON)) {
			fw.write(json);
		}
	}

	@Override
	public void remover(String placa) throws IOException {
		List<Veiculo> veiculos;
		
		List<Veiculo> veiculosNovo = new ArrayList<>();
		
		try (FileReader fr = new FileReader(CAMINHO_JSON)) {
			if (fr.read() == -1)
				veiculos = new ArrayList<>();
			else
				veiculos = new ArrayList<>(recuperarTodos());
		}
		
		for (Veiculo veiculoAtual : veiculos) {
			if (!placa.equals(veiculoAtual.getPlaca())) {
				veiculosNovo.add(veiculoAtual);
			}
		}

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Veiculo.class, new SerializadorModel<Veiculo>());
		
		Gson gson = gsonBuilder.create();
		
		String json = gson.toJson(veiculosNovo, new TypeToken<ArrayList<Veiculo>>(){}.getType());
		
		try (FileWriter fw = new FileWriter(CAMINHO_JSON)) {
			fw.write(json);
		}
	}
	
	@Override
	public void atualizar(Veiculo veiculo) throws IOException {
		List<Veiculo> veiculos;
		
		List<Veiculo> veiculosNovo = new ArrayList<>();
		
		try (FileReader fr = new FileReader(CAMINHO_JSON)) {
			if (fr.read() == -1)
				veiculos = new ArrayList<>();
			else
				veiculos = new ArrayList<>(recuperarTodos());
		}
		
		for (Veiculo veiculoAtual : veiculos) {
			if (veiculo.getPlaca().equals(veiculoAtual.getPlaca())) {
				veiculosNovo.add(veiculo);
			}
			else {
				veiculosNovo.add(veiculoAtual);
			}
		}
		
		Collections.sort(veiculosNovo);
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Veiculo.class, new SerializadorModel<Veiculo>());
		
		Gson gson = gsonBuilder.create();
		
		String json = gson.toJson(veiculosNovo, new TypeToken<ArrayList<Veiculo>>(){}.getType());
		
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
