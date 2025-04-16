package org.example.model.dao;

import java.io.IOException;
import java.util.List;

public interface Persistencia<T, K> {
	
	T recuperar(K k) throws IOException;
	
	List<T> recuperarTodos() throws IOException;
	
	void salvar(T t) throws IOException;
	
	void remover(K k) throws IOException;
	
	void atualizar(T t) throws IOException;
	
	boolean isVazio() throws IOException;
	
}
