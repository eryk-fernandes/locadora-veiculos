package controller;

import java.util.InputMismatchException;

import dao.VeiculoDAO;
import model.Caminhao;
import model.Carro;
import model.Moto;
import model.StatusLocacao;
import model.Veiculo;

public class VeiculoController {
	
	private Veiculo veiculo;
	
	public VeiculoController() {
		
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
	

	public void cadastrarDados(String tipo, String placa, String modelo, String ano, String status) throws Exception {
		
		if (tipo.equals("CARRO"))
			this.veiculo = new Carro();
		else if (tipo.equals("MOTO"))
			this.veiculo = new Moto();
		else if (tipo.equals("CAMINHAO"))
			this.veiculo = new Caminhao();
		else
			throw new IllegalArgumentException();
		
		try {
			veiculo.setPlaca(placa);
			veiculo.setModelo(modelo);
			veiculo.setAno(Integer.valueOf(ano));
			veiculo.setStatus(StatusLocacao.valueOf(status));
		}
		catch (InputMismatchException e) {
			throw e;
		}
		
		try {
			salvar();
		} catch (Exception e) {
			throw e;
		}
	}

}
