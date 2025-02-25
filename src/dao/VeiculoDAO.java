package dao;

import java.util.List;

import model.Veiculo;

public class VeiculoDAO implements DAO<Veiculo, String> {
	
	@Override
	public Veiculo recuperar(String placa) throws Exception {
		return null;
	}

	@Override
	public List<Veiculo> recuperarTodos() throws Exception {
		return null;
	}

	@Override
	public void salvar(Veiculo veiculo) throws Exception {
		
	}

	@Override
	public void remover(Veiculo veiculo) throws Exception {
		
	}
	
	@Override
	public void atualizar(Veiculo veiculo) throws Exception {
		
	}

	@Override
	public boolean isVazio() throws Exception {
		return false;
	}

}
