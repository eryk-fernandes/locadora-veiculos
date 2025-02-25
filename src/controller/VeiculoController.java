package controller;

import model.StatusLocacao;
import model.Veiculo;

public class VeiculoController {
	
	private Veiculo veiculo;

	public VeiculoController(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	
	public String getVeiculoPlaca() {
		return veiculo.getPlaca();
	}
	
	public String getVeiculoModelo() {
		return veiculo.getModelo();
	}
	
	public Integer getVeiculoAno() {
		return veiculo.getAno();
	}
	
	public StatusLocacao getVeiculoStatus() {
		return veiculo.getStatus();
	}
	
	public void setVeiculoPlaca(String placa) {
		veiculo.setPlaca(placa);
	}
	
	public void setVeiculoModelo(String modelo) {
		veiculo.setModelo(modelo);
	}
	
	public void setVeiculoAno(Integer ano) {
		veiculo.setAno(ano);
	}
	
	public void setVeiculoStatus(StatusLocacao status) {
		veiculo.setStatus(status);
	}

}
