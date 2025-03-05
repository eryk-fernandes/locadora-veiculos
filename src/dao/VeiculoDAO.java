package dao;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import model.Veiculo;

public class VeiculoDAO implements Persistencia<Veiculo, String> {
	
	private static final String CAMINHO_JSON = "src/json/veiculos.json";
	
	@Override
	public Veiculo recuperar(String placa) throws Exception {
		
		try (FileReader fr = new FileReader(CAMINHO_JSON)) {
			if (fr.read() == -1) {
				return null;
			}
		}
		
		for (Veiculo veiculo : recuperarTodos()) {
			if (placa.equals(veiculo.getPlaca())) {
				return veiculo;
			}
		}
		
		return null;
	}

	@Override
	public List<Veiculo> recuperarTodos() throws Exception {
		
		try (FileReader fr = new FileReader(CAMINHO_JSON)) {
			if (fr.read() == -1) {
				return null;
			}
		}

		ArrayList<Veiculo> veiculos = new ArrayList<>();;
		
		try (FileReader fr = new FileReader(CAMINHO_JSON)) {
			
			GsonBuilder gb = new GsonBuilder();
			gb.registerTypeAdapter(Veiculo.class, new Serializador<Veiculo>());
			
			Gson gson = gb.create();
			
			veiculos = gson.fromJson(fr, new TypeToken<ArrayList<Veiculo>>(){}.getType());
		}
		
		return veiculos;
	}

	@Override
	public void salvar(Veiculo moto) throws Exception {
		List<Veiculo> veiculos;
		
		try (FileReader fr = new FileReader(CAMINHO_JSON)) {
			if (fr.read() == -1) {
				veiculos = new ArrayList<>();
			}
			else {
				veiculos = new ArrayList<>(recuperarTodos());
			}
		}
		
		for (Veiculo veiculoAtual : veiculos) {
			if (moto.getPlaca().equals(veiculoAtual.getPlaca())) {
				return;
			}
		}
		
		veiculos.add(moto);
		
		GsonBuilder gb = new GsonBuilder();
		gb.registerTypeAdapter(Veiculo.class, new Serializador<Veiculo>());
		
		Gson gson = gb.create();
		
		String json = gson.toJson(veiculos, new TypeToken<ArrayList<Veiculo>>(){}.getType());
		
		try (FileWriter fw = new FileWriter(CAMINHO_JSON)) {
			fw.write(json);
		}
	}

	@Override
	public void remover(Veiculo moto) throws Exception {
		List<Veiculo> veiculos;
		
		List<Veiculo> veiculosNovo = new ArrayList<>();
		
		try (FileReader fr = new FileReader(CAMINHO_JSON)) {
			if (fr.read() == -1) {
				veiculos = new ArrayList<>();
			}
			else {
				veiculos = new ArrayList<>(recuperarTodos());
			}
		}
		
		for (Veiculo veiculoAtual : veiculos) {
			if (!moto.getPlaca().equals(veiculoAtual.getPlaca())) {
				veiculosNovo.add(veiculoAtual);
			}
		}

		GsonBuilder gb = new GsonBuilder();
		gb.registerTypeAdapter(Veiculo.class, new Serializador<Veiculo>());
		
		Gson gson = gb.create();
		
		String json = gson.toJson(veiculosNovo, new TypeToken<ArrayList<Veiculo>>(){}.getType());
		
		try (FileWriter fw = new FileWriter(CAMINHO_JSON)) {
			fw.write(json);
		}
	}
	
	@Override
	public void atualizar(Veiculo moto) throws Exception {
		List<Veiculo> veiculos;
		
		List<Veiculo> veiculosNovo = new ArrayList<>();
		
		try (FileReader fr = new FileReader(CAMINHO_JSON)) {
			if (fr.read() == -1) {
				veiculos = new ArrayList<>();
			}
			else {
				veiculos = new ArrayList<>(recuperarTodos());
			}
		}
		
		for (Veiculo veiculoAtual : veiculos) {
			if (moto.getPlaca().equals(veiculoAtual.getPlaca())) {
				veiculosNovo.add(moto);
			}
			else {
				veiculosNovo.add(veiculoAtual);
			}

		}
		
		GsonBuilder gb = new GsonBuilder();
		gb.registerTypeAdapter(Veiculo.class, new Serializador<Veiculo>());
		
		Gson gson = gb.create();
		
		String json = gson.toJson(veiculosNovo, new TypeToken<ArrayList<Veiculo>>(){}.getType());
		
		try (FileWriter fw = new FileWriter(CAMINHO_JSON)) {
			fw.write(json);
		}
	}

}
