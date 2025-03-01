package dao;

import java.io.FileReader;

public abstract class DAO<T, K> implements Pesistencia<T, K> {

	protected String caminhoJson;
	
	protected boolean isVazio() throws Exception {
		try (FileReader fr = new FileReader(caminhoJson)) {
			if (fr.read() == -1) {
				return true;
			}
		}
		
		return false;
	}
	
}
