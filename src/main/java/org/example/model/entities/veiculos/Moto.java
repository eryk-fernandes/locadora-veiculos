package org.example.model.entities;

public class Moto extends Veiculo {
	
	public Moto() {
		
	}

	public Moto(String placa, String modelo, Integer ano, StatusLocacao status) {
		super(placa, modelo, ano, status);
	}

	@Override
	public double calcularCustoLocacaoDiario() {
		return 60.0;
	}

}
