package dao;

import java.util.List;

import model.Pagamento;

public class PagamentoDAO extends DAO<Pagamento, Integer> {
	
	public PagamentoDAO() {
		this.caminhoJson = "src\\json\\pagamentos.json";
	}

	@Override
	public Pagamento recuperar(Integer id) throws Exception {
		return null;
	}

	@Override
	public List<Pagamento> recuperarTodos() throws Exception {
		return null;
	}

	@Override
	public void salvar(Pagamento pagamento) throws Exception {
		
	}

	@Override
	public void remover(Pagamento pagamento) throws Exception {
		
	}
	
	@Override
	public void atualizar(Pagamento pagamento) throws Exception {
		
	}

}
