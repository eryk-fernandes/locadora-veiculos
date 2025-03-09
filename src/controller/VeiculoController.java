package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import javax.swing.JTextField;

import dao.VeiculoDAO;
import excecoes.ProibidoRemoverException;
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
	
	public String[] criarListaPlacas() throws Exception {
		
		List<String> veiculos = new ArrayList<>();

		for (Veiculo veiculo : recuperarTodos()) {
			String locacao = (veiculo.getStatus() == StatusLocacao.DISPONIVEL) ? "DISPONÍVEL" : "LOCADO";
				
			veiculos.add(veiculo.getPlaca() + " - " + locacao);
		}
		
		if (veiculos.size() == 0) {
			veiculos.add("NENHUM VEÍCULO ADICIONADO");
		}
			
		return veiculos.toArray(new String[veiculos.size()]);
	}
	
	public String[] criarListaVeiculos() throws Exception {
		
		List<String> veiculos = new ArrayList<>();
		
		for (Veiculo veiculo : recuperarTodos()) {
				
			StringBuilder texto = new StringBuilder();
				
			texto.append(veiculo.getModelo() + " ");
			texto.append(veiculo.getAno() + " ");
			texto.append(veiculo.getPlaca() + " ");
			texto.append(veiculo.getStatus().toString());
			
			veiculos.add(texto.toString());
		}
		
		if (veiculos.size() == 0) {
			veiculos.add("NENHUM VEÍCULO ADICIONADO");
		}
			
		return veiculos.toArray(new String[veiculos.size()]);
	}
	
	public void cadastrarDados(Object tipo, JTextField placa, JTextField modelo, JTextField ano) throws Exception {
		
		if (tipo.toString().equals("CARRO")) {
			veiculo = new Carro();
		}
		else if (tipo.toString().equals("MOTO")) {
			veiculo = new Moto();
		}
		else if (tipo.toString().equals("CAMINHÃO")) {
			veiculo = new Caminhao();
		}
		else {
			throw new IllegalArgumentException("ESCOLHA UMA DAS OPÇÕES DE VEÍCULO");
		}
		
		if (placa.getText().replace("-", "").length() != 7) {
			throw new TamanhoInvalidoException("PLACA INVÁLIDA");
		}
		
		try {
			veiculo.setPlaca(placa.getText().replace("-", ""));
			veiculo.setModelo(modelo.getText());
			veiculo.setAno(Integer.valueOf(ano.getText()));
			veiculo.setStatus(StatusLocacao.DISPONIVEL);
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
	
	public void removerVeiculo(Object veiculoInfo) throws Exception {
		
		if (veiculoInfo.toString().equals("")) {
			throw new IllegalArgumentException("SELECIONE UMA OPÇÃO DE VEÍCULO");
		}
		
		String placa = veiculoInfo.toString().split(" ")[2];
		
		Veiculo veiculo = new VeiculoDAO().recuperar(placa);
		
		if (veiculo.getStatus() == StatusLocacao.DISPONIVEL) {
			new VeiculoDAO().remover(veiculo);
		}
		else {
			throw new ProibidoRemoverException("NÃO É PERMITIDO REMOVER CLIENTES COM LOCAÇÕES PENDENTES");
		}

	}

}
