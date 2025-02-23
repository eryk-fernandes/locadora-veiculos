package model;

public class Caminhao extends Veiculo {
	
	public Caminhao() {
		
	}

	public Caminhao(Integer id, String placa, String modelo, Integer ano, StatusLocacao status) {
		super(id, placa, modelo, ano, status);
	}

	@Override
	public double calcularCustoLocacaoDiario() {
		return 200.0;
	}

}
