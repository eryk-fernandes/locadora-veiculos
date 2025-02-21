package model;

public class Moto extends Veiculo {

	public Moto(String placa, String modelo, Integer ano, StatusVeiculo status) {
		super(placa, modelo, ano, status);
	}

	@Override
	public double calcularCustoLocacaoDiario() {
		return 60.0;
	}

}
