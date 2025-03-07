package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import javax.swing.JTextField;

import dao.VeiculoDAO;
import excecoes.TamanhoInvalidoException;
import model.Caminhao;
import model.Carro;
import model.Moto;
import model.StatusLocacao;
import model.Veiculo;

public class VeiculoController {
	
	private Veiculo veiculo;
	
	public VeiculoController() {
		
	}
	
	public List<Veiculo> recuperarTodos() throws Exception {
		return new VeiculoDAO().recuperarTodos();
	}
	
	public void salvar() throws Exception {
		new VeiculoDAO().salvar(veiculo);
	}
	
	public void remover() throws Exception {
		new VeiculoDAO().remover(veiculo);
	}
	
	public void atualizar() throws Exception {
		new VeiculoDAO().atualizar(veiculo);
	}
	
	public String[] recuperarTodosComboBox() {
		
		List<String> veiculos = new ArrayList<>();
		
		try {
			for (Veiculo veiculo : recuperarTodos()) {
				veiculos.add(veiculo.getPlaca());
			}
		} catch (Exception e) {
			
		}
		
		if (veiculos.size() == 0) {
			return new String[] {"NENHUM VEÍCULO ADICIONADO"};
		}
		
		veiculos.add("");
			
		return veiculos.toArray(new String[veiculos.size()]);
	}
	
	public void cadastrarDados(Object tipo, JTextField placa, JTextField modelo, JTextField ano, Object status) throws Exception {
		
		if (tipo.toString().equals("Carro")) {
			veiculo = new Carro();
		}
		else if (tipo.toString().equals("Moto")) {
			veiculo = new Moto();
		}
		else if (tipo.toString().equals("Caminhão")) {
			veiculo = new Caminhao();
		}
		else {
			throw new IllegalArgumentException("ESCOLHA UMA DAS OPÇÕES DE VEÍCULO");
		}
		
		if (status.toString() == "") {
			throw new IllegalArgumentException("ESCOLHA UM STATUS DE LOCAÇÃO");
		}
		
		if (placa.getText().replace("-", "").length() != 7) {
			throw new TamanhoInvalidoException("PLACA INVÁLIDA");
		}
		
		try {
			veiculo.setPlaca(placa.getText());
			veiculo.setModelo(modelo.getText());
			veiculo.setAno(Integer.valueOf(ano.getText()));
			veiculo.setStatus(StatusLocacao.valueOf(status.toString().toUpperCase().replace("Í", "I")));
		}
		catch (InputMismatchException e) {
			throw e;
		}
		
		try {
			salvar();
		} catch (Exception e) {
			throw new IOException("ERRO AO ADICIONAR O USUÁRIO");
		}
	}

}
