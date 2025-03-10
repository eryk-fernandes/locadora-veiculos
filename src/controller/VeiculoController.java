package controller;

import java.time.LocalDate;
import java.util.ArrayList;
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
	
	public String[] criarListaPlacas() throws Exception {
		
		List<String> veiculos = new ArrayList<>();
		
		if (new VeiculoDAO().isVazio()) {
			veiculos.add("NENHUM CLIENTE ADICIONADO");
			
			return veiculos.toArray(new String[veiculos.size()]);
		}

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
		
		if (new VeiculoDAO().isVazio()) {
			veiculos.add("NENHUM CLIENTE ADICIONADO");
			
			return veiculos.toArray(new String[veiculos.size()]);
		}
		
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
	
	public void cadastrarDados(Object tipo, JTextField veiculoPlaca, JTextField veiculoModelo, JTextField veiculoAno) throws Exception {
		
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
		
		String placa = veiculoPlaca.getText().replace("-", "");
		
		if (new VeiculoDAO().recuperar(placa) != null) {
			throw new IllegalArgumentException("ESSA PLACA JÁ ESTÁ PRESENTE NO SISTEMA");
		}
		
		String modelo = veiculoModelo.getText();
		
		if (placa.length() == 0 || modelo.length() == 0 || veiculoAno.getText().length() == 0) {
			throw new IllegalArgumentException("DIGITE TODOS OS ATRIBUTOS");
		}
		
		if (placa.length() != 7) {
			throw new TamanhoInvalidoException("PLACA INVÁLIDA");
		}
		
		if (veiculoAno.getText().length() != 4) {
			throw new TamanhoInvalidoException("ANO INVÁLIDO");
		}
		
		int ano = Integer.valueOf(veiculoAno.getText());
		
		int anoAtual = LocalDate.now().getYear();
		
		if (anoAtual < ano) {
			throw new IllegalArgumentException("ANO INVÁLIDO");
		}

		veiculo.setPlaca(placa);
		veiculo.setModelo(modelo);
		veiculo.setAno(ano);
		veiculo.setStatus(StatusLocacao.DISPONIVEL);
		
		new VeiculoDAO().salvar(veiculo);
		
	}
	
	public void removerVeiculo(Object veiculoInfo) throws Exception {
		
		if (veiculoInfo.toString().equals("")) {
			throw new IllegalArgumentException("SELECIONE UMA OPÇÃO DE VEÍCULO");
		}
		
		String placa = veiculoInfo.toString().split(" ")[2];
		
		Veiculo veiculo = new VeiculoDAO().recuperar(placa);
		
		if (veiculo.getStatus().equals(StatusLocacao.DISPONIVEL)) {
			new VeiculoDAO().remover(placa);
		}
		else {
			throw new ProibidoRemoverException("NÃO É PERMITIDO REMOVER CLIENTES COM LOCAÇÕES PENDENTES");
		}

	}

}
