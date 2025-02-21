package model;

public class Caminhao extends Veiculo {

	public Caminhao() {
		
	}

	public Caminhao(String placa, String modelo, Integer ano, StatusVeiculo status) {
		super(placa, modelo, ano, status);
	}

	@Override
	public double calcularCustoLocacao() {
		return 0;
	}

}
