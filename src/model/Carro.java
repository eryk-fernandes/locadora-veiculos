package model;

public class Carro extends Veiculo {
	
	public Carro() {
		
	}

	public Carro(Integer id, String placa, String modelo, Integer ano, StatusLocacao status) {
		super(id, placa, modelo, ano, status);
	}

	@Override
	public double calcularCustoLocacaoDiario() {
		return 100.0;
	}

}
