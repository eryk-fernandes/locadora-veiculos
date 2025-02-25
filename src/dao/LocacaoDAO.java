package dao;

import java.util.List;

import model.Locacao;

public class LocacaoDAO implements DAO<Locacao, Integer> {

	@Override
	public Locacao recuperar(Integer id) throws Exception {
		return null;
	}

	@Override
	public List<Locacao> recuperarTodos() throws Exception {
		return null;
	}

	@Override
	public void salvar(Locacao locacao) throws Exception {
		
	}

	@Override
	public void remover(Locacao locacao) throws Exception {
		
	}
	
	@Override
	public void atualizar(Locacao locacao) throws Exception {
		
	}

	@Override
	public boolean isVazio() throws Exception {
		return false;
	}
	
}
