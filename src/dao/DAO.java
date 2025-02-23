package dao;

import java.util.List;

public interface DAO<T> {

	T recuperarPorId(int Id);
	
	List<T> recuperarTodos();
	
	void salvar(T t);
	
	void remover(T t);
}
