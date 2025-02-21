package model;

public abstract class Veiculo {

	protected String placa;
	protected String modelo;
	protected Integer ano;
	protected StatusVeiculo status;
	
	public Veiculo() {
		
	}
	
	public Veiculo(String placa, String modelo, Integer ano, StatusVeiculo status) {
		this.placa = placa;
		this.modelo = modelo;
		this.ano = ano;
		this.status = status;
	}
	
	public String getPlaca() {
		return placa;
	}
	
	public String getModelo() {
		return modelo;
	}
	
	public Integer getAno() {
		return ano;
	}
	
	public StatusVeiculo getStatus() {
		return status;
	}
	
	public abstract double calcularCustoLocacao();
}
