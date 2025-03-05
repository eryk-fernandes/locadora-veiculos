package controller;

import dao.VeiculoDAO;
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
	
	public void salvar() throws Exception {
		new VeiculoDAO().salvar(veiculo);
	}
	
	public void remover() throws Exception {
		new VeiculoDAO().remover(veiculo);
	}
	
	public void atualizar() throws Exception {
		new VeiculoDAO().atualizar(veiculo);
	}

}
