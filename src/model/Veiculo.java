package model;

public abstract class Veiculo {

	protected Integer id;
	protected String placa;
	protected String modelo;
	protected Integer ano;
	protected StatusLocacao status;
	
	public Veiculo() {
		
	}
	
	public Veiculo(Integer id, String placa, String modelo, Integer ano, StatusLocacao status) {
		this.id = id;
		this.placa = placa;
		this.modelo = modelo;
		this.ano = ano;
		this.status = status;
	}
	
	public Integer getId() {
		return id;
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
	
	public StatusLocacao getStatus() {
		return status;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public void setAno(Integer ano) {
		this.ano = ano;
	}
	
	public void setStatus(StatusLocacao status) {
		this.status = status;
	}
	
	public abstract double calcularCustoLocacaoDiario();
}
