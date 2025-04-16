package org.example.model.entities;

public class Carro extends Veiculo {
	
	public Carro() {
		
	}

	public Carro(String placa, String modelo, Integer ano, StatusLocacao status) {
		super(placa, modelo, ano, status);
	}

	@Override
	public double calcularCustoLocacaoDiario() {
		return 100.0;
	}

}
