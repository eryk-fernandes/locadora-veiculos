package dao;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.Veiculo;

public class VeiculoDAO extends DAO<Veiculo, String> {
	
	public VeiculoDAO() {
		this.caminhoJson = "src\\json\\veiculos.json";
	}
	
	@Override
	public Veiculo recuperar(String placa) throws Exception {
		
		if (isVazio()) {
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
	public List<Veiculo> recuperarTodos() throws Exception {
		
		if (isVazio()) {
			return null;
		}

		ArrayList<Veiculo> veiculos = new ArrayList<>();;
		
		try (FileReader fr = new FileReader(caminhoJson)) {
			
			Type tipo = new TypeToken<ArrayList<Veiculo>>(){}.getType();

			veiculos = new Gson().fromJson(fr, tipo);
		}
		
		return veiculos;
	}

	@Override
	public void salvar(Veiculo veiculo) throws Exception {
		List<Veiculo> veiculos;
		
		if (isVazio()) 
			veiculos = new ArrayList<>();
		else
			veiculos = new ArrayList<>(recuperarTodos());
		
		for (Veiculo veiculoAtual : veiculos) {
			if (veiculo.getPlaca().equals(veiculoAtual.getPlaca())) {
				return;
			}
		}
		
		veiculos.add(veiculo);
		
		String json = new Gson().toJson(veiculos);
		
		try (FileWriter fw = new FileWriter(caminhoJson)) {
			fw.write(json);
		}
	}

	@Override
	public void remover(Veiculo veiculo) throws Exception {
		List<Veiculo> veiculos;
		
		List<Veiculo> veiculosNovo = new ArrayList<>();
		
		if (isVazio()) 
			veiculos = new ArrayList<>();
		else
			veiculos = new ArrayList<>(recuperarTodos());
		
		for (Veiculo veiculoAtual : veiculos) {
			if (!veiculo.getPlaca().equals(veiculoAtual.getPlaca())) {
				veiculosNovo.add(veiculoAtual);
			}
		}

		String json = new Gson().toJson(veiculosNovo);
		
		try (FileWriter fw = new FileWriter(caminhoJson)) {
			fw.write(json);
		}
	}
	
	@Override
	public void atualizar(Veiculo veiculo) throws Exception {
		List<Veiculo> veiculos;
		
		List<Veiculo> veiculosNovo = new ArrayList<>();
		
		if (isVazio()) 
			veiculos = new ArrayList<>();
		else
			veiculos = new ArrayList<>(recuperarTodos());
		
		for (Veiculo clienteAtual : veiculos) {
			if (veiculo.getPlaca().equals(clienteAtual.getPlaca())) {
				veiculosNovo.add(veiculo);
			}
			else {
				veiculosNovo.add(clienteAtual);
			}

		}

		String json = new Gson().toJson(veiculosNovo);
		
		try (FileWriter fw = new FileWriter(caminhoJson)) {
			fw.write(json);
		}
	}

}
