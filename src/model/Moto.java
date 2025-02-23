package model;

public class Moto extends Veiculo {
	
	public Moto() {
		
	}

	public Moto(Integer id, String placa, String modelo, Integer ano, StatusLocacao status) {
		super(id, placa, modelo, ano, status);
	}

	@Override
	public double calcularCustoLocacaoDiario() {
		return 60.0;
	}

}
