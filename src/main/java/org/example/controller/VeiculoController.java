package org.example.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;

import org.example.model.dao.VeiculoDAO;
import org.example.model.exception.ProibidoRemoverException;
import org.example.model.exception.TamanhoInvalidoException;
import org.example.model.entities.Caminhao;
import org.example.model.entities.Carro;
import org.example.model.entities.Moto;
import org.example.model.entities.StatusLocacao;
import org.example.model.entities.Veiculo;

public class VeiculoController {
	
	private Veiculo veiculo;
	
	public VeiculoController() {
		
	}
	
	public String[] criarListaPlacas() throws Exception {
		
		List<String> veiculos = new ArrayList<>();
		
		if (new VeiculoDAO().isVazio()) {
			veiculos.add("NENHUM VEÍCULO ADICIONADO");
			
			return veiculos.toArray(new String[veiculos.size()]);
		}

		for (Veiculo veiculo : new VeiculoDAO().recuperarTodos()) {
			String locacao = (veiculo.getStatus() == StatusLocacao.DISPONIVEL) ? "DISPONÍVEL" : "LOCADO";
				
			veiculos.add(veiculo.getPlaca() + " - " + locacao);
		}
		
		if (veiculos.isEmpty()) {
			veiculos.add("NENHUM VEÍCULO ADICIONADO");
		}
			
		return veiculos.toArray(new String[0]);
	}
	
	public String[] criarListaVeiculos() throws Exception {
		
		List<String> veiculos = new ArrayList<>();
		
		if (new VeiculoDAO().isVazio()) {
			veiculos.add("NENHUM VEÍCULO ADICIONADO");
			
			return veiculos.toArray(new String[0]);
		}
		
		for (Veiculo veiculo : new VeiculoDAO().recuperarTodos()) {

            String texto = veiculo.getModelo() + " " +
                    veiculo.getAno() + " " +
                    veiculo.getPlaca() + " " +
                    veiculo.getStatus().toString();
			
			veiculos.add(texto);
		}
		
		if (veiculos.isEmpty()) {
			veiculos.add("NENHUM VEÍCULO ADICIONADO");
		}
			
		return veiculos.toArray(new String[0]);
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
		
		String modelo = veiculoModelo.getText().replace(" ", "");
		
		if (placa.isEmpty() || modelo.isEmpty() || veiculoAno.getText().isEmpty()) {
			throw new IllegalArgumentException("DIGITE TODOS OS ATRIBUTOS");
		}
		
		if (placa.length() != 7) {
			throw new TamanhoInvalidoException("PLACA INVÁLIDA");
		}
		
		if (veiculoAno.getText().length() != 4) {
			throw new TamanhoInvalidoException("ANO INVÁLIDO");
		}
		
		int ano = Integer.parseInt(veiculoAno.getText());
		
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
		
		if (veiculoInfo.toString().isEmpty()) {
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
