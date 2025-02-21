package model;

public class Carro extends Veiculo {

	public Carro() {
		
	}

	public Carro(String placa, String modelo, Integer ano, StatusVeiculo status) {
		super(placa, modelo, ano, status);
	}

	@Override
	public double calcularCustoLocacao() {
		return 0;
	}

}
