package dao;

import java.util.List;

public interface Pesistencia<T, K> {
	
	T recuperar(K k) throws Exception;
	
	List<T> recuperarTodos() throws Exception;
	
	void salvar(T t) throws Exception;
	
	void remover(T t) throws Exception;
	
	void atualizar(T t) throws Exception;
}
