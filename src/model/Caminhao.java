package model;

public class Caminhao extends Veiculo {
	
	public Caminhao() {
		
	}

	public Caminhao(String placa, String modelo, Integer ano, StatusLocacao status) {
		super(placa, modelo, ano, status);
	}

	@Override
	public double calcularCustoLocacaoDiario() {
		return 200.0;
	}

}
